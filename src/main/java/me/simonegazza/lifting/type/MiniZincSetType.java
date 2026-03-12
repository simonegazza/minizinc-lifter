package me.simonegazza.lifting.type;

import java.util.List;

public class MiniZincSetType extends MiniZincCompositeType {

	public MiniZincSetType(List<MiniZincNamedType> ids) {
		super(ids);
	}

	@Override
	public List<MiniZincNamedType> getSubtypesIdentifiers() {
		return super.ids;
	}

}
