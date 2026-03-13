package me.simonegazza.lift.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.AssignItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.EnumItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.types.MiniZincCompositeType;
import me.simonegazza.lift.types.MiniZincEnumType;
import me.simonegazza.lift.types.MiniZincNamedType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.ParserRuleContext;

public class ParameterVisitor extends MiniZincBaseVisitor<DirectedGraph<OriginalParameter>> {
	private Map<String, ParserRuleContext> assignments;
	private Map<String, List<String>> dependencies;

	private String currentIdentifier;

	private DirectedGraph<OriginalParameter> graph;

	private void addAssignment(IdentContext idCtx, ParserRuleContext exprCtx) {
		String ident = idCtx.getText();
		currentIdentifier = ident;
		assignments.putIfAbsent(ident, exprCtx);
	}

	private void addDependency(String ident, String dependency) {
		dependencies.putIfAbsent(ident, new ArrayList<String>());
		dependencies.get(ident).add(dependency);
	}

	public ParameterVisitor() {
		this.currentIdentifier = null;

		this.assignments = new HashMap<>();
		this.dependencies = new HashMap<>();
		this.graph = new DirectedGraph<>();
	}

	@Override
	public DirectedGraph<OriginalParameter> visitIdent(IdentContext ctx) {
		if (currentIdentifier != null)
			addDependency(currentIdentifier, ctx.getText());
		return graph;
	}

	@Override
	public DirectedGraph<OriginalParameter> visitEnumItem(EnumItemContext ctx) {
		String enumName = ctx.ident().getText();
		MiniZincEnumType type = new MiniZincEnumType(enumName);
		OriginalParameter ep = new OriginalParameter(type, enumName);
		graph.addNode(ep);

		if (ctx.enumCasesList() != null) {
			currentIdentifier = enumName;
			dependencies.putIfAbsent(enumName, new ArrayList<String>());
			addAssignment(ctx.ident(), ctx.enumCasesList());
		}

		return graph;
	}

	/**
	 * Gives True when there's a var in the type definition of this type
	 * declaration.
	 *
	 * @param ctx must be of type TiExprContenxt at first call
	 *
	 * @return whether we should jump the declaration
	 */
	private boolean jumpVarDecl(ParserRuleContext ctx) {
		if (ctx instanceof TiExprContext) {
			var ti = (TiExprContext) ctx;
			if (ti.baseTiExpr() != null)
				return jumpVarDecl(ti.baseTiExpr());
			else
				return jumpVarDecl(ti.arrayTiExpr());
		}

		if (ctx instanceof BaseTiExprContext) {
			if (ctx.getChild(0).getText().equals("var"))
				return true;
		} else if (ctx instanceof ArrayTiExprContext)
			return jumpVarDecl(((ArrayTiExprContext) ctx).baseTiExpr());

		return false;
	}

	@Override
	public DirectedGraph<OriginalParameter> visitVarDeclItem(VarDeclItemContext ctx) {
		if (ctx.getChild(0).getText().startsWith("any"))
			throw new UnimplementedException("Unimplemented \"any\" delcaration");

		TiExprContext typeCtx = ctx.tiExprAndId().tiExpr();
		IdentContext ident = ctx.tiExprAndId().ident();

		if (jumpVarDecl(typeCtx))
			// Ignore variables
			return graph;

		MiniZincType type = new TypeVisitor().visitTiExpr(typeCtx);
		if (type instanceof MiniZincCompositeType) {
			MiniZincCompositeType composite = (MiniZincCompositeType) type;
			for (MiniZincNamedType typeDependency : composite.getSubtypesIdentifiers())
				addDependency(ident.getText(), typeDependency.getName());
		}

		OriginalParameter parameter = new OriginalParameter(type, ident.getText());
		if (ctx.expr() != null) {
			ExprContext value = ctx.expr();
			addAssignment(ident, value);
			visitExpr(value);
		}

		dependencies.putIfAbsent(ident.getText(), new ArrayList<String>());

		graph.addNode(parameter);
		return graph;
	}

	@Override
	public DirectedGraph<OriginalParameter> visitAssignItem(AssignItemContext ctx) {
		ExprContext value = ctx.expr();
		addAssignment(ctx.ident(), value);
		return visitExpr(value);
	}

	@Override
	public DirectedGraph<OriginalParameter> visitItem(ItemContext ctx) {
		if (ctx.varDeclItem() != null)
			return this.visitVarDeclItem(ctx.varDeclItem());
		else if (ctx.enumItem() != null)
			return this.visitEnumItem(ctx.enumItem());
		else if (ctx.assignItem() != null)
			return this.visitAssignItem(ctx.assignItem());
		else
			return graph;
	}

	/**
	 * We guaranty a correct state *only* with this method.
	 */
	@Override
	public DirectedGraph<OriginalParameter> visitModel(ModelContext ctx) {
		super.visitModel(ctx);

		for (OriginalParameter par : graph.getNodes()) {
			ParserRuleContext valueContext = assignments.get(par.getName());
			// There must be a value, otherwise the MiniZinc model won't compile
			if (valueContext == null)
				throw new IllegalStateException("Parameter " + par.getName() + " left undefined.");

			par.setValue(valueContext);

			var type = par.getType();
			if (type instanceof MiniZincCompositeType) {
				var t = (MiniZincCompositeType) type;
				dependencies.get(par.getName()).addAll(
					t.getSubtypesIdentifiers().stream()
						.map(s -> s.getName())
						.toList());
			}

			for (String d : dependencies.get(par.getName())) {
				Optional<OriginalParameter> dependency = graph.getNodes().stream()
					.filter(p -> p.getName().equals(d))
					.findFirst();

				// if there is an element, it means that it is a proper
				// parameter (because it was caught during a visitVarDelcItem,
				// otherwise it's not and it was just an identifier used for
				// other purposes (e.g., temporary name in a generator)
				if (!dependency.isEmpty())
					graph.addEdge(par, dependency.get());
			}

		}

		return graph;
	}
}
