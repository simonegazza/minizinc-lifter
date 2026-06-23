package me.simonegazza.lift.learning;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.Callable;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.ItemContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.ModelContext;
import me.simonegazza.lift.parameters.OriginalParameter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Removes the definition of parameters in a MiniZinc model.
 */
public class Remover extends MiniZincBaseVisitor<Void> implements Callable<String> {

	/**
	 * List of parameters that needs to be removed in the model.
	 */
	private final Collection<OriginalParameter> params;

	/**
	 * Token stream rewriter for modifying the MiniZinc model text.
	 */
	private final TokenStreamRewriter rewriter;

	/**
	 * The model context in which this will be called.
	 */
	private final ModelContext modelCtx;

	/**
	 * Get the lifted parameter by name.
	 *
	 * @param name the name of the {@link OriginalParameter}
	 *
	 * @return an {@link Optional} {@link OriginalParameter}
	 */
	private Optional<OriginalParameter> getByName(String name) {
		return params.stream()
			.filter(l -> l.getName().equals(name))
			.findAny();
	}

	public Remover(
		Collection<OriginalParameter> params,
		String model) {

		this.params = params;

		CharStream input = CharStreams.fromString(model);
		Lexer lexer = new MiniZincLexer(input);
		TokenStream ts = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(ts);

		modelCtx = parser.model();
		rewriter = new TokenStreamRewriter(ts);
	}

	@Override
	public Void visitItem(ItemContext ctx) {
		if (ctx.varDeclItem() != null) {
			Optional<OriginalParameter> p = getByName(ctx.varDeclItem().tiExprAndId().ident().getText());
			if (p.isPresent()) {
				rewriter.delete(ctx.getStart(), ctx.getStop());
			}
		} else if (ctx.assignItem() != null) {
			Optional<OriginalParameter> p = getByName(ctx.assignItem().ident().getText());
			if (p.isPresent()) {
				rewriter.delete(ctx.getStart(), ctx.getStop());
			}
		}

		return null;
	}

	@Override
	public String call() throws Exception {
		visitModel(modelCtx);
		return rewriter.getText();
	}
}
