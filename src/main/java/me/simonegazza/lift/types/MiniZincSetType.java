package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

/**
 * Represents a MiniZinc set type (e.g. {@code set of int}).
 * <p>
 * This is a composite type where the subtype defines the element type.
 * <p>
 * During lifting:
 * <ul>
 * <li>Without bounds transforms into {@code var set of <type>}</li>
 * <li>With bounds transforms into {@code var set of <bounds>}</li>
 * </ul>
 */
public class MiniZincSetType extends MiniZincCompositeType {

	public MiniZincSetType(MiniZincType innerType) {
		super(innerType);
	}

	@Override
	public String toString() {
		return "set of " + subtype;
	}

	@Override
	public String lift(Optional<String> bounding) {
		if (bounding.isEmpty())
			return "var " + toString();
		else
			return "var set of " + bounding.get();
	}

	@Override
	public List<MiniZincIdentifier> getSubtypesIdentifier() {
		if (subtype instanceof MiniZincExpressionType et)
			return et.getIdentifiers();
		else if (subtype instanceof MiniZincBasicType)
			return new ArrayList<>();
		else if (subtype instanceof MiniZincIdentifier i)
			return List.of(i);
		else
			throw new IllegalStateException("Impossible subtype of a non-basic type");
	}

}
