package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.visitors.EvaluatorVisitor;

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
	private List<Object> liftedValue;

	private List<Object> liftValue(Map<String, Object> environment) {
		if (liftedValue != null) {
			return liftedValue;
		}

		// Evaluate the expression to get the actual value for the parameter.
		// Since this is an array, it must be an array too
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) parameter.getValue();

		changes.forEach(c -> {
			List<Integer> locations = c.getLocationsAdjusted(environment);
			// EvaluatorVisitor.get returns an Object, that can actually be a
			// list, so this cast is safe
			@SuppressWarnings("unchecked")
			List<Object> lastDimension = (List<Object>) (EvaluatorVisitor.get(
				result,
				locations.subList(0, locations.size() - 1)));
			// then we can just set it to the placeholder value.
			lastDimension.set(locations.getLast(), "_");
		});

		liftedValue = EvaluatorVisitor.flatten(result);

		return liftedValue;
	}

	public LiftedArrayElementParameter(
		OriginalParameter parameter,
		List<LiftRequest> changes,
		Set<OriginalParameter> dependencies) {

		super(parameter, changes, dependencies);

		if (!(parameter.getType() instanceof MiniZincArrayType)) {
			throw new IllegalArgumentException("Cannot lift single array elements for non-array parameters");
		}

		boolean allArrayElementLifts = changes.stream()
			.allMatch(l -> l.getClass().equals(ArrayElementLiftRequest.class));
		if (!allArrayElementLifts) {
			throw new IllegalStateException(
				"Asking for an array element lift for " + parameter.getName() + " but other kinds of lift found");
		}
		this.changes = changes.stream()
			.map(ArrayElementLiftRequest.class::cast)
			.toList();
	}

	@Override
	public String getOriginalDeclaration() {
		MiniZincArrayType type = (MiniZincArrayType) parameter.getType();
		return type.toString(true)
			+ ": "
			+ parameter.getName()
			+ " = "
			+ parameter.getExpressionText()
			+ ";";
	}

	@Override
	public String getSolvePiece() {
		return changes.stream()
			.map(c -> c.getOriginalLocations().toString())
			.map(location -> "abs("
				+ getLiftedName() + location
				+ " - "
				+ getOriginalName() + location
				+ ")")
			.collect(Collectors.joining(" + "));
	}

	@Override
	public String getOutputPiece() {
		return "\"" + getOriginalName() + " = \\(" + getLiftedName() + ")\"";
	}

	@Override
	public String liftDeclaration(Map<String, Object> environment) {
		MiniZincArrayType type = (MiniZincArrayType) parameter.getType();
		List<MiniZincType> dimensions = type.getDimensions();
		int size = dimensions.size();
		return type.lift(Optional.empty(), true)
			+ ": "
			+ getLiftedName()
			+ " = array" + size + "d("
			+ dimensions.stream()
				.map(MiniZincType::toString)
				.collect(Collectors.joining(", "))
			+ ", "
			+ liftValue(environment)
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
