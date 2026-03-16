package me.simonegazza.lift.types;

import java.util.List;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

/**
 * Base class for composite MiniZinc types.
 * <p>
 * Composite types are types that contain other types, such as arrays and sets.
 * <p>
 * This class provides access to the inner type and defines a contract for
 * extracting identifier dependencies from nested structures.
 */
public abstract class MiniZincCompositeType implements MiniZincType {
	/**
	 * The inner (contained) type.
	 */
	protected MiniZincType subtype;

	/**
	 * Constructs a composite type.
	 *
	 * @param subtype the inner type
	 */
	public MiniZincCompositeType(MiniZincType subtype) {
		this.subtype = subtype;
	}

	/**
	 * Returns identifiers used inside this type.
	 * <p>
	 * This is used during parameter analysis to track dependencies introduced
	 * by type definitions (e.g., array dimensions).
	 *
	 * @return list of identifiers referenced in the type
	 */
	public abstract List<MiniZincIdentifier> getSubtypesIdentifier();

	/**
	 * @return the inner type
	 */
	public MiniZincType getSubtype() {
		return subtype;
	}

}
