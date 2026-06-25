package me.simonegazza.learning.generators;

import java.util.List;

/**
 * Generate the Radiation Therapy .dzn files.
 */
public class Mosp extends Problem<Boolean> {
	public Mosp() {
		super("mosp", ArrayGenerator.booleans(), 35 * 35);
	}

	@Override
	protected String getPreable() {
		return "c = 35;\n";
	}

	@Override
	protected String stringify(List<Boolean> data) {
		return "graph = array2d(1..35, 1..35, " + data.toString() + ");";
	}
}
