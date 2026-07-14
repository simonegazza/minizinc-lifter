package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

/**
 * Represents a MiniZinc array type.
 * <p>
 * Example:
 *
 * <pre>
 * array[1..n] of int
 * </pre>
 * <p>
 * The type consists of:
 * <ul>
 * <li>A list of dimension expressions</li>
 * <li>An inner element type</li>
 * </ul>
 * <p>
 * Dependencies may arise from:
 * <ul>
 * <li>Dimension expressions</li>
 * <li>The inner type</li>
 * </ul>
 */
public class MiniZincArrayType extends MiniZincCompositeType {
	/**
	 * Dimension definitions (ranges or expressions).
	 */
	private final List<MiniZincType> dimensions;

	public MiniZincArrayType(
		List<MiniZincType> dimensions,
		MiniZincType innerType) {

		super(innerType);
		this.dimensions = dimensions;
	}

	/**
	 * @return the list of dimensions
	 */
	public List<MiniZincType> getDimensions() {
		return dimensions;
	}

	@Override
	public List<MiniZincIdentifier> getSubtypesIdentifier() {
		List<MiniZincIdentifier> ids = new ArrayList<>();

		List<MiniZincType> toScan = new ArrayList<>(dimensions);
		toScan.add(subtype);
		for (MiniZincType t : toScan) {
			if (t instanceof MiniZincExpressionType et)
				ids.addAll(et.getIdentifiers());
			else if (t instanceof MiniZincCompositeType ct)
				ids.addAll(ct.getSubtypesIdentifier());
			else if (t instanceof MiniZincIdentifier it)
				ids.add(it);
			else {
				if (!(t instanceof MiniZincBasicType))
					throw new IllegalStateException("Unkown Minizinc subtype: " + t);
			}
		}
		return ids;
	}

	/**
	 * Returns the textual representation of the array dimensions.
	 *
	 * @param hided whether to return a list of "int" or the actual types
	 *
	 * @return a list of strings for the dimensions of this type
	 */
	public List<String> getDimensionsString(boolean hided) {
		return dimensions.stream()
			.map(d -> {
				if (hided)
					return "int";
				else if (d instanceof MiniZincIdentifier i)
					return i.getName();
				else if (d instanceof MiniZincBasicType bt)
					return bt.toString();
				else if (d instanceof MiniZincExpressionType et)
					return et.toString();
				else
					// Should not be possible to use a MiniZincSetType as a
					// dimension
					throw new IllegalStateException();
			}).toList();
	}

	/**
	 * Produces the original declaration of this type.
	 * <p>
	 * If {@code var} is true, it hides the expressions in the dimension
	 * definition with an "int"
	 *
	 * @param hided whether to hide the dimensions of this type
	 *
	 * @return the MiniZinc Type declaration fragment
	 */
	public String toString(boolean hided) {
		String ranges = getDimensionsString(hided).stream()
			.collect(Collectors.joining(", "));

		return "array[" + ranges + "] of " + subtype.toString();
	}

	@Override
	public String toString() {
		return toString(false);
	}

	/**
	 * Produces the lifted version of this type.
	 * <p>
	 * Converts the type into a {@code var} declaration, optionally constrained
	 * by bounds. When {@code var} is true, it hides the expressions in the
	 * dimension definition with an "int"
	 *
	 * @param bounding optional bounds provided by the user
	 * @param hided    whether to hide the dimensions of this type
	 *
	 * @return the MiniZinc Type declaration fragment for the lift
	 */
	public String lift(Optional<String> bounding, boolean hided) {
		return "array["
			+ getDimensionsString(hided).stream().collect(Collectors.joining(", "))
			+ "] of "
			+ subtype.lift(bounding);
	}

	@Override
	public String lift(Optional<String> bounding) {
		return lift(bounding, false);
	}

}
