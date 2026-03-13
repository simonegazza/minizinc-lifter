package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

public class MiniZincArrayType extends MiniZincCompositeType {
	/**
	 * String representation of the expression that compose the type ranges
	 */
	private final List<MiniZincType> dimensions;

	public MiniZincArrayType(
		List<MiniZincType> dimensions,
		MiniZincType innerType) {

		super(innerType);
		this.dimensions = dimensions;
	}

	@Override
	public List<MiniZincIdentifier> getSubtypesIdentifier() {
		List<MiniZincIdentifier> ids = new ArrayList<>();

		if (subtype instanceof MiniZincExpressionType)
			ids.addAll(((MiniZincExpressionType) subtype).getIdentifiers());
		else if (subtype instanceof MiniZincSetType)
			ids.addAll(((MiniZincSetType) subtype).getSubtypesIdentifier());
		else {
			if (!(subtype instanceof MiniZincBasicType))
				throw new IllegalStateException("Impossible subtype of a non-basic type");
		}
		return ids;
	}

	@Override
	public String toString() {
		String ranges = dimensions.stream()
			.map(d -> {
				if (d instanceof MiniZincIdentifier) {
					return ((MiniZincIdentifier) d).getName();
				} else if (d instanceof MiniZincBasicType) {
					return ((MiniZincBasicType) d).toString();
				} else if (d instanceof MiniZincExpressionType) {
					return ((MiniZincExpressionType) d).toString();
				} else
					// Should not be possible to use a MiniZincSetType as a
					// dimension
					throw new IllegalStateException();
			})
			.collect(Collectors.joining(", "));

		return "array[" + ranges + "] of " + subtype;
	}

}
