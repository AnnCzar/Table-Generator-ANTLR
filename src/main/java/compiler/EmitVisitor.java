package compiler;

import org.grammar.TableGrammarParser;
import org.grammar.TableGrammarParserBaseVisitor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class EmitVisitor extends TableGrammarParserBaseVisitor<ST> {
    private final STGroup stGroup;

    public EmitVisitor(STGroup group) {
        super();
        this.stGroup = group;
    }

    @Override
    protected ST defaultResult() {
        return stGroup.getInstanceOf("deflt");
    }

    @Override
    protected ST aggregateResult(ST aggregate, ST nextResult) {
        if(nextResult!=null)
            aggregate.add("elem",nextResult);
        return aggregate;
    }


//    @Override
//    public ST visitTerminal(TerminalNode node) {
//        return new ST("Terminal node:<n>").add("n",node.getText());
//    }

    @Override
    public ST visitTable(TableGrammarParser.TableContext ctx) {
        ST table = stGroup.getInstanceOf("table");
        return table.add("a", visit(ctx.inside()));
    }

    @Override
    public ST visitHead(TableGrammarParser.HeadContext ctx) {
        ST row = stGroup.getInstanceOf("header_row");
        return row.add("a", visit(ctx.headRow()));
    }

    @Override
    public ST visitHeadRow(TableGrammarParser.HeadRowContext ctx) {
        ST row = stGroup.getInstanceOf("table_header");
        String s = ctx.formattedText().TEXT().getText();
        s = s.substring(1, s.length()-1);
        if (ctx.DIVIDER() != null) {
            return row.add("a", s).add("b", visit(ctx.headRow()));
        } else  {
            return row.add("a", s);
        }
    }

    //    @Override
//    public ST visitInt_tok(firstParser.Int_tokContext ctx) {
//        ST st = stGroup.getInstanceOf("int");
//        st.add("i",ctx.INT().getText());
//        return st;
//    }
//
//    @Override
//    public ST visitDecl_stat(firstParser.Decl_statContext ctx) {
//        ST st = stGroup.getInstanceOf("dek");
//        return st.add("n", ctx.ID().getText());
//    }
//
//    @Override
//    public ST visitAssign(firstParser.AssignContext ctx) {
//        ST st = stGroup.getInstanceOf("zap");
//        return st.add("i",visit(ctx.expr())).add("n", ctx.ID().getText());
//    }
//
//    @Override
//    public ST visitDecl_assign(firstParser.Decl_assignContext ctx) {
//        ST st = stGroup.getInstanceOf("dek_zap");
//        ST st1 = stGroup.getInstanceOf("dek");
//        st1.add("n",ctx.ID().getText());
//        ST st2 = stGroup.getInstanceOf("zap");
//        st2.add("n",ctx.ID().getText()).add("i", visit(ctx.expr()));
//        return st.add("d", st1).add("z", st2);
//    }
//
//    @Override
//    public ST visitVar_id(firstParser.Var_idContext ctx) {
//        ST st = stGroup.getInstanceOf("czyt");
//        return st.add("n",ctx.ID().getText());
//    }
//
//    @Override
//    public ST visitBinOp(firstParser.BinOpContext ctx) {
//        ST st;
//        switch (ctx.op.getType()) {
//            case firstLexer.ADD:
//                st = stGroup.getInstanceOf("dodaj");
//                return st.add("p1",visit(ctx.l)).add("p2",visit(ctx.r));
//            case firstLexer.SUB:
//                st = stGroup.getInstanceOf("odejmij");
//                return st.add("p1",visit(ctx.l)).add("p2",visit(ctx.r));
//            case firstLexer.MUL:
//                st = stGroup.getInstanceOf("pomnoz");
//                return st.add("p1",visit(ctx.l)).add("p2",visit(ctx.r));
//            case firstLexer.DIV:
//                st = stGroup.getInstanceOf("podziel");
//                return st.add("p1",visit(ctx.l)).add("p2",visit(ctx.r));
//        }
//        return null;
//    }
}
