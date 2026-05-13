package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.exception.UnimplementedException;
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
		// TODO: to do this properly, this function should be made recursive (on
		// the subtypes) but it would mean restructuring these classes, so I'll
		// leave it as is for now.
		MiniZincArrayType type = (MiniZincArrayType) parameter.getType();
		MiniZincType inner = type.getSubtype();
		return changes.stream()
			.map(c -> c.getOriginalLocations().toString())
			.map(location -> {
				StringBuilder result = new StringBuilder("abs(");
				if (inner instanceof MiniZincSetType) {
					// Sort of Jaccard Distance
					result.append("(card(")
						.append(getLiftedName())
						.append(" symdiff ")
						.append(getOriginalName())
						.append(")")
						.append(")");

				} else if (inner instanceof MiniZincArrayType) { // MiniZincArrayType
					throw new UnimplementedException(
						"Recursive arrays of arrays are not implemented in the solve piece");
				} else {
					result.append(getLiftedName())
						.append(location)
						.append(" - ")
						.append(getOriginalName())
						.append(location)
						.append(")");
				}
				return result.toString();
			}).collect(Collectors.joining(" + "));
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

		StringBuilder result = new StringBuilder(type.lift(Optional.empty(), true));
		result.append(": ");
		result.append(getLiftedName());
		result.append(" = array" + size + "d(");

		if (size == 1 && dimensions.get(0) instanceof MiniZincBasicType) {
			result.append("index_set(" + getOriginalName() + ")");
		} else {
			result.append(IntStream.range(0, size).mapToObj(i -> {
				MiniZincType d = dimensions.get(i);
				if (d instanceof MiniZincBasicType) {
					return "index_set_"
						+ (i + 1)
						+ "of"
						+ size
						+ "("
						+ getOriginalName()
						+ ")";
				} else {
					return d.toString();
				}
			}).collect(Collectors.joining(", ")));
		}

		result.append(", ");
		result.append(liftValue(environment));
		result.append(");");
		return result.toString();
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
