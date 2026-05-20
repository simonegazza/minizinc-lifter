package me.simonegazza.lift.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a MiniZinc (multi-dimensional) array.
 */
public class MiniZincArray {

	/**
	 * Reconstructs a multi-dimensional array (nested {@link List}) from a
	 * flattened list according to the specified dimensions.
	 * <p>
	 * The {@code dimensions} parameter defines the shape of the resulting
	 * structure, where each element represents the size of a dimension. The
	 * {@code flattened} list is consumed in order to populate the resulting
	 * nested lists.
	 * <p>
	 * Through {@code reformatArrayRecursive}, it builds the nested list
	 * structure level by level, consuming elements from the flattened list as
	 * it reaches the innermost dimension.
	 *
	 * @param dimensions the sizes of each dimension
	 * @param flattened  the flat list of elements to distribute into the
	 *                       structure
	 *
	 * @return a nested {@link List} representing the multi-dimensional array
	 */
	private static List<Object> reformatArray(
		List<Integer> dimensions,
		List<Object> flattened) {
		return reformatArrayRecursive(dimensions, 0, flattened, new int[] { 0 });
	}

	/**
	 * Recursive helper method for {@link #reformatArray(List, List)}.
	 *
	 * @param dimensions the full list of dimensions
	 * @param dimIndex   the current dimension being processed
	 * @param flattened  the source list of elements
	 * @param index      a single-element array used as a mutable pointer into
	 *                       {@code flattened}
	 *
	 * @return a nested list representing the current dimension
	 */
	private static List<Object> reformatArrayRecursive(
		List<Integer> dimensions,
		int dimIndex,
		List<Object> flattened,
		int[] index) {

		int size = dimensions.get(dimIndex);

		if (dimIndex == dimensions.size() - 1) {
			// Last dimension
			return IntStream.range(0, size)
				.mapToObj(_ -> flattened.get(index[0]++))
				.collect(Collectors.toList());
		}

		return IntStream.range(0, size)
			.mapToObj(_ -> reformatArrayRecursive(dimensions, dimIndex + 1, flattened, index))
			.collect(Collectors.toList());
	}

	/**
	 * Construct the array based on a flattened representation.
	 *
	 * @param dimensions the dimensions of this representation
	 * @param flattened  the flattened representation
	 *
	 * @return the structured output array
	 */
	public static MiniZincArray fromFlattened(List<IndexRange> dimensions, List<Object> flattened) {
		int expectedSize = dimensions.stream()
			.mapToInt(IndexRange::size)
			.reduce(1, Math::multiplyExact);
		if (flattened.size() != expectedSize) {
			throw new IllegalArgumentException("Flattened size mismatch. Expected "
				+ expectedSize
				+ " but found "
				+ flattened.size());
		}

		return new MiniZincArray(
			dimensions,
			reformatArray(
				dimensions.stream().map(IndexRange::size).toList(),
				flattened));
	}

	/**
	 * Convenience method for {@link #fromFlattened(List<IndexRange>,
	 * List<Object>)}.
	 *
	 * @param dimensions the dimensions of this representation
	 * @param flattened  the flattened representation
	 *
	 * @return the structured output array
	 */
	public static MiniZincArray fromFlattened(List<IndexRange> dimensions, MiniZincArray flattened) {
		if (flattened.getDimensions().size() > 1) {
			throw new IllegalStateException("Flattened parameter was not actually flattened!");
		}

		return fromFlattened(dimensions, flattened.data);
	}

	/**
	 * Recursively flattens a (potentially) multi-dimensional list structure
	 * into a single list.
	 * <p>
	 * If the input object is a {@link List}, all of its elements are
	 * recursively traversed and collected into a flat list. Otherwise, the
	 * object itself is treated as a leaf element and added to the result.
	 *
	 * @param obj the object to flatten; may be a nested {@link List} or a
	 *                single value
	 *
	 * @return a flat {@link List} containing all leaf elements in traversal
	 *             order
	 */
	public static List<Object> flatten(Object obj) {
		List<Object> result = new ArrayList<>();

		if (obj instanceof List<?> objList) {
			for (Object item : objList) {
				result.addAll(flatten(item));
			}
		} else {
			result.add(obj);
		}

		return result;
	}

	/**
	 * Represents a MiniZinc index range.
	 * <p>
	 * Index can be also negative, but they must be an integer
	 */
	public static final class IndexRange {

		/**
		 * Lower bound of this range.
		 */
		private final int lowerBound;
		/**
		 * Upper bound of this range.
		 */
		private final int upperBound;

		public IndexRange(int lowerBound, int upperBound) {
			if (upperBound < lowerBound) {
				throw new IllegalArgumentException(
					"Upper bound must be >= lower bound");
			}
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
		}

		public IndexRange(List<?> range) {
			lowerBound = 1;
			upperBound = range.size();
		}

		/**
		 * Converts a MiniZinc index into the corresponding Java index.
		 *
		 * @param miniZincIndex the MiniZinc index
		 *
		 * @return the zero-based Java index
		 */
		public int toJavaIndex(int miniZincIndex) {
			if (miniZincIndex < lowerBound || miniZincIndex > upperBound) {
				throw new IndexOutOfBoundsException(
					"Index " + miniZincIndex
						+ " outside range "
						+ lowerBound + ".." + upperBound);
			}

			return miniZincIndex - lowerBound;
		}

		/**
		 * Computes and returns the size of this index.
		 *
		 * @return the size of this index
		 */
		public int size() {
			return upperBound - lowerBound + 1;
		}

		/**
		 * Provides an array over this IndexRange.
		 *
		 * @return the iterator
		 */
		public int[] toArray() {
			return IntStream.range(lowerBound, upperBound + 1).toArray();
		}
	}

	/**
	 * The dimensions for this array.
	 */
	private final List<IndexRange> dimensions;
	/**
	 * The actual (multidimensional) data.
	 */
	private final List<Object> data;

	/**
	 * Construct the array based on a flattened representation
	 *
	 * @param dimensions the dimensions of this representation
	 * @param data       the flattened representation
	 */
	public MiniZincArray(List<IndexRange> dimensions, List<Object> data) {
		this.dimensions = dimensions;
		this.data = data;
	}

	/**
	 * Equality between arrays or {@link MiniZincSet}.
	 * <p>
	 * Note that we evaluate equality also with {@link MiniZincSet} since
	 * MiniZinc allows it. Please note that final equality between an array or a
	 * set is delegated to the implementation of {@link MiniZincSet}.
	 */
	@Override
	@SuppressWarnings("unlikely-arg-type")
	public boolean equals(Object other) {
		if (other instanceof MiniZincArray oa) {
			return data.equals(oa.data);

		} else if (other instanceof MiniZincSet os) {
			if (data.get(0) instanceof List<?>) {
				return false;
			}

			return equals(os);
		}

		return false;
	}

	/**
	 * Returns an element using MiniZinc indices.
	 *
	 * @param indices the MiniZinc indices
	 *
	 * @throws IllegalArgumentException  if the number of provided indices does
	 *                                       not match the number of dimensions
	 * @throws IndexOutOfBoundsException if at least one index falls outside its
	 *                                       corresponding MiniZinc range
	 *
	 * @return the corresponding element
	 */
	public Object getMiniZinc(int... indices) {

		if (indices.length != dimensions.size()) {
			throw new IllegalArgumentException(
				"Expected " + dimensions.size()
					+ " indices but found "
					+ indices.length);
		}

		Object current = data;

		// Dimensions already checked before.
		for (int i = 0; i < indices.length; i++) {
			int miniZincIndex = indices[i];
			IndexRange idxRange = dimensions.get(i);
			current = ((List<?>) current).get(idxRange.toJavaIndex(miniZincIndex));
		}

		return current;
	}

	/**
	 * Convenience method for {@link #getMiniZinc(int...)}.
	 *
	 * @param locations a list of locations
	 *
	 * @throws IllegalArgumentException  if the number of provided indices does
	 *                                       not match the number of dimensions
	 * @throws IndexOutOfBoundsException if at least one index falls outside its
	 *                                       corresponding MiniZinc range
	 *
	 * @return the object in that location
	 */
	public Object getMiniZinc(List<Integer> locations) {
		return this.getMiniZinc(locations.stream()
			.mapToInt(Integer::intValue)
			.toArray());
	}

	/**
	 * Flattens the array.
	 *
	 * @return the flattened representation
	 */
	public List<Object> flatten() {
		return MiniZincArray.flatten(data);
	}

	/**
	 * Get the dimensions.
	 *
	 * @return the dimensions
	 */
	public List<IndexRange> getDimensions() {
		return dimensions;
	}

	/**
	 * Concats two MiniZinc array of dimensions 1.
	 *
	 * @param other the other array to be concatted
	 *
	 * @return a new concatted array
	 */
	public MiniZincArray concat(MiniZincArray other) {
		if (getDimensions().size() > 1 || other.getDimensions().size() > 1) {
			throw new IllegalStateException("MiniZincArray '++' operator on array of dimensions greater than one");
		}

		List<Object> newData = new ArrayList<>(data);
		newData.addAll(other.data);

		int upperIndex = getDimensions().get(0).size() + other.getDimensions().get(0).size();

		return new MiniZincArray(List.of(new IndexRange(0, upperIndex)), newData);
	}

}
