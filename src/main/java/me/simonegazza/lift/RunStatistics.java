package me.simonegazza.lift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.visitors.flatzinc.QuickXPlain;

/**
 * Collects and exposes runtime statistics for a single execution of the lifting
 * procedure driven by {@link Main}.
 * <p>
 * Statistics are accumulated incrementally as the procedure progresses. The
 * expected call sequence is:
 * <ol>
 * <li>{@link #start()} — once, before any work begins.</li>
 * <li>{@link #startIteration(int)} — at the beginning of every iteration of the
 * UNSAT-core extraction loop.</li>
 * <li>{@link #endIteration(Set, boolean, long)} — at the end of every
 * iteration, whether or not an UNSAT core was produced.</li>
 * <li>{@link #finish(FinalState)} — once, when the procedure terminates.</li>
 * </ol>
 * <p>
 * The collected data can be serialised to a JSON string via {@link #toJson()}.
 * The serialisation relies solely on the standard library and requires no
 * external dependencies.
 * <p>
 * All durations are expressed in milliseconds.
 */
public class RunStatistics {

	/**
	 * Describes the final outcome of the lifting procedure.
	 */
	public enum FinalState {

		/**
		 * A satisfying assignment was found by the main (chuffed) solver.
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
	 * the main loop in {@link Main}.
	 */
	public static final class IterationRecord {

		/**
		 * One-based index of this iteration, matching the {@code i} variable in
		 * the main loop.
		 */
		private final int iteration;

		/**
		 * Wall-clock duration of the entire iteration, in milliseconds.
		 */
		private final long durationMs;

		/**
		 * Variables that form the UNSAT core extracted during this iteration,
		 * expressed as {@link RevokedAssumption} objects.
		 * <p>
		 * Empty when the iteration terminated the procedure without producing a
		 * core (e.g. a solution was found or the problem was deemed
		 * unsatisfiable).
		 */
		private final Set<RevokedAssumption> coreVariables;

		/**
		 * Whether the {@link QuickXPlain} algorithm was invoked during this
		 * iteration to recover a minimal conflicting constraint set.
		 */
		private final boolean quickXPlainUsed;

		/**
		 * Wall-clock duration of the QuickXPlain run, in milliseconds.
		 * <p>
		 * This value is meaningful only when {@link #quickXPlainUsed} is
		 * {@code true}; it is {@code 0} otherwise.
		 */
		private final long quickXPlainDurationMs;

		/**
		 * The main solver used during this iteration.
		 */
		private final String solverUsed;

		/**
		 * The solver used by QuickXPlain, if applicable.
		 */
		private final String quickXPlainSolver;

		/**
		 * @param iteration             one-based iteration index
		 * @param durationMs            total wall-clock duration of the
		 *                                  iteration in milliseconds
		 * @param coreVariables         UNSAT core variables found in this
		 *                                  iteration; must not be {@code null}
		 * @param solverUsed            the main solver used during this
		 *                                  iteration
		 * @param quickXPlainUsed       {@code true} when QuickXPlain was
		 *                                  invoked during this iteration
		 * @param quickXPlainSolver     the solver used by QuickXPlain, or
		 *                                  {@code null} if not used
		 * @param quickXPlainDurationMs wall-clock duration of the QuickXPlain
		 *                                  run in milliseconds; ignored when
		 *                                  {@code quickXPlainUsed} is
		 *                                  {@code false}
		 */
		public IterationRecord(
			int iteration,
			long durationMs,
			Set<RevokedAssumption> coreVariables,
			String solverUsed,
			boolean quickXPlainUsed,
			String quickXPlainSolver,
			long quickXPlainDurationMs) {
			this.iteration = iteration;
			this.durationMs = durationMs;
			this.coreVariables = Collections.unmodifiableSet(coreVariables);
			this.solverUsed = solverUsed;
			this.quickXPlainUsed = quickXPlainUsed;
			this.quickXPlainSolver = quickXPlainSolver;
			this.quickXPlainDurationMs = quickXPlainDurationMs;
		}

		/**
		 * Returns the one-based index of this iteration.
		 *
		 * @return iteration index
		 */
		public int getIteration() {
			return iteration;
		}

		/**
		 * Returns the wall-clock duration of this iteration in milliseconds.
		 *
		 * @return duration in milliseconds
		 */
		public long getDurationMs() {
			return durationMs;
		}

		/**
		 * Returns the UNSAT core variables extracted during this iteration as
		 * an unmodifiable set.
		 * <p>
		 * The set is empty when the iteration terminated the procedure without
		 * producing a core.
		 *
		 * @return unmodifiable set of {@link RevokedAssumption}; never
		 *             {@code null}
		 */
		public Set<RevokedAssumption> getCoreVariables() {
			return coreVariables;
		}

		/**
		 * Returns the main solver used during this iteration.
		 *
		 * @return solver name
		 */
		public String getSolverUsed() {
			return solverUsed;
		}

		/**
		 * Returns whether the QuickXPlain algorithm was invoked during this
		 * iteration.
		 *
		 * @return {@code true} if QuickXPlain was used
		 */
		public boolean isQuickXPlainUsed() {
			return quickXPlainUsed;
		}

		/**
		 * Returns the wall-clock duration of the QuickXPlain run in
		 * milliseconds.
		 * <p>
		 * The value is {@code 0} and should be ignored when
		 * {@link #isQuickXPlainUsed()} returns {@code false}.
		 *
		 * @return QuickXPlain duration in milliseconds, or {@code 0} if not
		 *             used
		 */
		public long getQuickXPlainDurationMs() {
			return quickXPlainDurationMs;
		}

		/**
		 * Returns the solver used by QuickXPlain.
		 *
		 * @return QuickXPlain solver name, or {@code null} if not used
		 */
		public String getQuickXPlainSolver() {
			return quickXPlainSolver;
		}

		/**
		 * Serialises this record to a JSON object string, indented to fit
		 * inside the {@code iterations} array produced by
		 * {@link RunStatistics#toJson()}.
		 *
		 * @return a JSON object string representing this record
		 */
		String toJson() {
			StringBuilder sb = new StringBuilder();
			sb.append("    {\n");
			sb.append("      \"iteration\": ").append(iteration).append(",\n");
			sb.append("      \"durationMs\": ").append(durationMs).append(",\n");
			sb.append("      \"coreVariables\": [");
			List<String> vars = coreVariables.stream()
				.map(r -> "\"" + escapeJsonString(r.toString()) + "\"")
				.sorted()
				.toList();
			sb.append(String.join(", ", vars));
			sb.append("],\n");
			sb.append("      \"solverUsed\": \"").append(escapeJsonString(solverUsed)).append("\",\n");
			sb.append("      \"quickXPlainUsed\": ").append(quickXPlainUsed).append(",\n");
			sb.append("      \"quickXPlainSolver\": ");
			if (quickXPlainUsed && quickXPlainSolver != null) {
				sb.append("\"").append(escapeJsonString(quickXPlainSolver)).append("\"");
			} else {
				sb.append("null");
			}
			sb.append(",\n");
			sb.append("      \"quickXPlainDurationMs\": ");
			if (quickXPlainUsed) {
				sb.append(quickXPlainDurationMs);
			} else {
				sb.append("null");
			}
			sb.append("\n");
			sb.append("    }");
			return sb.toString();
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
	 * Ordered list of per-iteration records, appended by
	 * {@link #endIteration(Set, boolean, long)}.
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

		this.changes = result;
	}

	/**
	 * Records the number of parameters lifted in the model.
	 *
	 * @param parameters the list of parameters to lift
	 */
	public void parameterCount(List<LiftedParameter> parameters) {
		this.parameterNumber = parameters.stream()
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
		Set<RevokedAssumption> coreVariables,
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
	 * Must be called exactly once, after the final
	 * {@link #endIteration(Set, boolean, long)} call.
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
	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  \"totalDurationMs\": ").append(getTotalDurationMs()).append(",\n");
		sb.append("  \"liftedParameters\": ").append(parameterNumber).append(",\n");
		sb.append("  \"originalParameterModified\": ").append(changes).append(",\n");
		sb.append("  \"finalState\": ");
		if (finalState != null) {
			sb.append("\"").append(finalState.name()).append("\"");
		} else {
			sb.append("null");
		}
		sb.append(",\n");
		sb.append("  \"iterations\": [\n");
		for (int i = 0; i < iterations.size(); i++) {
			sb.append(iterations.get(i).toJson());
			if (i < iterations.size() - 1) {
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append("  ]\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Escapes a string value for safe inclusion inside a JSON string literal.
	 * <p>
	 * Handles backslash, double-quote, and the standard JSON control characters
	 * ({@code \n}, {@code \r}, {@code \t}).
	 *
	 * @param s the raw string
	 *
	 * @return the escaped string, ready to be enclosed in double quotes
	 */
	static String escapeJsonString(String s) {
		return s.replace("\\", "\\\\")
			.replace("\"", "\\\"")
			.replace("\n", "\\n")
			.replace("\r", "\\r")
			.replace("\t", "\\t");
	}
}
