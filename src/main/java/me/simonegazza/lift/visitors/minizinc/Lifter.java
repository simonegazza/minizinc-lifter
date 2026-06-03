package me.simonegazza.lift.visitors.minizinc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AddExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprTailContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.RangeExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SolveItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.utils.ParameterGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.Interval;

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
 * Unlike {@link ParameterExtractor}, this class does not build a semantic
 * model. Instead, it operates directly on the token stream using
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
public class Lifter implements Callable<String> {

	/**
	 * Rewritings file path.
	 */
	private final static String REWRITINGS_NAME = "/global_constraints_rewriting.mzn";

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
	 * The model context in which this will be called.
	 */
	private final ModelContext ctx;

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
	 * @param p     the parameter whose value must be computed
	 * @param graph the directed graph representing dependencies between
	 *                  parameters
	 * @param env   a mutable mapping from parameter names to their computed
	 *                  values; it is updated with newly computed results during
	 *                  execution
	 *
	 * @return the computed value of the given parameter
	 *
	 * @throws StackOverflowError if the dependency graph contains cycles
	 */
	private Object computeValue(
		OriginalParameter p,
		ParameterGraph graph,
		Map<String, Object> env) {

		if (env.containsKey(p.getName())) {
			return env.get(p.getName());
		}

		for (OriginalParameter dependency : graph.getAdjacent(p)) {
			Object result = computeValue(dependency, graph, env);
			env.put(p.getName(), result);
		}

		Object value = p.evaluate(env);
		env.put(p.getName(), value);
		return value;
	}

	public Lifter(
		TokenStream tokens,
		ModelContext ctx,
		List<LiftRequest> toLift,
		ParameterGraph graph,
		boolean setsDisallowed) {

		visitor = new LiftingVisitor();
		rewriter = new TokenStreamRewriter(tokens);
		env = new HashMap<String, Object>();
		this.ctx = ctx;

		// collect all dependencies
		Set<OriginalParameter> toLiftAll = toLift.stream()
			// get all names
			.map(LiftRequest::getName)
			// avoid duplicates
			.distinct()
			// get the original parameter back
			.map(graph::getByName)
			// if something fails here, the error is very weird. It would mean
			// that the name of the parameters used to asking a lift does not
			// match with any of the parameter found in the model (even though
			// we already check before that such parameter would exists). So it
			// is safe to Optional::get here without checking
			.map(Optional::get)
			// get all parameters, even with their dependencies
			.map(graph::backwardClosure)
			// return a set
			.flatMap(Set::stream)
			.collect(Collectors.toSet());

		Set<OriginalParameter> setsMarkedForRemoval = toLiftAll.stream()
			// filter away all elements by default (i.e. sets are allowed)
			.filter(_ -> setsDisallowed)
			// if something remains, it means that sets are *not* allowed, so we
			// filter away all elements that are not sets
			.filter(p -> p.getType() instanceof MiniZincSetType
				|| (p.getType() instanceof MiniZincArrayType t && t.getSubtype() instanceof MiniZincSetType))
			.collect(Collectors.toSet());

		Set<OriginalParameter> closuredMarkedForRemoval = setsMarkedForRemoval.stream()
			// get all the backward closures but temporarily remove the current
			// node
			.map(p -> graph.backwardClosure(p).stream()
				.filter(bc -> !bc.equals(p))
				.toList())
			.flatMap(List::stream)
			.collect(Collectors.toSet());

		// Filter away all the elements that do not present a dependency in the
		// closures of sets or in the sets themselves
		Set<OriginalParameter> markedForRemoval = Stream.concat(
			closuredMarkedForRemoval.stream()
				.filter(c -> graph.getAdjacent(c).stream()
					.allMatch(e -> setsMarkedForRemoval.contains(e)
						|| closuredMarkedForRemoval.contains(e))),
			setsMarkedForRemoval.stream()).collect(Collectors.toSet());

		toLiftAll.removeAll(markedForRemoval);

		lifted = toLiftAll.stream()
			// now, for each parameter we got, we get a list of all the lifts
			// for that parameter (it could be an empty list if the parameter
			// was just a dependency and was not requested for an
			// actual lift)
			.map(original -> {
				computeValue(original, graph, env);

				return LiftedParameter.create(
					original,
					toLiftAll,
					toLift.stream()
						.filter(l -> l.getName().equals(original.getName()))
						.toList());
			}).sorted((p1, p2) -> p1.getOriginalName().compareTo(p2.getOriginalName()))
			.toList();
	}

	/**
	 * @return the lifted parameters
	 */
	public List<LiftedParameter> getLifted() {
		return lifted;
	}

	/**
	 * Executes the rewriting process and returns the transformed model.
	 * <p>
	 * This method:
	 * <ol>
	 * <li>Add constraint rewriting</li>
	 * <li>Traverses the AST and applies rewriting rules</li>
	 * <li>Appends new declarations for lifted parameters</li>
	 * <li>Adds solve/output blocks if not already present</li>
	 * </ol>
	 *
	 * @return the rewritten MiniZinc model as a string
	 */
	@Override
	public String call() {
		visitor.visitModel(ctx);

		String rewritings = "";
		try (var is = Lifter.class.getResourceAsStream(REWRITINGS_NAME)) {
			rewritings = new String(is.readAllBytes());
		} catch (Exception e) {
			throw new IllegalStateException(
				"Something went very wrong: cannot find the overriding of global constraints");
		}
		StringBuilder model = new StringBuilder(rewritings)
			.append("\n")
			.append(rewriter.getText())
			.append("\n");

		for (LiftedParameter lp : lifted) {
			lp.getConstraints().forEach(c -> model.append(c + "\n"));
		}

		return model.toString();
	}

	/**
	 * Internal visitor that applies rewriting rules to the AST.
	 * <p>
	 * The visitor does not build new structures. Instead, it issues
	 * modifications to the {@link TokenStreamRewriter}.
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
		 * Determines and returns the variable declaration.
		 * <p>
		 * If the type contains the keyword {@code var}, the declaration
		 * represents a decision variable rather than a parameter.
		 * <p>
		 * This method recursively inspects type expressions to detect if this
		 * is a variable declaration
		 *
		 * @param ctx the type expression context
		 *
		 * @return an optional containing the variable declaration from the
		 *             "var" to the ":" if present
		 */
		private Optional<BaseTiExprTailContext> getVarDecl(ParserRuleContext ctx) {
			switch (ctx) {
			case TiExprContext ti -> {
				if (ti.baseTiExpr() != null) {
					return getVarDecl(ti.baseTiExpr());
				} else {
					return getVarDecl(ti.arrayTiExpr());
				}
			}
			case BaseTiExprContext ctxbtec -> {
				if ("var".equals(ctx.getChild(0).getText())) {
					return Optional.of(ctxbtec.baseTiExprTail());
				}
			}
			case ArrayTiExprContext arrayTiExprCtx -> {
				return getVarDecl(arrayTiExprCtx.tiExpr().getLast());
			}
			case null, default -> {
			}
			}

			return Optional.empty();
		}

		/**
		 * Jump the assignment completely. Prevent changing parameters in what
		 * was the data file.
		 *
		 * @return null
		 */
		@Override
		public Void visitAssignItem(AssignItemContext ctx) {
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
			TiExprContext typeCtx = ctx.tiExprAndId().tiExpr();
			Optional<BaseTiExprTailContext> varDeclaration = getVarDecl(typeCtx);

			if (p.isPresent()) {
				// This declaration was a parameter
				rewriter.insertAfter(ctx.getStop(), "\n" + p.get().liftDeclaration(env));
			} else if (varDeclaration.isPresent()) {
				// This is a declaration of a variable. We need to check if in
				// the bounding of the variable there are some parameters that
				// we lifted. We call visitBaseTiExprTail that can visit the
				// following stuff:
				// 1) identifier: in that case we lift the identifier,
				// 2) a baseType: in that case there's no need to do anything,
				// 3) a DOLLAR_IDENT: we do not know what would happen (probably
				// nothing: the identifier will stay put), and we do not care,
				// 4) an expr: we try to parse the expression (hopefully it's a
				// simple RangeExpression that just needs some rewriting).
				BaseTiExprTailContext declaration = varDeclaration.get();
				boolean boundingHasLiftedParameter = lifted.stream()
					.anyMatch(l -> declaration.getText().contains(l.getOriginalName()));

				// Visit it only if necessary
				if (boundingHasLiftedParameter) {
					visit(declaration);
				}
			}

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
			if (p.isPresent()) {
				rewriter.replace(
					ctx.IDENT().getSymbol(),
					p.get().getLiftedName());
			}

			return null;
		}

		/**
		 * Remove the solve statement with a generated one.
		 *
		 * @return null
		 */
		@Override
		public Void visitSolveItem(SolveItemContext ctx) {
			rewriter.delete(ctx.start, ctx.stop);
			return null;
		}

		/**
		 * Replaces asserts with inner expression if it was called by an
		 * assertion. Pass through it otherwise.
		 *
		 * @return null
		 */
		@Override
		public Void visitExpr(ExprContext ctx) {
			boolean isAssert = ctx.getText().startsWith("assert");
			boolean hasIdentifier = lifted.stream()
				.anyMatch(l -> ctx.getText().contains(l.getOriginalName()));
			if (isAssert && hasIdentifier) {
				// Descend the entire parsing line
				ExprContext argument = ctx.iffExpr()
					.implExpr(0).orExpr(0).xorExpr(0).andExpr(0)
					.compareExpr(0)
					.setExpr(0).rangeExpr(0)
					.addExpr(0).multExpr(0).powExpr(0)
					.unaryExpr(0)
					.primary().postfix(0).callSuffix()
					.expr(0);

				Interval interval = new Interval(
					argument.getStart().getStartIndex(),
					// The trailing comma must be removed
					argument.getStop().getStopIndex() - 1);
				String toReplace = ctx.getStart().getInputStream().getText(interval);
				// A semicolon must be added
				toReplace += ";";

				for (LiftedParameter l : lifted) {
					toReplace = toReplace.replaceAll(
						"\\b" + l.getOriginalName() + "\\b",
						l.getLiftedName());
				}

				rewriter.replace(ctx.getStart(), ctx.getStop(), toReplace);

			} else {
				super.visitExpr(ctx);
			}

			return null;
		}

		/**
		 * Ranges needs to have correct bounding, otherwise the MiniZinc model
		 * won't compile.
		 */
		@Override
		public Void visitRangeExpr(RangeExprContext ctx) {
			if (ctx.getChildCount() > 1) {
				// Wrap with at least the lower bounds
				boolean lhsHasLiftedParameter = lifted.stream()
					.anyMatch(l -> ctx.addExpr(0).getText().contains(l.getOriginalName()));
				if (lhsHasLiftedParameter) {
					AddExprContext lhs = ctx.addExpr(0);
					visitAddExpr(lhs);
					rewriter.insertBefore(lhs.getStart(), "lb(");
					rewriter.insertBefore(lhs.getStop(), ")");
				}

				if (ctx.getChildCount() > 2) {
					// Now we are sure that we have also the rhs of the range
					boolean rhsHasLiftedParameter = lifted.stream()
						.anyMatch(l -> ctx.addExpr(1).getText().contains(l.getOriginalName()));

					if (rhsHasLiftedParameter) {
						AddExprContext rhs = ctx.addExpr(1);
						visitAddExpr(rhs);
						rewriter.insertBefore(rhs.getStart(), "ub(");
						rewriter.insertBefore(rhs.getStop(), ")");
					}
				}
				return null;

			} else {
				// It is an identifier that needed to be lifted: pass through
				return visitAddExpr(ctx.addExpr(0));
			}
		}
	}
}
