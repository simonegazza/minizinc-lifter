package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.assumptions.RevokedAssumption;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Represents a lifted version of an array parameter where the entire array is
 * transformed into decision variables.
 * <p>
 * The lifting process replaces the original array with a new array variable and
 * introduces an objective that minimizes the distance between the lifted and
 * original arrays across all indices. The notion of distance can depend on the
 * subtype of the array elements (e.g. scalars, sets, or nested arrays).
 * <p>
 * This class supports multi-dimensional arrays.
 * <p>
 * Special handling is required for the set inner types: the solve piece
 * distance is computed via symmetric difference cardinality.
 * <p>
 * Nested arrays are not currently supported.
 */
public class LiftedArrayParameter extends LiftedParameter {

	protected LiftedArrayParameter(
		OriginalParameter parameter,
		LiftRequest change,
		Set<OriginalParameter> dependencies) {

		super(parameter, List.of(change), dependencies);
	}

	/**
	 * Utility function that returns a string representing an array access.
	 *
	 * @param name    the array name
	 * @param indices the list of indices
	 *
	 * @return an array access string of the form `name[i0, i1, ..]`
	 */
	public static final String arrayAccess(String name, List<?> indices) {
		return name + "[" + indices.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
	}

	/**
	 * Utility function that returns a string a parenthesized expression.
	 *
	 * @param expr the expression to parenthesize
	 *
	 * @return a string of the form `(expr)`
	 */
	private static final String parenthesize(String expr) {
		return "(" + expr + ")";
	}

	@Override
	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		String declaration = "";
		if (parameter.isAssignedAtDeclaration()) {
			CharStream input = CharStreams.fromString(parameter.getExpressionText());
			MiniZincLexer lexer = new MiniZincLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			MiniZincParser parser = new MiniZincParser(tokens);

			Rewriter rewriter = new Rewriter(tokens);
			rewriter.visit(parser.expr());

			declaration = " = " + rewriter.getText();
		}

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ declaration
			+ (parameter.isAssignedAtDeclaration()
				? ""
				: " :: doc_comment(\"" + getLiftedName() + "\")")
			+ ";";
	}

	@Override
	public String getSolvePiece() {
		// TODO: to do this properly, this function should be made recursive (on
		// the subtypes) but it would mean restructuring these classes, so I'll
		// leave it as is for now.

		MiniZincArrayType ct = (MiniZincArrayType) parameter.getType();
		MiniZincType inner = ct.getSubtype();
		List<String> dimensionsExpression = ct.getDimensionsString(false);
		List<String> indices = IntStream
			.range(0, dimensionsExpression.size())
			.mapToObj(e -> "i" + e)
			.toList();

		StringBuilder firstPart = new StringBuilder("abs(");
		if (inner instanceof MiniZincSetType) {
			// Sort of Jaccard Distance
			firstPart.append("card(")
				.append(arrayAccess(getLiftedName(), indices))
				.append(" symdiff ")
				.append(arrayAccess(getOriginalName(), indices))
				.append("))");

		} else if (inner instanceof MiniZincArrayType) { // MiniZincArrayType
			throw new UnimplementedException(
				"Recursive arrays of arrays are not implemented in the solve piece");
		} else {
			firstPart
				.append(arrayAccess(getLiftedName(), indices))
				.append(" - ")
				.append(arrayAccess(getOriginalName(), indices))
				.append(")");
		}

		String secondPart = IntStream.range(0, dimensionsExpression.size())
			.mapToObj(i -> {
				StringBuilder result = new StringBuilder(indices.get(i));
				result.append(" in ");
				// TODO: check that this is enough; we do not parse dimensions
				// and the only case that is considered now for "any range" is
				// "int" even we are not sure it is the only one
				if ("int".equals(dimensionsExpression.get(i))) {
					result.append("index_set");

					if (dimensionsExpression.size() != 1) {
						result.append("_");
						result.append(i + 1);
						result.append("of");
						result.append(dimensionsExpression.size());
					}

					result.append(parenthesize(getOriginalName()));
				} else {
					result.append(dimensionsExpression.get(i));
				}

				return result.toString();
			}).collect(Collectors.joining(", "));

		return (parameter.isAssignedAtDeclaration() ? "0 %" : "")
			+ "sum(["
			+ firstPart
			+ " | "
			+ secondPart
			+ "])";
	}

	@Override
	public String paramArrayPiece(boolean lifted, List<RevokedAssumption> assumptions) {
		// TODO: to do this properly, this function should be made recursive (on
		// the subtypes) but it would mean restructuring these classes, so I'll
		// leave it as is for now.

		MiniZincArrayType ct = (MiniZincArrayType) parameter.getType();
		MiniZincType inner = ct.getSubtype();
		List<String> dimensionsExpression = ct.getDimensionsString(false);
		List<String> indices = IntStream.range(0, dimensionsExpression.size())
			.mapToObj(e -> "i" + e)
			.toList();

		StringBuilder firstPart = new StringBuilder("[if ");

		String coordinates;
		if (assumptions.size() > 0) {
			coordinates = assumptions.stream()
				.map(a -> "(" + IntStream.range(0, a.indices().size())
					.mapToObj(i -> "i" + i + " = " + a.indices().get(i))
					.collect(Collectors.joining(" /\\ ")) + ")")
				.collect(Collectors.joining(" \\/ "));
		} else {
			coordinates = "false";
		}

		firstPart
			.append(coordinates)
			.append(" then\n\t\ttrue\n\telse\n\t\t")
			.append(arrayAccess(lifted ? getLiftedName() : getOriginalName(), indices))
			.append("\n\tendif");

		StringBuilder secondPart = new StringBuilder().append(
			IntStream.range(0, dimensionsExpression.size()).mapToObj(i -> {
				StringBuilder result = new StringBuilder(indices.get(i));
				result.append(" in ");
				// TODO: check that this is enough; we do not parse
				// dimensions and the only case that is considered now for "any
				// range" is "int" even we are not sure it is the only one
				if ("int".equals(dimensionsExpression.get(i))) {
					result.append("index_set");

					if (dimensionsExpression.size() != 1) {
						result.append("_");
						result.append(i + 1);
						result.append("of");
						result.append(dimensionsExpression.size());
					}

					result.append(parenthesize(getOriginalName()));
				} else {
					result.append(dimensionsExpression.get(i));
				}

				return result.toString();
			}).collect(Collectors.joining(", ")));

		StringBuilder preamble = new StringBuilder("");
		if (parameter.isAssignedAtDeclaration() && assumptions.isEmpty()) {
			preamble.append("[] %");
		}

		if (inner instanceof MiniZincSetType) {
			return new StringBuilder("[if false then true else e endif | ")
				.append(secondPart)
				.append(", e in ")
				.append(arrayAccess(lifted ? getLiftedName() : getOriginalName(), indices))
				.append(" where e in ")
				.append(arrayAccess(getOriginalName(), indices))
				.append("]")
				.toString();
		}

		return firstPart
			.append(" | ")
			.append(secondPart)
			.append("]")
			.toString();
	}

}
