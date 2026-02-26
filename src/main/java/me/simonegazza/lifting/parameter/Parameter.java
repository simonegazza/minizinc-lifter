package me.simonegazza.lifting.parameter;

public abstract class Parameter {

	public static String getLiftedName(String name) {
		return name + "_lifted";
	}

	String name;
	String type;

	public Parameter(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getLiftedName() {
		return Parameter.getLiftedName(name);
	}

	public abstract String getDiff();

	public abstract String getInitialDeclaration();

}
