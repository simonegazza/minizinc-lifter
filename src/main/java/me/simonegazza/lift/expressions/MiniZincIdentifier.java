package me.simonegazza.lift.expressions;

import me.simonegazza.lift.types.MiniZincType;

public class MiniZincIdentifier implements MiniZincType {
	private final String name;

	public MiniZincIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
