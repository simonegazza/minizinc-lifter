package me.simonegazza.lifting.type;

/**
 * Represents types that are only defined by their names
 */
public abstract class MiniZincNamedType implements MiniZincType {
	private final String name;

	public MiniZincNamedType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MiniZincNamedType)
			return name.equals(((MiniZincNamedType) obj).name);

		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
