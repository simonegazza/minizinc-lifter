package me.simonegazza.lift.parameters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class LiftedDependencyParameter extends LiftedParameter {

	/**
	 * Set of dependencies for this parameter.
	 */
	private final Set<OriginalParameter> dependencies;

	public LiftedDependencyParameter(
		OriginalParameter parameter,
		Set<OriginalParameter> dependencies) {

		super(parameter, List.of());
		this.dependencies = dependencies;
	}

	@Override
	public String liftDeclaration(Map<String, Object> environment) {
		Optional<String> bound = changes.size() > 0
			? changes.get(0).getBounds()
			: Optional.empty();

		CharStream input = CharStreams.fromString(parameter.getExpressionText());
		MiniZincLexer lexer = new MiniZincLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		Rewriter rewriter = new Rewriter(tokens);
		rewriter.visit(parser.expr());

		return parameter.getType().lift(bound)
			+ ": "
			+ getLiftedName()
			+ " = "
			+ rewriter.getText()
			+ ";";
	}

	@Override
	public String getSolvePiece() {
		return "0";
	}

	/**
	 * Internal rewriter used to rewrite identifiers.
	 */
	private class Rewriter extends MiniZincBaseVisitor<Void> {

		/**
		 * Token stream rewriter for modifying the expression text.
		 */
		private final TokenStreamRewriter rewriter;

		/**
		 * Get the original parameter by name.
		 *
		 * @param name the name of the {@link OriginalParameter}
		 *
		 * @return an {@link Optional} {@link OriginalParameter}
		 */
		private Optional<OriginalParameter> getByName(String name) {
			return dependencies.stream()
				.filter(l -> l.getName().equals(name))
				.findAny();
		}

		public Rewriter(TokenStream tokens) {
			this.rewriter = new TokenStreamRewriter(tokens);
		}

		@Override
		public Void visitIdent(IdentContext ctx) {
			Optional<OriginalParameter> op = getByName(ctx.getText());
			if (op.isPresent())
				rewriter.replace(
					ctx.IDENT().getSymbol(),
					LiftedParameter.liftString(op.get().getName()));

			return null;
		}

		/**
		 * Get the text from the internal rewriter.
		 *
		 * @return the text of this rewriter
		 */
		public String getText() {
			return rewriter.getText();
		}

	}

}
