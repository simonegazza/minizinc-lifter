package me.simonegazza.lift.visitors;

import java.util.ArrayList;
import java.util.List;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayLiteral2dContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayLiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayRowContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.LiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SetLiteralContext;
import me.simonegazza.lift.utils.exception.UnimplementedException;

public class ExpressionVisitor extends MiniZincBaseVisitor<Object> {

	@Override
	public List<String> visitArrayLiteral(ArrayLiteralContext ctx) {
		List<String> l = ctx.expr().stream().map(ExprContext::getText).toList();

		// list needs to be modifiable afterwards, hence why returning a copy
		return new ArrayList<String>(l);
	}

	@Override
	public List<String> visitArrayRow(ArrayRowContext ctx) {
		List<String> l = ctx.expr().stream().map(ExprContext::getText).toList();

		// list needs to be modifiable afterwards, hence why returning a copy
		return new ArrayList<String>(l);
	}

	@Override
	public List<List<String>> visitArrayLiteral2d(ArrayLiteral2dContext ctx) {
		return ctx.arrayRow().stream().map(this::visitArrayRow).toList();
	}

	@Override
	public Object visitSetLiteral(SetLiteralContext ctx) {
		if (ctx.compTail() != null)
			throw new UnimplementedException("Unimplemented generators for sets");
		return ctx.expr().stream().map(this::visitExpr).toList();
	}

	@Override
	public Object visitLiteral(LiteralContext ctx) {
		if (ctx.INT_LITERAL() != null)
			return Integer.parseInt(ctx.INT_LITERAL().getText());
		if (ctx.FLOAT_LITERAL() != null)
			return Double.parseDouble(ctx.FLOAT_LITERAL().getText());
		if (ctx.STRING_LITERAL() != null)
			return ctx.STRING_LITERAL().getText();
		if (ctx.getText().equals("true") || ctx.getText().equals("false"))
			return Boolean.parseBoolean(ctx.getText());
		if (ctx.setLiteral() != null)
			return visitSetLiteral(ctx.setLiteral());
		if (ctx.arrayLiteral() != null)
			return visitArrayLiteral(ctx.arrayLiteral());
		if (ctx.arrayLiteral2d() != null)
			return visitArrayLiteral2d(ctx.arrayLiteral2d());

		throw new RuntimeException("Unsupported literal: " + ctx.getText());

	}

}
