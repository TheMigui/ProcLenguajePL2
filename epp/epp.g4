grammar epp;

// ========================
// PARSER RULES
// ========================

prog
    : (line | NEWLINE)* EOF
    ;

line
    : statement
    | cond_line
    | terminarStmt
    | COMMENT_LINE
    ;

statement
    : assignment
    | simpleAssign
    | show
    | ifStmt
    ;

assignment
    : ASIGNAR ID '=' expr END
    ;

simpleAssign
    : ID '=' expr END
    ;

show
    : MOSTRAR expr END
    ;

cond_line
    : comp QMARKS
    ;

ifStmt
    : (comp QMARKS (NEWLINE)*)? SI ARROW (NEWLINE)* block
      (NO ARROW (NEWLINE)* block)?
      terminarStmt
    ;

terminarStmt
    : TERMINAR (END)?
    ;

block
    : (NEWLINE | statement | COMMENT_LINE)*
    ;

comp
    : expr (('<' | '>' | '<=' | '>=' | '==' | '!=') expr)
    ;

expr
    : expr '+' term
    | expr '-' term
    | term
    ;

term
    : '(' expr ')'
    | '-' term
    | NUMBER
    | ID
    | STRING
    ;

// ========================
// LEXER RULES
// ========================

ASIGNAR : 'asignar';
MOSTRAR : 'mostrar';
SI      : 'si';
NO      : 'no';
TERMINAR: 'terminar';
ARROW   : '->';
QMARKS  : '???';
END     : ';' [ \t]* 'P';

ID      : [a-zA-Z_] [a-zA-Z0-9_]*;
NUMBER  : [0-9]+ ('.' [0-9]+)?;
STRING  : '"' ~["\r\n]* '"';

COMMENT_LINE
    : '#' ~[\r\n]* -> skip
    ;

NEWLINE
    : '\r\n' | '\r' | '\n'
    ;

WS
    : [ \t]+ -> skip
    ;
