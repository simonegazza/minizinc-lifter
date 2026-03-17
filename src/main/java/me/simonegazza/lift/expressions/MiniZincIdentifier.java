package me.simonegazza.lift.expressions;

import java.util.Optional;
import me.simonegazza.lift.types.MiniZincType;

/**
 * Represents a MiniZinc identifier.
 * <p>
 * This class is used to model named references appearing in a MiniZinc model,
 * such as variable names, parameter names, or identifiers used inside type
 * expressions (e.g. array dimensions).
 * <p>
 * It implements {@link MiniZincType} because identifiers can legally appear in
 * type positions in MiniZinc (for example, when defining array bounds or
 * dependent types). This allows identifiers to seamlessly participate in the
 * type system abstraction used throughout the lifting process.
 * <p>
 * During lifting, identifiers are treated differently from other types:
 * <ul>
 * <li>If no bounds are provided, the identifier is preserved as-is</li>
 * <li>If bounds are provided, the identifier is replaced with the bound
 * value</li>
 * </ul>
 * <p>
 * This behavior is especially relevant when lifting parameters that are used to
 * define domains or constraints indirectly through other declarations.
 */
public class MiniZincIdentifier implements MiniZincType {

	/**
	 * The textual name of the MiniZinc identifier.
	 */
	private final String name;

	/**
	 * Creates a new identifier.
	 *
	 * @param name the textual name of the identifier
	 */
	public MiniZincIdentifier(String name) {
		this.name = name;
	}

	/**
	 * @return the identifier name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the textual representation of the identifier.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Produces the lifted representation of this identifier.
	 * <p>
	 * If bounds are provided, the identifier is replaced by the bound value.
	 * Otherwise, it is left unchanged.
	 *
	 * @param bounding optional bounds
	 *
	 * @return either the original identifier or the provided bound
	 */
	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return name;
		else
			return bounding.get();
	}

}
