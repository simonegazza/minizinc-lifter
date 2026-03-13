package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;

public class LiftedSetParameter extends LiftedParameter {
	private LiftRequest change;

	public LiftedSetParameter(OriginalParameter parameter, LiftRequest change) {
		super(parameter, List.of(change));
		this.change = change;
	}

	@Override
	public String getLiftedDeclaration() {
		String bounding = parameter.getType().toString();
		if (change.getBounds().isPresent())
			bounding = change.getBounds().get();
		return "var set of "
			+ bounding
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

	@Override
	public String getOutputPiece() {
		return "[" + getOriginalName() + " = \\(" + getLiftedName() + ")]";
	}

}
