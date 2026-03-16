package me.simonegazza.lift.requests;

import java.util.Optional;

/**
 * Basic implementation of {@link LiftRequest}.
 * <p>
 * Represents a request to lift a full parameter (entire arrays or sets or
 * atomic variables).
 */
public class SimpleLiftRequest implements LiftRequest {

	/**
	 * Name of the parameter to lift.
	 */
	private final String name;

	/**
	 * Optional bounds provided by the user.
	 */
	private final Optional<String> bounds;

	/**
	 * Creates a simple lift request.
	 *
	 * @param name   the parameter name
	 * @param bounds optional bounds
	 */
	public SimpleLiftRequest(String name, Optional<String> bounds) {
		this.name = name;
		this.bounds = bounds;
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
