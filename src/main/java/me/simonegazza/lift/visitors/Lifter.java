package me.simonegazza.lift.visitors;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.OutputItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SolveItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
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
	 * @return the set of dependent parameter names (including the input)
	 */
	private static Set<String> dependsOn(
		DirectedGraph<OriginalParameter> parameterGraph,
		String parameter) {

		Set<String> dependents = Set.of(parameter);
		while (true) {
			final Set<String> currentDependents = dependents;

			Set<String> newDependents = parameterGraph.getNodes().stream()
				.filter(p -> parameterGraph.getAdjacent(p).stream()
					.anyMatch(a -> currentDependents.contains(a.getName())))
				.map(OriginalParameter::getName)
				.collect(Collectors.toSet());

			if (dependents.equals(newDependents))
				break;

			dependents = newDependents;
		}

		return dependents;

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

		// collect all dependencies
		var liftedStream = toLift.stream()
			// get all names
			.map(LiftRequest::getName)
			// avoid duplicates
			.distinct()
			// flat the dependencies for each lift
			.flatMap(pName -> dependsOn(parameters, pName).stream())
			// some parameters could have the same dependencies, we eant to
			// avoid counting them twice
			.distinct();

		this.lifted = Stream.concat(liftedStream, toLift.stream().map(LiftRequest::getName))
			// get back the OriginalParameter based on the name
			.map(s -> parameters.getNodes().stream()
				.filter(p -> p.getName().equals(s))
				.findAny())

			// if something fails here, the error is very weird. It would mean
			// that the name of the parameters used to asking a lift does not
			// match with any of the parameter found in the model (even though
			// we already check before that such parameter would exists). So it
			// is safe to Optional::get here without checking
			.map(Optional::get)
			.distinct()
			// now, for each parameter we got, we get a list of all the lifts
			// for that parameter (it could be an empty list if the parameter
			// was just a dependency and was not requested for an
			// actual lift)
			.map(op -> LiftedParameter.create(
				op,
				toLift.stream()
					.filter(l -> l.getName().equals(op.getName()))
					.toList()))
			.toList();
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
		visitor.visit(ctx);
		StringBuilder model = new StringBuilder(rewriter.getText() + "\n");
		for (LiftedParameter lp : this.lifted) {
			model.append(lp.getLiftedDeclaration() + "\n");
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
		 * Do not touch any assignments.
		 *
		 * @returns null
		 */
		@Override
		public Void visitAssignItem(AssignItemContext ctx) {
			return null;
		}

		/**
		 * Do not touch any declaration.
		 *
		 * @returns null
		 */
		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			return null;
		}

		/**
		 * Replaces usages of lifted parameters with their new names.
		 *
		 * @returns null
		 */
		@Override
		public Void visitIdent(IdentContext ctx) {
			Optional<LiftedParameter> p = lifted.stream()
				.filter(l -> l.getOriginalName().equals(ctx.getText()))
				.findAny();
			if (p.isPresent())
				rewriter.replace(
					ctx.IDENT().getSymbol(),
					p.get().getLiftedName());

			return null;
		}

		/**
		 * Replaces the solve statement with a generated one.
		 *
		 * @returns null
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
		 * @returns null
		 */
		@Override
		public Void visitOutputItem(OutputItemContext ctx) {
			isOutputPresent = true;
			rewriter.replace(ctx.start, ctx.stop, getOutput());
			return null;
		}

	}

}
