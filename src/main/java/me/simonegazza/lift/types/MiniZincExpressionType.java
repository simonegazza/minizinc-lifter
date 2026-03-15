package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.simonegazza.lift.Main;
import me.simonegazza.lift.expressions.MiniZincIdentifier;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This type has the sole purpose of keeping a list of identifiers. This avoid
 * parsing expressions but makes possible to register which parameter is
 * dependent on another one
 */
public class MiniZincExpressionType implements MiniZincType {
	private final String expression;
	private List<MiniZincIdentifier> ids;

	public MiniZincExpressionType(ParserRuleContext ctx) {
		this.expression = Main.getFullText(ctx);
		this.ids = new ArrayList<>();
	}

	public void addIdentifier(MiniZincIdentifier i) {
		this.ids.add(i);
	}

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
