package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;

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
		MiniZincArrayType ct = (MiniZincArrayType) parameter.getType();
		List<String> dimensionsExpression = ct.getDimensionsString(false);
		List<String> indices = IntStream
			.range(0, dimensionsExpression.size())
			.mapToObj(e -> "i" + e)
			.toList();

		String firstPart = "abs("
			+ getLiftedName() + "[" + indices.stream().collect(Collectors.joining(", ")) + "]"
			+ " - "
			+ getOriginalName() + "[" + indices.stream().collect(Collectors.joining(", ")) + "]"
			+ ")";

		String secondPart = IntStream.range(0, dimensionsExpression.size())
			.mapToObj(i -> indices.get(i) + " in " + dimensionsExpression.get(i))
			.collect(Collectors.joining(", "));

		return "sum([" + firstPart + " | " + secondPart + "])";
	}

}
