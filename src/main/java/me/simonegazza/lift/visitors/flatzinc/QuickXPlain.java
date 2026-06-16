package me.simonegazza.lift.visitors.flatzinc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ModelRunner;
import org.json.JSONObject;

/**
 * Implementation of the QuickXPlain algorithm for identifying a minimal
 * unsatisfiable subset (MUS) of constraints in a FlatZinc model.
 * <p>
 * The algorithm works by recursively partitioning the set of constraints and
 * performing satisfiability checks to isolate the constraints responsible for
 * an inconsistency. Each satisfiability check is performed by generating a
 * temporary MiniZinc model containing a subset of the original constraints and
 * invoking the configured solver.
 * <p>
 * The input model is first transformed into a FlatZinc representation from
 * which all constraints are extracted. The resulting minimal conflict set can
 * then be used for explanation generation.
 */
public class QuickXPlain implements Callable<List<Object>> {
	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(QuickXPlain.class.getSimpleName());

	/**
	 * Model Path that constitutes naming for the models that will be created.
	 */
	private final Path baseModelPath;

	/**
	 * Solver identifier for MiniZinc.
	 */
	private final String solverName;

	/**
	 * Internal incremental counter used for naming files.
	 */
	private int counter;

	/**
	 * List of constraints of the original model.
	 */
	private List<Object> constraints;

	/**
	 * Internal, upper and constant part of the model that has nothing to do
	 * with constraints.
	 */
	private final Map<String, Object> otherModelParts;

	public QuickXPlain(
		Path baseModelPath,
		List<LiftedParameter> liftedParameters,
		String solverName) {

		this.baseModelPath = baseModelPath;
		counter = 0;
		this.solverName = solverName;

		// retrieve both the constraints and the other parts of the model
		String mznModel;
		try {
			mznModel = Files.readString(Path.of(baseModelPath.toString() + ".mzn")).lines()
				.filter(r -> !r.contains("include \"huub.mzn\";")
					&& !r.contains("assume(assumed)"))
				.collect(Collectors.joining(
					"\n",
					"\nconstraint forall(assumed);\n",
					""));
		} catch (IOException e) {
			throw new IllegalStateException(
				"Unable to read last MiniZinc file for QuickXPlain");
		}
		Path modelBasePath = Path.of(baseModelPath + "-qx");
		Path modelPath = Path.of(modelBasePath + ".mzn");
		try {
			Files.writeString(modelPath, mznModel);
		} catch (IOException e) {
			throw new IllegalArgumentException(
				"Unable to write MiniZinc model for QuickXPlain to file");
		}

		ModelRunner.compile(modelBasePath, solverName);
		Path fznPath = Path.of(modelBasePath + ".fzn.json");

		JSONObject fzn;
		try {
			fzn = new JSONObject(Files.readAllLines(fznPath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(
				"Unable to read last FlatZinc file for QuickXPlain");
		}

		constraints = fzn.getJSONArray("constraints").toList();

		otherModelParts = new HashMap<String, Object>();
		fzn.keySet().stream()
			.filter(k -> !"constraints".equals(k))
			.forEach(k -> otherModelParts.put(k, fzn.get(k)));
		// "solve": { "method" : "satisfy" },
		otherModelParts.replace(
			"solve",
			Map.of("method", "satisfy"));

	}

	/**
	 * Executes the QuickXPlain algorithm on the current model and returns a the
	 * minimal set of constraints that caused the failure.
	 * <p>
	 * The method:
	 * <ol>
	 * <li>Creates a recovery version of the MiniZinc model without
	 * assumption-based solving.</li>
	 * <li>Compiles the model to FlatZinc.</li>
	 * <li>Extracts all FlatZinc constraints.</li>
	 * <li>Runs QuickXPlain to identify a minimal conflicting subset of those
	 * constraints.</li>
	 * </ol>
	 *
	 * @return the minimal set of FlatZinc constraints responsible for the
	 *             detected inconsistency with the upper part of the model above
	 */
	@Override
	public List<Object> call() {
		return quickXPlain(List.of(), constraints);
	}

	/**
	 * Computes the union of two constraint sets while removing duplicates.
	 *
	 * @param a first constraint set
	 * @param b second constraint set
	 *
	 * @return a list containing all distinct constraints from both inputs
	 */
	private List<Object> union(List<Object> a, List<Object> b) {
		return Stream.concat(a.stream(), b.stream())
			.distinct()
			.toList();
	}

	/**
	 * Determines whether a given set of constraints is unsatisfiable.
	 * <p>
	 * A temporary MiniZinc model containing only the supplied constraints is
	 * generated and executed using the configured solver.
	 * <p>
	 * If the solver returns {@code UNKNOWN}, the search is aborted because the
	 * satisfiability status cannot be determined reliably within the configured
	 * limits.
	 *
	 * @param constraints constraints to test
	 *
	 * @return {@code true} if the constraint set is unsatisfiable;
	 *             {@code false} otherwise
	 */
	private boolean checkUnsat(List<Object> constraints) {
		logger.info("Running iteration " + counter);
		Path model = buildModel(constraints);
		List<String> output = ModelRunner.run(model, solverName);

		if (output.stream().anyMatch(l -> l.contains("UNKNOWN"))) {
			logger.info("Unable to find any solutions or unsat cores given the time limit, exiting, sorry!");
			System.exit(1);
		}

		return output.stream().anyMatch(l -> l.contains("UNSATISFIABLE"));
	}

	/**
	 * Creates a temporary MiniZinc model containing the specified constraints.
	 * <p>
	 * The generated model consists of:
	 * <ul>
	 * <li>The non-constraint declarations extracted from the original FlatZinc
	 * model.</li>
	 * <li>The supplied subset of constraints.</li>
	 * <li>A {@code solve satisfy;} statement.</li>
	 * </ul>
	 *
	 * @param constraints constraints to include in the generated model
	 *
	 * @return the base path of the generated model (without file extension)
	 *
	 * @throws IllegalStateException if the model cannot be written to disk
	 */
	private Path buildModel(List<Object> constraints) {
		Map<String, Object> model = new HashMap<>(otherModelParts);
		model.put("constraints", constraints);

		Path qxModelPath = Path.of(baseModelPath.toString() + "-qx-" + (counter++));

		try {
			Files.writeString(
				Path.of(qxModelPath + ".mzn"),
				JSONObject.valueToString(model));
			return qxModelPath;
		} catch (IOException e) {
			throw new IllegalStateException("Cannot write QuickExplain model to file: " + qxModelPath);
		}
	}

	/**
	 * QuickXPlain algorithm.
	 * <p>
	 * Given a background constraint set {@code B} and a candidate conflict set
	 * {@code C}, the algorithm recursively partitions {@code C} and performs
	 * satisfiability checks to isolate a minimal subset of constraints whose
	 * presence causes unsatisfiability.
	 * <p>
	 * The recursion terminates when:
	 * <ul>
	 * <li>{@code B} is already unsatisfiable, in which case no additional
	 * constraints are required for the conflict.</li>
	 * <li>{@code C} contains a single constraint, which is therefore part of
	 * the conflict set.</li>
	 * </ul>
	 *
	 * @param B background constraints assumed to be present
	 * @param C candidate constraints that may contribute to the conflict
	 *
	 * @return a minimal conflicting subset of {@code C}
	 */
	private List<Object> quickXPlain(List<Object> B, List<Object> C) {
		boolean unsat = checkUnsat(B);
		if (!B.isEmpty() && unsat) {
			return List.of();
		}

		if (C.size() == 1) {
			return C;
		}

		int mid = C.size() / 2;
		List<Object> C1 = new ArrayList<>(C.subList(0, mid));
		List<Object> C2 = new ArrayList<>(C.subList(mid, C.size()));

		List<Object> delta1 = quickXPlain(union(B, C2), C1);
		List<Object> delta2 = quickXPlain(union(B, delta1), C2);

		return union(delta1, delta2);
	}
}
