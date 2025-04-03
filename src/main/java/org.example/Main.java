package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.grammar.TableGrammarLexer;
import org.grammar.TableGrammarParser;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream input = CharStreams.fromStream(System.in);

        TableGrammarLexer lexer = new TableGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        TableGrammarParser parser = new TableGrammarParser(tokens);

        ParseTree tree = parser.program();

        STGroup group = new STGroupFile("src/main/resources/org/grammar/LatexST.stg"); //otwieramy plik z .stg

        TableLatexVisitor visitor = new TableLatexVisitor(group);
        String latexCode = visitor.visit(tree);
//        System.out.println(((ST) latexCode).render());
        System.out.println(latexCode);

    }
}
