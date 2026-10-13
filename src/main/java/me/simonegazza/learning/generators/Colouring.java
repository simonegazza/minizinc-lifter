package me.simonegazza.learning.generators;

/**
 * Generate the Graph Colouring .dzn files.
 */
public class Colouring extends SymmetricGraphProblem {
	public Colouring() {
		// The diagonal is ignored by the model
		super("colouring", 50, false);
	}

	@Override
	protected String getPreable() {
		return "n = 50;\n";
	}
}
