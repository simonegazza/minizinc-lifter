package me.simonegazza.lift.visitors;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.OutputItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SolveItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.DirectedGraph;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class Lifter {
	private final LiftingVisitor visitor;
	private final TokenStreamRewriter rewriter;
	private final List<LiftedParameter> lifted;

	/**
	 * Termination is guaranteed because graph is topological. This is a
	 * fixpoint algorithm
	 *
	 * @param graph the current graph. Must not contain cycles
	 *
	 * @return the set of identifiers that a certain parameter depends on
	 *             (incluse dependencies up above the chain)
	 */
	private static Set<String> dependsOn(
		DirectedGraph<OriginalParameter> parameterGraph,
		String parameter) {

		Set<String> dependents = Set.of(parameter);
		while (true) {
			final Set<String> currentDependents = dependents;

			Set<String> newDependents = parameterGraph.getNodes().stream()
				.filter(p -> parameterGraph.getAdjacent(p).stream()
					.anyMatch(a -> currentDependents.contains(a.getName())))
				.map(OriginalParameter::getName)
				.collect(Collectors.toSet());

			if (dependents.equals(newDependents))
				break;

			dependents = newDependents;
		}

		return dependents;

	}

	public Lifter(
		TokenStream tokens,
		List<LiftRequest> toLift,
		DirectedGraph<OriginalParameter> parameters) {

		visitor = new LiftingVisitor();
		this.rewriter = new TokenStreamRewriter(tokens);

		// collect all dependencies
		var liftedStream = toLift.stream()
			// get all names
			.map(LiftRequest::getName)
			// avoid duplicates
			.distinct()
			// flat the dependencies for each lift
			.flatMap(pName -> dependsOn(parameters, pName).stream())
			// some parameters could have the same dependencies, we eant to
			// avoid counting them twice
			.distinct();

		this.lifted = Stream.concat(liftedStream, toLift.stream().map(LiftRequest::getName))
			// get back the OriginalParameter based on the name
			.map(s -> parameters.getNodes().stream()
				.filter(p -> p.getName().equals(s))
				.findAny())

			// if something fails here, the error is very weird. It would mean
			// that the name of the parameters used to asking a lift does not
			// match with any of the parameter found in the model (even though
			// we already check before that such parameter would exists). So it
			// is safe to Optional::get here without checking
			.map(Optional::get)
			.distinct()
			// now, for each parameter we got, we get a list of all the lifts
			// for that parameter (it could be an empty list if the parameter
			// was just a dependency and was not requested for an
			// actual lift)
			.map(op -> LiftedParameter.create(
				op,
				toLift.stream()
					.filter(l -> l.getName().equals(op.getName()))
					.toList()))
			.toList();
	}

	public String execute(ModelContext ctx) {
		visitor.visitModel(ctx);
		StringBuilder model = new StringBuilder(rewriter.getText());
		for (LiftedParameter lp : this.lifted)
			model.append("\n" + lp.getLiftedDeclaration() + "\n");

		return model.toString();
	}

	private class LiftingVisitor extends MiniZincBaseVisitor<Void> {

		private Optional<LiftedParameter> getByName(String name) {
			return lifted.stream()
				.filter(l -> l.getOriginalName().equals(name))
				.findAny();
		}

		@Override
		public Void visitAssignItem(AssignItemContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.getText());
			if (p.isPresent())
				rewriter.delete(ctx.getStart(), ctx.getStop());

			return null;
		}

		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.getText());
			if (p.isPresent())
				rewriter.delete(ctx.getStart(), ctx.getStop());

			return null;
		}

		@Override
		public Void visitIdent(IdentContext ctx) {
			Optional<LiftedParameter> p = getByName(ctx.getText());
			if (p.isPresent())
				rewriter.replace(
					ctx.getStart(),
					ctx.getStop(),
					p.get().getLiftedName());

			return null;
		}

		@Override
		public Void visitSolveItem(SolveItemContext ctx) {
			String replace = lifted.stream()
				.map(LiftedParameter::getSolvePiece)
				.collect(Collectors.joining(" + "));
			rewriter.replace(ctx.getStart(), ctx.getStop(), "solve minimize " + replace + ";\n");
			return null;
		}

		@Override
		public Void visitOutputItem(OutputItemContext ctx) {
			String replace = lifted.stream()
				.map(LiftedParameter::getOutputPiece)
				.collect(Collectors.joining("\n"));
			rewriter.replace(ctx.getStart(), ctx.getStop(), "output [\" " + replace + "\"];\n");
			return null;
		}

	}

}
