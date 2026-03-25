package me.simonegazza.lift.requests;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import me.simonegazza.lift.utils.exception.UnimplementedException;

/**
 * Lift request targeting a specific element of an array parameter.
 * <p>
 * This allows more fine-grained lifting when working with indexed parameters.
 * <p>
 * Example:
 *
 * <pre>
 * arr[3]
 * </pre>
 */
public class ArrayElementLiftRequest implements LiftRequest {
	/**
	 * Name of the array parameter.
	 */
	private final String name;

	/**
	 * Optional bounds provided by the user.
	 */
	private final Optional<String> bounds;

	/**
	 * Adjusted and transformed indexes for the target array.
	 */
	private List<Integer> locationsAdjusted;

	/**
	 * Indexes of the targeted element. This is a list of String because a
	 * location into an array can also be a value of a minizinc enum
	 */
	private final List<String> originalLocations;

	/**
	 * Creates an array element lift request.
	 *
	 * @param name      the parameter name
	 * @param bounds    optional bounds
	 * @param locations index of the element to lift
	 */
	public ArrayElementLiftRequest(
		String name,
		Optional<String> bounds,
		List<String> locations) {

		this.name = name;
		this.bounds = bounds;
		this.originalLocations = locations;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<String> getBounds() {
		return bounds;
	}

	/**
	 * Returns the original index representation as it appears in the MiniZinc
	 * model.
	 * <p>
	 * This is a textual representation of the array access (e.g.
	 * {@code [1,2]}), preserving the original indexing convention used in the
	 * source model.
	 * <p>
	 * It is used when generating MiniZinc code (e.g. in constraints and
	 * objective functions), ensuring consistency with the original model
	 * syntax.
	 *
	 * @return list of string indices representing the original array access
	 */
	public List<String> getOriginalLocations() {
		return originalLocations;
	}

	/**
	 * Compute and returns the zero-based indices used internally to access the
	 * array.
	 * <p>
	 * These indices are adjusted to match Java's indexing convention and are
	 * intended for navigating the parsed multi-dimensional array structure.
	 * <p>
	 * This method is used during the transformation phase to locate and modify
	 * specific elements inside nested list representations.
	 *
	 * @param environment the environment used to evaluate index keys that are
	 *                        not integers
	 *
	 * @return list of zero-based indices representing the target element
	 *             position
	 */
	public List<Integer> getLocationsAdjusted(Map<String, Object> environment) {
		if (locationsAdjusted == null)
			locationsAdjusted = originalLocations.stream().map(l -> {
				if (environment.containsKey(l))
					return ((Integer) environment.get(l)) - 1;
				else
					try {
						// MiniZinc arrays are 1-based, hence why the - 1
						return Integer.parseInt(l) - 1;
					} catch (Exception e) {
						throw new UnimplementedException("Unimplemented type of index for lift " + name);
					}
			}).toList();

		return locationsAdjusted;
	}

}
