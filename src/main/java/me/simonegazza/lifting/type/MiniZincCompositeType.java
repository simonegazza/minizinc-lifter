package me.simonegazza.lifting.type;

import java.util.List;

public abstract class MiniZincCompositeType implements MiniZincType {
	protected List<MiniZincNamedType> ids;

	public MiniZincCompositeType(List<MiniZincNamedType> ids) {
		this.ids = ids;
	}

	/**
	 * Provide the identifiers in the type declaration. For example, given a
	 * type declaration of the form: array[M] of set of N, this method will
	 * return a list of M and N
	 *
	 * @return all the identifiers in the type declaration
	 */
	public abstract List<MiniZincNamedType> getSubtypesIdentifiers();

}
