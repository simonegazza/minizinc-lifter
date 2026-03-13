package me.simonegazza.lift.parameters;

import java.util.List;
import me.simonegazza.lift.requests.LiftRequest;

public abstract class LiftedParameter {
	public static LiftedParameter create(OriginalParameter parameter, List<LiftRequest> changes) {
//		if (changes.size() == 0)
//			throw new IllegalStateException(
//				"Asked to modify parameter " + parameter.getName() + " but no changes were asked");
//
//		if (changes.get(0) instanceof SimpleLiftRequest) {
//			if (changes.size() > 1)
//				throw new IllegalArgumentException(
//					"Trying multiple lifts of different kinds on " + parameter.getName());
//
//			LiftRequest change = changes.get(0);
//			MiniZincType type = parameter.getType();
//			if (type instanceof MiniZincBasicType) {
//
//			}
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

	public abstract String getLiftedDeclaration();

	public abstract List<String> getConstraints();

	public abstract String getSolvePiece();

	public abstract String getOutputPiece();
}
