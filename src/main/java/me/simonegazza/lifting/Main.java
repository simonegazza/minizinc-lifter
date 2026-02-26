package me.simonegazza.lifting;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import picocli.CommandLine;
import picocli.CommandLine.Option;

@CommandLine.Command(name = "mzn-parameter-lifting", mixinStandardHelpOptions = true, version = "0.1", description = "Parameter lifts a MiniZinc Model")
public class Main implements Callable<Integer> {
	private final static String DELIMITER = ":";

	@Option(names = { "-m", "--models" }, arity = "1..*", description = "MZN and MDN file paths")
	private List<String> filePaths;

	@Option(names = { "-p", "--parameters" }, arity = "1..*", description = "Parameter to lift the model with")
	private Set<String> parameters;

	@Option(names = { "-o", "--output" }, description = "Output file path (default prints to console)")
	private Optional<File> outputFile = Optional.empty();

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() throws Exception {
		if (filePaths == null)
			throw new IllegalArgumentException("no file to parse, exiting");
		else if (parameters == null)
			throw new IllegalArgumentException("no lifting asked, nothing to do");

		List<CLIParameter> cliParameters = parameters.stream().map(p -> {
			if (p.contains(DELIMITER)) {
				String[] a = p.split(DELIMITER);
				return new CLIParameter(a[0], a[1]);
			} else
				return new CLIParameter(p);
		}).toList();

		StringBuilder sb = new StringBuilder();
		for (String fp : filePaths)
			sb.append(Files.readString(Path.of(fp)) + "\n");

		CharStream input = CharStreams.fromString(sb.toString());
		Lexer lexer = new MiniZincLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		MiniZincParser parser = new MiniZincParser(tokens);

		LiftingVisitor lv = new LiftingVisitor(tokens, cliParameters);
		lv.visitModel(parser.model());
		String lifted = lv.getTranspiled();

		if (outputFile.isEmpty()) {
			System.out.println(lifted);
		} else {
			PrintWriter pw = new PrintWriter(outputFile.get());
			pw.println(lifted);
			pw.close();
		}

		return 0;
	}
}
