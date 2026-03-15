package me.simonegazza.lift.parameters;

import me.simonegazza.lift.types.MiniZincType;
import org.antlr.v4.runtime.ParserRuleContext;

public class OriginalParameter {
	private final String name;
	private final MiniZincType type;
	private ParserRuleContext value;

	public OriginalParameter(MiniZincType type, String name) {
		this.type = type;
		this.name = name;
	}

	public void setValue(ParserRuleContext expr) {
		this.value = expr;
	}

	public MiniZincType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDeclaration() {
		return type.toString() + ": " + name + " = " + value.getText();
	}

	public ParserRuleContext getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OriginalParameter)
			return name.equals(((OriginalParameter) obj).name);
		else
			return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
