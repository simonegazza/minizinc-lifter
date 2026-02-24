package me.simonegazza.lifting;

import java.io.File;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.Callable;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@CommandLine.Command(name = "mzn-parameter-lifting", mixinStandardHelpOptions = true, version = "0.1", description = "Parameter lifts a MiniZinc Model")
public class Main implements Callable<Integer> {
    @Parameters(index = "0", description = "MZN model file path")
    private String filePath;

    @Option(names = {"-o", "--output"}, description = "Output file path (default prints to console)")
    private Optional<File> outputFile = Optional.empty();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        CharStream input = CharStreams.fromFileName(filePath);

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        LiftingVisitor visitor = new LiftingVisitor(tokens);
        String lifted = visitor.visitModel(parser.model());

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
