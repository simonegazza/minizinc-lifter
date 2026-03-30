package me.simonegazza.lift.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.OutputItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SolveItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.LiftedArrayElementParameter;
import me.simonegazza.lift.parameters.LiftedArrayParameter;
import me.simonegazza.lift.parameters.LiftedDependencyParameter;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.LiftedSetParameter;
import me.simonegazza.lift.parameters.LiftedSimpleParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.DirectedGraph;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Applies the parameter lifting transformation to a MiniZinc model.
 * <p>
 * This class is responsible for rewriting the parsed model by:
 * <ul>
 * <li>Removing original parameter declarations and assignments</li>
 * <li>Replacing parameter usages with their lifted counterparts</li>
 * <li>Injecting new variable declarations for lifted parameters</li>
 * <li>Generating a {@code solve} and {@code output} section if missing</li>
 * </ul>
 * <p>
 * Unlike {@link ParameterVisitor}, this class does not build a semantic model.
 * Instead, it operates directly on the token stream using
 * {@link TokenStreamRewriter}, making it a purely syntactic transformation
 * step.
 * <p>
 * The transformation is driven by:
 * <ul>
 * <li>A set of {@link LiftRequest}</li>
 * <li>The parameter dependency graph</li>
 * </ul>
 * <p>
 * Dependencies are resolved transitively: lifting a parameter automatically
 * lifts all parameters that depend on it.
 */
public class Lifter {

	/**
	 * Internal visitor that applies lifting rules to AST nodes.
	 */
	private final LiftingVisitor visitor;

	/**
	 * Token stream rewriter for modifying the MiniZinc model text.
	 */
	private final TokenStreamRewriter rewriter;

	/**
	 * List of parameters that have been lifted in the model.
	 */
	private final List<LiftedParameter> lifted;

	/**
	 * The environment in which the expressions should be evaluated.
	 */
	private final Map<String, Object> env;

	/**
	 * Flag indicating whether a solve block is present in the model.
	 */
	private boolean isSolvePresent;

	/**
	 * Flag indicating whether an output block is present in the model.
	 */
	private boolean isOutputPresent;

	/**
	 * Builds a default solve statement based on lifted parameters.
	 * <p>
	 * The objective is constructed as the sum of all lifted parameter
	 * contributions.
	 *
	 * @return the solve component of the combined lifts
	 */
	private String getSolve() {
		return "solve minimize "
			+ lifted.stream()
				.map(LiftedParameter::getSolvePiece)
				.collect(Collectors.joining(" + "))
			+ ";\n";
	}

	/**
	 * Builds a default output statement listing lifted parameters.
	 *
	 * @return the output component of the combined lifts
	 */
	private String getOutput() {
		return "output ["
			+ lifted.stream()
				.map(LiftedParameter::getOutputPiece)
				.collect(Collectors.joining(", \"\\n\", "))
			+ ", \"\\n\"];\n";
	}

	/**
	 * Computes the transitive closure of dependencies for a given parameter.
	 * <p>
	 * This method returns all parameters that directly or indirectly depend on
	 * the given one. It iteratively expands the set until a fixpoint is
	 * reached.
	 * <p>
	 * <b>Important:</b> This assumes the graph is acyclic (topological),
	 * otherwise termination is not guaranteed.
	 *
	 * @param parameterGraph the dependency graph
	 * @param parameter      the starting parameter
	 *
	 * @return the set of dependent parameters (including the input)
	 */
	private static Set<OriginalParameter> dependsOn(
		DirectedGraph<OriginalParameter> parameterGraph,
		OriginalParameter parameter) {

		Queue<OriginalParameter> stack = new LinkedList<>();
		Set<OriginalParameter> visited = new HashSet<>();
		stack.add(parameter);
		while (!stack.isEmpty()) {
			OriginalParameter current = stack.poll();
			visited.add(current);

			Set<OriginalParameter> dependencies = parameterGraph.getNodes().stream()
				.filter(p -> parameterGraph.getAdjacent(p).stream()
					.anyMatch(a -> a.equals(current)))
				.collect(Collectors.toSet());

			for (OriginalParameter adj : dependencies)
				stack.add(adj);
		}

		return visited;
	}

	/**
	 * Computes the value of the given parameter by recursively evaluating all
	 * of its dependencies.
	 * <p>
	 * This method traverses the dependency graph starting from the specified
	 * parameter, ensuring that each dependent parameter is evaluated before
	 * evaluating the parameter itself. Computed values are stored in the
	 * provided {@code env} map to avoid redundant computations (memoization).
	 * <p>
	 * If the value of the parameter is already present in {@code env}, it is
	 * returned immediately. Otherwise, all dependent (adjacent) parameters are
	 * recursively evaluated, and their values are added to {@code env}.
	 * Finally, the value of the parameter is computed using
	 * {@link OriginalParameter#evaluate(Map)} and stored in {@code env}.
	 *
	 * @param p          the parameter whose value must be computed
	 * @param parameters the directed graph representing dependencies between
	 *                       parameters
	 * @param env        a mutable mapping from parameter names to their
	 *                       computed values; it is updated with newly computed
	 *                       results during execution
	 *
	 * @return the computed value of the given parameter
	 *
	 * @throws StackOverflowError if the dependency graph contains cycles
	 */
	private Object computeValue(
		OriginalParameter p,
		DirectedGraph<OriginalParameter> parameters,
		Map<String, Object> env) {

		if (env.containsKey(p.getName()))
			return env.get(p.getName());

		for (OriginalParameter dependency : parameters.getAdjacent(p)) {
			Object result = computeValue(dependency, parameters, env);
			env.put(p.getName(), result);
		}

		Object value = p.evaluate(env);
		env.put(p.getName(), value);
		return value;
	}

	/**
	 * Factory method that creates the appropriate {@link LiftedParameter}
	 * implementation based on the parameter type and requested changes.
	 * <p>
	 * Multiple or mixed request types will result in an exception.
	 *
	 * @param parameter          the original parameter
	 * @param toLiftDependencies the dependencies of this lift
	 * @param changes            the list of lift requests for this parameter
	 *
	 * @return a concrete {@code LiftedParameter}
	 *
	 * @throws IllegalStateException    if no changes are provided
	 * @throws IllegalArgumentException if incompatible changes are requested
	 */
	public LiftedParameter create(
		OriginalParameter parameter,
		Set<OriginalParameter> toLiftDependencies,
		List<LiftRequest> changes) {

		List<LiftRequest> allChanges = new ArrayList<>(changes);
		if (changes.size() == 0) {
			System.out.println("% WARNING: lifting " + parameter + " too");
			return new LiftedDependencyParameter(parameter, toLiftDependencies);
		} else if (allChanges.get(0) instanceof SimpleLiftRequest slc) {
			MiniZincType type = parameter.getType();
			if (type instanceof MiniZincBasicType)
				return new LiftedSimpleParameter(parameter, slc);
			else if (type instanceof MiniZincSetType)
				return new LiftedSetParameter(parameter, slc);
			else if (type instanceof MiniZincArrayType)
				return new LiftedArrayParameter(parameter, slc);
		} else if (allChanges.get(0) instanceof ArrayElementLiftRequest) {
			return new LiftedArrayElementParameter(parameter, allChanges);
		} else
			throw new IllegalStateException("Unkown type of lift request for " + parameter.getName());
		return null;
	}

	/**
	 * Construct and prepares the lifting transformation.
	 * <p>
	 * This constructor:
	 * <ul>
	 * <li>Resolves all parameters that need to be lifted</li>
	 * <li>Expands lifting to include dependencies</li>
	 * <li>Creates {@link LiftedParameter} instances</li>
	 * </ul>
	 * <p>
	 * Note that even parameters not explicitly requested may be lifted if they
	 * are required to preserve correctness.
	 *
	 * @param tokens     the token stream of the parsed model
	 * @param toLift     the user-provided lift requests
	 * @param parameters the parameter dependency graph
	 */
	public Lifter(
		TokenStream tokens,
		List<LiftRequest> toLift,
		DirectedGraph<OriginalParameter> parameters) {

		isSolvePresent = false;
		isOutputPresent = false;
		visitor = new LiftingVisitor();
		rewriter = new TokenStreamRewriter(tokens);

		this.env = new HashMap<String, Object>();

		// collect all dependencies
		Set<OriginalParameter> toLiftAll = toLift.stream()
			// get all names
			.map(LiftRequest::getName)
			// avoid duplicates
			.distinct()
			// get the original parameter back
			.map(s -> parameters.getNodes().stream()
				.filter(p -> p.getName().equals(s))
				.findAny())
			// if something fails here, the error is very weird. It would mean
			// that the name of the parameters used to asking a lift does not
			// match with any of the parameter found in the model (even though
			// we already check before that such parameter would exists). So it
			// is safe to Optional::get here without checking
			.map(Optional::get)
			// get all parameters, even with their dependencies
			.map(op -> dependsOn(parameters, op))
			// return a set
			.flatMap(Set::stream)
			.collect(Collectors.toSet());

		this.lifted = toLiftAll.stream()
			// now, for each parameter we got, we get a list of all the lifts
			// for that parameter (it could be an empty list if the parameter
			// was just a dependency and was not requested for an
			// actual lift)
			.map(original -> {
				computeValue(original, parameters, env);

				return create(
					original,
					toLiftAll,
					toLift.stream()
						.filter(l -> l.getName().equals(original.getName()))
						.toList());
			}).toList();
	}

	/**
	 * Executes the rewriting process and returns the transformed model.
	 * <p>
	 * This method:
	 * <ol>
	 * <li>Traverses the AST and applies rewriting rules</li>
	 * <li>Appends new declarations for lifted parameters</li>
	 * <li>Adds solve/output blocks if not already present</li>
	 * </ol>
	 *
	 * @param ctx the root model context
	 *
	 * @return the rewritten MiniZinc model as a string
	 */
	public String execute(ModelContext ctx) {
		visitor.visitModel(ctx);
		StringBuilder model = new StringBuilder(rewriter.getText() + "\n");

		for (LiftedParameter lp : this.lifted) {
			model.append(lp.liftDeclaration(env) + "\n");
			lp.getConstraints().forEach(c -> model.append(c + "\n"));
		}

		if (!isSolvePresent)
			model.append(getSolve());

		if (!isOutputPresent)
			model.append(getOutput());

		return model.toString();
	}

	/**
	 * Internal visitor that applies rewriting rules to the AST.
	 * <p>
	 * The visitor does not build new structures. Instead, it issues
	 * modifications to the {@link TokenStreamRewriter}.
	 * <p>
	 * Main responsibilities:
	 * <ul>
	 * <li>Remove original parameter declarations and assignments</li>
	 * <li>Replace parameter usages with lifted variables</li>
	 * <li>Override solve/output sections if present</li>
	 * </ul>
	 */
	private class LiftingVisitor extends MiniZincBaseVisitor<Void> {

		/**
		 * Get the lifted parameter by name.
		 *
		 * @param name the name of the {@link LiftedParameter}
		 *
		 * @return an {@link Optional} {@link LiftedParameter}
		 */
		private Optional<LiftedParameter> getByName(String name) {
			return lifted.stream()
				.filter(l -> l.getOriginalName().equals(name))
				.findAny();
		}

		/**
		 * Remove the assignment completely.
		 *
		 * @return null
		 */
		@Override
		public Void visitAssignItem(AssignItemContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.ident().getText());
			if (p.isPresent())
				rewriter.delete(ctx.getStart(), ctx.getStop());

			return null;
		}

		/**
		 * Modify the declaration by adding the value found during the
		 * assignment phase.
		 *
		 * @return null
		 */
		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.tiExprAndId().ident().getText());
			if (p.isPresent())
				rewriter.replace(
					ctx.getStart(),
					ctx.getStop(),
					p.get().getOriginalDeclaration());
			else
				return super.visitVarDeclItem(ctx);

			return null;
		}

		/**
		 * Replaces usages of lifted parameters with their new names.
		 *
		 * @return null
		 */
		@Override
		public Void visitIdent(IdentContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.getText());
			if (p.isPresent())
				rewriter.replace(
					ctx.IDENT().getSymbol(),
					p.get().getLiftedName());

			return null;
		}

		/**
		 * Replaces the solve statement with a generated one.
		 *
		 * @return null
		 */
		@Override
		public Void visitSolveItem(SolveItemContext ctx) {
			isSolvePresent = true;
			rewriter.replace(ctx.start, ctx.stop, getSolve());
			return null;
		}

		/**
		 * Replaces the output statement with a generated one.
		 *
		 * @return null
		 */
		@Override
		public Void visitOutputItem(OutputItemContext ctx) {
			isOutputPresent = true;
			rewriter.replace(ctx.start, ctx.stop, getOutput());
			return null;
		}

	}

}
