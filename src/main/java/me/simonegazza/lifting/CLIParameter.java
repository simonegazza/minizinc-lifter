package me.simonegazza.lifting;

import java.util.Optional;

public record CLIParameter(String name, Optional<String> bounds) {

	public CLIParameter(String name, String bounds) {
		this(name, Optional.of(bounds));
	}

	public CLIParameter(String name) {
		this(name, Optional.empty());
	}

}
