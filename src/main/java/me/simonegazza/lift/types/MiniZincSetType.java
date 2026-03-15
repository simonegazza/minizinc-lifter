package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

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
		if (subtype instanceof MiniZincExpressionType)
			return ((MiniZincExpressionType) subtype).getIdentifiers();
		else {
			if (subtype instanceof MiniZincBasicType)
				return new ArrayList<>();
			else if (subtype instanceof MiniZincIdentifier)
				return List.of((MiniZincIdentifier) subtype);
			else
				throw new IllegalStateException("Impossible subtype of a non-basic type");
		}

	}

}
