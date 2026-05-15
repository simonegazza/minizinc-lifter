package me.simonegazza.lift.visitors;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.antlr.flatzinc.FlatZincBaseVisitor;
import me.simonegazza.antlr.flatzinc.FlatZincParser.BasicExprContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ConstraintItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ModelContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.SolveItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.VarDeclItemContext;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.parameters.LiftedParameter;

/**
 * Visits a FlatZinc model and extracts the set of {@link RevokedAssumption}s
 * that must be disabled in the next MiniZinc execution.
 */
public class FlatZincVisitor {

	/**
	 * List of nogood variable names given by the solver.
	 */
	private final List<String> nogoods;

	/**
	 * List of {@link LiftedParameter}.
	 */
	private final List<LiftedParameter> liftedParameters;

	public FlatZincVisitor(Path modelPath, List<LiftedParameter> liftedParameters, List<String> nogoods) {
		this.nogoods = nogoods;
		this.liftedParameters = liftedParameters;
	}

	/**
	 * Visits the solve item to locate the variable referenced by the `assume`
	 * annotation.
	 */
	private static class SolveAssumeFinder extends FlatZincBaseVisitor<String> {
		@Override
		public String visitSolveItem(SolveItemContext ctx) {
			if (ctx.annotation().size() == 0) {
				throw new IllegalStateException("No annotation for the compiled FlatZinc file");
			}
			return ctx.annotation().stream()
				.filter(a -> "assume".equals(a.IDENTIFIER().getText()))
				.map(a -> a.annExpr().getFirst().getText())
				.findFirst()
				.get();
		}
	}

	/**
	 * Extracts the variable names contained inside the assumption array
	 * declaration.
	 */
	private class VarNamesFinder extends FlatZincBaseVisitor<Void> {
		/**
		 * Name of the variable in the assume annotation.
		 */
		private final String assumeName;

		/**
		 * Variable names collected from the assumption array.
		 */
		private final List<String> names;

		public VarNamesFinder(String assumptionVarName) {
			assumeName = assumptionVarName;
			names = new ArrayList<>();
		}

		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			if (ctx.IDENTIFIER().getText().equals(assumeName)) {
				return visitArrayLiteral(ctx.arrayLiteral());
			}

			return null;
		}

		@Override
		public Void visitBasicExpr(BasicExprContext ctx) {
			if (ctx.setLiteral() != null) {
				throw new IllegalStateException("Found a set literal inside the array literal");
			}
			names.add(ctx.getText());
			return null;
		}

		/**
		 * Scans the FlatZinc model and retrieves all variable names contained
		 * in the assumption array.
		 *
		 * @param ctx the root FlatZinc model context
		 *
		 * @return the list of assumption variable names
		 */
		public List<String> getVarNames(ModelContext ctx) {
			ctx.varDeclItem().stream().forEach(this::visitVarDeclItem);
			return names;
		}

	}

	/**
	 * Recursively traverses introduced variables in order to identify the
	 * original parameters involved in a failure.
	 */
	private class BackwardFinder extends FlatZincBaseVisitor<Optional<Set<String>>> {
		/**
		 * Current variable name being analyzed.
		 */
		private final String name;

		/**
		 * FlatZinc model context.
		 */
		private final ModelContext ctx;

		public BackwardFinder(String name, ModelContext ctx) {
			this.name = name;
			this.ctx = ctx;
		}

		@Override
		public Optional<Set<String>> visitVarDeclItem(VarDeclItemContext ctx) {
			// Check if variable is introduced by the compilation
			boolean isElsewhereDefined = ctx.annotation().stream()
				.anyMatch(a -> "is_defined_var".equals(a.IDENTIFIER().getText()));

			if (ctx.IDENTIFIER().getText().equals(name) && !isElsewhereDefined) {
				return Optional.of(Set.of(name));
			}
			return Optional.empty();
		}

		@Override
		public Optional<Set<String>> visitConstraintItem(ConstraintItemContext ctx) {
			if (ctx.annotation().size() > 0 && ctx.expr().size() > 0) {

				// Verify we are looking at the right annotation
				boolean correctAnnotation = ctx.annotation().stream()
					.anyMatch(a -> a.annExpr().size() > 0
						&& "defines_var".equals(a.IDENTIFIER().getText())
						&& a.annExpr().getFirst().getText().equals(name));
				if (!correctAnnotation) {
					return Optional.empty();
				}

				// Recursively look up at the variables in the constraint
				Set<String> variables = ctx.expr().stream()
					.map(e -> e.getText())
					.filter(e -> e.startsWith("X_INTRODUCED_"))
					.filter(e -> !e.equals(name))
					.flatMap(vn -> new BackwardFinder(vn, this.ctx).execute().stream())
					.collect(Collectors.toSet());

				return Optional.of(variables);

			}

			return Optional.empty();
		}

		/**
		 * Executes the backward dependency retrieval starting from the current
		 * variable.
		 *
		 * @return the set of discovered variable names
		 */
		public Set<String> execute() {
			return Stream.concat(ctx.constraintItem().stream(), ctx.varDeclItem().stream())
				.map(this::visit)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
		}

	}

	/**
	 * Constructs the {@link RevokedAssumption} from the FlatZinc variable name
	 * and the FlatZinc file.
	 */
	private class Namer extends FlatZincBaseVisitor<String> {
		/**
		 * The name flattened name of the variable to be constructed.
		 */
		private final String varName;

		public Namer(String varName) {
			this.varName = varName;
		}

		/**
		 * Utility function that extracts the original name (stripped from the
		 * apexes) from the "doc_comment" of a variable.
		 *
		 * @param ctx the variable declaration item context
		 *
		 * @return a stream maybe containing the variable name
		 */
		private Stream<String> getOriginalVarName(VarDeclItemContext ctx) {
			return ctx.annotation().stream()
				.filter(a -> "doc_comment".equals(a.IDENTIFIER().getText()))
				.map(a -> a.annExpr()
					.get(0)
					.basicAnnExpr()
					.getFirst()
					.getText()
					.replaceAll("\"", ""));
		}

		@Override
		public String visitVarDeclItem(VarDeclItemContext ctx) {
			if (ctx.IDENTIFIER().getText().equals(varName)) {
				return getOriginalVarName(ctx).findFirst().get();
			}

			return null;
		}

		/**
		 * Retrieves all the necessary informations to build a
		 * {@link RevokedAssumption} and construct it.
		 *
		 * @param ctx the root FlatZinc model context
		 *
		 * @return the reconstructed revoked assumption
		 */
		public RevokedAssumption retrieve(ModelContext ctx) {
			String originalVarName = ctx.varDeclItem().stream()
				.map(this::visit)
				.filter(Objects::nonNull)
				.findFirst()
				.get();

			int index = 0;
			for (VarDeclItemContext vdictx : ctx.varDeclItem()) {
				boolean sameDocComment = getOriginalVarName(vdictx).anyMatch(originalVarName::equals);

				if (vdictx.IDENTIFIER().getText().equals(varName)) {
					break;
				} else if (sameDocComment) {
					index += 1;
				}
			}

			Optional<LiftedParameter> liftedParameter = liftedParameters.stream()
				.filter(p -> p.getLiftedName().equals(originalVarName))
				.findFirst();
			if (liftedParameter.isEmpty()) {
				throw new IllegalArgumentException(
					"Found a lifted parameter that nobody asked the lift of: " + originalVarName);
			}

			List<Integer> coordinates = new ArrayList<>();
			for (Integer dim : liftedParameter.get().getDimensions().reversed()) {
				coordinates.add(index % dim);
				index /= dim;
			}

			return new RevokedAssumption(
				originalVarName,
				// Return them as MiniZinc array indexed
				coordinates.reversed().stream().map(i -> i + 1).toList());
		}

	}

	/**
	 * Performs the full analysis of the FlatZinc model and determines which
	 * assumptions must be revoked.
	 *
	 * @param ctx the root FlatZinc model context
	 *
	 * @return the set of revoked assumptions
	 */
	public Set<RevokedAssumption> execute(ModelContext ctx) {
		String assumedName = new SolveAssumeFinder().visitSolveItem(ctx.solveItem());
		List<String> flatAssumedNames = new VarNamesFinder(assumedName).getVarNames(ctx);

		Set<RevokedAssumption> assumptions = new HashSet<>();
		for (String nogoodName : nogoods) {
			boolean isInAssumed = flatAssumedNames.stream()
				.anyMatch(nogoodName::equals);
			if (!isInAssumed) {
				throw new IllegalStateException(
					"The nogood variable " + nogoodName + " is not in the assumption annotation");
			}

			Set<String> nogoodParamNames = new BackwardFinder(nogoodName, ctx).execute();
			Set<RevokedAssumption> nogoodAssumptions = nogoodParamNames.stream()
				.map(n -> new Namer(n).retrieve(ctx))
				.collect(Collectors.toSet());
			assumptions.addAll(nogoodAssumptions);
		}

		return assumptions;
	}

}
