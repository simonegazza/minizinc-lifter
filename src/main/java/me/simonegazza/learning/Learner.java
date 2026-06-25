package me.simonegazza.learning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.assumptions.Assumer;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.utils.exception.UnimplementedException;

public class Learner implements Callable<String> {

	/**
	 * List of parameters divided by base type.
	 */
	private final Map<Object, List<LiftedParameter>> collector;

	/**
	 * The names of the parameter array.
	 */
	private final List<String> paramArrayIdentifiers;

	/**
	 * The names of the lifted parameter array.
	 */
	private final List<String> paramArrayLiftedIdentifiers;

	/**
	 * Number of instances.
	 */
	private final int instances;

	public Learner(int instances, Collection<LiftedParameter> lifted) {
		collector = Assumer.collectParameterTypes(lifted);
		this.instances = instances;
		paramArrayIdentifiers = new ArrayList<>();
		paramArrayLiftedIdentifiers = new ArrayList<>();
	}

	/**
	 * A hacky workaround for obtaining the lifted parameter piece.
	 *
	 * @param p        the parameter used for the piece
	 * @param ofLifted whether it should be lifted or not
	 *
	 * @return the modified string for this parameter
	 */
	private String paramArrayPiece(LiftedParameter p, boolean ofLifted) {
		String result = p.paramArrayPiece(ofLifted);

		if (!ofLifted) {
			if (p.getParameter().getType() instanceof MiniZincArrayType) {
				return result.replaceFirst("\\[i0", "[i, i0");
			} else if (p.getParameter().getType() instanceof MiniZincSetType) {
				throw new UnimplementedException("'paramArrayPiece' function not implemented for this type");
			} else {
				return result.replaceFirst("]", "[i]]");
			}
		}
		return result;
	}

	/**
	 * A hacky workaround for obtaining the solver piece.
	 *
	 * @param p the parameter used for the piece
	 *
	 * @return the modified string for this parameter
	 */
	private String solvePiece(LiftedParameter p) {
		String result = p.getSolvePiece();
		return result.replaceFirst(
			p.getOriginalName() + "\\[",
			p.getOriginalName() + "\\[i, ");
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
	 * @param parameterType this is either a String or a Class object depending
	 *                          if the parameter was a simple parameter or a
	 *                          {@link MiniZincSetType}
	 * @param parameters    the list of parameters involved in this type lifting
	 *
	 * @return the generated MiniZinc parameter array declaration
	 */
	private String getParamsArray(
		boolean ofLifted,
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

		StringBuilder result = new StringBuilder(
			ofLifted ? "array[int] of var " : "array[int] of array[int] of ");

		if (ofLifted) {
			paramArrayName.append("_lifted");
			paramArrayLiftedIdentifiers.add(paramArrayName.toString());
		} else {
			paramArrayIdentifiers.add(paramArrayName.toString());
		}

		result.append(paramArrayType);
		result.append(": ");
		result.append(paramArrayName);
		result.append(" = ");
		if (!ofLifted) {
			result.append("[");
		}
		result.append("\n\t");

		result.append(parameters.stream()
			.map(p -> paramArrayPiece(p, ofLifted))
			.collect(Collectors.joining("\n\t ++ ")));
		if (!ofLifted) {
			result.append("\n\t| i in instances]");
		}
		result.append(";\n");

		return result.toString();
	}

	/**
	 * Builds the solve statement based on lifted parameters.
	 *
	 * @return the solve component of the combined lifts
	 */
	private String getSolve() {
		StringBuilder obj = new StringBuilder("array[instances] of var ");
		if (collector.containsKey("float")) {
			obj.append("float");
		} else {
			obj.append("int");
		}

		obj.append(": objective_lifted :: output_var = [\n\t");
		obj.append(
			collector.values().stream()
				.flatMap(List::stream)
				.sorted()
				.map(this::solvePiece)
				.collect(Collectors.joining("\n\t+ ")));
		obj.append("\n\t| i in instances\n];\n\n");

		return obj.toString();
	}

	@Override
	public String call() throws Exception {
		StringBuilder sb = new StringBuilder("include \"experimental/assume/fzn_assume.mzn\";\n\n");
		sb.append("int: max_instances = " + instances + ";\n");
		sb.append("set of int: instances = 1..max_instances;\n");
		sb.append("var instances: current_instance;\n\n");

		collector.entrySet().stream().forEach(entry -> {
			Object key = entry.getKey();
			List<LiftedParameter> valueList = entry.getValue();
			sb.append(getParamsArray(false, key, valueList))
				.append("\n")
				.append(getParamsArray(true, key, valueList))
				.append("\n");
		});

		sb.append("array[int] of array[int] of var bool: assumed = [\n\t");
		sb.append(IntStream.range(0, paramArrayIdentifiers.size()).boxed()
			.map(i -> "["
				+ paramArrayLiftedIdentifiers.get(i)
				+ "[i0] = "
				+ paramArrayIdentifiers.get(i)
				+ "[i][i0] | i0 in index_set(" + paramArrayLiftedIdentifiers.get(i) + ")]")
			.collect(Collectors.joining("\n\t++")));
		sb.append("\n\t| i in instances];\n\n");

		sb.append("constraint nogood_learning(");
		sb.append("\n\t[assumed[i][i0] | i in instances, i0 in index_set(assumed[i])],");
		sb.append("\n\tlength(instances),\n\tcurrent_instance\n);\n\n");

		sb.append(getSolve() + "\n\n");

		sb.append(collector.values().stream()
			.flatMap(List::stream)
			.map(l -> "\\noriginal " + l.getOriginalName()
				+ " = \\(" + l.getOriginalName() + "[fix(current_instance), .., ..])\\n"
				+ "lifted   " + l.getOriginalName()
				+ " = \\(" + l.getLiftedName() + ")\\n\\n\"]")
			.collect(Collectors.joining(
				" ++ ",
				"output [\"current_instance = \\(current_instance)",
				";\n")));

		return sb.toString();
	}

}
