parser grammar TableGrammarParser;
options { tokenVocab=TableGrammarLexer; }

program
    : table EOF
    ;

table  // dodanie ew podpisów - do przemyślenia
    : TABLE ID caption? LCURLY inside RCURLY
    ;
caption
    : CAPTION COLON TEXT
    ;

inside
    : column head layout borderStyle? row+
    ;

column
    : COLUMNS COLON INT
    ;

//head
//    : HEADER COLON LCURLY TEXT (DIVIDER TEXT)* RCURLY
//    ;
head
    : HEADER COLON LCURLY headerCell (DIVIDER headerCell)* RCURLY
    ;

headerCell  // mozna zdef. ew. wyrówananie czy łączenie komórek
    : formattedText (ALIGN COLON alignStyle)?
    ;

alignStyle
    : (LEFT | RIGHT | CENTER)?
    ;

layout
    : LAYOUT COLON LCURLY cellLayout (DIVIDER cellLayout)* RCURLY
    ;

cellLayout
    : (ALIGN COLON alignStyle)?
    ;

borderStyle  // styl obramowanie - opcjonalne
    : BORDER COLON (FRAME | GRID | NONE)
    ;
row
    : ROW COLON LCURLY content (DIVIDER content)* RCURLY
    ;

content
    : formattedText
    | table
    ;

formattedText // opcjonalne formatowanie tkestu
    : (ITALIC | BOLD)? TEXT
    ;