package me.simonegazza.lift;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aggregates the {@link RunStatistics} produced by multiple executions of the
 * lifting procedure, each driven by a distinct data file.
 * <p>
 * Instances of this class are built incrementally by {@link Runner}: after each
 * data file is processed, the caller adds the corresponding statistics via
 * {@link #add(Path, RunStatistics)}. When all files have been processed, the
 * full collection can be serialised to JSON with {@link #toJson()}.
 * <p>
 * The serialisation relies solely on the standard library and requires no
 * external dependencies.
 */
public class BatchRunStatistics {

	/**
	 * Associates the path of a data file with the {@link RunStatistics}
	 * collected during the lifting procedure that processed it.
	 *
	 * @param dataFile   path to the data file ({@code .dzn}) that was used as
	 *                       input for this run
	 * @param statistics statistics gathered during the run; must not be
	 *                       {@code null}
	 */
	public record RunEntry(Path dataFile, RunStatistics statistics) {
	}

	/**
	 * Ordered list of per-run entries, in the order they were added via
	 * {@link #add(Path, RunStatistics)}.
	 */
	private final List<RunEntry> entries = new ArrayList<>();

	/**
	 * Adds the statistics for one completed (or aborted) run.
	 *
	 * @param dataFile   path to the data file that was used for this run
	 * @param statistics statistics collected during the run
	 */
	public void add(Path dataFile, RunStatistics statistics) {
		entries.add(new RunEntry(dataFile, statistics));
	}

	/**
	 * Returns the list of per-run entries in the order they were added.
	 *
	 * @return unmodifiable list of {@link RunEntry} instances
	 */
	public List<RunEntry> getEntries() {
		return Collections.unmodifiableList(entries);
	}

	/**
	 * Serialises all collected run statistics to a pretty-printed JSON string.
	 * <p>
	 * The top-level object has the following shape:
	 *
	 * <pre>{@code
	 * {
	 *   "runs": [
	 *     {
	 *       "dataFile": "<filename>",
	 *       "statistics": {
	 *         "totalDurationMs": <long>,
	 *         "finalState": "<FinalState name>",
	 *         "iterations": [ ... ]
	 *       }
	 *     },
	 *     ...
	 *   ]
	 * }
	 * }</pre>
	 * <p>
	 * {@code dataFile} contains only the file name (not the full path).
	 *
	 * @return a self-contained JSON string
	 */
	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  \"runs\": [\n");
		for (int i = 0; i < entries.size(); i++) {
			RunEntry entry = entries.get(i);
			sb.append("    {\n");
			sb.append("      \"dataFile\": \"")
				.append(RunStatistics.escapeJsonString(entry.dataFile().getFileName().toString()))
				.append("\",\n");
			sb.append("      \"statistics\": ")
				.append(indentTail(entry.statistics().toJson(), "      "))
				.append("\n");
			sb.append("    }");
			if (i < entries.size() - 1) {
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append("  ]\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Adds {@code prefix} to the beginning of every line after the first in
	 * {@code text}.
	 * <p>
	 * Used to nest the output of {@link RunStatistics#toJson()} inside a deeper
	 * JSON structure without altering the indentation of the first line.
	 *
	 * @param text   the multi-line string to indent
	 * @param prefix the prefix to insert after every newline
	 *
	 * @return the indented string
	 */
	private static String indentTail(String text, String prefix) {
		return text.replace("\n", "\n" + prefix);
	}
}
