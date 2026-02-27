package me.simonegazza.lifting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.OutputItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lifting.parameter.ArrayParameter;
import me.simonegazza.lifting.parameter.Parameter;
import me.simonegazza.lifting.parameter.SetParameter;
import me.simonegazza.lifting.parameter.SimpleParameter;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftingVisitor extends MiniZincBaseVisitor<Void> {
	private final List<CLIParameter> cliParameters;
	private final TokenStreamRewriter rewriter;

	private Map<String, String> initializations = new HashMap<>();
	private List<Parameter> liftedParameters = new ArrayList<>();

	public LiftingVisitor(TokenStream tokens, List<CLIParameter> parameters) {
		this.cliParameters = parameters;
		this.rewriter = new TokenStreamRewriter(tokens);
	}

	@Override
	public Void visitIdent(IdentContext ctx) {
		if (cliParameters.stream().anyMatch(p -> p.name().equals(ctx.getText())))
			rewriter.replace(
				ctx.getStart(),
				ctx.getStop(),
				Parameter.getLiftedName(ctx.getText()));
		return null;
	}

	@Override
	public Void visitAssignItem(AssignItemContext ctx) {
		String ident = ctx.ident().getText();
		if (cliParameters.stream().anyMatch(p -> p.name().equals(ident)))
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
	public Void visitOutputItem(OutputItemContext ctx) {
		String output = cliParameters.stream()
			.map(p -> {
				String name = Parameter.getLiftedName(p.name());
				return "\"" + name + " = \\(" + name + ")\\n\"";
			}).collect(Collectors.joining(", "));

		output = "[" + output + "] ++ ";
		// In this case the output is usually a simple "show(vars)", so we
		// need to wrap it in a array
		if (!ctx.expr().getText().startsWith("[")) {
			output += "[";
			rewriter.insertAfter(ctx.expr().getStop(), "]");
		}

		rewriter.insertBefore(ctx.expr().getStart(), output);

		return null;
	}

	@Override
	public Void visitVarDeclItem(VarDeclItemContext ctx) {
		TiExprAndIdContext declarationLhs = ctx.tiExprAndId();
		IdentContext ident = declarationLhs.ident();

		Optional<CLIParameter> cliParameter = cliParameters.stream()
			.filter(p -> p.name().equals(ident.getText()))
			.findAny();
		if (cliParameter.isPresent()) {
			TiExprContext typeExpr = declarationLhs.tiExpr();
			BaseTiExprContext type;

			Parameter parameter;
			// simple or set parameter declaration case
			if (declarationLhs.tiExpr().baseTiExpr() != null) {
				type = typeExpr.baseTiExpr();

				// set parameter
				if (type.children.stream().anyMatch(c -> c.getText().equals("set of"))) {
					parameter = new SetParameter(
						ident.getText(),
						type.baseTiExprTail().getText(),
						cliParameter.get().bounds());
				}
				// simple parameter
				else {
					parameter = new SimpleParameter(
						ident.getText(),
						type.getText(),
						cliParameter.get().bounds());

				}
			}
			// array declaration case
			else {
				type = typeExpr.arrayTiExpr().baseTiExpr();
				parameter = new ArrayParameter(
					ident.getText(),
					type.getText(),
					cliParameter.get().bounds(),
					typeExpr.arrayTiExpr().tiExpr().stream()
						.map(cte -> cte.getText())
						.toList());
			}
			liftedParameters.add(parameter);

			if (type.getChild(0).getText().equals("var"))
				throw new IllegalStateException("Requested lifting of a variable");

			if (ctx.expr() != null)
				initializations.put(
					ident.getText(),
					ctx.expr().getText());

			rewriter.replace(
				ctx.getStart(),
				ctx.getStop(),
				parameter.getLiftedDeclaration());

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

		sb.append(liftedParameters.stream()
			.map(Parameter::getInitialDeclaration)
			.collect(Collectors.joining("\n")));
		sb.append("\n");

		initializations.forEach((k, v) -> {
			sb.append(k + " = " + v + ";\n");
		});

		sb.append("solve minimize ");
		sb.append(liftedParameters.stream()
			.map(Parameter::getDiff)
			.collect(Collectors.joining(" + ")));
		sb.append(";");

		return sb.toString();
	}
}
