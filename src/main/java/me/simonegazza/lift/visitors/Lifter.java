package me.simonegazza.lift.visitors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
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

	public Lifter(
		TokenStream tokens,
		List<LiftRequest> toLift,
		DirectedGraph<OriginalParameter> parameters) {

		visitor = new LiftingVisitor();
		this.rewriter = new TokenStreamRewriter(tokens);

		this.lifted = toLift.stream()
			.collect(Collectors.groupingBy(LiftRequest::getName))
			.entrySet().stream()
			.map(mapEntry -> {
				String parameterName = mapEntry.getKey();
				List<LiftRequest> requests = mapEntry.getValue();

				OriginalParameter param = parameters.getNodes().stream()
					.filter(l -> l.getName().equals(parameterName))
					.findAny()
					// Parameter existence already checked in Main
					.get();

				return LiftedParameter.create(param, requests);
			}).toList();

	}

	public String execute(ModelContext ctx) {
		visitor.visitModel(ctx);
		return rewriter.getText();
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
//
//		@Override
//		public String visitSolveItem(SolveItemContext ctx) {
//
//		}
//
//		@Override
//		public String visitOutputItem(OutputItemContext ctx) {
//
//		}

	}

}
