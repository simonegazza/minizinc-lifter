grammar FlatZinc;

model: predicateItem* parDeclItem* varDeclItem* constraintItem* solveItem EOF;

// Predicates
predicateItem: 'predicate' IDENTIFIER '(' predParamDeclList? ')' ';';
predParamDeclList: predParamDecl (',' predParamDecl)*;
predParamDecl: predParamType ':' IDENTIFIER;

// Types
basicParType
    : 'bool'
    | 'int'
    | 'float'
    | 'set' 'of' 'int'
    ;

basicVarType
    : 'var' basicParType
    | 'var' INT_LITERAL '..' INT_LITERAL
    | 'var' '{' intLiteralList '}'
    | 'var' FLOAT_LITERAL '..' FLOAT_LITERAL
    | 'var' 'set' 'of' INT_LITERAL '..' INT_LITERAL
    | 'var' 'set' 'of' '{' intLiteralList? '}'
    ;

arrayVarType: 'array' '[' indexSet ']' 'of' basicVarType;

predIndexSet: indexSet | 'int';
indexSet: INT_LITERAL '..' INT_LITERAL;

basicPredParamType
    : basicParType
    | basicVarType
    | INT_LITERAL '..' INT_LITERAL
    | FLOAT_LITERAL '..' FLOAT_LITERAL
    | '{' intLiteralList '}'
    | 'set' 'of' INT_LITERAL '..' INT_LITERAL
    | 'set' 'of' '{' intLiteralList? '}'
    ;

predParamType
    : basicPredParamType
    | 'array' '[' predIndexSet ']' 'of' basicPredParamType
    ;

// Expressions
expr
    : basicExpr
    | arrayLiteral
    ;

basicExpr
    : 'true'
    | 'false'
    | INT_LITERAL
    | FLOAT_LITERAL
    | setLiteral
    | IDENTIFIER
    ;

setLiteral
    : '{' intLiteralList? '}'
    | INT_LITERAL '..' INT_LITERAL
    | '{' floatLiteralList? '}'
    | FLOAT_LITERAL '..' FLOAT_LITERAL
    ;

basicExprList: basicExpr (',' basicExpr)*;

basicLiteralExprList: basicExpr (',' basicExpr)*;

arrayLiteral: '[' basicExprList? ']';

// Lists
intLiteralList: INT_LITERAL (',' INT_LITERAL)*;
floatLiteralList: FLOAT_LITERAL (',' FLOAT_LITERAL)*;

// Declarations
parDeclItem: 'array' '[' indexSet ']' 'of' basicParType ':' IDENTIFIER '=' '[' basicLiteralExprList? ']' ';';

varDeclItem
    : basicVarType ':' IDENTIFIER ('::' annotation)* ('=' basicExpr)? ';'
    | arrayVarType ':' IDENTIFIER ('::' annotation)* '=' arrayLiteral ';'
    ;

// Constraints
constraintItem: 'constraint' IDENTIFIER '(' (expr (',' expr)*)? ')' ('::' annotation)* ';';

// Solve
solveItem
    : 'solve' ('::' annotation)* 'satisfy' ';'
    | 'solve' ('::' annotation)* 'minimize' basicExpr ';'
    | 'solve' ('::' annotation)* 'maximize' basicExpr ';'
    ;

// Annotations
annotation: IDENTIFIER ('(' annExpr (',' annExpr)* ')')?;

annExpr
    : basicAnnExpr
    | '[' (basicAnnExpr (',' basicAnnExpr)*)? ']'
    ;

basicAnnExpr
    : basicExpr
    | IDENTIFIER
    | STRING_LITERAL
    | annotation
    ;

// Literals
INT_LITERAL
    : '-'? [0-9]+
    | '-'? '0x' [0-9A-Fa-f]+
    | '-'? '0o' [0-7]+
    ;

FLOAT_LITERAL
    : '-'? [0-9]+ '.' [0-9]+ ([Ee] [+-]? [0-9]+)?
    | '-'? [0-9]+ [Ee] [+-]? [0-9]+
    ;
STRING_LITERAL: '"' ( ~["\\\r\n] | '\\' . )* '"';

// Identifiers
IDENTIFIER:         [A-Za-z] [A-Za-z0-9_]*;
VAR_PAR_IDENTIFIER: [A-Za-z_] [A-Za-z0-9_]*;

// Whitespace and comments
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '%' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
