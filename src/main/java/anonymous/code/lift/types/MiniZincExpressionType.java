package anonymous.code.lift.types;

import anonymous.code.lift.Main;
import anonymous.code.lift.expressions.MiniZincIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Represents a type defined by an expression.
 * <p>
 * This class does not attempt to semantically interpret the expression.
 * Instead, it:
 * <ul>
 * <li>Stores the original textual representation</li>
 * <li>Tracks identifiers used inside the expression</li>
 * </ul>
 * <p>
 * This is mainly used for dependency tracking without requiring a full
 * expression evaluation system.
 */
public class MiniZincExpressionType implements MiniZincType {

	/**
	 * Original textual representation of the expression.
	 */
	private final String expression;

	/**
	 * Original textual representation of the expression.
	 */
	private List<MiniZincIdentifier> ids;

	public MiniZincExpressionType(ParserRuleContext ctx) {
		this.expression = Main.getFullText(ctx);
		this.ids = new ArrayList<>();
	}

	/**
	 * Registers an identifier used inside the expression.
	 *
	 * @param i the {@link MiniZincIdentifier} to add for this expression
	 */
	public void addIdentifier(MiniZincIdentifier i) {
		this.ids.add(i);
	}

	/**
	 * @return identifiers referenced in the expression
	 */
	public List<MiniZincIdentifier> getIdentifiers() {
		return this.ids;
	}

	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return "var " + toString();
		else
			return "var " + bounding.get();
	}

	@Override
	public String toString() {
		return expression;
	}
}
