package me.simonegazza.lift.parameters;

import java.util.Map;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.visitors.EvaluatorVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Represents a parameter as originally declared in the MiniZinc model.
 * <p>
 * An {@code OriginalParameter} captures:
 * <ul>
 * <li>The parameter name</li>
 * <li>Its type</li>
 * <li>Its assigned value (as a parse tree)</li>
 * </ul>
 * <p>
 * The value is stored as a {@link ParserRuleContext} instead of a string to
 * preserve the original structure of the parsed model and allow precise
 * transformations later in the pipeline.
 * <p>
 * Instances of this class are also used as nodes in the dependency graph, where
 * edges represent relationships between parameters (e.g. usage inside
 * expressions or type definitions).
 * <p>
 * <b>Equality semantics:</b> two {@code OriginalParameter}s are considered
 * equal if they share the same name. This allows them to be safely used in
 * graph structures and sets without duplicating logical parameters.
 */
public class OriginalParameter {

	/**
	 * The name of the parameter as declared in the MiniZinc model.
	 */
	private final String name;

	/**
	 * The MiniZinc type of the parameter.
	 */
	private final MiniZincType type;

	/**
	 * The parse tree representing the assigned value of the parameter, if any.
	 */
	private ParserRuleContext expression;

	/**
	 * The expression text of the assignment of the parameter, if any.
	 */
	private String expressionText;

	/**
	 * The value obtained from the parse tree expression, if any.
	 */
	private Object value;

	/**
	 * Creates a parameter with a given type and name.
	 *
	 * @param type the MiniZinc type of the parameter
	 * @param name the parameter name
	 */
	public OriginalParameter(MiniZincType type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Assigns the expression of this parameter.
	 * <p>
	 * The value is stored as a parse tree to preserve exact source information.
	 *
	 * @param expression the expression representing the parameter value
	 */
	public void setExpression(
		ParserRuleContext expression,
		String expressionText) {

		this.expression = expression;
		this.expressionText = expressionText;
	}

	/**
	 * @return the parameter type
	 */
	public MiniZincType getType() {
		return type;
	}

	/**
	 * @return the parameter name
	 */
	public String getName() {
		return name;
	}

	public String getExpressionText() {
		return expressionText;
	}

	/**
	 * Reconstructs the original MiniZinc declaration.
	 * <p>
	 * Example:
	 *
	 * <pre>
	 * int: n = 10;
	 * </pre>
	 * <p>
	 *
	 * @return the parameter declaration as a string
	 */
	public String getDeclaration() {
		return type.toString() + ": " + name + " = " + expressionText + ";";
	}

	public Object evaluate(Map<String, Object> environment) {
		if (value == null)
			value = new EvaluatorVisitor(environment).visit(expression);
		return value;
	}

	public Object getValue() {
		if (value == null)
			throw new IllegalStateException("Asked value of " + name + " before computing it");
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OriginalParameter objop)
			return name.equals(objop.name);
		else
			return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}

}
