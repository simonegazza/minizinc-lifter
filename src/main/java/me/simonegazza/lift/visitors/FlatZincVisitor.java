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
import me.simonegazza.antlr.flatzinc.FlatZincParser.AnnotationContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.AnnotationsContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.BoolLiteralContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ConstraintItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ModelContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.SolveItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.VarDeclItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.VarParIdentifierContext;
import me.simonegazza.lift.RevokedAssumption;
import me.simonegazza.lift.parameters.LiftedParameter;

/**
 * Visit the FlatZinc and it main objective is to return a set of
 * {@link RevokedAssumption} to be implemented in the following run of the
 * MiniZinc file.
 */
public class FlatZincVisitor {

	/**
	 * List of nogood names given by the solver.
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
	 * Find and returns the name of the variable containing the assumptions.
	 */
	private static class SolveAssumeFinder extends FlatZincBaseVisitor<String> {
		@Override
		public String visitSolveItem(SolveItemContext ctx) {
			if (ctx.annotations() == null) {
				throw new IllegalStateException("No annotation for the compiled FlatZinc file");
			}
			return visitAnnotations(ctx.annotations());
		}

		@Override
		public String visitAnnotations(AnnotationsContext ctx) {
			return ctx.annotation().stream()
				.map(this::visitAnnotation)
				.filter(Objects::nonNull)
				.findFirst()
				.get();
		}

		@Override
		public String visitAnnotation(AnnotationContext ctx) {
			if ("assume".equals(ctx.identifier().getText())) {
				// This should be enough to get the variable name
				return ctx.annExprList().getText();
			}
			return null;
		}
	}

	/**
	 * Find and returns the array of assumptions.
	 */
	private class VarNamesFinder extends FlatZincBaseVisitor<Void> {
		/**
		 * The name of the variable in the assume annotation.
		 */
		private final String assumptionVarName;

		/**
		 * The names of the variables inside the assume array.
		 */
		private final List<String> varNames;

		public VarNamesFinder(String assumptionVarName) {
			this.assumptionVarName = assumptionVarName;
			varNames = new ArrayList<>();
		}

		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			if (ctx.varParIdentifier().getText().equals(assumptionVarName)) {
				return visitArrayLiteral(ctx.arrayLiteral());
			}

			return null;
		}

		@Override
		public Void visitVarParIdentifier(VarParIdentifierContext ctx) {
			varNames.add(ctx.getText());
			return null;
		}

		@Override
		public Void visitBoolLiteral(BoolLiteralContext ctx) {
			varNames.add(ctx.getText());
			return null;
		}

	}

	/**
	 * Recursively look at variables to get all parameters involved in the
	 * failure.
	 */
	private class BackwardFinder extends FlatZincBaseVisitor<Optional<Set<String>>> {
		/**
		 * Name of the current variable we are looking for.
		 */
		private final String varName;

		/**
		 * The FlatZinc model context.
		 */
		private final ModelContext ctx;

		public BackwardFinder(String varName, ModelContext ctx) {
			this.varName = varName;
			this.ctx = ctx;
		}

		@Override
		public Optional<Set<String>> visitVarDeclItem(VarDeclItemContext ctx) {
			boolean isElsewhereDefined = ctx.annotations().annotation().stream()
				.anyMatch(a -> "is_defined_var".equals(a.identifier().getText()));
			if (ctx.varParIdentifier().getText().equals(varName) && !isElsewhereDefined) {
				return Optional.of(Set.of(varName));
			}
			return Optional.empty();
		}

		@Override
		public Optional<Set<String>> visitConstraintItem(ConstraintItemContext ctx) {
			if (ctx.annotations().annotation().size() > 0 && ctx.exprList() != null) {
				boolean correctAnnotation = ctx.annotations().annotation().stream()
					.anyMatch(a -> a.annExprList() != null
						&& "defines_var".equals(a.identifier().getText())
						&& a.annExprList().getText().equals(varName));

				if (!correctAnnotation) {
					return Optional.empty();
				}

				Set<String> variables = ctx.exprList().expr().stream()
					.map(e -> e.getText())
					.filter(e -> e.startsWith("X_INTRODUCED_"))
					.filter(e -> !e.equals(varName))
					.flatMap(vn -> new BackwardFinder(vn, this.ctx).execute().stream())
					.collect(Collectors.toSet());

				return Optional.of(variables);

			}

			return Optional.empty();
		}

		/**
		 * Recursively look at the fzn file and search for the variables names.
		 *
		 * @return the set of founded variables.
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
	 * Constructs the actual {@link RevokedAssumption} from the flattened
	 * variable name and the FlatZinc file.
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
		 * Utility function that returns the original name (stripped from the
		 * apexes) from the "doc_comment" of a variable.
		 *
		 * @param ctx the variable declaration item that these will be applied
		 *                to
		 *
		 * @return a {@link Stream} of one element containing the variable name
		 */
		private Stream<String> getOriginalVarName(VarDeclItemContext ctx) {
			return ctx.annotations().annotation().stream()
				.filter(a -> "doc_comment".equals(a.identifier().getText()))
				.map(a -> a.annExprList().annExpr().get(0).basicAnnExpr().stringLiteral()
					.getText()
					.replaceAll("\"", ""));
		}

		@Override
		public String visitVarDeclItem(VarDeclItemContext ctx) {
			if (ctx.varParIdentifier().getText().equals(varName)) {
				return getOriginalVarName(ctx).findFirst().get();
			}

			return null;
		}

		/**
		 * Retrieves and construct all the necessary informations to build a
		 * {@link RevokedAssumption}.
		 *
		 * @param ctx the context of the model
		 *
		 * @return the {@link RevokedAssumption}
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

				if (vdictx.varParIdentifier().getText().equals(varName)) {
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
	 * Executes the analysis, trying to understand which assumption needs to
	 * revoked.
	 *
	 * @param ctx the {@link ModelContext} for starting the analysis
	 *
	 * @return the assumptions to be revoked
	 */
	public Set<RevokedAssumption> execute(ModelContext ctx) {
		String assumedName = new SolveAssumeFinder().visitSolveItem(ctx.solveItem());
		VarNamesFinder vnf = new VarNamesFinder(assumedName);
		vnf.visit(ctx);
		List<String> flatAssumedNames = vnf.varNames;

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
