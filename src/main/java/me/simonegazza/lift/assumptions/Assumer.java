package me.simonegazza.lift.assumptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.expressions.MiniZincArray;
import me.simonegazza.lift.expressions.MiniZincIdentifier;
import me.simonegazza.lift.expressions.MiniZincSet;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincExpressionType;
import me.simonegazza.lift.types.MiniZincSetType;

/**
 * Enriches a base MiniZinc model with assumption handling logic and applies
 * assumption revocations when required.
 */
public class Assumer implements Callable<String> {

	/**
	 * Original MiniZinc model before assumption injection.
	 */
	private final String baseModel;

	/**
	 * List of parameters that have been lifted in the model.
	 */
	private final List<LiftedParameter> lifted;

	/**
	 * List of parameters divided by base type.
	 */
	private final Map<Object, List<LiftedParameter>> parameterTypeCollector;

	/**
	 * The assumptions that needs to be revoked.
	 */
	private final Set<RevokedAssumption> assumptions;

	/**
	 * The names of the parameter array.
	 */
	private final List<String> paramArrayIdentifiers;

	/**
	 * The names of the lifted parameter array.
	 */
	private final List<String> paramArrayLiftedIdentifiers;

	/**
	 * The cliParameters
	 */
	private final List<LiftedParameter> maxParameters;

	public Assumer(
		String baseModel,
		List<LiftedParameter> lifted,
		List<LiftedParameter> maxParameters,
		Set<RevokedAssumption> revokedAssumption) {

		this.baseModel = baseModel;
		this.lifted = lifted;
		this.maxParameters = maxParameters;
		assumptions = revokedAssumption;
		paramArrayIdentifiers = new ArrayList<>();
		paramArrayLiftedIdentifiers = new ArrayList<>();

		// Create the map of parameters divided by basic type (try to do the
		// best you can to separate them)
		parameterTypeCollector = new HashMap<>();
		for (LiftedParameter lp : lifted) {
			Object mapDiscriminant = lp.getParameter().getType();
			if (mapDiscriminant instanceof MiniZincArrayType mda) {
				// TODO: to do this operation properly, it should be recursive
				mapDiscriminant = mda.getSubtype();
			}

			if (mapDiscriminant instanceof MiniZincBasicType mdb) {
				mapDiscriminant = mdb.toString();
			}

			if (mapDiscriminant.getClass().equals(MiniZincSetType.class)) {
				mapDiscriminant = MiniZincSetType.class;
			}

			if (mapDiscriminant instanceof MiniZincIdentifier || mapDiscriminant instanceof MiniZincExpressionType) {
				Object value = lp.getParameter().getValue();
				if (value instanceof MiniZincArray va) {
					Object e = va.flatten().getFirst();
					if (e instanceof Integer) {
						mapDiscriminant = MiniZincBasicType.INT.toString();
					} else if (e instanceof Double) {
						mapDiscriminant = MiniZincBasicType.FLOAT.toString();
					} else if (e instanceof String) {
						mapDiscriminant = MiniZincBasicType.STRING.toString();
					} else if (e instanceof Boolean) {
						mapDiscriminant = MiniZincBasicType.BOOL.toString();
					}
				} else if (value instanceof MiniZincSet) {
					mapDiscriminant = MiniZincSet.class;
				} else {
					throw new IllegalStateException(
						"Unable to discriminate the type for computing the parameter arrays");
				}
			}
			List<LiftedParameter> specificTypeCollector = parameterTypeCollector.getOrDefault(
				mapDiscriminant,
				new ArrayList<>());
			specificTypeCollector.add(lp);
			parameterTypeCollector.put(mapDiscriminant, specificTypeCollector);
		}

	}

	/**
	 * Builds the solve statement based on lifted parameters.
	 *
	 * @return the solve component of the combined lifts
	 */
	private String getSolve() {
		StringBuilder obj = new StringBuilder("var ");
		if (parameterTypeCollector.containsKey("float")) {
			obj.append("float");
		} else {
			obj.append("int");
		}

		List<String> warmStarts = parameterTypeCollector.values().stream().map(parametersByType -> {
			List<RevokedAssumption> assumptionsFilteredByType = assumptions.stream()
				.filter(a -> parametersByType.stream()
					.map(LiftedParameter::getLiftedName)
					.anyMatch(p -> p.equals(a.name())))
				.toList();

			List<String> warmStartOriginal = new ArrayList<>();
			List<String> warmStartLifted = new ArrayList<>();

			for (LiftedParameter parameter : parametersByType) {
				Optional<String> pieceOriginal = parameter.warmStartPiece(false, assumptionsFilteredByType);
				if (pieceOriginal.isPresent()) {
					warmStartOriginal.add(pieceOriginal.get());
					warmStartLifted.add(parameter.warmStartPiece(true, assumptionsFilteredByType).get());
				}
			}

			if (warmStartLifted.isEmpty() && warmStartOriginal.isEmpty()) {
				return null;
			}

			return ":: warm_start(\n\t\t"
				+ warmStartLifted.stream().collect(Collectors.joining("\n\t\t\t++ "))
				+ ",\n\t\t"
				+ warmStartOriginal.stream().collect(Collectors.joining("\n\t\t\t++ "))
				+ "\n\t)";
		}).filter(Objects::nonNull).toList();

		obj.append(": objective_lifted :: output_var = ");
		obj.append(
			lifted.stream()
				.sorted()
				.filter(maxParameters::contains)
				.map(LiftedParameter::getSolvePiece)
				.collect(Collectors.joining("\n\t+ ")))
			.append("\n;\n")
			.append("solve")
			.append("\n\t:: assume(assumed)")
			.append(warmStarts.stream().collect(Collectors.joining("\n", "\n\t", "")))
			.append("\nminimize objective_lifted;\n\n");

		return obj.toString();
	}

	/**
	 * Builds one of the parameter arrays used by the generated model.
	 * <p>
	 * Depending on the selected mode, the method produces either:
	 * <ul>
	 * <li>the concrete parameter array</li>
	 * <li>the lifted parameter array</li>
	 * </ul>
	 * while applying the required revoked assumptions.
	 *
	 * @param ofLifted      whether the generated array should be of lifted
	 *                          parameters
	 * @param revoked       the assumptions to revoke while generating the array
	 * @param parameterType this is either a String or a Class object depending
	 *                          if the parameter was a simple parameter or a
	 *                          {@link MiniZincSetType}
	 * @param parameters    the list of parameters involved in this type lifting
	 *
	 * @return the generated MiniZinc parameter array declaration
	 */
	private String getParamsArray(
		boolean ofLifted,
		Set<RevokedAssumption> revoked,
		Object parameterType,
		List<LiftedParameter> parameters) {

		StringBuilder paramArrayName = new StringBuilder("params_");

		String paramArrayType;
		if (parameterType.equals(MiniZincSetType.class)) {
			paramArrayType = "set of int";
			paramArrayName.append("set");
		} else {
			paramArrayType = parameterType.toString();
			paramArrayName.append(parameterType.toString());
		}

		StringBuilder result = new StringBuilder("array[int] of ");

		if (ofLifted) {
			result.append("var ");
			paramArrayName.append("_lifted");
			paramArrayLiftedIdentifiers.add(paramArrayName.toString());
		} else {
			paramArrayIdentifiers.add(paramArrayName.toString());
		}

		result.append(paramArrayType);
		result.append(": ");
		result.append(paramArrayName);
		result.append(" = ");

		String ending = parameters.stream()
			.map(p -> p.paramArrayPiece(ofLifted, revoked.stream().toList()))
			.collect(Collectors.joining("\n\t ++ "));
		result.append(ending);
		result.append("\n;\n");

		return result.toString();
	}

	/**
	 * Applies all model customizations and generates the final MiniZinc model.
	 *
	 * @return the customized model source code
	 */
	@Override
	public String call() {
		StringBuilder result = new StringBuilder(baseModel);

		parameterTypeCollector.entrySet().stream().forEach(entry -> {
			Object key = entry.getKey();
			List<LiftedParameter> valueList = entry.getValue();
			result.append(getParamsArray(false, assumptions, key, valueList))
				.append("\n")
				.append(getParamsArray(true, assumptions, key, valueList))
				.append("\n");
		});

		result.append("include \"chuffed.mzn\";\n\n");

		result.append("array[int] of var bool: assumed = ");
		result.append(IntStream.range(0, paramArrayIdentifiers.size())
			.boxed()
			.map(i -> "[" + paramArrayLiftedIdentifiers.get(i) + "[i] = " + paramArrayIdentifiers.get(i)
				+ "[i] | i in index_set(" + paramArrayIdentifiers.get(i) + ")]")
			.collect(Collectors.joining("\n\t++"))
			+ ";\n\n");

		IntStream.range(0, paramArrayIdentifiers.size())
			.boxed()
			.forEach(i -> {
				result.append("""
					constraint assert(trace(
						\"Length of %s        = \\(length(%s))\\n\",
						length(%s)
					)""".formatted(paramArrayIdentifiers.get(i), paramArrayIdentifiers.get(i),
					paramArrayIdentifiers.get(i)));

				result.append("""
					= trace(
						\"Length of %s = \\(length(%s))\\n\",
						length(%s)
					), \"ERROR: length of parameters and lifted does not match\");\n
					""".formatted(paramArrayLiftedIdentifiers.get(i), paramArrayLiftedIdentifiers.get(i),
					paramArrayLiftedIdentifiers.get(i)));
			});

		result.append(getSolve());

		return result.toString();
	}

}
