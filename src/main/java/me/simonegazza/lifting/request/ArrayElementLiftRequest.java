package me.simonegazza.lifting.request;

import java.util.Optional;

public class ArrayElementLiftRequest implements LiftRequest {
	private final String name;
	private final Optional<String> bounds;
	@SuppressWarnings("unused")
	private final int location;

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
