package me.simonegazza.lift.requests;

import java.util.Optional;

/**
 * Represents a user request to lift a parameter.
 * <p>
 * A lift request is created from a CLI string and specifies:
 * <ul>
 * <li>The parameter name</li>
 * <li>Optional bounds</li>
 * <li>Optional targeting information (e.g. array element)</li>
 * </ul>
 * <p>
 * The expected input format is:
 *
 * <pre>
 * parameterName[:bounds]
 * parameterName[index][:bounds]
 * parameterName[*][:bounds]
 * </pre>
 * <p>
 * Examples:
 * <ul>
 * <li>{@code x} transforms into lift parameter {@code x}</li>
 * <li>{@code x:0..10} transforms into lift {@code x} with bounds</li>
 * <li>{@code arr[3]} transforms into lift a specific array element</li>
 * <li>{@code arr[*]} transforms into lift all elements (treated as base
 * parameter)</li>
 * </ul>
 * <p>
 * The {@link #parse(String)} method is responsible for interpreting the CLI
 * string and returning the appropriate implementation.
 */
public interface LiftRequest {
	/**
	 * Delimiter used to separate parameter name and bounds.
	 */
	public final static String BOUNDS_DELIMITER = ":";

	/**
	 * Parses a CLI string into a {@link LiftRequest}.
	 * <p>
	 * This method supports also extra bounding.
	 *
	 * @param p the CLI string representation
	 *
	 * @return a concrete {@link LiftRequest} implementation
	 */
	public static LiftRequest parse(String p) {
		String[] nameAndBound = p.split(BOUNDS_DELIMITER);
		String name = nameAndBound[0];
		Optional<String> bounds = Optional.empty();
		if (nameAndBound.length > 1) {
			bounds = Optional.of(nameAndBound[1]);
		}

		return new SimpleLiftRequest(name, bounds);
	}

	/**
	 * @return the name of the parameter to lift
	 */
	public String getName();

	/**
	 * @return the name of the parameter to lift
	 */
	public Optional<String> getBounds();

}
