package me.simonegazza.learning.generators;

import java.util.List;

/**
 * Generate the Radiation Therapy .dzn files.
 */
public class Radiation extends Problem<Integer> {
	public Radiation() {
		super("radiation", ArrayGenerator.integers(1, 10), 15 * 12);
	}

	@Override
	protected String getPreable() {
		return """
			m = 15;
			n = 12;
			Beamtime = 31;
			Bt_max = 10;
			""";
	}

	@Override
	protected String stringify(List<Integer> data) {
		return "Intensity = array2d(1..15, 1..12, " + data.toString() + ");";
	}

}
