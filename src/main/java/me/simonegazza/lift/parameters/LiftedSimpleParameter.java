package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import me.simonegazza.lift.requests.LiftRequest;

/**
 * Represents a lifted version of a scalar (non-collection) parameter.
 * <p>
 * This class specializes {@link LiftedParameter} by defining how the parameter
 * contributes to the generated {@code solve} objective.
 * <p>
 * The lifting strategy introduces a new decision variable and minimizes the
 * distance between the lifted value and the original parameter value.
 */
public class LiftedSimpleParameter extends LiftedParameter {

	protected LiftedSimpleParameter(
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
			+ " :: doc_comment(\""
			+ getLiftedName()
			+ "\");";
	}

	@Override
	public String paramArrayPiece(boolean lifted) {
		return "[" + (lifted ? getLiftedName() : getOriginalName()) + "]";
	}

	@Override
	public String getSolvePiece() {
		return "abs(" + getLiftedName() + " - " + getOriginalName() + ")";
	}

}
