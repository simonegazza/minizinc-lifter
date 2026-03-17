package me.simonegazza.lift.requests;

import java.util.List;
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
	private final List<Integer> locationsAdjusted;

	/**
	 * Indexes of the targeted element. This is a list of String because a
	 * location into an array can also be a value of a minizinc enum
	 */
	private final List<String> originalLocations;

	/**
	 * Creates an array element lift request.
	 *
	 * @param name     the parameter name
	 * @param bounds   optional bounds
	 * @param location index of the element to lift
	 */
	public ArrayElementLiftRequest(String name, Optional<String> bounds, List<String> locations) {
		this.name = name;
		this.bounds = bounds;
		this.originalLocations = locations;
		this.locationsAdjusted = locations.stream()
			.map(l -> {
				try {
					// MiniZinc arrays are 1-based, hence why the + 1
					return Integer.parseInt(l) - 1;
				} catch (NumberFormatException e) {
					throw new UnimplementedException("No conversion for enum indices in locations");
				}
			})
			.toList();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<String> getBounds() {
		return bounds;
	}

	public List<Integer> getLocationsAdjusted() {
		return locationsAdjusted;
	}

	public List<String> getOriginalLocations() {
		return originalLocations;
	}

}
