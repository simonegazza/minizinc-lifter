package me.simonegazza.lift;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.lift.parameters.LiftedParameter;

/**
 * Customize a base model with assumption and revokes them when needed.
 */
public class Assumer {

	/**
	 * The base model to increment.
	 */
	private final String baseModel;

	/**
	 * The lifted parameters.
	 */
	private final List<LiftedParameter> lifted;

	/**
	 * The assumptions that needs to be revoked.
	 */
	private final Set<RevokedAssumption> assumptions;

	public Assumer(String baseModel, List<LiftedParameter> lifted, Set<RevokedAssumption> revokedAssumption) {
		this.baseModel = baseModel;
		this.lifted = lifted;
		assumptions = revokedAssumption;
	}

	/**
	 * Builds the parameter array (both versions).
	 *
	 * @param ofLifted whether it should be of lifted parameters or not
	 * @param revoked  the assumptions to be revoked
	 *
	 * @return the solve component of the combined lifts
	 */
	private String getParamsArray(boolean ofLifted, Set<RevokedAssumption> revoked) {
		StringBuilder result;
		if (ofLifted) {
			result = new StringBuilder("array[int] of var int: params_lifted = ");
		} else {
			result = new StringBuilder("array[int] of int: params = ");
		}

		String ending = lifted.stream()
			.map(p -> {
				List<RevokedAssumption> parameterAssumption = revoked.stream()
					.filter(a -> a.name().equals(p.getOriginalName()))
					.sorted()
					.toList();
				return p.paramArrayPiece(ofLifted, parameterAssumption);
			}).collect(Collectors.joining("\n\t++ "));
		result.append(ending);
		result.append("\n;\n");

		return result.toString();
	}

	/**
	 * Executes the customizations.
	 *
	 * @return the customized model
	 */
	public String execute() {
		StringBuilder result = new StringBuilder(baseModel)
			.append(getParamsArray(false, assumptions))
			.append("\n")
			.append(getParamsArray(true, assumptions))
			.append("\n");

		return result.toString();
	}

}
