package me.simonegazza.lift.types;

import java.util.ArrayList;
import java.util.List;

public class MiniZincArrayType extends MiniZincCompositeType {
	/**
	 * String representation of the expression that compose the type ranges
	 */
	private final List<String> ranges;
	private final MiniZincType innerType;

	public MiniZincArrayType(
		List<MiniZincNamedType> subtypesIdentifiers,
		List<String> ranges,
		MiniZincType innerType) {

		super(subtypesIdentifiers);
		this.ranges = ranges;
		this.innerType = innerType;
	}

	public List<String> getRanges() {
		return ranges;
	}

	@Override
	public List<MiniZincNamedType> getSubtypesIdentifiers() {
		List<MiniZincNamedType> ids = new ArrayList<>(super.ids);

		if (innerType instanceof MiniZincCompositeType) {
			ids.addAll(((MiniZincCompositeType) innerType).getSubtypesIdentifiers());
		} else if (innerType instanceof MiniZincNamedType)
			ids.add((MiniZincNamedType) innerType);
		// Do not need to do anything for the basic types

		return ids;
	}

}
