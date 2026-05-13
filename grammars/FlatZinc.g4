grammar FlatZinc;

model
    : predicateItem* parDeclItem* varDeclItem* constraintItem* solveItem EOF
    ;

// Predicate items
predicateItem
    : PREDICATE identifier LPAREN predParamDeclList? RPAREN SEMI
    ;

predParamDeclList
    : predParamDecl (COMMA predParamDecl)*
    ;

predParamDecl
    : predParamType COLON identifier
    ;

// Types
basicParType
    : BOOL
    | INT
    | FLOAT
    | SET OF INT
    ;

basicVarType
    : VAR basicParType
    | VAR intLiteral RANGE intLiteral
    | VAR LBRACE intLiteralList RBRACE
    | VAR floatLiteral RANGE floatLiteral
    | VAR SET OF intLiteral RANGE intLiteral
    | VAR SET OF LBRACE intLiteralList? RBRACE
    ;

arrayVarType
    : ARRAY LBRACK indexSet RBRACK OF basicVarType
    ;

indexSet
    : INT_LITERAL RANGE intLiteral
    ;

basicPredParamType
    : basicParType
    | basicVarType
    | intLiteral RANGE intLiteral
    | floatLiteral RANGE floatLiteral
    | LBRACE intLiteralList RBRACE
    | SET OF intLiteral RANGE intLiteral
    | SET OF LBRACE intLiteralList? RBRACE
    ;

predParamType
    : basicPredParamType
    | ARRAY LBRACK predIndexSet RBRACK OF basicPredParamType
    ;

predIndexSet
    : indexSet
    | INT
    ;

// Expressions
basicLiteralExpr
    : boolLiteral
    | intLiteral
    | floatLiteral
    | setLiteral
    ;

basicExpr
    : basicLiteralExpr
    | varParIdentifier
    ;

expr
    : basicExpr
    | arrayLiteral
    ;

varParIdentifier
    : IDENTIFIER
    ;

// Literals
boolLiteral
    : FALSE
    | TRUE
    ;

intLiteral
    : INT_LITERAL
    ;

floatLiteral
    : FLOAT_LITERAL
    ;

setLiteral
    : LBRACE intLiteralList? RBRACE
    | intLiteral RANGE intLiteral
    | LBRACE floatLiteralList? RBRACE
    | floatLiteral RANGE floatLiteral
    ;

arrayLiteral
    : LBRACK basicExprList? RBRACK
    ;

parArrayLiteral
    : LBRACK basicLiteralExprList? RBRACK
    ;

// Lists
intLiteralList
    : intLiteral (COMMA intLiteral)*
    ;

floatLiteralList
    : floatLiteral (COMMA floatLiteral)*
    ;

basicExprList
    : basicExpr (COMMA basicExpr)*
    ;

basicLiteralExprList
    : basicLiteralExpr (COMMA basicLiteralExpr)*
    ;

exprList
    : expr (COMMA expr)*
    ;

annExprList
    : annExpr (COMMA annExpr)*
    ;

// Parameter declarations
parDeclItem
    : ARRAY LBRACK indexSet RBRACK OF basicParType
      COLON varParIdentifier
      ASSIGN parArrayLiteral
      SEMI
    ;

// Variable declarations
varDeclItem
    : basicVarType COLON varParIdentifier annotations (ASSIGN basicExpr)? SEMI
    | arrayVarType COLON varParIdentifier annotations ASSIGN arrayLiteral SEMI
    ;

// Constraint items
constraintItem
    : CONSTRAINT identifier LPAREN exprList? RPAREN annotations SEMI
    ;

// Solve item
solveItem
    : SOLVE annotations SATISFY SEMI
    | SOLVE annotations MINIMIZE basicExpr SEMI
    | SOLVE annotations MAXIMIZE basicExpr SEMI
    ;

// Annotations
annotations
    : (DCOLON annotation)*
    ;

annotation
    : identifier
    | identifier LPAREN annExprList RPAREN
    ;

annExpr
    : basicAnnExpr
    | LBRACK basicAnnExprList? RBRACK
    ;

basicAnnExpr
    : basicLiteralExpr
    | varParIdentifier
    | stringLiteral
    | annotation
    ;

basicAnnExprList
    : basicAnnExpr (COMMA basicAnnExpr)*
    ;

// Identifiers

identifier
    : IDENTIFIER
    ;

// Strings
stringLiteral
    : STRING_LITERAL
    ;

// Lexer Rules
PREDICATE  : 'predicate';
ARRAY      : 'array';
OF         : 'of';
VAR        : 'var';
BOOL       : 'bool';
INT        : 'int';
FLOAT      : 'float';
SET        : 'set';
CONSTRAINT : 'constraint';
SOLVE      : 'solve';
SATISFY    : 'satisfy';
MINIMIZE   : 'minimize';
MAXIMIZE   : 'maximize';
TRUE       : 'true';
FALSE      : 'false';

LPAREN     : '(';
RPAREN     : ')';
LBRACK     : '[';
RBRACK     : ']';
LBRACE     : '{';
RBRACE     : '}';
COLON      : ':';
SEMI       : ';';
COMMA      : ',';
ASSIGN     : '=';
RANGE      : '..';
DCOLON     : '::';

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

STRING_LITERAL
    : '"' ( ~["\\\r\n] | '\\' . )* '"'
    ;

// Identifiers
IDENTIFIER
    : [A-Za-z] [A-Za-z0-9_]*
    ;

VAR_PAR_IDENTIFIER
    : [A-Za-z_] [A-Za-z0-9_]*
    ;

// Whitespace and comments
WS
    : [ \t\r\n]+ -> skip
    ;

LINE_COMMENT
    : '%' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;
