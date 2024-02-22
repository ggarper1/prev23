lexer grammar PrevLexer;

@header {
	package prev23.phase.lexan;
	import prev23.common.report.*;
	import prev23.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {
		return (Token) super.nextToken();
	}
}

fragment Letter     : 'A'..'Z' | 'a'..'z';
fragment Digit      : '0'..'9';
fragment Symbol     : ' '|'!'|'#'|'$'|'%'|'&'|'('|')'|'*'|'+'|','|'-'|
                        '.'|'/'|':'|';'|'<'|'='|'>'|'?'|'@'|'['|'\\'|']'|
                        '^'|'_'|'`'|'{'|'|'|'}'|'~';
fragment SQuote     : '\'';
fragment DQuote     : '"';
fragment Tab        : '\t';

/** White spaces and Line feeds */

WS                  : ' ' -> skip;
LF                  : '\n' -> skip;
TAB                 : '\t' {setCharPositionInLine(((getCharPositionInLine()/8)+1)*8);}-> skip;

/** Keywords */

BOOL                : 'bool';
CHAR                : 'char';
DEL                 : 'del';
DO                  : 'do';
ELSE                : 'else';
FUN                 : 'fun';
IF                  : 'if';
IN                  : 'in';
INT                 : 'int';
LET                 : 'let';
NEW                 : 'new';
THEN                : 'then';
TYP                 : 'typ';
VAR                 : 'var';
VOID                : 'void';
WHILE               : 'while';

/** Comments */

COMMENT             : '#' (Letter|Symbol|Digit|Tab|SQuote|DQuote)*? '\n' -> skip;

/** Symbols */

OPARENTHESES        : '(';
OBRACES             : '{';
OBRACKETS           : '[';
CPARENTHESES        : ')';
CBRACES             : '}';
CBRACKETS           : ']';
DOT                 : '.';
COMMA               : ',';
COLON               : ':';
SEMICOLON           : ';';
AND                 : '&';
OR                  : '|';
NOT                 : '!';
EQUAL               : '==';
DIFFER              : '!=';
GREATER             : '>';
LESSER              : '<';
LESSER_OR_EQUAL     : '<=';
GREATER_OR_EQUAL    : '>=';
MULT                : '*';
DIV                 : '/';
REMAINDER           : '%';
SUM                 : '+';
SUB                 : '-';
CIRCUMFLEX          : '^';
ASIGN               : '=';

/** Constants */

NONE                : 'none';
TRUE                : 'true';
FALSE               : 'false';
INTEGER             : (Digit)+;
CHARACTER           : SQuote (Letter|Symbol|Digit|Tab|'\\'SQuote|DQuote) SQuote;
STRING              : DQuote (Letter|Symbol|Digit|Tab|SQuote|'\\"')* DQuote;
NIL                 : 'nil';

/** IDs */

ID                  : (Letter|'_') (Letter|Digit|'_')*;

/** Errors */

QUOTEERROR            :  ('"' | '\'') {if(1>0){throw new Report.Error(new Location(
                        	getLine(),
                        	getCharPositionInLine(),
                        	getLine(),
                        	getCharPositionInLine())
                        	,"Lonely quote!");}};


NONASCII              : .+? {if(1>0){
								throw new Report.Error(
									new Location(getLine(), getCharPositionInLine(),
                        				getLine(), getCharPositionInLine()  + getText().length() - 1),
                        			"Non ASCII character!");
								}
							};

