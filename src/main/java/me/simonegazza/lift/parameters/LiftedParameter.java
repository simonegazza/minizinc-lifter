package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;

/**
 * Represents a lifted version of an {@link OriginalParameter}.
 * <p>
 * Lifting is the process of transforming a fixed parameter into a decision
 * variable, optionally constrained by bounds, and introducing an optimization
 * objective that keeps the new variable as close as possible to the original
 * value.
 * <p>
 * This class encapsulates all the pieces required to perform this
 * transformation:
 * <ul>
 * <li>The original parameter</li>
 * <li>The list of lifting requests applied to it</li>
 * <li>The logic to generate new declarations, constraints, and objective
 * terms</li>
 * </ul>
 * <p>
 * Subclasses define how different kinds of parameters (e.g. scalar, set, array)
 * contribute to the optimization model.
 */
public abstract class LiftedParameter {

	/**
	 * Factory method that creates the appropriate {@link LiftedParameter}
	 * implementation based on the parameter type and requested changes.
	 * <p>
	 * Currently supports:
	 * <ul>
	 * <li>Scalar parameters transforms into {@link LiftedSimpleParameter}</li>
	 * <li>Set parameters transforms into {@link LiftedSetParameter}</li>
	 * <li>Array parameters transforms into {@link LiftedArrayParameter}</li>
	 * </ul>
	 * <p>
	 * Multiple or mixed requests will result in an exception.
	 *
	 * @param parameter the original parameter
	 * @param changes   the list of lift requests for this parameter
	 *
	 * @return a concrete {@code LiftedParameter}
	 *
	 * @throws IllegalStateException    if no changes are provided
	 * @throws IllegalArgumentException if incompatible changes are requested
	 */
	public static LiftedParameter create(
		OriginalParameter parameter,
		List<LiftRequest> changes) {

		List<LiftRequest> allChanges = new ArrayList<>(changes);
		if (changes.size() == 0) {
			System.out.println("% WARNING: lifting " + parameter + " too");
			return new LiftedDependencyParameter(parameter);
		} else if (allChanges.get(0) instanceof SimpleLiftRequest slc) {
			MiniZincType type = parameter.getType();
			if (type instanceof MiniZincBasicType)
				return new LiftedSimpleParameter(parameter, slc);
			else if (type instanceof MiniZincSetType)
				return new LiftedSetParameter(parameter, slc);
			else if (type instanceof MiniZincArrayType)
				return new LiftedArrayParameter(parameter, slc);
		} else if (allChanges.get(0) instanceof ArrayElementLiftRequest) {
			return new LiftedArrayElementParameter(parameter, allChanges);
		} else
			throw new IllegalStateException("Unkown type of lift request for " + parameter.getName());
		return null;
	}

	/**
	 * The original parameter being lifted.
	 */
	protected final OriginalParameter parameter;

	/**
	 * The original parameter being lifted.
	 */
	protected final List<LiftRequest> changes;

	/**
	 * Creates a lifted parameter from an original one and some lift requests.
	 *
	 * @param parameter the original parameter
	 * @param changes   a list of lifting request associated with this parameter
	 */
	protected LiftedParameter(OriginalParameter parameter, List<LiftRequest> changes) {
		this.parameter = parameter;
		this.changes = changes;

		boolean nameMatch = changes.stream()
			.map(LiftRequest::getName)
			.allMatch(n -> n.equals(parameter.getName()));
		if (!nameMatch)
			throw new IllegalStateException(
				"A lift requested for " + parameter.getName() + " had the wrong identifier");

		boolean sameConcreteType = changes.stream()
			.map(Object::getClass)
			.distinct()
			.count() <= 1;
		if (!sameConcreteType)
			throw new IllegalStateException(
				"Asking different kinds of lifts for " + parameter.getName());
	}

	/**
	 * Initial parameter.
	 *
	 * @return the parameter to call the lift onto.
	 */
	public OriginalParameter getParameter() {
		return parameter;
	}

	/**
	 * @return the original parameter name
	 */
	public String getOriginalName() {
		return parameter.getName();
	}

	/**
	 * @return the original parameter name
	 */
	public String getLiftedName() {
		return parameter.getName() + "_lifted";
	}

	/**
	 * Returns additional constraints introduced by the lifting.
	 * <p>
	 * Default implementation returns no constraints, but subclasses may
	 * override this to enforce structural or semantic properties.
	 *
	 * @return a list of MiniZinc constraint strings
	 */
	public List<String> getConstraints() {
		return new ArrayList<String>();
	}

	/**
	 * Generates the output fragment for this lifted parameter.
	 * <p>
	 * This is used to print the value of the lifted variable in the final
	 * model.
	 *
	 * @return a MiniZinc output string
	 */
	public String getOutputPiece() {
		return "\"" + getOriginalName() + " = \\(" + getLiftedName() + ")\"";
	}

	/**
	 * Return the declaration of the original parameter.
	 * <p>
	 * This uses the original parameter type and calls
	 * {@link MiniZincType#getDeclaration()}.
	 *
	 * @return the original MiniZinc declaration (with initialization)
	 */
	public String getOriginalDeclaration() {
		return parameter.getDeclaration();
	}

	/**
	 * Lift the parameter declaration
	 * <p>
	 * This uses the original parameter type and applies the lifting
	 * transformation defined by {@link MiniZincType#lift(Optional)}.
	 * <p>
	 * <b>This can modify the environment</b> (by adding the calculated value as
	 * a new key), especially in {@link LiftedArrayElementParameter}
	 *
	 * @return a MiniZinc declaration (without initialization)
	 */
	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ ";";
	}

	/**
	 * Generates the declaration of the lifted variable.
	 * <p>
	 * This uses the original parameter type and applies the lifting
	 * transformation defined by {@link MiniZincType#lift(Optional)}.
	 *
	 * @return a MiniZinc declaration (without initialization)
	 */
	public abstract String getSolvePiece();

}
