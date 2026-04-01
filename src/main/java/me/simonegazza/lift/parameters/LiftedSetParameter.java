package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import me.simonegazza.lift.requests.LiftRequest;

/**
 * Represents a lifted version of a set parameter.
 * <p>
 * Although structurally similar to {@link LiftedSimpleParameter}, this class
 * exists to distinguish lifting behavior for set-based parameters and allow
 * specialization.
 */
public class LiftedSetParameter extends LiftedParameter {

	public LiftedSetParameter(
		OriginalParameter parameter,
		LiftRequest change,
		Set<OriginalParameter> dependencies) {

		super(parameter, List.of(change), dependencies);

		if (change.getBounds().isEmpty()) {
			throw new IllegalStateException("Cannot lift a set without bounding");
		}

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
		// Jaccard distance
		return "("
			+ "card(" + getLiftedName() + " symdiff " + getOriginalName() + ")"
			// commented to avoid performance loss
			// + " / "
			// + "card(" + getLiftedName() + " union " + getOriginalName() + ")"
			+ ")";
	}

}
