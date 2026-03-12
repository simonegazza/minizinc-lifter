package me.simonegazza.lifting.request;

import java.util.Optional;

public interface LiftRequest {
	public final static String BOUNDS_DELIMITER = ":";
	public final static String LOCATION_DELIMITER = "\\[";

	public static LiftRequest parse(String p) {
		String[] nameAndBound = p.split(BOUNDS_DELIMITER);
		String name = nameAndBound[0];
		Optional<String> bounds = Optional.empty();
		if (nameAndBound.length > 1)
			bounds = Optional.of(nameAndBound[1]);

		if (name.endsWith("[*]"))
			name = name.substring(0, name.length() - 3);

		String[] nameAndLocations = name.split(LOCATION_DELIMITER);
		name = nameAndLocations[0];
		if (nameAndLocations.length > 1) {
			String location = nameAndLocations[1].substring(0, nameAndLocations[1].length() - 1);
			return new ArrayElementLiftRequest(name, bounds, Integer.parseInt(location));
		}

		return new SimpleLiftRequest(name, bounds);
	}

	public String getName();

	public Optional<String> getBounds();
}
