package me.simonegazza.lifting.parameter;

import java.util.Optional;

public class SimpleParameter extends Parameter {

	public SimpleParameter(String name, String type, Optional<String> bounds) {
		super(name, type, bounds);
	}

	@Override
	public String getDiff() {
		return "abs(" + name + " - " + getLiftedName() + ")";
	}

	@Override
	public String getInitialDeclaration() {
		return type + ": " + name + ";";
	}

	@Override
	public String getLiftedDeclaration() {
		return "var " + (bounds.isEmpty() ? type : bounds.get()) + ": " + getLiftedName();
	}
}
