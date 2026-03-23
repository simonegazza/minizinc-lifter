package me.simonegazza.lift.parameters;

import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;

/**
 * Represents a lifted version of a set parameter.
 * <p>
 * Although structurally similar to {@link LiftedSimpleParameter}, this class
 * exists to distinguish lifting behavior for set-based parameters and allow
 * specialization.
 */
public class LiftedSetParameter extends LiftedParameter {

	protected LiftedSetParameter(
		OriginalParameter parameter,
		LiftRequest change) {

		super(parameter, List.of(change));
	}

	/**
	 * Contribution to the solve objective.
	 *
	 * @return a MiniZinc expression computing the distance between lifted and
	 *             original parameter
	 */
	@Override
	public String getSolvePiece() {
		return "abs(" + getLiftedName() + " - " + getOriginalName() + ")";
	}

}
