package me.simonegazza.lifting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincLexer;
import me.simonegazza.antlr.minizinc.MiniZincParser;
import me.simonegazza.antlr.minizinc.MiniZincParser.VarDeclItemContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.jupiter.api.Test;

class NaiveLiftTest {
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
            var declaration = ctx.tiExprAndId();
            if (declaration.IDENT().getText().equals(parameter)) {
                // simple variable declaration verification
                if (declaration.tiExpr().baseTiExpr() != null) {
                    var type = declaration.tiExpr().baseTiExpr();
                    setVerified(type.getText().startsWith("var"));
                }
                // array declaration
                else {
                    throw new InternalError();
                }
            }
            return null;
        }

    }

    private String naiveLift(String modelPath, String parameter) throws IOException {
        CharStream input = CharStreams.fromFileName(modelPath);

        Lexer lexer = new MiniZincLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniZincParser parser = new MiniZincParser(tokens);

        LiftingVisitor visitor = new LiftingVisitor(tokens, parameter);
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
    void test() throws IOException {
        String parameter = "capacity";
        String liftedModel = naiveLift("resources/knapsack.mzn", parameter);

        assertTrue(verify(liftedModel, parameter));
    }
}
