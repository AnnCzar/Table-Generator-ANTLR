// DELETE THIS CONTENT IF YOU PUT COMBINED GRAMMAR IN Parser TAB
lexer grammar TableGrammarLexer;

AND : 'and' ;
OR : 'or' ;
NOT : 'not' ;
EQ : '=' ;
COMMA : ',' ;
SEMI : ';' ;
COLON : ':' ;
QUOTE : '"' ;
DIVIDER : '|' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;
TABLE : 'table' ;
COLUMNS : 'columns' ;
HEADER : 'header' ;
LAYOUT : 'layout' ;
ROW : 'row' ;

INT : [0-9]+ ;
WS: [ \t\n\r\f]+ -> skip ;
TEXT: '"' ~[{}|)]+ '"' ;
ID: [a-zA-Z_0-9]+ ;