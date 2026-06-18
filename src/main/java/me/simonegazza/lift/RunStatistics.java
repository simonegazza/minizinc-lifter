package me.simonegazza.lift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.simonegazza.lift.parameters.LiftedParameter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Collects and exposes runtime statistics for a single execution of the lifting
 * procedure driven by {@link Main}. The collected data can be serialised to a
 * JSON string via {@link #toJson()}. The serialisation relies solely on the
 * standard library and requires no external dependencies.
 * <p>
 * All durations are expressed in milliseconds.
 */
public class RunStatistics {

	/**
	 * Describes the final outcome of the lifting procedure.
	 */
	public enum FinalState {

		/**
		 * A satisfying assignment was found by the main solver.
		 */
		SOLUTION_FOUND,

		/**
		 * The solver established that the problem class is entirely
		 * unsatisfiable, so no lifting can produce a valid assignment.
		 */
		UNSATISFIABLE,

		/**
		 * A satisfying assignment was found by the recovery solver after the
		 * main solver failed to produce either a solution or an UNSAT core.
		 */
		SOLUTION_FOUND_VIA_RECOVERY,

		/**
		 * The procedure was aborted because neither a solution nor an UNSAT
		 * core could be obtained even after invoking the recovery solver and
		 * QuickXPlain.
		 */
		ABORTED
	}

	/**
	 * Immutable snapshot of the statistics gathered for a single iteration of
	 * the main lifting loop.
	 * <p>
	 * Each record describes the outcome of one execution of the iterative
	 * assumption-removal process, including its duration, the extracted UNSAT
	 * core, the solver used, and whether a recovery step based on QuickXPlain
	 * was required.
	 *
	 * @param iteration             one-based index of the iteration, matching
	 *                                  the loop counter used by the lifting
	 *                                  procedure
	 * @param durationMs            wall-clock duration of the iteration in
	 *                                  milliseconds
	 * @param coreVariables         indexes of the lifted variables that belong
	 *                                  to the extracted UNSAT core; empty when
	 *                                  no core was produced
	 * @param solverUsed            name of the solver used for the main model
	 *                                  execution during this iteration
	 * @param quickXPlainUsed       {@code true} if QuickXPlain was invoked to
	 *                                  recover a conflicting set of
	 *                                  constraints, {@code false} otherwise
	 * @param quickXPlainSolver     name of the solver used internally by
	 *                                  QuickXPlain, or {@code null} when
	 *                                  QuickXPlain was not used
	 * @param quickXPlainDurationMs wall-clock duration of the QuickXPlain
	 *                                  execution in milliseconds; meaningful
	 *                                  only when {@code quickXPlainUsed} is
	 *                                  {@code true}
	 */
	public record IterationRecord(
		int iteration,
		long durationMs,
		List<Integer> coreVariables,
		String solverUsed,
		boolean quickXPlainUsed,
		String quickXPlainSolver,
		long quickXPlainDurationMs) {

		/**
		 * The json iteration record.
		 *
		 * @return the json of this iteration record
		 */
		public JSONObject toJson() {
			JSONObject json = new JSONObject();

			json.put("iteration", iteration);
			json.put("durationMs", durationMs);
			json.put("coreVariables", new JSONArray(coreVariables));
			json.put("solverUsed", solverUsed);
			json.put("quickXPlainUsed", quickXPlainUsed);

			json.put(
				"quickXPlainSolver",
				quickXPlainUsed ? quickXPlainSolver : JSONObject.NULL);

			json.put(
				"quickXPlainDurationMs",
				quickXPlainUsed ? quickXPlainDurationMs : JSONObject.NULL);

			return json;
		}
	}

	/**
	 * System-nanotime captured when {@link #start()} was called.
	 */
	private long startNano;

	/**
	 * System-nanotime captured when {@link #finish(FinalState)} was called.
	 */
	private long endNano;

	/**
	 * Outcome of the procedure, set by {@link #finish(FinalState)}.
	 */
	private FinalState finalState;

	/**
	 * Ordered list of per-iteration records.
	 */
	private final List<IterationRecord> iterations = new ArrayList<>();

	/**
	 * One-based index of the iteration currently in progress, set by
	 * {@link #startIteration(int)}.
	 */
	private int currentIteration;

	/**
	 * System-nanotime captured when {@link #startIteration(int)} was last
	 * called.
	 */
	private long currentIterationStartNano;

	/**
	 * Amount of changes originally introduced in the model, counted by scanning
	 * for "% CHANGED HERE from" comments.
	 */
	private int changes;

	/**
	 * Number of parameters lifted in the model.
	 */
	private int parameterNumber;

	/**
	 * Records the number of changes originally introduced in the model, by
	 * counting the number of "% CHANGED HERE from" comments.
	 *
	 * @param originalModel the original model as a string
	 */
	public void countChanges(String originalModel) {
		Matcher matcher = Pattern.compile("% CHANGED HERE from").matcher(originalModel);

		int result = 0;
		while (matcher.find()) {
			result++;
		}

		changes = result;
	}

	/**
	 * Records the number of parameters lifted in the model.
	 *
	 * @param parameters the list of parameters to lift
	 */
	public void parameterCount(List<LiftedParameter> parameters) {
		parameterNumber = parameters.stream()
			.mapToInt(p -> p.getDimensions().stream().reduce(1, (a, b) -> a * b))
			.sum();
	}

	/**
	 * Records the start of the entire procedure by capturing the current system
	 * time.
	 * <p>
	 * Must be called once, before any other method on this object.
	 */
	public void start() {
		startNano = System.nanoTime();
	}

	/**
	 * Records the start of one iteration by capturing the current system time.
	 * <p>
	 * Must be called at the beginning of each iteration, before any work for
	 * that iteration is performed.
	 *
	 * @param iteration the one-based index of the iteration that is about to
	 *                      begin
	 */
	public void startIteration(int iteration) {
		currentIteration = iteration;
		currentIterationStartNano = System.nanoTime();
	}

	/**
	 * Records the end of the current iteration and appends a snapshot of its
	 * statistics to the internal list.
	 * <p>
	 * Must be called after {@link #startIteration(int)} and before the next
	 * call to {@link #startIteration(int)} or {@link #finish(FinalState)}.
	 * <p>
	 * Callers that terminate the procedure without extracting a core (e.g. when
	 * a solution is found) should pass an empty set for {@code coreVariables}.
	 *
	 * @param coreVariables         UNSAT core variables found in this
	 *                                  iteration; pass an empty set when no
	 *                                  core was produced
	 * @param solverUsed            the main solver used during this iteration
	 * @param quickXPlainUsed       {@code true} when QuickXPlain was invoked
	 *                                  during this iteration
	 * @param quickXPlainSolver     the solver used by QuickXPlain, or
	 *                                  {@code null} if not used
	 * @param quickXPlainDurationMs wall-clock duration of the QuickXPlain run
	 *                                  in milliseconds; ignored when
	 *                                  {@code quickXPlainUsed} is {@code false}
	 */
	public void endIteration(
		List<Integer> coreVariables,
		String solverUsed,
		boolean quickXPlainUsed,
		String quickXPlainSolver,
		long quickXPlainDurationMs) {

		long durationMs = (System.nanoTime() - currentIterationStartNano) / 1_000_000L;
		iterations.add(new IterationRecord(
			currentIteration,
			durationMs,
			coreVariables,
			solverUsed,
			quickXPlainUsed,
			quickXPlainSolver,
			quickXPlainDurationMs));
	}

	/**
	 * Records the end of the entire procedure and its outcome.
	 * <p>
	 * Must be called exactly once, after the final.
	 *
	 * @param state the outcome of the procedure; must not be {@code null}
	 */
	public void finish(FinalState state) {
		endNano = System.nanoTime();
		finalState = state;
	}

	/**
	 * Returns the list of per-iteration records in order of execution.
	 *
	 * @return unmodifiable list of {@link IterationRecord} instances
	 */
	public List<IterationRecord> getIterations() {
		return Collections.unmodifiableList(iterations);
	}

	/**
	 * Returns the total wall-clock duration of the procedure in milliseconds,
	 * measured from the {@link #start()} call to the {@link #finish} call.
	 * <p>
	 * Returns {@code 0} when {@link #finish(FinalState)} has not yet been
	 * called.
	 *
	 * @return total duration in milliseconds, or {@code 0} if not yet finished
	 */
	public long getTotalDurationMs() {
		if (endNano == 0) {
			return 0;
		}
		return (endNano - startNano) / 1_000_000L;
	}

	/**
	 * Returns the outcome of the procedure.
	 * <p>
	 * Returns {@code null} when {@link #finish(FinalState)} has not yet been
	 * called.
	 *
	 * @return the final state, or {@code null} if not yet finished
	 */
	public FinalState getFinalState() {
		return finalState;
	}

	/**
	 * Serialises the collected statistics to a pretty-printed JSON string.
	 * <p>
	 * The top-level object has the following shape:
	 *
	 * <pre>{@code
	 * {
	 *   "totalDurationMs": <long>,
	 *   "finalState": "<FinalState name>",
	 *   "iterations": [
	 *     {
	 *       "iteration": <int>,
	 *       "durationMs": <long>,
	 *       "coreVariables": [ "<variable>", ... ],
	 *       "solverUsed": "<string>",
	 *       "quickXPlainUsed": <boolean>,
	 *       "quickXPlainSolver": <string | null>,
	 *       "quickXPlainDurationMs": <long | null>
	 *     },
	 *     ...
	 *   ]
	 * }
	 * }</pre>
	 * <p>
	 * {@code quickXPlainDurationMs} is {@code null} when
	 * {@code quickXPlainUsed} is {@code false}. {@code finalState} is
	 * {@code null} when {@link #finish(FinalState)} has not been called yet.
	 *
	 * @return a self-contained JSON string
	 */
	public JSONObject toJson() {

		JSONObject root = new JSONObject();

		root.put("totalDurationMs", getTotalDurationMs());
		root.put("liftedParameters", parameterNumber);
		root.put("originalParameterModified", changes);

		if (finalState != null) {
			root.put("finalState", finalState.name());
		} else {
			root.put("finalState", JSONObject.NULL);
		}

		JSONArray iterationsArray = new JSONArray();

		for (IterationRecord record : iterations) {
			iterationsArray.put(record.toJson());
		}

		root.put("iterations", iterationsArray);

		return root;
	}

	@Override
	public String toString() {
		return toJson().toString(2);
	}
}
