package me.simonegazza.lift.requests;

import java.util.Optional;

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
	@SuppressWarnings("unused")

	/**
	 * Index of the targeted element.
	 */
	private final int location;

	/**
	 * Creates an array element lift request.
	 *
	 * @param name     the parameter name
	 * @param bounds   optional bounds
	 * @param location index of the element to lift
	 */
	public ArrayElementLiftRequest(String name, Optional<String> bounds, int location) {
		this.name = name;
		this.bounds = bounds;
		this.location = location;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<String> getBounds() {
		return bounds;
	}

}
