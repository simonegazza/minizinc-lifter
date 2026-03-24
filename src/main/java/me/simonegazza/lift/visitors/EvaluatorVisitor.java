package me.simonegazza.lift.visitors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.AddExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.AndExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayAccessTailContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayLiteral2dContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayLiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayRowContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.CallSuffixContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.CompareExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.EnumCasesContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.EnumCasesListContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.GeneratorContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.GeneratorListContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IfThenElseExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IffExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ImplExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.LiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.MultExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.OrExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.PostfixContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.PowExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.PrimaryContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.QuantifierExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.RangeExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SetExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.SetLiteralContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.UnaryExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.XorExprContext;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Visitor for evaluating expressions in a MiniZinc-like language.
 * <p>
 * This evaluator works over an environment of variable bindings and supports
 * arithmetic operations, array accesses, and generator expressions. It does not
 * handle certain language constructs, specifically:
 * <ul>
 * <li>Annotations</li>
 * <li>Tuples</li>
 * <li>Records</li>
 * <li>Let expressions</li>
 * <li>Indexed literals</li>
 * </ul>
 * <p>
 * The visitor uses the {@link #env} map as its environment, which is extended
 * as needed for local scopes during expression evaluation.
 */
public class EvaluatorVisitor extends MiniZincBaseVisitor<Object> {

	/**
	 * The evaluation environment containing variable bindings.
	 * <p>
	 * Each entry in this map represents a name-value association used during
	 * expression evaluation. The keys are variable names, and the values are
	 * their corresponding values (which may be numbers, arrays, or other
	 * supported objects).
	 * <p>
	 * This environment is extended as needed when evaluating generator
	 * expressions or creating local scopes, but the original map is never
	 * modified directly.
	 */
	private final Map<String, Object> env;

	/**
	 * Counter used to differentiate between underscores identifiers.
	 */
	private int underscoreCounter;

	/**
	 * Recursively flattens a potentially multi-dimensional list structure into
	 * a single list.
	 * <p>
	 * If the input object is a {@link List}, all of its elements are
	 * recursively traversed and collected into a flat list. Otherwise, the
	 * object itself is treated as a leaf element and added to the result.
	 *
	 * @param obj the object to flatten; may be a nested {@link List} or a
	 *                single value
	 *
	 * @return a flat {@link List} containing all leaf elements in traversal
	 *             order
	 */
	public static List<Object> flatten(Object obj) {
		List<Object> result = new ArrayList<>();

		if (obj instanceof List<?> objList)
			for (Object item : objList)
				result.addAll(flatten(item));
		else
			result.add(obj);

		return result;
	}

	/**
	 * Reconstructs a multi-dimensional array (nested {@link List}) from a
	 * flattened list according to the specified dimensions.
	 * <p>
	 * The {@code dimensions} parameter defines the shape of the resulting
	 * structure, where each element represents the size of a dimension. The
	 * {@code flattened} list is consumed in order to populate the resulting
	 * nested lists.
	 * <p>
	 * Through {@code reformatArrayRecursive}, it builds the nested list
	 * structure level by level, consuming elements from the flattened list as
	 * it reaches the innermost dimension.
	 *
	 * @param dimensions the sizes of each dimension
	 * @param flattened  the flat list of elements to distribute into the
	 *                       structure
	 *
	 * @return a nested {@link List} representing the multi-dimensional array
	 */
	private static List<Object> reformatArray(
		List<Integer> dimensions,
		List<Object> flattened) {
		return reformatArrayRecursive(dimensions, 0, flattened, new int[] { 0 });
	}

	/**
	 * Recursive helper method for {@link #reformatArray(List, List)}.
	 *
	 * @param dimensions the full list of dimensions
	 * @param dimIndex   the current dimension being processed
	 * @param flattened  the source list of elements
	 * @param index      a single-element array used as a mutable pointer into
	 *                       {@code flattened}
	 *
	 * @return a nested list representing the current dimension
	 */
	private static List<Object> reformatArrayRecursive(
		List<Integer> dimensions,
		int dimIndex,
		List<Object> flattened,
		int[] index) {

		int size = dimensions.get(dimIndex);

		if (dimIndex == dimensions.size() - 1) {
			// Last dimension
			return IntStream.range(0, size)
				.mapToObj(_ -> flattened.get(index[0]++))
				.collect(Collectors.toList());
		}

		return IntStream.range(0, size)
			.mapToObj(_ -> reformatArrayRecursive(dimensions, dimIndex + 1, flattened, index))
			.collect(Collectors.toList());
	}

	/**
	 * Retrieves an element from a multi-dimensional array represented as nested
	 * {@link List}s, of objects following the specified index path.
	 * <p>
	 * Each element in {@code locations} represents the index to access at the
	 * corresponding level of nesting.
	 *
	 * @param array     the root nested list structure
	 * @param locations the list of indices describing the path to the desired
	 *                      element
	 *
	 * @return the element located at the specified position
	 *
	 * @throws ClassCastException        if the structure does not match the
	 *                                       expected nested list shape
	 * @throws IndexOutOfBoundsException if any index is invalid
	 */
	@SuppressWarnings("unchecked")
	public static Object get(Object array, List<Integer> locations) {
		Object current = array;

		for (int i = 0; i < locations.size(); i++)
			current = ((List<Object>) current).get(locations.get(i));

		return current;
	}

	/**
	 * Creates a new environment map by extending the current one with
	 * additional bindings.
	 * <p>
	 * In case of key conflicts, values from {@code augmentation} override those
	 * already present in the current environment, as in MiniZinc.
	 * <p>
	 * This method returns a shallow copy, so it does not modify the
	 * environments.
	 *
	 * @param augmentation the variable bindings to include
	 *
	 * @return a new {@link Map} containing the merged environment
	 */
	private Map<String, Object> augmentEnv(Map<String, Object> augmentation) {
		Map<String, Object> augmented = new HashMap<>();
		augmented.putAll(this.env);
		augmented.putAll(augmentation);
		return augmented;
	}

	public EvaluatorVisitor(Map<String, Object> env) {
		this.env = env;
		this.underscoreCounter = 0;
	}

	@Override
	public List<Integer> visitEnumCasesList(EnumCasesListContext ctx) {
		List<Integer> result = new ArrayList<>();
		int index = 0;
		for (EnumCasesContext ecctx : ctx.enumCases()) {
			List<Object> l = visitEnumCases(ecctx);
			for (Object e : l) {
				Integer toPush;
				if (e instanceof String es)
					toPush = ((Integer) env.get(es)) + (index++);
				else if (e instanceof Integer ei)
					toPush = ei + index;
				else
					throw new IllegalStateException("Undefined type of value for this expression: " + ctx.getText());

				result.add(toPush);
			}
		}

		return result;
	}

	@Override
	public List<Object> visitEnumCases(EnumCasesContext ctx) {
		if (ctx.ident().isEmpty()) { // second and last rules, anon enum
			// This should always evaluate to an Integer
			Integer upper = (Integer) visitExpr(ctx.expr());
			return IntStream.rangeClosed(1, upper)
				.boxed()
				.map(Object.class::cast)
				.toList();
		} else if (!ctx.getText().startsWith("{")) { // last-but-one rule
			// This cast is safe since the visit should return a list of string,
			// i.e., the list of the values of the enum
			@SuppressWarnings("unchecked")
			List<Object> range = (List<Object>) visitIdent(ctx.ident(1));
			return range;
		} else
			// first rule
			return ctx.ident().stream()
				.map(ParseTree::getText)
				.map(Object.class::cast)
				.toList();
	}

	@Override
	public Object visitExpr(ExprContext ctx) {
		return visitIffExpr(ctx.iffExpr());
	}

	@Override
	public Object visitIffExpr(IffExprContext ctx) {
		Object result = visitImplExpr(ctx.implExpr(0));
		for (int i = 1; i < ctx.implExpr().size(); i++) {
			Object rhs = visitImplExpr(ctx.implExpr(i));
			result = result.equals(rhs);
		}
		return result;
	}

	@Override
	public Object visitImplExpr(ImplExprContext ctx) {
		Object result = visitOrExpr(ctx.orExpr(0));
		for (int i = 1; i < ctx.orExpr().size(); i++) {
			Object rhs = visitOrExpr(ctx.orExpr(i));
			result = !((Boolean) result) || ((Boolean) rhs);
		}
		return result;
	}

	@Override
	public Object visitOrExpr(OrExprContext ctx) {
		Object result = visitXorExpr(ctx.xorExpr(0));
		for (int i = 1; i < ctx.xorExpr().size(); i++) {
			Object rhs = visitXorExpr(ctx.xorExpr(i));
			result = ((Boolean) result) || ((Boolean) rhs);
		}
		return result;
	}

	@Override
	public Object visitXorExpr(XorExprContext ctx) {
		Object result = visitAndExpr(ctx.andExpr(0));
		for (int i = 1; i < ctx.andExpr().size(); i++) {
			Object rhs = visitAndExpr(ctx.andExpr(i));
			result = ((Boolean) result) ^ ((Boolean) rhs);
		}
		return result;
	}

	@Override
	public Object visitAndExpr(AndExprContext ctx) {
		Object result = visitCompareExpr(ctx.compareExpr(0));
		for (int i = 1; i < ctx.compareExpr().size(); i++) {
			Object rhs = visitCompareExpr(ctx.compareExpr(i));
			result = ((Boolean) result) && ((Boolean) rhs);
		}
		return result;
	}

	@Override
	public Object visitCompareExpr(CompareExprContext ctx) {
		Object lhs = visitSetExpr(ctx.setExpr(0));
		if (ctx.setExpr().size() == 1)
			return lhs;

		Object rhs = visitSetExpr(ctx.setExpr(1));
		String op = ctx.getChild(1).getText();

		return switch (op) {
		case "<" -> (lhs instanceof Integer)
			? (Integer) lhs < (Integer) rhs
			: (Double) lhs < (Double) rhs;
		case ">" -> (lhs instanceof Integer)
			? (Integer) lhs > (Integer) rhs
			: (Double) lhs > (Double) rhs;
		case "<=" -> (lhs instanceof Integer)
			? (Integer) lhs <= (Integer) rhs
			: (Double) lhs <= (Double) rhs;
		case ">=" -> (lhs instanceof Integer)
			? (Integer) lhs >= (Integer) rhs
			: (Double) lhs >= (Double) rhs;
		case "=", "==" -> lhs.equals(rhs);
		case "!=" -> !lhs.equals(rhs);
		case "in" -> ((Collection<?>) lhs).contains(rhs);
		case "subset" -> ((Collection<?>) rhs).containsAll((Collection<?>) lhs);
		case "superset" -> ((Collection<?>) lhs).containsAll((Collection<?>) rhs);
		default -> throw new UnimplementedException(op);
		};
	}

	@Override
	public Object visitSetExpr(SetExprContext ctx) {
		Object result = visitRangeExpr(ctx.rangeExpr(0));
		for (int i = 1; i < ctx.rangeExpr().size(); i++) {
			Object rhs = visitRangeExpr(ctx.rangeExpr(i));
			String op = ctx.getChild(2 * i - 1).getText();

			result = switch (op) {
			case "union" -> {
				Set<Object> s = new HashSet<>((Collection<?>) result);
				s.addAll((Collection<?>) rhs);
				yield new ArrayList<>(s);
			}
			case "diff" -> {
				Set<Object> s = new HashSet<>((Collection<?>) result);
				s.removeAll((Collection<?>) rhs);
				yield new ArrayList<>(s);
			}
			case "intersect" -> {
				Set<Object> s = new HashSet<>((Collection<?>) result);
				s.retainAll((Collection<?>) rhs);
				yield new ArrayList<>(s);
			}
			case "symdiff" -> {
				Set<Object> a = new HashSet<>((Collection<?>) result);
				Set<Object> b = new HashSet<>((Collection<?>) rhs);
				Set<Object> res = new HashSet<>(a);
				res.removeAll(b);
				b.removeAll(a);
				res.addAll(b);
				yield new ArrayList<>(res);
			}
			default -> throw new UnimplementedException(op);
			};
		}
		return result;
	}

	@Override
	public Object visitRangeExpr(RangeExprContext ctx) {
		Object lhs = visitAddExpr(ctx.addExpr(0));
		if (ctx.addExpr().size() == 1)
			return lhs;

		Object rhs = visitAddExpr(ctx.addExpr(1));
		return IntStream.rangeClosed((Integer) lhs, (Integer) rhs).boxed().toList();
	}

	@Override
	public Object visitAddExpr(AddExprContext ctx) {
		Object lhs = visitMultExpr(ctx.multExpr(0));

		for (int i = 1; i < ctx.multExpr().size(); i++) {
			Object rhs = visitMultExpr(ctx.multExpr(i));
			String op = ctx.getChild(2 * i - 1).getText();

			lhs = switch (op) {
			case "+" -> {
				if (lhs instanceof Integer li && rhs instanceof Integer ri)
					yield li + ri;
				else
					yield ((Double) lhs) + ((Double) rhs);
			}
			case "-" -> {
				if (lhs instanceof Integer li && rhs instanceof Integer ri)
					yield li - ri;
				else
					yield ((Double) lhs) - ((Double) rhs);
			}
			case "++" -> {
				if (lhs instanceof String ls && rhs instanceof String rs)
					yield ls + rs;
				else if (lhs instanceof Collection<?> lc && rhs instanceof Collection<?> rc) {
					List<Object> l = new ArrayList<>(lc);
					l.addAll(rc);
					yield l;
				} else
					throw new IllegalStateException("Unrecognized types");
			}
			default -> throw new UnimplementedException("Unkown operator: " + op);
			};
		}

		return lhs;
	}

	@Override
	public Object visitMultExpr(MultExprContext ctx) {
		Object lhs = visitPowExpr(ctx.powExpr(0));
		for (int i = 1; i < ctx.powExpr().size(); i++) {
			Object rhs = visitPowExpr(ctx.powExpr(i));
			String op = ctx.getChild(2 * i - 1).getText();

			lhs = switch (op) {
			case "*" -> {
				if (lhs instanceof Integer li && rhs instanceof Integer ri)
					yield li * ri;
				else
					yield ((Double) lhs) * ((Double) rhs);
			}
			case "/" -> (Double) lhs / (Double) rhs;
			case "div" -> (Integer) lhs / (Integer) rhs;
			case "mod" -> (Integer) lhs % (Integer) rhs;
			default -> throw new UnimplementedException(op);
			};
		}
		return lhs;
	}

	@Override
	public Object visitPowExpr(PowExprContext ctx) {
		Object result = visitUnaryExpr(ctx.unaryExpr(0));
		for (int i = 1; i < ctx.unaryExpr().size(); i++) {
			Object rhs = visitUnaryExpr(ctx.unaryExpr(i));
			result = (result instanceof Integer)
				? Math.pow((Integer) result, (Integer) rhs)
				: Math.pow((Double) result, (Double) rhs);
		}
		return result;
	}

	@Override
	public Object visitUnaryExpr(UnaryExprContext ctx) {
		if (ctx.unaryExpr() != null) {
			Object val = visitUnaryExpr(ctx.unaryExpr());
			String op = ctx.getChild(0).getText();
			return switch (op) {
			case "not" -> !((Boolean) val);
			case "+" -> val;
			case "-" -> {
				if (val instanceof Integer vali)
					yield -vali;
				else
					yield -((Double) val);
			}
			default -> throw new UnimplementedException(op);
			};
		}
		return visitPrimary(ctx.primary());
	}

	@Override
	public Object visitPrimary(PrimaryContext ctx) {
		if (ctx.letExpr() != null)
			throw new UnimplementedException("Let expressions are not implemented");

		if (ctx.ident() != null) {
			// Try to see if this is a function call first
			if (!ctx.postfix().isEmpty() && ctx.postfix(0).callSuffix() != null) {
				CallSuffixContext cfctx = ctx.postfix(0).callSuffix();

				String functionName = ctx.ident().getText();

				switch (functionName) {
				case "sum" -> {
					if (cfctx.expr().size() > 1)
						throw new IllegalStateException("Unkown function call sum with multiple arguments");

					List<Object> array = flatten(visitExpr(cfctx.expr(0)));

					if (array.get(0) instanceof Integer)
						return array.stream().mapToInt(Integer.class::cast).sum();
					else
						return array.stream().mapToDouble(Double.class::cast).sum();
				}
				case "length" -> {
					if (cfctx.expr().size() > 1)
						throw new IllegalStateException("Unkown function call length with multiple arguments");

					Object argument = visitExpr(cfctx.expr(0));
					if (argument instanceof String argStr)
						return argStr.length();
					else if (argument instanceof Collection<?> ac)
						return ac.size();
					else
						throw new UnimplementedException("Length function not implemented for this type");
				}

				default -> {
					// case of the arrayXd function call
					if (functionName.startsWith("array")) {

						// In the arrayXd function, all the arguments are
						// List<Object>, so these casts are safe
						@SuppressWarnings("unchecked")
						List<Integer> dimensions = cfctx.expr()
							.subList(0, cfctx.expr().size() - 1)
							.stream()
							.map(extx -> (List<Object>) (visitExpr(extx)))
							.map(List::size)
							.toList();

						@SuppressWarnings("unchecked")
						List<Object> flattened = (List<Object>) (visitExpr(cfctx.expr().getLast()));

						return reformatArray(dimensions, flattened);
					} else
						throw new UnimplementedException("Unkown function");
				}
				}
			}

		}

		Object result;
		if (ctx.ident() != null)
			result = visitIdent(ctx.ident());

		else if (ctx.literal() != null)
			result = visitLiteral(ctx.literal());

		else if (ctx.expr() != null)
			result = visitExpr(ctx.expr());

		else if (ctx.ifThenElseExpr() != null)
			result = visitIfThenElseExpr(ctx.ifThenElseExpr());

		else
			result = visitQuantifierExpr(ctx.quantifierExpr());

		for (PostfixContext p : ctx.postfix()) {
			List<Integer> locations = visitPostfix(p);
			// Since the callSuffix was considered previously and we do not
			// parse field access, we consider only array access in the postfix
			// call, so this cast is safe
			@SuppressWarnings("unchecked")
			List<Object> array = (List<Object>) result;
			return get(array, locations);
		}
		return result;
	}

	@Override
	public List<Integer> visitPostfix(PostfixContext ctx) {
		if (ctx.callSuffix() != null)
			throw new IllegalStateException(
				"Shouldn't be able to reach this callSuffix since it was intercepted in the previous rule");

		if (ctx.fieldAccessTail() != null)
			throw new UnimplementedException("Field access is not implemented");

		return visitArrayAccessTail(ctx.arrayAccessTail());
	}

	@Override
	public Object visitQuantifierExpr(QuantifierExprContext ctx) {
		switch (ctx.ident().getText()) {
		case "sum" -> {
			return visitGeneratorList(ctx.generatorList()).stream()
				.map(generatorValues -> new EvaluatorVisitor(
					augmentEnv(generatorValues)).visitExpr(ctx.expr()))
				.reduce((a, b) -> {
					if (a instanceof Integer ai && b instanceof Integer bi)
						return ai + bi;
					else if (a instanceof Double ad && b instanceof Double bd)
						return ad + bd;
					else
						throw new IllegalStateException("Non-homogenous type in the sum");
				});
		}
		default -> throw new UnimplementedException("Unkown generator function name: " + ctx.getText());
		}
	}

	@Override
	public List<Integer> visitArrayAccessTail(ArrayAccessTailContext ctx) {
		return ctx.expr().stream()
			.map(this::visitExpr)
			.map(e -> {
				if (e instanceof Integer ei)
					// MiniZinc array start at one
					return ei - 1;
				else
					throw new IllegalStateException("Trying to access an array without using an integer: " + e);
			})
			.toList();
	}

	@Override
	public Object visitLiteral(LiteralContext ctx) {
		if (ctx.INT_LITERAL() != null)
			return Integer.parseInt(ctx.INT_LITERAL().getText());

		if (ctx.FLOAT_LITERAL() != null)
			return Double.parseDouble(ctx.FLOAT_LITERAL().getText());

		if (ctx.STRING_LITERAL() != null) {
			// Remove the " at the begin and end of the string
			String result = ctx.STRING_LITERAL().getText();
			return result.substring(1, result.length() - 1);
		}

		if (ctx.getText().equals("true"))
			return true;
		if (ctx.getText().equals("false"))
			return false;

		if (ctx.setLiteral() != null)
			return visitSetLiteral(ctx.setLiteral());

		if (ctx.arrayLiteral() != null)
			return visitArrayLiteral(ctx.arrayLiteral());

		if (ctx.arrayLiteral2d() != null)
			return visitArrayLiteral2d(ctx.arrayLiteral2d());

		if (ctx.tupleLiteral() != null)
			throw new UnimplementedException("Tuples are not implemented");
		if (ctx.recordLiteral() != null)
			throw new UnimplementedException("Records are not implemented");
		throw new UnimplementedException("Unkown type of literal");
	}

	@Override
	public List<Object> visitSetLiteral(SetLiteralContext ctx) {
		if (ctx.generatorList() != null) {
			List<Map<String, Object>> values = visitGeneratorList(ctx.generatorList());
			return values.stream()
				.map(v -> new EvaluatorVisitor(augmentEnv(v)).visitExpr(ctx.expr(0)))
				.toList();
		} else {
			// At this point, a set literal of the form {1, 2, 3} is
			// indistinguishable from a enum declaration of the form {a, b, c}.
			// But in this env, there's no a, b, and c variables declared. This
			// means that this literal is an enum. So if it fails, we parse the
			// expression directly, if not it means that it was a proper one
			try {
				return ctx.expr().stream().map(this::visitExpr).toList();
			} catch (IllegalStateException e) {
				List<String> identifiers = ctx.expr().stream()
					.map(ParseTree::getText)
					.toList();
				Map<String, Integer> valueAssigned = identifiers.stream()
					.collect(Collectors.groupingBy(
						Function.identity(),
						Collectors.summingInt(_ -> 1)));
				env.putAll(valueAssigned);

				return identifiers.stream().map(Object.class::cast).toList();
			}
		}
	}

	@Override
	public List<Object> visitArrayLiteral(ArrayLiteralContext ctx) {
		if (ctx.generatorList() != null) {
			List<Map<String, Object>> values = visitGeneratorList(ctx.generatorList());
			return values.stream()
				.map(v -> new EvaluatorVisitor(augmentEnv(v)).visitExpr(ctx.expr(0)))
				.toList();
		} else
			return ctx.expr().stream()
				.map(this::visitExpr)
				// List needs to be modifiable for future lifting
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public List<Map<String, Object>> visitGeneratorList(GeneratorListContext ctx) {
		Map<String, Collection<?>> generators = ctx.generator().stream()
			// get all the generators
			.map(this::visitGenerator)
			// flatten them in case there are multiple generators in one
			// visitGenerator (see the grammar)
			.flatMap(List::stream)
			// since the keys are always different (since the model compiles) we
			// can transform the stream of maps in a stream of map-entries
			.flatMap(m -> m.entrySet().stream())
			// and then we put the map back together
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue));

		// now we can compute the cartesian product
		List<Map<String, Object>> product = new ArrayList<>();
		product.add(new HashMap<>());
		for (Map.Entry<String, Collection<?>> entry : generators.entrySet()) {
			List<Map<String, Object>> newResult = new ArrayList<>();

			for (Map<String, Object> partial : product) {
				for (Object v : entry.getValue()) {
					Map<String, Object> newMap = new HashMap<>(partial);
					newMap.put(entry.getKey(), v);
					newResult.add(newMap);
				}
			}

			product = newResult;
		}

		// filter where cases
		return product.stream().filter(genEnv -> ctx.expr().stream()
			.allMatch(e -> {
				Map<String, Object> augmented = augmentEnv(genEnv);
				Object accepted = new EvaluatorVisitor(augmented).visitExpr(e);
				return (Boolean) accepted;
			})).toList();
	}

	/**
	 * Since a generator can generate multiple identifiers, it returns a list of
	 * singled key maps that contains the collection of elements
	 */
	@Override
	public List<Map<String, Collection<?>>> visitGenerator(GeneratorContext ctx) {
		List<Map<String, Collection<?>>> result = new ArrayList<>();
		List<String> identifiers = ctx.children.stream()
			.map(ParseTree::getText)
			.takeWhile(c -> !c.equals("in"))
			.filter(e -> !e.equals(","))
			.map(i -> i.equals("_") ? i + (underscoreCounter++) : i)
			.toList();
		Map<String, Collection<?>> acc = new HashMap<>();
		for (String id : identifiers)
			acc.put(id, (Collection<?>) visitExpr(ctx.expr()));

		result.add(acc);
		return result;
	}

	@Override
	public List<List<Object>> visitArrayLiteral2d(ArrayLiteral2dContext ctx) {
		List<List<Object>> result = new ArrayList<>();
		for (ArrayRowContext row : ctx.arrayRow())
			result.add(row.expr().stream()
				.map(this::visitExpr)
				// List may need to be modifiable at the end
				.collect(Collectors.toCollection(ArrayList::new)));

		return result;
	}

	@Override
	public Object visitIfThenElseExpr(IfThenElseExprContext ctx) {
		for (int i = 0; i < ctx.expr().size() - 1; i += 2)
			if ((Boolean) visitExpr(ctx.expr(i)))
				return visitExpr(ctx.expr(i + 1));

		if (ctx.expr().size() % 2 == 1)
			return visitExpr(ctx.expr(ctx.expr().size() - 1));

		throw new IllegalStateException("Didn't match an else case in " + ctx.getText());
	}

	@Override
	public Object visitIdent(IdentContext ctx) {
		String name = ctx.getText();
		if (!env.containsKey(name))
			throw new IllegalStateException("Undefined identifier: " + name);

		return env.get(name);
	}

}
