package me.simonegazza.learning.generators;

/**
 * Generate the MOSP .dzn files.
 */
public class Mosp extends SymmetricGraphProblem {
	public Mosp() {
		// Every customer shares its products with itself
		super("mosp", 35, true);
	}

	@Override
	protected String getPreable() {
		return "c = 35;\n";
	}
}
