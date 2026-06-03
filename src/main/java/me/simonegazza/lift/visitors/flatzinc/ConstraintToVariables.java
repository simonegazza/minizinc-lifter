package me.simonegazza.lift.visitors.flatzinc;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.antlr.flatzinc.FlatZincBaseVisitor;
import me.simonegazza.antlr.flatzinc.FlatZincParser.BasicExprContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ConstraintItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ModelContext;

/**
 * Visitor that extracts variable names referenced by FlatZinc constraints.
 * <p>
 * The visitor traverses constraint expressions and collects all variable
 * identifiers appearing within them. For constraints annotated with
 * {@code defines_var}, the defined variable can be reported directly instead of
 * traversing the constraint expression.
 * <p>
 * The resulting set can be used to identify the variables involved in a
 * conflict, explanation, or unsatisfiable core.
 */
public class ConstraintToVariables extends FlatZincBaseVisitor<Set<String>> {
	@Override
	public Set<String> visitModel(ModelContext ctx) {
		return ctx.constraintItem().stream()
			.map(this::visitConstraintItem)
			.flatMap(Set::stream)
			.collect(Collectors.toSet());
	}

	@Override
	public Set<String> visitConstraintItem(ConstraintItemContext ctx) {
		Optional<String> definesVarName = ctx.annotation().stream()
			.filter(a -> a.annExpr().size() > 0
				&& "defines_var".equals(a.IDENTIFIER().getText()))
			.map(a -> a.annExpr(0).basicAnnExpr(0).getText())
			.findFirst();

		if (definesVarName.isPresent()) {
			return Set.of(definesVarName.get());
		}

		return ctx.expr().stream()
			.map(this::visitExpr)
			.flatMap(Set::stream)
			.collect(Collectors.toSet());
	}

	@Override
	public Set<String> visitBasicExpr(BasicExprContext ctx) {
		if (ctx.IDENTIFIER() != null) {
			return Set.of(ctx.IDENTIFIER().getText());
		}
		return Set.of();
	}
}
