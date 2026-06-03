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

/**
 * Visitor that partitions a FlatZinc model into its main components.
 * <p>
 * During traversal, the visitor collects:
 * <ul>
 * <li>Model declarations (predicates, parameters, and variables).</li>
 * <li>Constraints associated with variables through the {@code defines_var}
 * annotation.</li>
 * <li>All remaining constraints.</li>
 * <li>The solve item.</li>
 * </ul>
 * <p>
 * This separation allows callers to reconstruct partial FlatZinc models or
 * analyze constraints independently from declarations.
 * <p>
 * Constraints annotated with {@code defines_var} are indexed by the variable
 * they define, enabling efficient lookup of the defining constraint for a
 * particular variable.
 */
public class FlatZincDivider extends FlatZincBaseVisitor<Void> {

	/**
	 * Portion of the FlatZinc model preceding the constraint section, including
	 * predicate, parameter, and variable declarations.
	 */
	private final StringBuilder firstModelPart;

	/**
	 * Mapping between a variable name and the constraint annotated with
	 * {@code defines_var} that defines it.
	 */
	private final Map<String, String> variableConstraintAssociation;

	/**
	 * Constraints that are not associated with a variable through a
	 * {@code defines_var} annotation.
	 */
	private final List<String> otherConstraints;

	/**
	 * The solve item of the FlatZinc model.
	 */
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
	 * Returns all constraints extracted from the model.
	 * <p>
	 * A {@link List} is used instead of a {@link java.util.Set} to avoid the
	 * overhead of duplicate elimination on potentially large FlatZinc models.
	 *
	 * @return all extracted constraints, or an empty list if the visitor has
	 *             not processed any constraint items
	 */
	public List<String> getConstraints() {
		return Stream.concat(
			variableConstraintAssociation.values().stream(),
			otherConstraints.stream()).toList();
	}

	/**
	 * Returns the portion of the FlatZinc model that appears before the first
	 * constraint declaration.
	 * <p>
	 * This typically includes predicate declarations, parameter declarations,
	 * and variable declarations.
	 *
	 * @return the model preamble as FlatZinc source code
	 */
	public String getAboveConstraints() {
		return firstModelPart.toString();
	}
}
