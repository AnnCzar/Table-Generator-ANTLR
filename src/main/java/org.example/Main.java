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

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Program started");
//        CharStream input = CharStreams.fromStream(System.in);
        CharStream input = null;

        try {
            System.out.println("dziala");
            input = CharStreams.fromFileName("src/main/java/org.example/we.first");
            System.out.println("dziala1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TableGrammarLexer lexer = new TableGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        TableGrammarParser parser = new TableGrammarParser(tokens);

        ParseTree tree = parser.program();

//        STGroup group = new STGroupFile("src/main/resources/org/grammar/LatexST.stg"); //otwieramy plik z .stg
        STGroup group = new STGroupFile("src/main/resources/org/grammar/HTMLFlexST.stg"); //otwieramy plik z .stg

//        TableLatexVisitor visitor = new TableLatexVisitor(group);

        TableHtmlFlexVisitor visitor = new TableHtmlFlexVisitor(group);
        String latexCode = visitor.visit(tree);   // nie chcialo mi sie zmianiac anzwy
        try {
//            var wr = new FileWriter("C:kod_tabeli.txt", true);
            var wr = new FileWriter("C:proba.html", true);
            wr.write(latexCode);
            wr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(((ST) latexCode).render());
        System.out.println(latexCode);

    }
}
