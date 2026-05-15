package me.simonegazza.lift.assumptions;

import java.util.List;

/**
 * Immutable container used to transfer information between the Lifter and the
 * {@link Assumer}.
 * <p>
 * Represents a specific assumption instance that must be revoked, identified by
 * its original parameter name and its position inside a possibly
 * multidimensional structure.
 * </p>
 *
 * @param name    the original name of the assumption to revoke
 * @param indices the list of indices identifying the exact position of this
 *                    parameter within its lifted structure; may be empty but
 *                    must never be null
 */
public record RevokedAssumption(String name, List<Integer> indices) implements Comparable<RevokedAssumption> {

	@Override
	public int compareTo(RevokedAssumption other) {
		int namesCompare = name.compareTo(other.name);
		if (namesCompare != 0) {
			return namesCompare;
		}

		if (indices.size() != other.indices.size()) {
			throw new IllegalStateException(
				"Two assumptions that have the same name have a different number of indices: " + this + " and "
					+ other);
		}

		for (int i = 0; i < indices.size(); i++) {
			int thisInteger = indices.get(i);
			int otherInteger = other.indices.get(i);
			if (thisInteger != otherInteger) {
				Integer.compare(thisInteger, otherInteger);
			}
		}

		return 0;
	}

}
