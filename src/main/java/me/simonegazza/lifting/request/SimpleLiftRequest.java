package me.simonegazza.lifting.request;

import java.util.Optional;

public class SimpleLiftRequest implements LiftRequest {
	private final String name;
	private final Optional<String> bounds;

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
