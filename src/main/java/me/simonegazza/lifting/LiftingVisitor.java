package me.simonegazza.lifting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lifting.parameter.ArrayParameter;
import me.simonegazza.lifting.parameter.Parameter;
import me.simonegazza.lifting.parameter.SimpleParameter;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftingVisitor extends MiniZincBaseVisitor<Void> {
	private final Set<String> cliParameters;
	private final TokenStreamRewriter rewriter;

	private Map<String, String> initializations = new HashMap<>();
	private List<Parameter> liftedParameters = new ArrayList<>();

	public LiftingVisitor(TokenStream tokens, Set<String> parameters) {
		this.cliParameters = parameters;
		this.rewriter = new TokenStreamRewriter(tokens);
	}

	@Override
	public Void visitIdent(IdentContext ctx) {
		if (cliParameters.stream().anyMatch(p -> p.equals(ctx.getText())))
			rewriter.replace(
				ctx.getStart(),
				ctx.getStop(),
				Parameter.getLiftedName(ctx.getText()));
		return null;
	}

	@Override
	public Void visitAssignItem(AssignItemContext ctx) {
		String ident = ctx.ident().getText();
		if (cliParameters.stream().anyMatch(p -> p.equals(ident)))
			return null;
		return super.visitAssignItem(ctx);
	}

	@Override
	public Void visitItem(ItemContext ctx) {
		if (ctx.solveItem() != null) {
			rewriter.delete(ctx.getStart(), ctx.getStop());
			return null;
		}
		return super.visitItem(ctx);
	}

	@Override
	public Void visitVarDeclItem(VarDeclItemContext ctx) {
		TiExprAndIdContext declarationLhs = ctx.tiExprAndId();
		IdentContext ident = declarationLhs.ident();

		if (cliParameters.stream().anyMatch(p -> p.equals(ident.getText()))) {
			TiExprContext typeExpr = declarationLhs.tiExpr();
			BaseTiExprContext type;

			// simple variable declaration case
			if (declarationLhs.tiExpr().baseTiExpr() != null) {
				type = typeExpr.baseTiExpr();
				liftedParameters.add(new SimpleParameter(
					ident.getText(),
					type.getText()));

			}
			// array declaration case
			else {
				type = typeExpr.arrayTiExpr().baseTiExpr();
				liftedParameters.add(
					new ArrayParameter(
						ident.getText(),
						type.getText(),
						typeExpr.arrayTiExpr().tiExpr().stream()
							.map(cte -> cte.getText())
							.toList()));
			}

			if (type.getChild(0).getText().equals("var"))
				throw new IllegalStateException(
					"Requesting lifting of a variable");

			if (type.getChild(0).getText().equals("par"))
				rewriter.delete(type.getStart());

			if (ctx.expr() != null) {
				initializations.put(
					ident.getText(),
					ctx.expr().getText());
				rewriter.delete(ctx.EQ().getSymbol(), ctx.expr().getStop());
			}

			rewriter.insertBefore(type.getStart(), "var ");
			rewriter.replace(
				ident.getStart(),
				ident.getStop(),
				Parameter.getLiftedName(ident.getText()));

			return null;
		} else
			// Identifiers of the parameter that we want to lift could be
			// present in the declarations of a dimension of other arrays too,
			// and we need to renaming it too, but, since we are capturing the
			// exploration of the array initialization part in the then branch
			// of this if, we need to request the exploration of the lhs of the
			// array too. So for example, given a parameter P that we
			// want to lift, the original model could something like:
			// ```minizinc
			// int: P = 3;
			// array[1..P] of int: A;
			// ``
			// in this case, this else branch will be able to capture the P in
			// array[1..P] and lift it too.
			return super.visitVarDeclItem(ctx);
	}

	public String getTranspiled() {
		StringBuilder sb = new StringBuilder(rewriter.getText());

		initializations.forEach((k, v) -> {
			sb.append(k + " = " + v + ";\n");
		});

		sb.append(liftedParameters.stream()
			.map(Parameter::getInitialDeclaration)
			.collect(Collectors.joining("\n")));
		sb.append("\n");

		sb.append("solve minimize ");
		sb.append(liftedParameters.stream()
			.map(Parameter::getDiff)
			.collect(Collectors.joining(" + ")));
		sb.append(";");

		return sb.toString();
	}
}
