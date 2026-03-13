package me.simonegazza.lift.types;

public enum MiniZincBasicType implements MiniZincType {
	INT,
	FLOAT,
	STRING,
	BOOL;

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public String getName() {
		return name();
	}
}
