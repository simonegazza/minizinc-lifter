grammar MiniZinc;

model: (item)* EOF;
item
    : includeItem
    | varDeclItem
    | enumItem
    | typeInstSynItem
    | assignItem
    | constraintItem
    | solveItem
    | outputItem
    | predicateItem
    | testItem
    | functionItem
    | annotationItem
    ;
includeItem: 'include' STRING_LITERAL ';';
varDeclItem
    : tiExprAndId annotations (EQ expr)? ';'
    | 'any' ':' ident annotations (EQ expr)? ';'
    ;
enumItem: 'enum' ident annotations (EQ enumCasesList)? ';' ;
typeInstSynItem: 'type' ident annotations EQ tiExpr ';';
assignItem: ident EQ expr ';';
constraintItem: 'constraint' ('::' STRING_LITERAL)? expr ';';
solveItem
    : 'solve' annotations 'satisfy' ';'
    | 'solve' annotations 'minimize' expr ';'
    | 'solve' annotations 'maximize' expr ';'
    ;
outputItem: 'output' ('::' STRING_LITERAL)? expr ';';
annotationItem: 'annotation' ident params (EQ expr)? ';';
predicateItem: 'predicate' operationItemTail ';';
testItem: 'test' operationItemTail ';';
functionItem: 'function' tiExpr ':' operationItemTail ';';


enumCasesList: enumCases ('++' enumCases)*;
enumCases
    : '{' ident (',' ident)* '}'
    | '_' '(' expr ')'
    | ident '(' ident ')'
    | 'anon_enum' '(' expr ')'
    ;


operationItemTail: ident params annotations (EQ expr)?;

params: ('(' tiExprAndId (',' tiExprAndId)* ')')?;

tiExprAndId: tiExpr ':' ident;
tiExpr: baseTiExpr | arrayTiExpr;
baseTiExpr
    : ('var' | 'par')? 'opt'? 'set of'? baseTiExprTail ('++' baseTiExpr)?
    | 'any' DOLLAR_IDENT
    ;
baseTiExprTail
    : ident
    | baseType
    | DOLLAR_IDENT
    | 'ann'
    | expr
    ;
baseType: 'bool' | 'int' | 'float' | 'string';
arrayTiExpr
    : 'array' ('[' tiExpr (',' tiExpr)* ']')? 'of' baseTiExpr
    | 'list' 'of' baseTiExpr
    ;

arrayAccessTail: '[' expr (',' expr)* ']';
fieldAccessTail: '.' ident | '.' INT_LITERAL;

annotations: ('::' annotation)*;
annotation: exprAtomHead exprAtomTail;

exprAtomHead
    : '(' expr ')'
    | ident callSuffix?
    | '_'
    | 'true' | 'false'
    | INT_LITERAL
    | FLOAT_LITERAL
    | STRING_LITERAL
    | setLiteral
    | arrayLiteral
    | arrayLiteral2d
    | indexedArrayLiteral
    | indexedArrayLiteral2d
    | tupleLiteral
    | recordLiteral
    | arrayComp
    | indexedArrayComp
    | annLiteral
    | ifThenElseExpr
    | letExpr
    | callExpr
    | genCallExpr
    ;

annLiteral: ident ('(' expr (',' expr)* ')')?;

exprAtomTail: 
    | arrayAccessTail exprAtomTail
    | fieldAccessTail exprAtomTail
    ;

expr: iffExpr;
iffExpr: implExpr ('<->' implExpr)*;
implExpr: orExpr ('->' orExpr)*;
orExpr: xorExpr ('\\/' xorExpr)*;
xorExpr: andExpr ('xor' andExpr)*;
andExpr: compareExpr ('/\\' compareExpr)*;
compareExpr: setExpr (('<' | '>' | '<=' | '>=' | EQ | '!=' | 'in' | 'subset' | 'superset') setExpr)*;
setExpr: rangeExpr (('union' | 'diff' | 'symdiff' | 'intersect') rangeExpr)*;
rangeExpr: addExpr ('..' addExpr)*;
addExpr: multExpr (('+' | '-' | '++') multExpr)*;
multExpr: powExpr (('*' | '/' | 'div' | 'mod') powExpr)*;
powExpr: unaryExpr ('pow' unaryExpr)*;
unaryExpr
    : ('not' | '+' | '-') unaryExpr
    | primary
    ;
primary
    : literal postfix*
    | quantifierExpr postfix*
    | ident postfix*
    | '(' expr ')' postfix*
    | ifThenElseExpr postfix*
    | letExpr postfix*
    ;
postfix
    : callSuffix
    | arrayAccessTail
    | fieldAccessTail
    ;
quantifierExpr: ident '(' generatorList ')' '(' expr ')';
callSuffix: '(' (expr (',' expr)*)? ')';

literal
    : 'true' | 'false'
    | INT_LITERAL
    | FLOAT_LITERAL
    | STRING_LITERAL
    | setLiteral
    | arrayLiteral
    | arrayLiteral2d
    | tupleLiteral
    | recordLiteral
    ;
setLiteral
    : '{' (expr (',' expr)*)? '}'
    | '{' expr '|' compTail '}'
    ;
arrayLiteral
    : '[' (expr (',' expr)*)? ','? ']'
    | '[' expr '|' compTail ']'
    ;
arrayLiteral2d: '[|' (arrayRow ('|' arrayRow)*)? '|]';
arrayRow: expr (',' expr)*;

tupleLiteral: '(' expr ',' expr (',' expr)* ')';
recordLiteral: '(' ident ':' expr ',' ident ':' expr (',' ident ':' expr)* ')';

ifThenElseExpr
    : 'if' expr 'then' expr
      ('elseif' expr 'then' expr)*
      ('else' expr)?
      'endif'
    ;

letExpr: 'let' '{' letItem (';' letItem)* '}' 'in' expr;
letItem: varDeclItem | constraintItem;

compTail: generator ('where' expr)? (',' generator)* ;
generatorList: generator (',' generator)*;
generator: (ident | '_') (',' (ident | '_'))* 'in' expr ( 'where' expr )? ','?;

indexedArrayLiteral
    : '[' (indexTuple ':' expr (',' indexTuple ':' expr)*)? ']'
    | '[' (indexTuple ':' expr (',' expr)+ )? ']'
    ;
indexedArrayLiteral2d: '[|' (indexedArrayRow ('|' indexedArrayRow)*)? '|]';
indexedArrayRow: (indexTuple ':' expr) (',' (indexTuple ':' expr))*;
indexTuple: expr | '(' expr (',' expr)* ')';
arrayComp: '[' expr '|' compTail ']';
indexedArrayComp: '[' indexTuple ':' expr '|' compTail ']';

callExpr: ident '(' (expr (',' expr)*)? ')';

genCallExpr: ident '(' compTail ')' '(' expr ')';

ident: IDENT;

EQ : '=' | '==';
INT_LITERAL: [0-9]+;
FLOAT_LITERAL: [0-9]+ '.' [0-9]+ ([Ee] [+-]? [0-9]+)?;
DOLLAR_IDENT: '$' [A-Za-z$] [A-Za-z0-9_]*;
IDENT: '_'? [A-Za-z] [A-Za-z0-9_]*;
STRING_LITERAL
    : '"' ( ~["\\\r\n]
      | '\\' [ntr"\\]
      | '\\(' .*? ')'
      )* '"'
    ;

COMMENT: '%' ~[\r\n]* -> channel(HIDDEN);
WS: [ \t\r\n]+ -> channel(HIDDEN);
