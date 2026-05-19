package me.simonegazza.lift.assumptions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.lift.parameters.LiftedParameter;

/**
 * Enriches a base MiniZinc model with assumption handling logic and applies
 * assumption revocations when required.
 */
public class Assumer {

	/**
	 * Original MiniZinc model before assumption injection.
	 */
	private final String baseModel;

	/**
	 * Collection of lifted parameters used to generate the parameter arrays.
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
	 * Builds one of the parameter arrays used by the generated model.
	 * <p>
	 * Depending on the selected mode, the method produces either:
	 * <ul>
	 * <li>the concrete parameter array</li>
	 * <li>the lifted parameter array</li>
	 * </ul>
	 * while applying the required revoked assumptions.
	 *
	 * @param ofLifted whether the generated array should be of lifted
	 *                     parameters
	 * @param revoked  the assumptions to revoke while generating the array
	 *
	 * @return the generated MiniZinc parameter array declaration
	 */
	private String getParamsArray(boolean ofLifted, Set<RevokedAssumption> revoked) {
		StringBuilder result;
		// We add "opt" in case there are "array of sets" to be lifted
		if (ofLifted) {
			result = new StringBuilder("array[int] of var opt int: params_lifted = ");
		} else {
			result = new StringBuilder("array[int] of int: params = ");
		}

		String ending = lifted.stream()
			.map(p -> {
				List<RevokedAssumption> parameterAssumption = revoked.stream()
					.filter(a -> a.name().equals(p.getLiftedName()))
					.sorted()
					.toList();
				return p.paramArrayPiece(ofLifted, parameterAssumption);
			}).collect(Collectors.joining("\n\t++ "));
		result.append(ending);
		result.append("\n;\n");

		return result.toString();
	}

	/**
	 * Applies all model customizations and generates the final MiniZinc model.
	 *
	 * @return the customized model source code
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
