package me.simonegazza.lift.types;

import java.util.List;
import me.simonegazza.lift.expressions.MiniZincIdentifier;

public abstract class MiniZincCompositeType implements MiniZincType {
	protected MiniZincType subtype;
	protected List<String> expressions;

	public MiniZincCompositeType(MiniZincType subtype) {
		this.subtype = subtype;
	}

	public abstract List<MiniZincIdentifier> getSubtypesIdentifier();

	public MiniZincType getSubtype() {
		return subtype;
	}

}
