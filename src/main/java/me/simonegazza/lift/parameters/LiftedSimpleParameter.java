package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;

public class LiftedSimpleParameter extends LiftedParameter {
	public LiftedSimpleParameter(OriginalParameter parameter, LiftRequest change) {
		super(parameter, List.of(change));
	}

	@Override
	public String getLiftedDeclaration() {
		return parameter.getType().lift(changes.get(0).getBounds())
			+ ": "
			+ getLiftedName();
	}

	@Override
	public List<String> getConstraints() {
		return new ArrayList<String>();
	}

	@Override
	public String getSolvePiece() {
		return "abs(" + getLiftedName() + " - " + getOriginalName() + ")";
	}

}
