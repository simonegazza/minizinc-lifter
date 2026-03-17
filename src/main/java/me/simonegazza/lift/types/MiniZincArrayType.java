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

		if (subtype instanceof MiniZincExpressionType)
			ids.addAll(((MiniZincExpressionType) subtype).getIdentifiers());
		else if (subtype instanceof MiniZincCompositeType)
			ids.addAll(((MiniZincCompositeType) subtype).getSubtypesIdentifier());
		else {
			if (!(subtype instanceof MiniZincBasicType))
				throw new IllegalStateException("Impossible subtype of a non-basic type");
		}
		return ids;
	}

	/**
	 * Returns the textual representation of dimensions.
	 *
	 * @return a list of strings for the dimensions of this type
	 */
	public List<String> getDimensionsString() {
		return dimensions.stream()
			.map(d -> {
				if (d instanceof MiniZincIdentifier i) {
					return i.getName();
				} else if (d instanceof MiniZincBasicType bt) {
					return bt.toString();
				} else if (d instanceof MiniZincExpressionType et) {
					return et.toString();
				} else
					// Should not be possible to use a MiniZincSetType as a
					// dimension
					throw new IllegalStateException();
			}).toList();
	}

	@Override
	public String toString() {
		String ranges = getDimensionsString().stream()
			.collect(Collectors.joining(", "));

		return "array[" + ranges + "] of " + subtype.toString();
	}

	/**
	 * Produces the lifted version of the array type.
	 * <p>
	 * The array structure is preserved, but the inner type is lifted.
	 */
	@Override
	public String lift(Optional<String> bounding) {
		return "array["
			+ getDimensionsString().stream().collect(Collectors.joining(", "))
			+ "] of "
			+ subtype.lift(bounding);
	}

}
