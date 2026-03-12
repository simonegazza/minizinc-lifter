package me.simonegazza.lifting.type;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

/**
 * This type has the sole purpose of keeping a (running) list of identifiers.
 * This avoid parsing expressions but makes possibile to registre which type
 * (and parameter) is dependent on another parameter
 */
public class MiniZincExpressionType extends MiniZincCompositeType {
	@SuppressWarnings("unused")
	private final String expression;

	public MiniZincExpressionType(String expression) {
		super(new ArrayList<MiniZincNamedType>());
		this.expression = expression;
	}

	public MiniZincExpressionType(ParserRuleContext ctx) {
		super(new ArrayList<MiniZincNamedType>());
		CharStream stream = ctx.start.getInputStream();
		this.expression = stream.getText(new Interval(
			ctx.getStart().getStartIndex(),
			ctx.getStop().getStopIndex()));
	}

	public void addIdentifier(MiniZincNamedType i) {
		this.ids.add(i);
	}

	@Override
	public List<MiniZincNamedType> getSubtypesIdentifiers() {
		return this.ids;
	}

}
