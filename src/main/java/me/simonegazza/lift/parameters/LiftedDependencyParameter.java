package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LiftedDependencyParameter extends LiftedParameter {

	protected LiftedDependencyParameter(OriginalParameter parameter) {
		super(parameter, List.of());
	}

	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ " = "
			+ parameter.getExpressionText()
			+ ";";
	}

	@Override
	public String getSolvePiece() {
		return "0";
	}

}
