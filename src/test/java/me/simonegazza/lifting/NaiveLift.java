package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Set;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprAndIdContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.jupiter.api.Test;

class NaiveLift {
    private class VerifierVisitor extends MiniZincBaseVisitor<Void> {
        private final String parameter;
        private boolean verified;

        public VerifierVisitor(String parameter) {
            this.parameter = parameter;
            this.setVerified(false);
        }

        public boolean isVerified() {
            return verified;
        }

        private void setVerified(boolean verified) {
            this.verified = verified;
        }

        @Override
        public Void visitVarDeclItem(VarDeclItemContext ctx) {
            TiExprAndIdContext declaration = ctx.tiExprAndId();
            TiExprContext typeExpr = declaration.tiExpr();
            BaseTiExprContext type;
            if (declaration.IDENT().getText().equals(parameter)) {
                // simple variable declaration
                if (declaration.tiExpr().baseTiExpr() != null)
                    type = typeExpr.baseTiExpr();
                else // array declaration
                    type = typeExpr.arrayTiExpr().baseTiExpr();

                if (type.getChild(0).getText().startsWith("var")) {
                    setVerified(true);
                }

            }
            return null;
        }

    }

    private String naiveLift(String modelPath, Set<String> parameters) throws IOException {
        CharStream input = CharStreams.fromFileName(modelPath);

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        VarInserterVisitor visitor = new VarInserterVisitor(tokens, parameters);
        visitor.visitModel(parser.model());
        return visitor.getTranspiled();
    }

    private boolean verify(String model, String parameter) {
        CharStream input = CharStreams.fromString(model);

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        VerifierVisitor verifier = new VerifierVisitor(parameter);
        verifier.visitModel(parser.model());
        return verifier.isVerified();
    }

    @Test
    void simpleParameterTest() throws IOException {
        String parameter = "capacity";
        String liftedModel = naiveLift("resources/knapsack/original.mzn", Set.of(parameter));

        assertTrue(verify(liftedModel, parameter));
    }

    @Test
    void arrayParameterTest() throws IOException {
        String parameter = "profit";
        String liftedModel = naiveLift("resources/knapsack/original.mzn", Set.of(parameter));

        assertTrue(verify(liftedModel, parameter));
    }
}
