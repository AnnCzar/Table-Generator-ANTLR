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
    private static String option;
    private static String filename;
    private static String tableInput;
    private static String format ;
    private static String output;

    public static void setOption(String selectedOption) {
        option = selectedOption;
    }

    public static void setFilename(String file) {
        filename = file;
    }

    public static void setTableInput(String input) {
        tableInput = input;
    }
    public static void setFormat(String tableFormat) {
        format = tableFormat;

    }

    public static void setOutput(String tableOutput) {
        output = tableOutput;
    }
    public static String getOutput() {
        return output;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Program started");
//        CharStream input = CharStreams.fromStream(System.in);
        CharStream input = null;
        try {
            System.out.println("dziala");
            if(option.equals("Z pliku w formacie .txt")){
                input = CharStreams.fromFileName(filename);
                System.out.println("dziala1");
            }
            else {
                input = CharStreams.fromString(tableInput);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TableGrammarLexer lexer = new TableGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        TableGrammarParser parser = new TableGrammarParser(tokens);

        ParseTree tree = parser.program();
        System.out.println(format);

        if (format.equals("LaTeX")) {
            STGroup group = new STGroupFile("src/main/resources/org/grammar/LatexST.stg"); //otwieramy plik z .stg
            TableLatexVisitor visitor = new TableLatexVisitor(group);
            String latexCode = visitor.visit(tree);
            setOutput(latexCode);
            String texCode = "\\documentclass{article}\n" +
                    "\\begin{document}\n" +
                    latexCode + "\n" +
                    "\\end{document}";
            try {
                var wr = new FileWriter("C:latex.txt", true);
                var wr_current = new FileWriter("C:latex_current.tex", false);
                wr.write(latexCode);
                wr_current.write(texCode);
                wr.close();
                wr_current.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
//        System.out.println(((ST) latexCode).render());

            System.out.println(latexCode);


        } else if (format.equals("html flex")) {
            STGroup group = new STGroupFile("src/main/resources/org/grammar/HTMLFlexST.stg"); //otwieramy plik z .stg
            TableHtmlFlexVisitor visitor = new TableHtmlFlexVisitor(group);
            String htmlFlexCode = visitor.visit(tree);
            setOutput(htmlFlexCode);
            try {
                var wr = new FileWriter("C:flex.html", true);
                var wr_current = new FileWriter("C:flex_current.html", false);
                wr.write(htmlFlexCode);
                wr_current.write(htmlFlexCode);
                wr.close();
                wr_current.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(htmlFlexCode);

        } else {
            STGroup group = new STGroupFile("src/main/resources/org/grammar/HtmlST.stg"); //otwieramy plik z .stg
            TableHtmlFlexVisitor visitor = new TableHtmlFlexVisitor(group);
            String htmlCode = visitor.visit(tree);
            setOutput(htmlCode);
            try {
                var wr = new FileWriter("C:html_table.html", true);
                var wr_current = new FileWriter("C:html_table_current.html", false);
                wr.write(htmlCode);
                wr_current.write(htmlCode);
                wr.close();
                wr_current.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(htmlCode);



        }

//



    }
}
