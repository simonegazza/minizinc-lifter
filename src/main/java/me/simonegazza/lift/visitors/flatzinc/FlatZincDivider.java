package me.simonegazza.lift.visitors.flatzinc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import me.simonegazza.antlr.flatzinc.FlatZincBaseVisitor;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ConstraintItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.ParDeclItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.PredicateItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.SolveItemContext;
import me.simonegazza.antlr.flatzinc.FlatZincParser.VarDeclItemContext;

public class FlatZincDivider extends FlatZincBaseVisitor<Void> {

	private final StringBuilder firstModelPart;
	private final Map<String, String> variableConstraintAssociation;
	private final List<String> otherConstraints;
	private final StringBuilder solveItem;

	public FlatZincDivider() {
		firstModelPart = new StringBuilder();
		variableConstraintAssociation = new HashMap<>();
		otherConstraints = new ArrayList<>();
		solveItem = new StringBuilder();
	}

	@Override
	public Void visitPredicateItem(PredicateItemContext ctx) {
		firstModelPart.append(ctx.getText()).append("\n");
		return null;
	}

	@Override
	public Void visitParDeclItem(ParDeclItemContext ctx) {
		firstModelPart.append(ctx.getText()).append("\n");
		return null;
	}

	@Override
	public Void visitVarDeclItem(VarDeclItemContext ctx) {
		firstModelPart.append(ctx.getText()).append("\n");
		return null;
	}

	@Override
	public Void visitConstraintItem(ConstraintItemContext ctx) {
		// Verify we are looking at the right annotation
		Optional<String> definesVarName = ctx.annotation().stream()
			.filter(a -> a.annExpr().size() > 0
				&& "defines_var".equals(a.IDENTIFIER().getText()))
			.map(a -> a.annExpr(0).basicAnnExpr(0).getText())
			.findFirst();

		if (definesVarName.isEmpty()) {
			otherConstraints.add(ctx.getText());
			return null;
		}

		variableConstraintAssociation.put(definesVarName.get(), ctx.getText());

		return null;
	}

	@Override
	public Void visitSolveItem(SolveItemContext ctx) {
		solveItem.append(ctx.getText()).append("\n");
		return null;
	}

	/**
	 * Getter for the list of constraints present in the model.
	 * <p>
	 * Due to the FlatZinc model being very big, we return a list instead of a
	 * set (even an ordered one) due to potential performance issues.
	 * <p>
	 * Can be an empty list if the extractor wasn't called for parsing.
	 *
	 * @return the list of constraints
	 */
	public List<String> getConstraints() {
		return Stream.concat(
			variableConstraintAssociation.values().stream(),
			otherConstraints.stream()).toList();
	}

	public String getAboveConstraints() {
		return firstModelPart.toString();
	}
}
