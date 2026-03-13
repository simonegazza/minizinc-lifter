package me.simonegazza.lift.utils.exception;

public class UnimplementedException extends IllegalStateException {
	private static final long serialVersionUID = 4146653963309713851L;

	public UnimplementedException(String error) {
		super(error);
	}

}
