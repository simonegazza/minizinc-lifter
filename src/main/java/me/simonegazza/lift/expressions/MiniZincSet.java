package me.simonegazza.lift.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a MiniZinc Set.
 * <p>
 * In MiniZinc, sets are actually lists (in fact, you can index them with
 * integers). But they have also other special operators that are used to
 * compute over them.
 */
public class MiniZincSet {

	/**
	 * Internal data.
	 */
	private final List<?> data;

	public MiniZincSet(Collection<?> data) {
		this.data = List.copyOf(new HashSet<>(data));
	}

	public Integer lowerBound() {
		return (Integer) data.getFirst();
	}

	public Integer upperBound() {
		return (Integer) data.getLast();
	}

	public int size() {
		return data.size();
	}

	public MiniZincSet union(MiniZincSet other) {
		List<Object> newData = new ArrayList<>(data);
		newData.addAll(other.data);
		return new MiniZincSet(newData);
	}

	public MiniZincSet diff(MiniZincSet other) {
		List<?> newData = new ArrayList<>(data);
		newData.removeAll(other.data);
		return new MiniZincSet(newData);
	}

	public MiniZincSet intersect(MiniZincSet other) {
		List<?> newData = new ArrayList<>(data);
		newData.retainAll(other.data);
		return new MiniZincSet(newData);
	}

	public MiniZincSet symdiff(MiniZincSet other) {
		List<Object> a = new ArrayList<>(data);
		List<Object> b = new ArrayList<>(other.data);
		List<Object> result = new ArrayList<>(data);

		result.removeAll(b);
		b.removeAll(a);
		result.addAll(b);

		return new MiniZincSet(result);
	}

	public boolean contains(Object other) {
		return data.contains(other);
	}

	public boolean subset(MiniZincSet other) {
		return other.data.containsAll(data);
	}

	/**
	 * Please note that this implementations of equals tries equality also with
	 * {@link MiniZincArray}.
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

	public Object[] toArray() {
		return data.toArray();
	}

}
