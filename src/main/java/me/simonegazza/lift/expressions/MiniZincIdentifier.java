package me.simonegazza.lift.expressions;

import java.util.Optional;
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

	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return name;
		else
			return bounding.get();
	}

}
