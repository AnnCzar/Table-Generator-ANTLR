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
ROWS : 'rows' ;

CAPTION: 'caption';
BORDER: 'border';
ALIGN: 'align';

LEFT: 'left'; // to do położenia tekstu w tab
RIGHT: 'right';
CENTER  : 'center' ;


ITALIC : 'italic' ;
BOLD : 'bold' ;

FRAME   : 'frame' ;
GRID    : 'grid' ;
// ROWS    : 'rows' ;
// COLS    : 'cols' ;
NONE    : 'none' ;

INT : [0-9]+ ;
WS: [ \t\n\r\f]+ -> skip ;
TEXT: '"' ~[{}|)]+ '"' ;
ID: [a-zA-Z_0-9]+ ;