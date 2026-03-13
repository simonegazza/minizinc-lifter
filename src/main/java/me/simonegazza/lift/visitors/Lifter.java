package me.simonegazza.lift.visitors;

import java.util.List;
import java.util.Optional;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.MiniZincParameter;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.utils.DirectedGraph;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class Lifter {
	private final LiftingVisitor visitor;
	private final TokenStreamRewriter rewriter;
	private final List<LiftRequest> toLift;
	private final DirectedGraph<MiniZincParameter> parameters;

	public Lifter(
		TokenStream tokens,
		List<LiftRequest> toLift,
		DirectedGraph<MiniZincParameter> parameters) {

		visitor = new LiftingVisitor();
		this.rewriter = new TokenStreamRewriter(tokens);
		this.toLift = toLift;
		this.parameters = parameters;
	}

	public String execute(ModelContext ctx) {
		visitor.visitModel(ctx);
		return rewriter.getText();
	}

	private class LiftingVisitor extends MiniZincBaseVisitor<Void> {
		private Optional<LiftRequest> getByName(String name) {
			return toLift.stream()
				.filter(l -> l.getName().equals(name))
				.findAny();
		}

		private Optional<MiniZincParameter> getByLiftRequest(LiftRequest lr) {
			return parameters.getNodes().stream()
				.filter(l -> l.getName().equals(lr.getName()))
				.findAny();
		}

		@Override
		public Void visitAssignItem(AssignItemContext ctx) {
			Optional<LiftRequest> lr = getByName(ctx.getText());
			if (lr.isPresent())
				rewriter.delete(ctx.getStart(), ctx.getStop());

			return null;
		}

		@Override
		public Void visitVarDeclItem(VarDeclItemContext ctx) {
			Optional<LiftRequest> lr = getByName(ctx.getText());
			if (lr.isPresent()) {
				Optional<MiniZincParameter> param = getByLiftRequest(lr.get());

			}

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

//		@Override
//		public Void visitIdent(IdentContext ctx) {
//			Optional<LiftRequest> lr = getByName(ctx.getText());
//			if (lr.isPresent()) {
//				Optional<MiniZincParameter> param = getByLiftRequest(lr.get());
//				rewriter.replace(
//					ctx.getStart(),
//					ctx.getStop(),
//					param.get().getLiftedName());
//			}
//
//			return null;
//		}

	}

}
