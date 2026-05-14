package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.lift.RevokedAssumption;
import me.simonegazza.lift.requests.LiftRequest;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.exception.UnimplementedException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class LiftedArrayParameter extends LiftedParameter {

	protected LiftedArrayParameter(
		OriginalParameter parameter,
		LiftRequest change,
		Set<OriginalParameter> dependencies) {

		super(parameter, List.of(change), dependencies);
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
				.append(getLiftedName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]")
				.append(" symdiff ")
				.append(getOriginalName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]))");

		} else if (inner instanceof MiniZincArrayType) { // MiniZincArrayType
			throw new UnimplementedException(
				"Recursive arrays of arrays are not implemented in the solve piece");
		} else {
			firstPart.append(getLiftedName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("] - ")
				.append(getOriginalName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("])");
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

					result.append("(");
					result.append(getOriginalName());
					result.append(")");
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
		List<String> indices = IntStream
			.range(0, dimensionsExpression.size())
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
			.append(" then\n\ttrue\nelse\n\t")
			.append(lifted ? getLiftedName() : getOriginalName())
			.append("[")
			.append(indices.stream().collect(Collectors.joining(", ")))
			.append("] endif");

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

					result.append("(");
					result.append(getOriginalName());
					result.append(")");
				} else {
					result.append(dimensionsExpression.get(i));
				}

				return result.toString();
			}).collect(Collectors.joining(", ")));

		StringBuilder preamble = new StringBuilder(parameter.isAssignedAtDeclaration() ? "[] %" : "");
		if (inner instanceof MiniZincSetType) {
			return preamble
				.append("[if false then true else e endif | ")
				.append(secondPart)
				.append(", e in ")
				.append(lifted ? getLiftedName() : getOriginalName())
				.append("[")
				.append(indices.stream().collect(Collectors.joining(", ")))
				.append("]]")
				.toString();
		}

		return preamble
			.append(firstPart)
			.append(" | ")
			.append(secondPart)
			.append("]")
			.toString();
	}

}
