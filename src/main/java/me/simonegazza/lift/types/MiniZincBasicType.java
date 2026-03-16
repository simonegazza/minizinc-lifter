package me.simonegazza.lift.types;

import java.util.Optional;

/**
 * Represents primitive MiniZinc types.
 * <p>
 * This includes:
 * <ul>
 * <li>int</li>
 * <li>float</li>
 * <li>string</li>
 * <li>bool</li>
 * </ul>
 * <p>
 * During lifting, these types are converted into decision variables by
 * prefixing them with {@code var}, optionally using user-provided bounds.
 */
public enum MiniZincBasicType implements MiniZincType {
	INT,
	FLOAT,
	STRING,
	BOOL;

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	/**
	 * @return the enum name (uppercase)
	 */
	public String getName() {
		return name();
	}

	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return "var " + toString();
		else
			return "var " + bounding.get();
	}
}
