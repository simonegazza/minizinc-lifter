package me.simonegazza.lifting.parameter;

import java.util.Optional;

public abstract class Parameter {

	public static String getLiftedName(String name) {
		return name + "_lifted";
	}

	protected final String name;
	protected final String type;
	protected final Optional<String> bounds;

	public Parameter(String name, String type, Optional<String> bounds) {
		this.name = name;
		this.type = type;
		this.bounds = bounds;
	}

	public String getLiftedName() {
		return Parameter.getLiftedName(name);
	}

	public abstract String getDiff();

	public abstract String getInitialDeclaration();

	public abstract String getLiftedDeclaration();

}
