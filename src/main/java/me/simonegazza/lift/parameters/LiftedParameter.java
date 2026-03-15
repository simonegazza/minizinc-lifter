package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;

public abstract class LiftedParameter {
	public static LiftedParameter create(OriginalParameter parameter, List<LiftRequest> changes) {
		if (changes.size() == 0)
			throw new IllegalStateException(
				"Asked to modify parameter " + parameter.getName() + " but no changes were asked");

		if (changes.get(0) instanceof SimpleLiftRequest) {
			if (changes.size() > 1)
				throw new IllegalArgumentException(
					"Trying multiple lifts of different kinds on " + parameter.getName());

			LiftRequest change = changes.get(0);
			MiniZincType type = parameter.getType();
			if (type instanceof MiniZincBasicType)
				return new LiftedSimpleParameter(parameter, change);
			else if (type instanceof MiniZincSetType)
				return new LiftedSetParameter(parameter, change);
			else if (type instanceof MiniZincArrayType)
				return new LiftedArrayParameter(parameter, change);
		}
//		else {
//			// multiple ArrayElementLiftRequest
//		}

		return null;
	}

	protected final OriginalParameter parameter;
	protected final List<LiftRequest> changes;

	public LiftedParameter(OriginalParameter parameter, List<LiftRequest> changes) {
		this.parameter = parameter;
		this.changes = changes;

		if (this.changes.size() == 0) {
			throw new IllegalStateException(
				"Cannot construct a lift for " + parameter.getName() + " from an empty list");
		}
	}

	public String getOriginalName() {
		return parameter.getName();
	}

	public String getLiftedName() {
		return parameter.getName() + "_lifed";
	}

	public List<String> getConstraints() {
		return new ArrayList<String>();
	}

	public String getOutputPiece() {
		return getOriginalName() + " = \\(" + getLiftedName() + ")";
	}

	public abstract String getLiftedDeclaration();

	public abstract String getSolvePiece();

}
