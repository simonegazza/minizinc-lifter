package me.simonegazza.lift.utils.exception;

/**
 * Exception thrown to signal that a piece of functionality has not been
 * implemented yet.
 * <p>
 * This is typically used as a placeholder during development to mark code paths
 * that are expected to be completed in the future but are currently
 * unsupported.
 * <p>
 * It extends {@link IllegalStateException} because it represents a situation
 * where the program reaches a state that should not occur in a fully
 * implemented system.
 * <p>
 * Unlike generic exceptions, this class makes the intent explicit: the failure
 * is not due to invalid input or runtime conditions, but to missing
 * implementation.
 */
public class UnimplementedException extends IllegalStateException {

	private static final long serialVersionUID = 4146653963309713851L;

	/**
	 * Creates a new exception with a descriptive error message.
	 *
	 * @param error explanation of the missing functionality
	 */
	public UnimplementedException(String error) {
		super(error);
	}

}
