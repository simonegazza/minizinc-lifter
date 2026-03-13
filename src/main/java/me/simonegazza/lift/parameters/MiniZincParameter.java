package me.simonegazza.lift.parameters;

import me.simonegazza.lift.types.MiniZincType;
import org.antlr.v4.runtime.ParserRuleContext;

public class MiniZincParameter {
	protected final String name;
	protected final MiniZincType type;
	protected ParserRuleContext value;

	public MiniZincParameter(MiniZincType type, String name) {
		this.type = type;
		this.name = name;
	}

	public MiniZincParameter(MiniZincType type, String name, ParserRuleContext value) {
		this.type = type;
		this.name = name;
		this.value = value;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MiniZincParameter)
			return name.equals(((MiniZincParameter) obj).name);
		else
			return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * This remains like this as of know for debugging purposes
	 */
	@Override
	public String toString() {
		return name;
	}

}
