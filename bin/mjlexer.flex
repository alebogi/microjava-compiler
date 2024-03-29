
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

import rs.ac.bg.etf.pp1.CompilerError;

%%

%{
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type){
		return new Symbol(type, yyline + 1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline + 1, yycolumn, value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}


%%

"\t"     { }
"\r\n"  { } 
" "      { }
"\b"     { }
"\f"     { }


"program"	{ return new_symbol(sym.PROG, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"class"		{ return new_symbol(sym.CLASS, yytext()); }
"enum"		{ return new_symbol(sym.ENUM, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"switch"	{ return new_symbol(sym.SWITCH, yytext()); }
"do"		{ return new_symbol(sym.DO, yytext()); }
"while"		{ return new_symbol(sym.WHILE, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"print"		{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"return"	{ return new_symbol(sym.RETURN, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"case"		{ return new_symbol(sym.CASE, yytext()); }

"+"			{ return new_symbol(sym.PLUS, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"*"			{ return new_symbol(sym.MULTIPLY, yytext()); }
"/"			{ return new_symbol(sym.DIVIDE, yytext()); }
"%"			{ return new_symbol(sym.MOD, yytext()); }
"=="		{ return new_symbol(sym.EQUALS_TO, yytext()); }
"!="		{ return new_symbol(sym.NOT_EQUAL, yytext()); }
">"			{ return new_symbol(sym.GREATER, yytext()); }
">="		{ return new_symbol(sym.GREATER_OR_EQUAL, yytext()); }
"<"			{ return new_symbol(sym.LESS, yytext()); }
"<="		{ return new_symbol(sym.LESS_OR_EQUAL, yytext()); }
"&&"		{ return new_symbol(sym.LOGICAL_AND, yytext()); }
"||"		{ return new_symbol(sym.LOGICAL_OR, yytext()); }
"="			{ return new_symbol(sym.EQUAL, yytext()); }
"++"		{ return new_symbol(sym.INCREMENT, yytext()); }
"--"		{ return new_symbol(sym.DECREMENT, yytext()); }
";"			{ return new_symbol(sym.SEMI, yytext()); }
","			{ return new_symbol(sym.COMMA, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }
"("			{ return new_symbol(sym.L_PAREN, yytext()); }
")"			{ return new_symbol(sym.R_PAREN, yytext()); }
"["			{ return new_symbol(sym.L_SQUARE_BRACKET, yytext()); }
"]"			{ return new_symbol(sym.R_SQUARE_BRACKET, yytext()); }
"{"			{ return new_symbol(sym.L_BRACE, yytext()); }
"}"			{ return new_symbol(sym.R_BRACE, yytext()); }
"?"			{ return new_symbol(sym.QUESTIONMARK, yytext()); }
":"			{ return new_symbol(sym.COLON, yytext()); }


"//" 				{ yybegin(COMMENT); } 
<COMMENT> . 		{ yybegin(COMMENT); }
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }


("true"|"false")	{ return new_symbol(sym.BOOL_CONST, new Boolean(yytext()) ); }

[0-9]+				{ return new_symbol(sym.NUM_CONST, new Integer( yytext() )); }
"'"[ -~]"'"				{ return new_symbol(sym.CHAR_CONST,  new Character(yytext().charAt(1)) ); }

([a-z]|[A-Z])[a-zA-Z0-9_]*	{ return new_symbol(sym.IDENT, yytext()); }
			
			



.	{ 
System.err.println("Leksicka greska (" + yytext() + ") u liniji " + (yyline + 1) + " kolona " + (yycolumn + 1));
MJParserTest.dodajGresku(new CompilerError(yyline + 1,
    			"Ne postoji simbol: (" + yytext() + ") ",
        		CompilerError.CompilerErrorType.LEXICAL_ERROR));
;
 }













