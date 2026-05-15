package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.requests.ArrayElementLiftRequest;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.requests.SimpleLiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincCompositeType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

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
	 * Auxiliary method for returning a lifted name.
	 *
	 * @param name the name to lift
	 *
	 * @return the lifted name
	 */
	public final static String liftString(final String name) {
		return name + "_lifted";
	}

	/**
	 * Factory method that creates the appropriate {@link LiftedParameter}
	 * implementation based on the parameter type and requested changes.
	 * <p>
	 * Multiple or mixed request types will result in an exception.
	 *
	 * @param parameter the original parameter
	 * @param allLifts  all the parameter that are being lifted of this lift
	 * @param changes   the list of lift requests for this parameter
	 *
	 * @return a concrete {@code LiftedParameter}
	 *
	 * @throws IllegalStateException    if no changes are provided
	 * @throws IllegalArgumentException if incompatible changes are requested
	 */
	public static LiftedParameter create(
		OriginalParameter parameter,
		Set<OriginalParameter> allLifts,
		List<LiftRequest> changes) {

		List<LiftRequest> allChanges = new ArrayList<>(changes);
		// If there is no actual request, then this means that this lift is a
		// dependency one, so we create an artificial SimpleLiftRequest
		if (allChanges.size() == 0) {
			MiniZincType resultType;
			MiniZincType type = parameter.getType();
			if (type instanceof MiniZincBasicType bt) {
				resultType = bt;

			} else { // MiniZincCompositeType
				resultType = type;
				while (resultType instanceof MiniZincCompositeType ct) {
					resultType = ct.getSubtype();
				}
			}
			allChanges.add(new SimpleLiftRequest(
				parameter.getName(),
				Optional.of(resultType.toString())));
		}

		if (allChanges.get(0) instanceof SimpleLiftRequest slr) {
			MiniZincType type = parameter.getType();
			if (type instanceof MiniZincBasicType) {
				return new LiftedSimpleParameter(parameter, slr, allLifts);
			} else if (type instanceof MiniZincSetType) {
				return new LiftedSetParameter(parameter, slr, allLifts);
			} else if (type instanceof MiniZincArrayType) {
				return new LiftedArrayParameter(parameter, slr, allLifts);
			}
		} else if (changes.get(0) instanceof ArrayElementLiftRequest) {
			return new LiftedArrayElementParameter(parameter, changes, allLifts);
		} else {
			throw new IllegalStateException("Unkown type of lift request for " + parameter.getName());
		}
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
	 * Set of dependencies for this parameter.
	 */
	protected final Set<OriginalParameter> dependencies;

	/**
	 * Internal rewriter used to rewrite identifiers.
	 */
	protected class Rewriter extends MiniZincBaseVisitor<Void> {

		/**
		 * Token stream rewriter for modifying the expression text.
		 */
		private final TokenStreamRewriter rewriter;

		/**
		 * Get the original parameter by name.
		 *
		 * @param name the name of the {@link OriginalParameter}
		 *
		 * @return an {@link Optional} {@link OriginalParameter}
		 */
		private Optional<OriginalParameter> getByName(String name) {
			return dependencies.stream()
				.filter(l -> l.getName().equals(name))
				.findAny();
		}

		public Rewriter(TokenStream tokens) {
			rewriter = new TokenStreamRewriter(tokens);
		}

		@Override
		public Void visitIdent(IdentContext ctx) {
			Optional<OriginalParameter> op = getByName(ctx.getText());
			if (op.isPresent()) {
				rewriter.replace(
					ctx.IDENT().getSymbol(),
					LiftedParameter.liftString(op.get().getName()));
			}

			return null;
		}

		/**
		 * Get the text from the internal rewriter.
		 *
		 * @return the text of this rewriter
		 */
		public String getText() {
			return rewriter.getText();
		}

	}

	/**
	 * Creates a lifted parameter from an original one and some lift requests.
	 *
	 * @param parameter    the original parameter
	 * @param changes      a list of lifting request associated with this
	 *                         parameter
	 * @param dependencies a list of containing all the dependencies for this
	 *                         parameter
	 */
	protected LiftedParameter(
		OriginalParameter parameter,
		List<LiftRequest> changes,
		Set<OriginalParameter> dependencies) {

		this.parameter = parameter;
		this.changes = changes;
		this.dependencies = dependencies;

		boolean nameMatch = changes.stream()
			.map(LiftRequest::getName)
			.allMatch(n -> n.equals(parameter.getName()));
		if (!nameMatch) {
			throw new IllegalStateException(
				"A lift requested for " + parameter.getName() + " had the wrong identifier");
		}

		boolean sameConcreteType = changes.stream()
			.map(Object::getClass)
			.distinct()
			.count() <= 1;
		if (!sameConcreteType) {
			throw new IllegalStateException(
				"Asking different kinds of lifts for " + parameter.getName());
		}
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
		return liftString(parameter.getName());
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
	 * {@link OriginalParameter#getDeclaration()}.
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
	 *
	 * @param environment the environment used to evaluate expressions that are
	 *                        identifiers
	 *
	 * @return a MiniZinc declaration (with initialization)
	 */
	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		CharStream input = CharStreams.fromString(parameter.getExpressionText());
		MiniZincLexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		Rewriter rewriter = new Rewriter(tokens);
		rewriter.visit(parser.expr());

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ " = "
			+ rewriter.getText()
			+ " :: doc_comment(\""
			+ getLiftedName()
			+ "\");";
	}

	/**
	 * Get the dimensions of the lifted parameter.
	 *
	 * @return the dimensions for this lifted parameter
	 */
	public List<Integer> getDimensions() {
		return parameter.getDimensions();
	}

	/**
	 * Generates the contribution to the params arrays.
	 *
	 * @param lifted             whether we should consider lifted quantities or
	 *                               not
	 * @param revokedAssumptions the assumptions to be revoked
	 *
	 * @return a sum of absolute differences over selected indices
	 */
	public abstract String paramArrayPiece(boolean lifted, List<RevokedAssumption> revokedAssumptions);

	/**
	 * Generates the contribution to the objective function.
	 * <p>
	 * Only lifted elements contribute to the objective, each minimizing the
	 * absolute difference with the original value.
	 *
	 * @return a sum of absolute differences over selected indices
	 */
	public abstract String getSolvePiece();

}
