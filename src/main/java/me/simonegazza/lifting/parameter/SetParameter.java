package me.simonegazza.lifting.parameter;

import java.util.Optional;

public class SetParameter extends Parameter {
	private final String bounds;

	public SetParameter(String name, String type, Optional<String> bounds) {
		super(name, type, bounds);
		if (bounds.isEmpty())
			throw new IllegalArgumentException("No bounds provided for set parameter " + name);
		this.bounds = bounds.get();

	}

	@Override
	public String getDiff() {
		return "max([abs(min(" + name + ") - max(" +
			getLiftedName() + ")), abs(min(" + getLiftedName() +
			") - max(" + name + "))])";
	}

	@Override
	public String getInitialDeclaration() {
		return "set of " + type + ": " + name + ";";
	}

	@Override
	public String getLiftedDeclaration() {
		return "var set of " + bounds + ": " + getLiftedName();
	}

}
