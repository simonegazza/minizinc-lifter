package me.simonegazza.lifting.parameter;

public class SimpleParameter extends Parameter {

	public SimpleParameter(String name, String type) {
		super(name, type);
	}

	@Override
	public String getDiff() {
		return "abs(" + name + " - " + getLiftedName() + ")";
	}

	@Override
	public String getInitialDeclaration() {
		return type + ": " + name + ";";
	}
}
