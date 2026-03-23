package me.simonegazza.lift.parameters;

import java.util.List;
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
		LiftRequest change) {

		super(parameter, List.of(change));
	}

	/**
	 * Contribution to the solve objective.
	 * <p>
	 * The objective minimizes the absolute difference between the lifted
	 * variable and the original parameter value.
	 *
	 * @return a MiniZinc expression for the solve objective
	 */
	@Override
	public String getSolvePiece() {
		return "abs(" + getLiftedName() + " - " + getOriginalName() + ")";
	}

}
