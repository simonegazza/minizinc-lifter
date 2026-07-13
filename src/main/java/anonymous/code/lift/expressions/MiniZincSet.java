package anonymous.code.lift.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Representation of a MiniZinc set.
 * <p>
 * MiniZinc sets differ slightly from mathematical sets because they indexable.
 * <p>
 * These objects support dedicated set operators such as union, difference, and
 * intersection.
 * <p>
 * Internally, this implementation stores the set as a deduplicated
 * {@link List}. Duplicate elements are removed during construction.
 * <p>
 * Equality follows MiniZinc semantics and is supported both against other
 * {@link MiniZincSet} instances and against one-dimensional
 * {@link MiniZincArray} objects.
 */
public class MiniZincSet {

	/**
	 * Internal representation.
	 */
	private final List<?> data;

	public MiniZincSet(Collection<?> data) {
		this.data = List.copyOf(new HashSet<>(data));
	}

	/**
	 * Returns the first (lowest) element of this set.
	 * <p>
	 * This method is mainly intended for sets representing integer intervals or
	 * ordered domains.
	 *
	 * @return the first element
	 *
	 * @throws ClassCastException if the stored elements are not integers
	 */
	public Integer lowerBound() {
		return (Integer) data.getFirst();
	}

	/**
	 * Returns the last (highest) element of this set.
	 * <p>
	 * This method is mainly intended for sets representing integer intervals or
	 * ordered domains.
	 *
	 * @return the last element
	 *
	 * @throws ClassCastException if the stored elements are not integers
	 */
	public Integer upperBound() {
		return (Integer) data.getLast();
	}

	/**
	 * Returns the number of elements contained in this set.
	 *
	 * @return the set cardinality
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Computes the union of this set and another set.
	 * <p>
	 * The resulting set contains all elements that appear in either operand.
	 *
	 * @param other the other set
	 *
	 * @return the union set
	 */
	public MiniZincSet union(MiniZincSet other) {
		List<Object> newData = new ArrayList<>(data);
		newData.addAll(other.data);
		return new MiniZincSet(newData);
	}

	/**
	 * Computes the set difference between this set and another set.
	 * <p>
	 * The resulting set contains only elements that belong to this set but not
	 * to {@code other}.
	 *
	 * @param other the set to subtract
	 *
	 * @return the difference set
	 */
	public MiniZincSet diff(MiniZincSet other) {
		List<?> newData = new ArrayList<>(data);
		newData.removeAll(other.data);
		return new MiniZincSet(newData);
	}

	/**
	 * Computes the intersection of this set and another set.
	 * <p>
	 * The resulting set contains only elements shared by both sets.
	 *
	 * @param other the other set
	 *
	 * @return the intersection set
	 */
	public MiniZincSet intersect(MiniZincSet other) {
		List<?> newData = new ArrayList<>(data);
		newData.retainAll(other.data);
		return new MiniZincSet(newData);
	}

	/**
	 * Computes the symmetric difference between this set and another set.
	 * <p>
	 * The resulting set contains elements that belong to exactly one of the two
	 * sets.
	 *
	 * @param other the other set
	 *
	 * @return the symmetric difference set
	 */
	public MiniZincSet symdiff(MiniZincSet other) {
		List<Object> a = new ArrayList<>(data);
		List<Object> b = new ArrayList<>(other.data);
		List<Object> result = new ArrayList<>(data);

		result.removeAll(b);
		b.removeAll(a);
		result.addAll(b);

		return new MiniZincSet(result);
	}

	/**
	 * Checks whether this set contains the specified value.
	 *
	 * @param other the value to test
	 *
	 * @return {@code true} if the value is contained in the set
	 */
	public boolean contains(Object other) {
		return data.contains(other);
	}

	/**
	 * Checks whether this set is a subset of another set.
	 * <p>
	 * A set {@code A} is considered a subset of {@code B} if every element of
	 * {@code A} also belongs to {@code B}.
	 *
	 * @param other the candidate superset
	 *
	 * @return {@code true} if this set is a subset of {@code other}
	 */
	public boolean subset(MiniZincSet other) {
		return other.data.containsAll(data);
	}

	/**
	 * Compares this set with another object using MiniZinc equality semantics.
	 * <p>
	 * Equality is supported against:
	 * <ul>
	 * <li>other {@link MiniZincSet} instances</li>
	 * <li>one-dimensional {@link MiniZincArray} objects</li>
	 * </ul>
	 * <p>
	 * In both cases, they are considered equal if they contain exactly the same
	 * elements, independently of ordering.
	 *
	 * @param other the object to compare against
	 *
	 * @return {@code true} if the objects are considered equal
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof MiniZincArray oa) {
			if (oa.getDimensions().size() == 1) {
				boolean result = true;

				for (Integer i : oa.getDimensions().get(0).toArray()) {
					if (!data.contains(oa.getMiniZinc(i))) {
						result = false;
					}
				}

				return result;
			}
			return false;
		} else if (other instanceof MiniZincSet os) {
			return os.data.size() == data.size()
				&&
				os.data.stream().allMatch(data::contains)
				&&
				data.stream().allMatch(os.data::contains);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}

	/**
	 * Returns the contents of this set as an array.
	 *
	 * @return the set elements as an array
	 */
	public Object[] toArray() {
		return data.toArray();
	}

}
