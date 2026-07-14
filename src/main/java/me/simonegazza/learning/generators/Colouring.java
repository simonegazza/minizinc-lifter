package me.simonegazza.learning.generators;

import java.util.List;

/**
 * Generate the Radiation Therapy .dzn files.
 */
public class Colouring extends Problem<Boolean> {
	public Colouring() {
		super("colouring", ArrayGenerator.booleans(), 50 * 50);
	}

	@Override
	protected String getPreable() {
		return "n = 50;\n";
	}

	@Override
	protected String stringify(List<Boolean> data) {
		return "graph = array2d(1..50, 1..50, " + data.toString() + ");";
	}

}
