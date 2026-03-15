package me.simonegazza.lift.types;

import java.util.Optional;

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

	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return "var " + name();
		else
			return "var " + bounding.get();
	}
}
