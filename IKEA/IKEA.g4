grammar IKEA;
prog
    : declaracion paso+ EOF
    ;

declaracion
    : (herramienta ';')* (herraje (',' 'Usable')? ';')*
    ;
    
paso
    : 'PASO ' NUM ':' (instruccion ';')+
    ;
    
instruccion
    : montaje ('CON '(herramienta | herraje) | 'MONTAR') '(' ((pieza | herraje) (',' (pieza | herraje))*)? ')'
    | montaje ('CON 'herramienta)? 'FIJAR' '(' herraje ')'
    | 'NOTA ' STRING
    ;

herramienta
    : 'HERRAMIENTA ' NOMBRE
    ;

herraje
    : 'HERRAJE ' ID ',' cantidad
    ;

pieza
    : 'PIEZA ' NOMBRE
    ;

montaje
    : 'MONTAJE ' NOMBRE
    ;

cantidad
    : NUM
    ;

NOMBRE  : ([a-zA-Z]|' ')+ ;
ID      : [0-9][0-9][0-9][0-9][0-9][0-9]([0-9][0-9])? ;
NUM     : [0-9]+ ;
STRING  : '"' (~[;"\r\n])* '"' ;
