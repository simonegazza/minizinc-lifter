package me.simonegazza.learning;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayLiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.GeneratorListContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.PrimaryContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SetLiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.LiftedParameter;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.utils.ParameterGraph;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Rewrites a derived parameter declaration.
 */
public class DeclarationRewriter extends MiniZincBaseVisitor<Void> implements Callable<String> {

	/**
	 * Parameter used to do the model rewrite.
	 */
	private final LiftedParameter parameter;

	/**
	 * Dependencies of the parameter.
	 */
	private final Set<LiftedParameter> dependencies;

	/**
	 * Original model to modify.
	 */
	private final String model;

	/**
	 * Rewriter to change model String.
	 */
	private final TokenStreamRewriter rewriter;

	/**
	 * Internal parser used to change the model.
	 */
	private final MiniZincParser parser;

	public DeclarationRewriter(
		LiftedParameter parameter,
		Collection<LiftedParameter> liftedParameters,
		ParameterGraph graph,
		String model) {
		this.parameter = parameter;
		this.model = model;
		dependencies = graph.getAdjacent(parameter.getParameter()).stream()
			.filter(op -> liftedParameters.stream().anyMatch(lp -> lp.getOriginalName().equals(op.getName())))
			.map(op -> liftedParameters.stream()
				.filter(lp -> lp.getOriginalName().equals(op.getName()))
				.findFirst()
				.get())
			.collect(Collectors.toSet());

		CharStream input = CharStreams.fromString(model);
		MiniZincLexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		this.parser = new MiniZincParser(tokens);
		this.rewriter = new TokenStreamRewriter(tokens);
	}

	@Override
	public String call() {
		visitModel(parser.model());
		return rewriter.getText();
	}

	@Override
	public Void visitItem(ItemContext ctx) {
		if (ctx.varDeclItem() != null) {
			visitVarDeclItem(ctx.varDeclItem());
		}

		return null;
	}

	@Override
	public Void visitVarDeclItem(VarDeclItemContext ctx) {
		if (ctx.tiExprAndId().ident().getText().equals(parameter.getOriginalName())) {
			visitTiExprAndId(ctx.tiExprAndId());

			if (ctx.expr() != null) {

				// This is a single array and it must now become a 2d array, so
				// we put it in front
				if (ctx.expr().getText().startsWith("[")) {
					MiniZincArrayType type = (MiniZincArrayType) parameter.getParameter().getType();
					rewriter.insertBefore(
						ctx.expr().getStart(),
						"array2d(instances, "
							+ type.getDimensionsString(false).stream().collect(Collectors.joining(", "))
							+ ", ");

					rewriter.insertAfter(ctx.expr().getStop(), ")");
				}

				visitExpr(ctx.expr());
			}
		}

		return null;
	}

	@Override
	public Void visitTiExprAndId(TiExprAndIdContext ctx) {
		return visitTiExpr(ctx.tiExpr());
	}

	@Override
	public Void visitBaseTiExpr(BaseTiExprContext ctx) {
		rewriter.insertBefore(ctx.getStart(), "array[instances] of ");
		return null;
	}

	@Override
	public Void visitArrayTiExpr(ArrayTiExprContext ctx) {
		rewriter.insertBefore(ctx.tiExpr().get(0).getStart(), "instances, ");
		return null;
	}

	@Override
	public Void visitAssignItem(AssignItemContext ctx) {
		throw new UnimplementedException("Case not considered");
	}

	@Override
	public Void visitPrimary(PrimaryContext ctx) {
		if (ctx.ident() != null) {
			// arrayXd case: we need to up the dimension, insert the first
			// argument and visit it inside
			if (ctx.ident().getText().startsWith("array")) {
				String dims = ctx.ident().getText();
				dims = dims.substring(5, dims.length() - 1);
				rewriter.replace(
					ctx.ident().getStart(),
					ctx.ident().getStop(),
					"array" + (Integer.parseInt(dims) + 1) + "d");

				// insert "instances" argument
				rewriter.insertAfter(ctx.postfix().get(0).getStart(), "instances, ");

				// visit the inner part
				return super.visitPostfix(ctx.postfix().get(0));
			} else if (dependencies.stream().anyMatch(d -> d.getOriginalName().equals(ctx.ident().getText()))) {

				if (ctx.postfix().get(0).arrayAccessTail() == null) {
					throw new UnimplementedException("Unsupported operation: " + ctx.getText());
				}

				rewriter.insertBefore(
					ctx.postfix().get(0).arrayAccessTail().expr().get(0).getStart(),
					"i0, ");
			}
		}

		// Need a recursive call otherwise it might not visit inside
		return super.visitPrimary(ctx);
	}

	@Override
	public Void visitArrayLiteral(ArrayLiteralContext ctx) {
		if (ctx.generatorList() != null) {
			rewriter.insertBefore(ctx.generatorList().getStart(), "i0 in instances, ");
			return visitExpr(ctx.expr().get(0));
		} else {
			rewriter.insertBefore(ctx.expr().getLast().getStart(), "| i0 in instances");
			ctx.expr().stream().forEach(this::visitExpr);
		}
		return null;
	}

	@Override
	public Void visitSetLiteral(SetLiteralContext ctx) {
		if (ctx.generatorList() != null) {
			if (ctx.generatorList().expr() != null) {
				ctx.generatorList().expr().stream().forEach(this::visitExpr);
			}
		}
		return null;
	}

	@Override
	public Void visitGeneratorList(GeneratorListContext ctx) {
		super.visitGeneratorList(ctx);
		rewriter.insertBefore(ctx.generator().get(0).getStart(), "i0 in instances, ");
		return null;
	}

}
