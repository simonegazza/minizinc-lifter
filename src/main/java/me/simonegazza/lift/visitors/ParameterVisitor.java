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
import me.simonegazza.lift.expressions.MiniZincIdentifier;
import me.simonegazza.lift.parameters.OriginalParameter;
import me.simonegazza.lift.types.MiniZincCompositeType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.DirectedGraph;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * ANTLR visitor responsible for extracting parameters from a MiniZinc model and
 * building their dependency graph.
 * <p>
 * This visitor walks the MiniZinc AST and identifies all parameter-like
 * entities (including enums and assignments), collecting:
 * <ul>
 * <li>Parameter definitions (name + type)</li>
 * <li>Parameter values (expressions assigned to them)</li>
 * <li>Dependencies between parameters</li>
 * </ul>
 * <p>
 * The result is a {@link DirectedGraph} where:
 * <ul>
 * <li>Nodes are {@link OriginalParameter}</li>
 * <li>Edges represent "depends on" relationships</li>
 * </ul>
 * <p>
 * This graph is later used by the lifting phase to understand how
 * transformations should propagate across parameters.
 * <p>
 * <b>Important:</b> The visitor collects partial information during traversal
 * (assignments, dependencies) and only produces a consistent graph after
 * {@link #visitModel(ModelContext)} completes.
 */
public class ParameterVisitor extends MiniZincBaseVisitor<DirectedGraph<OriginalParameter>> {
	/**
	 * Maps parameter names to their assigned expression context.
	 * <p>
	 * This is populated during the visit and later used in {@link #visitModel}
	 * to attach values to {@link OriginalParameter} instances.
	 */
	private Map<String, ParserRuleContext> assignments;

	/**
	 * Temporary storage of dependencies between identifiers.
	 * <p>
	 * Each key represents a parameter, and the associated list contains the
	 * identifiers it depends on (as discovered during expression traversal).
	 */
	private Map<String, List<String>> dependencies;

	/**
	 * Tracks the identifier currently being analyzed.
	 * <p>
	 * This is used while visiting expressions: whenever an {@link IdentContext}
	 * is encountered, it is recorded as a dependency of this identifier.
	 */
	private String currentIdentifier;

	/**
	 * The resulting dependency graph.
	 */
	private DirectedGraph<OriginalParameter> graph;

	/**
	 * Registers an assignment for a given identifier.
	 * <p>
	 * This also updates {@link #currentIdentifier} so that any identifiers
	 * encountered while visiting the expression are tracked as dependencies.
	 *
	 * @param idCtx   the {@link IdentContext} to get the ident name
	 * @param exprCtx a {@link ParserRuleContext} that contains the expression
	 *                    of this assignment
	 */
	private void addAssignment(IdentContext idCtx, ParserRuleContext exprCtx) {
		String ident = idCtx.getText();
		currentIdentifier = ident;
		assignments.putIfAbsent(ident, exprCtx);
	}

	/**
	 * Handles enum declarations.
	 * <p>
	 * Enums are treated as parameters and added as graph nodes. If the enum has
	 * explicit cases, they are treated as an assignment and analyzed for
	 * dependencies.
	 *
	 * @param ident      the identifier name
	 * @param dependency the dependency name
	 */
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
		MiniZincIdentifier type = new MiniZincIdentifier(enumName);
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
	 * Determines whether a variable declaration should be ignored.
	 * <p>
	 * If the type contains the keyword {@code var}, the declaration represents
	 * a decision variable rather than a parameter, and is therefore skipped.
	 * <p>
	 * This method recursively inspects type expressions to detect such cases.
	 *
	 * @param ctx the type expression context
	 *
	 * @return true if the declaration should be ignored
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
		} else if (ctx instanceof ArrayTiExprContext arrayTiExprCtx)
			return jumpVarDecl(arrayTiExprCtx.baseTiExpr());

		return false;
	}

	/**
	 * Processes variable/parameter declarations.
	 * <p>
	 * Only parameter declarations are considered. Decision variables (those
	 * containing {@code var}) are ignored.
	 * <p>
	 * This method:
	 * <ul>
	 * <li>Extracts the parameter type</li>
	 * <li>Registers dependencies from composite types</li>
	 * <li>Captures assigned expressions (if present)</li>
	 * <li>Adds the parameter to the graph</li>
	 * </ul>
	 *
	 * @return a parameter graph with the current declaration added
	 */
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
		if (type instanceof MiniZincCompositeType composite) {
			for (MiniZincIdentifier typeDependency : composite.getSubtypesIdentifier())
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

	/**
	 * Processes assignment statements.
	 * <p>
	 * Registers the assigned expression and extracts dependencies from it.
	 *
	 * @return a parameter graph with the current assignation added to the
	 *             corresponding parameter
	 */
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
	 * Finalizes parameter extraction and builds the dependency graph.
	 * <p>
	 * This is the only method that guarantees a consistent result. It performs
	 * a post-processing step after the full AST traversal:
	 * <ul>
	 * <li>Assigns expression values to each parameter</li>
	 * <li>Validates that all parameters have a value</li>
	 * <li>Resolves dependencies into graph edges</li>
	 * <li>Adds implicit dependencies from composite types</li>
	 * </ul>
	 * <p>
	 * Only identifiers that correspond to actual parameters are turned into
	 * edges. Other identifiers (e.g., local variables) are ignored.
	 *
	 * @param ctx the root model context
	 *
	 * @return the fully constructed dependency graph
	 *
	 * @throws IllegalStateException if a parameter has no assigned value
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
			if (type instanceof MiniZincCompositeType t) {
				dependencies.get(par.getName()).addAll(
					t.getSubtypesIdentifier().stream()
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
