package me.simonegazza.lift.visitors.flatzinc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.expressions.MiniZincArray;
import me.simonegazza.lift.parameters.LiftedParameter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Visits a FlatZinc JSON model and extracts the set of
 * {@link RevokedAssumption}s that must be disabled in the next MiniZinc
 * execution.
 */
public class UnsatCoreExtractor implements Callable<Set<RevokedAssumption>> {

	/**
	 * List of {@link LiftedParameter}.
	 */
	private final List<LiftedParameter> liftedParameters;

	/**
	 * Array of variable names to identify in the model.
	 */
	private final List<String> topLevelVariables;

	/**
	 * The .fzn JSON object.
	 */
	private final Map<String, Object> fzn;

	public UnsatCoreExtractor(
		Path fznModelPath,
		List<LiftedParameter> liftedParameters,
		List<Integer> miniZincNoGoodIndexes) {

		List<Integer> javaNoGoodsIndexes = miniZincNoGoodIndexes.stream().map(i -> i - 1).toList();
		this.liftedParameters = liftedParameters;

		try {
			fzn = new JSONObject(Files.readString(fznModelPath)).toMap();

			// Look for the variable in the huub_assume constraint
			@SuppressWarnings("unchecked")
			List<String> assumeVariables = ((List<Map<String, Object>>) fzn.get("constraints")).stream()
				// get the "huub_assume" constraints
				.filter(c -> "huub_assume".equals(c.get("id")))
				// get the variable
				.flatMap(c -> ((List<String>) c.get("args")).stream())
				.toList();

			if (assumeVariables.size() > 1) {
				throw new IllegalArgumentException("There were multiple 'assume' constraint in the model");
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> arrays = ((Map<String, Object>) fzn.get("arrays"));
			@SuppressWarnings("unchecked")
			Map<String, Object> assumeVar = (Map<String, Object>) arrays.get(assumeVariables.get(0));
			@SuppressWarnings("unchecked")
			List<String> assumeArray = (List<String>) assumeVar.get("a");

			topLevelVariables = javaNoGoodsIndexes.stream()
				.map(assumeArray::get)
				.toList();

		} catch (JSONException e) {
			e.printStackTrace();
			throw new IllegalStateException("The fzn was not a proper JSON");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Unable to read the fzn JSON file");
		}
	}

	public UnsatCoreExtractor(
		Path fznModelPath,
		List<LiftedParameter> liftedParameters,
		// This constructor takes a because we cannot use Lists due to template
		// erasure during compilation that will make the constructor have the
		// same signature as the costructor above it
		Set<String> topLevelVariables) {

		this.liftedParameters = liftedParameters;
		this.topLevelVariables = topLevelVariables.stream().sorted().toList();

		try {
			fzn = new JSONObject(Files.readString(fznModelPath)).toMap();
		} catch (JSONException e) {
			e.printStackTrace();
			throw new IllegalStateException("The fzn was not a proper JSON");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Unable to read the fzn JSON file");
		}
	}

	/**
	 * Creates a {@link RevokedAssumption} based on variable name and a
	 * flattened index.
	 *
	 * @param varName      the name of the variable for the
	 *                         {@link RevokedAssumption}
	 * @param flattenIndex the flattened index for the {@link RevokedAssumption}
	 *
	 * @return a {@link RevokedAssumption}
	 */
	private RevokedAssumption getRevokedAssumption(String varName, int flattenIndex) {
		Optional<LiftedParameter> liftedParameter = liftedParameters.stream()
			.filter(p -> p.getLiftedName().equals(varName))
			.findFirst();

		if (liftedParameter.isEmpty()) {
			throw new IllegalArgumentException(
				"No parameter with name " + varName + " revoke the assumption to");
		}

		return new RevokedAssumption(liftedParameter.get(), flattenIndex);
	}

	/**
	 * Recursively finds all the original model variables associated with this
	 * variable.
	 *
	 * @param variable the current variable to analyze.
	 *
	 * @return a Set of {@link RevokedAssumption} containing all the original
	 *             model variables that are associated with this one
	 */
	private Set<RevokedAssumption> recursiveFind(String variable) {
		@SuppressWarnings("unchecked")
		Map<String, Object> variables = (Map<String, Object>) fzn.get("variables");
		@SuppressWarnings("unchecked")
		Map<String, Object> varObject = (Map<String, Object>) variables.get(variable);

		if (varObject.containsKey("defined") && varObject.get("defined").equals(Boolean.TRUE)) {
			// the variable was introduced by the compilation process and we
			// need to recursively find the origin of those variables

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> constraints = (List<Map<String, Object>>) fzn.get("constraints");

			return constraints.stream()
				// get the variables that defines the current one
				.filter(c -> c.containsKey("defines") && c.get("defines").equals(variable))
				// get the quantities involved in the constraint and flat them
				.map(c -> MiniZincArray.flatten(c.get("args")))
				// filter only the constraints that contains our variable of
				// interest
				.filter(args -> args.contains(variable))
				.flatMap(List::stream)
				// get only the strings (e.g., we are not interested in integers
				// or other values)
				.filter(String.class::isInstance)
				.map(String.class::cast)
				// avoid getting the original variable
				.filter(v -> !variable.equals(v))
				// recursive call
				.map(this::recursiveFind)
				// flat the result
				.flatMap(Set::stream)
				// remove duplicates
				.collect(Collectors.toSet());

		} else {
			// this variable was in the model originally, so we need to parse
			// its mzn_path and get both the original name of the variable and
			// its flatten index so we can build a RevokedAssumption

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> annotations = ((List<Map<String, Object>>) varObject.get("ann"));
			if (annotations == null) {
				return Set.of();
			}

			List<String> docComment = annotations.stream()
				.filter(a -> "doc_comment".equals(a.get("id")))
				.map(a -> {
					@SuppressWarnings("unchecked")
					List<Map<String, String>> args = (List<Map<String, String>>) a.get("args");
					return args.get(0).get("string");
				}).toList();

			if (docComment.size() == 0) {
				// no doc_comment means that the variable was not an independent
				// one, so we ignore it because lifting it would not generate a
				// real lift (because we need to lift the variables that compute
				// its value)
				return Set.of();
			} else if (docComment.size() > 1) {
				// to avoid messing up the state, we block execution if there
				// are more than one doc_comment annotations
				throw new IllegalStateException("Multiple 'doc_comment' found for a variable");
			} else {
				return annotations.stream()
					// get only the doc_comment annotations
					.filter(a -> "mzn_path".equals(a.get("id")))
					.map(p -> {
						@SuppressWarnings("unchecked")
						List<Map<String, String>> paths = (List<Map<String, String>>) p.get("args");

						if (paths.size() > 1) {
							throw new IllegalStateException(
								"Why this element has more than one path? " + varObject);
						}

						String path = paths.get(0).get("string");
						String[] splitted = path.split(";");

						String[] fileVarPath = splitted[0].split("\\|");
						String id = fileVarPath[fileVarPath.length - 1];

						if (!id.equals(docComment.get(0))) {
							throw new IllegalStateException("doc_comment and path do not agree");
						}

						String[] infoAndIndex = splitted[1].split("\\|");
						String index = infoAndIndex[infoAndIndex.length - 1];
						int idx;
						if (!"vd".equals(index)) {
							idx = Integer.parseInt(infoAndIndex[infoAndIndex.length - 1]);
						} else {
							idx = 0;
						}

						return getRevokedAssumption(id, idx);

					}).collect(Collectors.toSet());
			}
		}
	}

	/**
	 * Performs the full analysis of the FlatZinc model and determines which
	 * assumptions must be revoked.
	 *
	 * @return the set of revoked assumptions
	 */
	@Override
	public Set<RevokedAssumption> call() {
		return topLevelVariables.stream()
			.map(this::recursiveFind)
			.flatMap(Set::stream)
			.collect(Collectors.toSet());
	}
}
