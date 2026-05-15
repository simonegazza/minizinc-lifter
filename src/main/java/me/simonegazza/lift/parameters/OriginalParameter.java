package me.simonegazza.lift.parameters;

import java.util.ArrayList;
import java.util.List;
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
	 * Wheter the parameter was assigned during declaration.
	 */
	private final boolean assignedAtDeclaration;

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
	 * @param type                  the MiniZinc type of the parameter
	 * @param name                  the parameter name
	 * @param assignedAtDeclaration whether the parameter was assigned at
	 *                                  declaration
	 */
	public OriginalParameter(MiniZincType type, String name, boolean assignedAtDeclaration) {
		this.type = type;
		this.name = name;
		this.assignedAtDeclaration = assignedAtDeclaration;
	}

	/**
	 * Assigns the expression (text and parser tree) of this parameter.
	 * <p>
	 * The value is stored both as a parse tree to preserve value information
	 * and as a string to preserve spaces.
	 *
	 * @param expression     the expression representing the parameter value
	 * @param expressionText the expression text of this value
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

	/**
	 * @return the text of the expression
	 */
	public String getExpressionText() {
		return expressionText;
	}

	/**
	 * @return the expression
	 */
	public ParserRuleContext getExpression() {
		return expression;
	}

	/**
	 * @return true if it was assigned at declaration
	 */
	public boolean isAssignedAtDeclaration() {
		return assignedAtDeclaration;
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

	/**
	 * Evaluates the expression associated with this parameter using the
	 * provided environment.
	 * <p>
	 * The evaluation is performed lazily: the expression is evaluated only the
	 * first time this method is invoked, and the result is cached for
	 * subsequent calls.
	 * </p>
	 *
	 * @param environment a mapping from variable names to their corresponding
	 *                        values, used during expression evaluation
	 *
	 * @return the computed value of the expression
	 */
	public Object evaluate(Map<String, Object> environment) {
		if (value == null) {
			value = new EvaluatorVisitor(environment).visit(expression);
		}
		return value;
	}

	/**
	 * Returns the previously computed value of this parameter.
	 * <p>
	 * This method does not trigger evaluation. If the value has not been
	 * computed yet (i.e., {@link #evaluate(Map)} has not been called), an
	 * exception is thrown.
	 * </p>
	 *
	 * @return the computed value of this parameter
	 *
	 * @throws IllegalStateException if the value has not been computed yet
	 */
	public Object getValue() {
		if (value == null) {
			throw new IllegalStateException("Asked value of " + name + " before computing it");
		}
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OriginalParameter objop) {
			return name.equals(objop.name);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Get the dimensions of the value of this parameter.
	 *
	 * @return the dimensions of this parameter
	 */
	public List<Integer> getDimensions() {
		if (value instanceof Integer
			|| value instanceof Double
			|| value instanceof String) {
			return List.of(1);
		} else if (value instanceof List<?> vc) {
			// Assumption: the array has homogeneous dimension. This is false
			// because arrays of sets can have different dimension (and at this
			// level we do not distinguish anymore between arrays and sets, but
			// this is a good approximation
			List<Integer> result = new ArrayList<>();
			while (true) {
				result.add(vc.size());
				if (vc.getFirst() instanceof List<?> vcFirst) {
					vc = vcFirst;
				} else {
					break;
				}
			}
			return result;
		}

		throw new IllegalStateException("Unsupported value for dimension request: " + value.toString());
	}

}
