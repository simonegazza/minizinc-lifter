package me.simonegazza.lift.visitors.flatzinc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ModelRunner;

public class QuickXPlain {
	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(QuickXPlain.class.getSimpleName());

	private final Path baseModelPath;

	private final String solverName;

	private int counter;
	private List<String> constraints;
	private String aboveModel;

	public QuickXPlain(Path baseModelPath, List<LiftedParameter> liftedParameters, String solverName) {
		this.baseModelPath = baseModelPath;
		// divider = new FlatZincDivider();
		counter = 0;
		this.solverName = solverName;
	}

	public List<String> execute() {
		// Read and write the last model to remove the chuffed parts
		String mznModel;
		try {
			mznModel = Files.readString(Path.of(baseModelPath.toString() + ".mzn")).lines()
				.filter(r -> !r.contains("include \"chuffed.mzn\";") && !r.contains("assume(assumed)"))
				.collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read last MiniZinc file");
		}
		mznModel += "\nconstraint forall(assumed);\n";
		Path modelBasePath = Path.of(baseModelPath + "-qx");
		Path modelPath = Path.of(modelBasePath + ".mzn");
		try {
			Files.writeString(modelPath, mznModel);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write MiniZinc model to file");
		}

		ModelRunner.compile(modelBasePath, solverName);
		// Prepare the divider
		Path fznPath = Path.of(modelBasePath + ".fzn");
//		CharStream fznInput;
//		try {
//			fznInput = CharStreams.fromPath(fznPath);
//		} catch (IOException e) {
//			throw new IllegalStateException("Unable to find model in " + fznPath);
//		}
//		Lexer fznLexer = new FlatZincLexer(fznInput);
//		TokenStream fznTokens = new CommonTokenStream(fznLexer);
//		FlatZincParser fznParser = new FlatZincParser(fznTokens);
//		divider.visitModel(fznParser.model());
//
//		// run with extracted constraints
//		List<String> constraints = divider.getConstraints();
		try {
			List<String> fznModel = Files.readAllLines(fznPath);
			constraints = fznModel.stream()
				.filter(r -> r.startsWith("constraint"))
				.toList();
			aboveModel = fznModel.stream()
				.takeWhile(r -> !r.startsWith("constraint"))
				.collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to read FlatZinc model");
		}

		return quickXPlain(List.of(), constraints);
	}

	private List<String> union(List<String> a, List<String> b) {
		return Stream.concat(a.stream(), b.stream())
			.distinct()
			.toList();
	}

	private boolean checkUnsat(List<String> constraints) {
		logger.info("Running iteration " + counter);
		Path model = buildModel(constraints);
		List<String> output = ModelRunner.run(model, solverName);

		if (output.stream().anyMatch(l -> l.contains("UNKNOWN"))) {
			logger.info("Unable to find any solutions or unsat cores given the time limit, exiting, sorry!");
			System.exit(1);
		}

		return output.stream().anyMatch(l -> l.contains("UNSATISFIABLE"));
	}

	private Path buildModel(List<String> constraints) {
		StringBuilder sb = new StringBuilder(aboveModel);

		constraints.forEach(c -> sb.append(c).append("\n"));
		sb.append("solve satisfy;\n\n");

		Path qxModelPath = Path.of(baseModelPath.toString() + "-qx-" + (counter++));

		try {
			Files.writeString(Path.of(qxModelPath + ".mzn"), sb.toString());
			return qxModelPath;
		} catch (IOException e) {
			throw new IllegalStateException("Cannot write QuickExplain model to file: " + qxModelPath);
		}
	}

	private List<String> quickXPlain(List<String> B, List<String> C) {
		boolean unsat = checkUnsat(B);
		if (!B.isEmpty() && unsat) {
			return List.of();
		}

		if (C.size() == 1) {
			return C;
		}

		int mid = C.size() / 2;
		List<String> C1 = new ArrayList<>(C.subList(0, mid));
		List<String> C2 = new ArrayList<>(C.subList(mid, C.size()));

		List<String> delta1 = quickXPlain(union(B, C2), C1);
		List<String> delta2 = quickXPlain(union(B, delta1), C2);

		return union(delta1, delta2);
	}
}
