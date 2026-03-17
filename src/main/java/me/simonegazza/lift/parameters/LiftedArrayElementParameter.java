package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.visitors.ExpressionVisitor;

public class LiftedArrayElementParameter extends LiftedParameter {
	private final List<ArrayElementLiftRequest> changes;

	// A multidimensional array
	private final List<?> liftedValue;

	private void liftValueInMultidimensional(Object multidemensional, List<Integer> locations) {
		List<?> list = (List<?>) multidemensional;
		// This list is an array up to the last element
		for (Integer l : locations.subList(0, locations.size() - 1))
			list = (List<?>) list.get(l);

		// Safe cast since last wrap on this list is a List<String>
		@SuppressWarnings("unchecked")
		List<String> l = (List<String>) list;
		Integer location = Integer.valueOf(locations.get(locations.size() - 1));
		l.set(location, "_");
	}

	public static List<String> flattenMultidimensional(Object obj) {
		List<String> result = new ArrayList<>();

		if (obj instanceof List<?> objList)
			for (Object item : objList)
				result.addAll(flattenMultidimensional(item));
		else if (obj instanceof String)
			result.add((String) obj);

		return result;
	}

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

		Object arrParsed = new ExpressionVisitor().visit(parameter.getValue());
		this.changes.forEach(c -> liftValueInMultidimensional(arrParsed, c.getLocationsAdjusted()));
		this.liftedValue = (List<?>) arrParsed;
	}

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
