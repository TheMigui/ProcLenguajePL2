grammar MontajeIKEA;

/*
 * ===========================================================
 *   Lenguaje de Montaje IKEA (LMI)
 *   Gramática formal para describir instrucciones de montaje
 *   Ejemplo de uso:
 *
 *   MONTAJE "BAGGEBO Estantería" {
 *     PIEZA P1(lateral_izq, metal, 136x30)
 *     PIEZA P2(lateral_der, metal, 136x30)
 *     HERRAJE H1(tornillo)
 *     HERRAMIENTA T1(destornillador)
 *
 *     PASO 1:
 *       ENSAMBLAR(P1, P2)
 *       ATORNILLAR(P1, P3, H1)
 *       USAR(T1)
 *       NOTA "Asegúrese de apretar bien los tornillos."
 *   }
 * ===========================================================
 */

// ---------------------------
// Reglas principales
// ---------------------------

montaje
    : 'MONTAJE' STRING '{' elemento* paso+ '}' EOF
    ;

elemento
    : pieza
    | herraje
    | herramienta
    ;

pieza
    : 'PIEZA' ID '(' tipo ',' material (',' dimension)? ')'
    ;

herraje
    : 'HERRAJE' ID '(' tipo ')'
    ;

herramienta
    : 'HERRAMIENTA' ID
    ;

// ---------------------------
// Pasos de montaje
// ---------------------------

paso
    : 'PASO' NUM ':' instruccion+ // cada paso tiene una o más instrucciones
    ;

instruccion
    : accion
    | nota
    | bucle
    ;

// ---------------------------
// Acciones (verbos de montaje)
// ---------------------------

accion
    : verbo '(' argumentos? ')'
    ;

verbo
    : 'ENSAMBLAR'
    | 'ATORNILLAR'
    | 'ENCOLAR'
    | 'FIJAR'
    | 'COLOCAR'
    | 'INTRODUCIR'
    | 'APRETAR'
    | 'VOLTEAR'
    | 'CONECTAR'
    | 'USAR'
    | 'ANCLAR'
    ;

argumentos
    : argumento (',' argumento)*
    ;

argumento
    : ID
    | STRING
    | NUM
    | lista
    ;

lista
    : '[' ID (',' ID)* ']'
    ;

// ---------------------------
// Control de flujo (repeticiones)
// ---------------------------

bucle
    : 'PARA' 'CADA' ID 'IN' lista ':' instruccion+
    ;

// ---------------------------
// Notas y comentarios
// ---------------------------

nota
    : 'NOTA' STRING
    ;

// ---------------------------
// Tipos básicos
// ---------------------------

tipo
    : ID
    ;

material
    : ID
    ;

dimension
    : NUM 'x' NUM
    ;

// ---------------------------
// Tokens léxicos
// ---------------------------

ID          : [A-Za-z_][A-Za-z0-9_]* ;
NUM         : [0-9]+ ;
STRING      : '"' (~["\r\n])* '"' ;
WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '//' ~[\r\n]* -> skip ;
