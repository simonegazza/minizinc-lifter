package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.visitors.ExpressionVisitor;

/**
 * Represents a lifted parameter where only specific elements of an array are
 * turned into decision variables.
 * <p>
 * Only selected positions (provided via {@link ArrayElementLiftRequest}) are
 * replaced with decision variables, while all other elements remain fixed.
 * <p>
 * This class supports multi-dimensional arrays.
 */
public class LiftedArrayElementParameter extends LiftedParameter {

	/**
	 * The list of element-wise lift requests.
	 */
	private final List<ArrayElementLiftRequest> changes;

	/**
	 * Internal representation of the array after lifting transformation.
	 * <p>
	 * This is a nested list structure mirroring the original array shape, where
	 * lifted elements are replaced with "_".
	 */
	private final List<?> liftedValue;

	/**
	 * Replaces a specific element inside a multi-dimensional array structure
	 * with the anonymous placeholder "_".
	 * <p>
	 * The method navigates the nested list using the provided indices and
	 * updates the target position.
	 *
	 * @param multidemensional the nested list representing the array
	 * @param locations        the indices identifying the element to lift
	 */
	private void liftValueInMultidimensional(Object multidemensional, List<Integer> locations) {
		List<?> list = (List<?>) multidemensional;

		// Traverse dimensions except the last
		for (Integer l : locations.subList(0, locations.size() - 1))
			list = (List<?>) list.get(l);

		// Safe cast since last wrap on this list is a List<String>
		@SuppressWarnings("unchecked")
		List<String> l = (List<String>) list;
		Integer location = Integer.valueOf(locations.get(locations.size() - 1));
		l.set(location, "_");
	}

	/**
	 * Flattens a multi-dimensional array (nested lists) into a linear list.
	 * <p>
	 * This is required to generate MiniZinc {@code arrayXd(...)} declarations,
	 * which expect a flat list of values.
	 *
	 * @param obj nested list or string
	 *
	 * @return flattened list of values
	 */
	public static List<String> flattenMultidimensional(Object obj) {
		List<String> result = new ArrayList<>();

		if (obj instanceof List<?> objList)
			for (Object item : objList)
				result.addAll(flattenMultidimensional(item));
		else if (obj instanceof String)
			result.add((String) obj);

		return result;
	}

	/**
	 * Constructs a lifted array element parameter.
	 * <p>
	 * Validates that:
	 * <ul>
	 * <li>The parameter is an array</li>
	 * <li>All lift requests are element-based</li>
	 * </ul>
	 * <p>
	 * The original array value is parsed and transformed by replacing selected
	 * elements with placeholders.
	 *
	 * @param parameter the original array parameter
	 * @param changes   the list of element-wise lift requests
	 */
	protected LiftedArrayElementParameter(OriginalParameter parameter, List<LiftRequest> changes) {
		super(parameter, changes);

		if (!(parameter.getType() instanceof MiniZincArrayType))
			throw new IllegalArgumentException("Cannot lift single array elements for non-array parameters");

		boolean allArrayElementLifts = changes.stream()
			.allMatch(l -> l.getClass().equals(ArrayElementLiftRequest.class));
		if (!allArrayElementLifts)
			throw new IllegalStateException(
				"Asking for an array element lift for " + parameter.getName() + " but other kinds of lift found");
		this.changes = changes.stream().map(ArrayElementLiftRequest.class::cast).toList();

		// Put the "_" in the right place
		Object arrParsed = new ExpressionVisitor().visit(parameter.getValue());
		this.changes.forEach(c -> liftValueInMultidimensional(arrParsed, c.getLocationsAdjusted()));
		this.liftedValue = (List<?>) arrParsed;
	}

	/**
	 * Generates the contribution to the objective function.
	 * <p>
	 * Only lifted elements contribute to the objective, each minimizing the
	 * absolute difference with the original value.
	 *
	 * @return a sum of absolute differences over selected indices
	 */
	@Override
	public String getSolvePiece() {
		return this.changes.stream()
			.map(c -> c.getOriginalLocations().toString())
			.map(location -> "abs("
				+ getLiftedName() + location
				+ " - "
				+ getOriginalName() + location
				+ ")")
			.collect(Collectors.joining(" + "));
	}

	/**
	 * Generates the contribution to the objective function.
	 * <p>
	 * Only lifted elements contribute to the objective, each minimizing the
	 * absolute difference with the original value.
	 *
	 * @return a sum of absolute differences over selected indices
	 */
	public String getOutputPiece() {
		return "\"" + getOriginalName() + " = \\(" + getLiftedName() + ")\"";
	}

	@Override
	public String getLiftedDeclaration() {
		List<MiniZincType> dimensions = ((MiniZincArrayType) (parameter.getType())).getDimensions();
		return parameter.getType().lift(changes.get(0).getBounds())
			+ ": "
			+ getLiftedName()
			+ " = array" + dimensions.size() + "d("
			+ dimensions.stream()
				.map(MiniZincType::toString)
				.collect(Collectors.joining(", "))
			+ ", "
			+ flattenMultidimensional(liftedValue)
			+ ");";
	}

	/**
	 * Generates the lifted array declaration.
	 * <p>
	 * The declaration uses {@code arrayXd(...)} with:
	 * <ul>
	 * <li>Original dimensions</li>
	 * <li>Flattened values with "_" for lifted elements</li>
	 * </ul>
	 *
	 * @return a MiniZinc array declaration
	 */
	@Override
	public List<String> getConstraints() {
		return changes.stream()
			.filter(c -> c.getBounds().isPresent())
			.map(c -> "constraint "
				+ getLiftedName()
				+ c.getOriginalLocations().toString()
				+ " in "
				+ c.getBounds().get()
				+ ";")
			.toList();
	}

}
