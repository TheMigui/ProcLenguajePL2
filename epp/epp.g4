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
    | whileStmt
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
    : boolExpr QMARKS
    ;

ifStmt
    : (boolExpr QMARKS (NEWLINE)*)? SI ARROW (NEWLINE)* block
      (NO ARROW (NEWLINE)* block)?
      terminarStmt
    ;

whileStmt
    : (boolExpr QMARKS (NEWLINE)*)? MIENTRAS ARROW (NEWLINE)* block terminarStmt
    ;

terminarStmt
    : TERMINAR (END)?
    ;

block
    : (NEWLINE | statement | COMMENT_LINE)*
    ;

expr
    : expr '+' term
    | expr '-' term
    | term
    ;

term
    : term '*' factor
    | term '/' factor
    | factor
    ;

factor
    : '(' expr ')'
    | '-' factor
    | NUMBER
    | ID
    | STRING
    ;

boolExpr
    : boolExpr OR boolExpr
    | boolExpr AND boolExpr
    | NOT boolExpr
    | expr compOp expr
    | '(' boolExpr ')'
    | expr
    ;

compOp
    : '==' | '!=' | '<=' | '>=' | '<' | '>'
    ;

// ========================
// LEXER RULES
// ========================

ASIGNAR : 'asignar';
MOSTRAR : 'mostrar';
SI      : 'si';
NO      : 'no';
TERMINAR: 'terminar';
MIENTRAS: 'mientras';
ARROW   : '->';
QMARKS  : '???';
END     : ';' [ \t]* 'P';

AND     : '&&';
OR      : '||';
NOT     : '!';

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
