package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;

public class LiftedArrayParameter extends LiftedParameter {

	public LiftedArrayParameter(OriginalParameter parameter, LiftRequest change) {
		super(parameter, List.of(change));
	}

	@Override
	public String getLiftedDeclaration() {
		return parameter.getType().lift(changes.get(0).getBounds())
			+ ": "
			+ getLiftedName();
	}

	@Override
	public String getSolvePiece() {
		MiniZincArrayType ct = (MiniZincArrayType) parameter.getType();
		List<String> dimensionsExpression = ct.getDimensionsString();
		List<String> indices = IntStream
			.range(0, dimensionsExpression.size())
			.mapToObj(e -> "i" + e)
			.toList();

		String firstPart = "abs("
			+ getOriginalName() + "[" + indices.stream().collect(Collectors.joining(", ")) + "]"
			+ " - "
			+ getLiftedDeclaration() + "[" + indices.stream().collect(Collectors.joining(", ")) + "]"
			+ ")";

		String secondPart = IntStream.range(0, dimensionsExpression.size())
			.mapToObj(i -> indices.get(i) + " in " + dimensionsExpression.get(i))
			.collect(Collectors.joining(", "));

		return "sum([" + firstPart + " | " + secondPart + "]);";
	}

}
