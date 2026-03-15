package me.simonegazza.lift.parameters;

import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;

public class LiftedSimpleParameter extends LiftedParameter {
	public LiftedSimpleParameter(OriginalParameter parameter, LiftRequest change) {
		super(parameter, List.of(change));
	}

	@Override
	public String getSolvePiece() {
		return "abs(" + getLiftedName() + " - " + getOriginalName() + ")";
	}

}
