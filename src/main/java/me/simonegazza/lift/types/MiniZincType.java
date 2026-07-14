package me.simonegazza.lift.types;

import java.util.Optional;

/**
 * Represents a MiniZinc type abstraction used during parsing and lifting.
 * <p>
 * This interface is implemented by all type representations in the system,
 * including basic types, arrays, sets, and expression-based types.
 * <p>
 * Implementations serve two main purposes:
 * <ul>
 * <li>Provide a textual representation of the type ({@link #toString()})</li>
 * <li>Generate the corresponding lifted declaration
 * ({@link #lift(Optional)})</li>
 * </ul>
 * <p>
 * The {@link #lift(Optional)} method is a key part of the transformation
 * pipeline: it defines how a type is converted into a decision variable
 * declaration, optionally applying user-provided bounds.
 */
public interface MiniZincType {
	/**
	 * Returns the MiniZinc textual representation of the type.
	 * <p>
	 * <b>Implementations must override this method.</b>
	 *
	 * @return the type as it should appear in MiniZinc code
	 */
	public String toString();

	/**
	 * Produces the lifted version of this type.
	 * <p>
	 * This typically means converting the type into a {@code var} declaration,
	 * optionally constrained by bounds.
	 *
	 * @param bounding optional bounds provided by the user
	 *
	 * @return a MiniZinc declaration fragment
	 */
	public String lift(Optional<String> bounding);
}
