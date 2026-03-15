package me.simonegazza.lift.types;

import java.util.Optional;

public interface MiniZincType {
	/**
	 * Proper subclass must redefine toString
	 *
	 * @return the string mention of this type
	 */
	public String toString();

	public String lift(Optional<String> bounding);
}
