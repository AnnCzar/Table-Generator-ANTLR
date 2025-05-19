parser grammar TableGrammarParser;
options { tokenVocab=TableGrammarLexer; }

program
    : table EOF
    ;

table
    : TABLE ID LCURLY inside RCURLY
    ;

inside
    : column align borderStyle? head rows
    ;

column
    : COLUMNS COLON INT
    ;

head
    : HEADER COLON LCURLY headRow RCURLY
    ;

headRow
    : formattedText DIVIDER headRow
    | formattedText
    ;

align
    : ALIGN COLON (LEFT | RIGHT | CENTER)
    ;

borderStyle  // styl obramowanie - opcjonalne
    : BORDER COLON (FRAME | GRID | NONE)
    ;

rows
    : ROWS COLON LCURLY ( LPAREN row RPAREN )* RCURLY
    ;

row
    : content DIVIDER row
    | content
    ;

content
    : formattedText+
    | table
    ;

formattedText // opcjonalne formatowanie tekstu
    : (ITALIC | BOLD)? TEXT
    ;