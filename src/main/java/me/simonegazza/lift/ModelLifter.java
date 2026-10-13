package me.simonegazza.lift;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.assumptions.Assumer;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.utils.ApplicationLogger;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.visitors.Lifter;
import me.simonegazza.lift.visitors.ParameterExtractor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

/**
 * Lifts the requested parameters of a MiniZinc model.
 * <p>
 * The model does not need any data: parameters left undefined are expected to
 * be assigned by a data file when the lifted model is run. If data is present
 * (e.g., a data file appended to the model), the values of the lifted
 * parameters are also computed.
 * <p>
 * The steps executed are:
 * <ol>
 * <li>Parse the model using ANTLR ({@link MiniZincLexer} and
 * {@link MiniZincParser}).</li>
 * <li>Traverse the AST with {@link ParameterExtractor} to discover parameters
 * and their dependencies.</li>
 * <li>Validate the requested parameters.</li>
 * <li>Apply the lifting transformation using {@link Lifter}.</li>
 * </ol>
 */
public class ModelLifter implements Callable<ModelLifter.LiftedModel> {

	/**
	 * Application logger.
	 */
	private static final ApplicationLogger logger = ApplicationLogger.getLogger(ModelLifter.class.getSimpleName());

	/**
	 * The result of the lifting procedure.
	 *
	 * @param baseModel            the lifted model, without assumptions
	 * @param liftedParameters     all the lifted parameters (requested ones and
	 *                                 their dependencies)
	 * @param parametersToMaximize the lifted parameters that appear in the
	 *                                 objective function
	 */
	public record LiftedModel(
		String baseModel,
		List<LiftedParameter> liftedParameters,
		List<LiftedParameter> parametersToMaximize) {

		/**
		 * Builds the complete lifted model, adding assumptions and the
		 * objective function through {@link Assumer}.
		 *
		 * @param revokedAssumptions the indices of the assumptions to revoke
		 *
		 * @return the lifted MiniZinc model with assumptions
		 */
		public String withAssumptions(List<Integer> revokedAssumptions) {
			return new Assumer(
				baseModel,
				liftedParameters,
				parametersToMaximize,
				revokedAssumptions.stream().sorted().toList()).call();
		}
	}

	/**
	 * The MiniZinc model to lift.
	 */
	private final String model;

	/**
	 * The lift requests.
	 */
	private final List<LiftRequest> requests;

	/**
	 * Whether lifting sets and arrays of sets is disallowed.
	 */
	private final boolean setsDisallowed;

	/**
	 * Whether only the requested parameters appear in the objective function.
	 */
	private final boolean maximizeUserParamsOnly;

	/**
	 * Creates a lifter for a MiniZinc model.
	 *
	 * @param model                  the MiniZinc model source code
	 * @param requests               the parameters to lift
	 * @param setsDisallowed         if {@code true}, avoids lifting sets and
	 *                                   arrays of sets
	 * @param maximizeUserParamsOnly if {@code true}, only the requested
	 *                                   parameters appear in the objective
	 *                                   function
	 */
	public ModelLifter(
		String model,
		List<LiftRequest> requests,
		boolean setsDisallowed,
		boolean maximizeUserParamsOnly) {

		this.model = model;
		this.requests = requests;
		this.setsDisallowed = setsDisallowed;
		this.maximizeUserParamsOnly = maximizeUserParamsOnly;
	}

	/**
	 * Executes the lifting procedure.
	 *
	 * @return the lifted model
	 *
	 * @throws IllegalArgumentException if a requested parameter does not exist
	 *                                      or it is a set while sets are
	 *                                      disallowed
	 */
	@Override
	public LiftedModel call() {
		// Parse the original model
		logger.info("Parsing the original model...");
		CharStream input = CharStreams.fromString(model);
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		// Get the dependency graph of the parameters and verify the existence
		// of the parameters to be lifted
		ParameterExtractor pe = new ParameterExtractor(parser.model());
		ParameterGraph graph = pe.call();
		for (LiftRequest request : requests) {
			Optional<OriginalParameter> toLift = graph.getByName(request.getName());
			if (toLift.isEmpty()) {
				throw new IllegalArgumentException("Requested lift for "
					+ request.getName()
					+ " but it does not exists");
			}
			OriginalParameter p = toLift.get();
			if (setsDisallowed && (p.getType() instanceof MiniZincSetType
				|| p.getType() instanceof MiniZincArrayType t
					&& t.getSubtype() instanceof MiniZincSetType)) {
				throw new IllegalArgumentException("Requested lift for "
					+ request.getName()
					+ " that has set type but also requesting to disallowing sets");
			}
		}

		// Reset the token for the next pass
		tokens.seek(0);

		// Resolve the dependencies of the parameters and create base model
		logger.info("Lifting parameter representation...");
		Lifter lifter = new Lifter(
			tokens,
			parser.model(),
			requests,
			graph,
			setsDisallowed);
		String baseModel = lifter.call();

		List<LiftedParameter> liftedParameters = lifter.getLifted();

		List<LiftedParameter> parametersToMaximize;
		if (maximizeUserParamsOnly) {
			parametersToMaximize = liftedParameters.stream()
				.filter(p -> requests.stream()
					.anyMatch(c -> c.getName().equals(p.getOriginalName())))
				.toList();
		} else {
			parametersToMaximize = liftedParameters;
		}

		return new LiftedModel(baseModel, liftedParameters, parametersToMaximize);
	}
}
