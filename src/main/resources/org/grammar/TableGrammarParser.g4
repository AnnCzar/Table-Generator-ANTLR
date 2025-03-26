parser grammar TableGrammarParser;
options { tokenVocab=TableGrammarLexer; }

program
    : table EOF
    ;

table
    : TABLE ID LCURLY inside RCURLY
    ;

inside
    : column head layout row+
    ;

column
    : COLUMNS COLON INT
    ;

head
    : HEADER COLON LCURLY TEXT (DIVIDER TEXT)* RCURLY
    ;

layout
    : LAYOUT COLON LCURLY TEXT (DIVIDER TEXT)* RCURLY
    ;

row
    : ROW COLON LCURLY content (DIVIDER content)* RCURLY
    ;

content
    : TEXT
    | table
    ;