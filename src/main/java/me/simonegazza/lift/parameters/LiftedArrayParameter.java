package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.exception.UnimplementedException;

public class LiftedArrayParameter extends LiftedParameter {

	protected LiftedArrayParameter(
		OriginalParameter parameter,
		LiftRequest change,
		Set<OriginalParameter> dependencies) {

		super(parameter, List.of(change), dependencies);
	}

	@Override
	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ ";";
	}

	@Override
	public String getSolvePiece() {
		// TODO: to do this properly, this function should be made recursive (on
		// the subtypes) but it would mean restructuring these classes, so I'll
		// leave it as is for now.

		MiniZincArrayType ct = (MiniZincArrayType) parameter.getType();
		MiniZincType inner = ct.getSubtype();
		List<String> dimensionsExpression = ct.getDimensionsString(false);
		List<String> indices = IntStream
			.range(0, dimensionsExpression.size())
			.mapToObj(e -> "i" + e)
			.toList();

		StringBuilder firstPart = new StringBuilder("abs(");
		if (inner instanceof MiniZincSetType) {
			// Sort of Jaccard Distance
			firstPart.append("card(")
				.append(getLiftedName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]")
				.append(" symdiff ")
				.append(getOriginalName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]")
				.append(")")
				.append(")");

		} else if (inner instanceof MiniZincArrayType) { // MiniZincArrayType
			throw new UnimplementedException(
				"Recursive arrays of arrays are not implemented in the solve piece");
		} else {
			firstPart.append(getLiftedName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]")
				.append(" - ")
				.append(getOriginalName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]")
				.append(")");
		}

		String secondPart = IntStream.range(0, dimensionsExpression.size())
			.mapToObj(i -> indices.get(i) + " in " + dimensionsExpression.get(i))
			.collect(Collectors.joining(", "));

		return "sum([" + firstPart + " | " + secondPart + "])";
	}

}
