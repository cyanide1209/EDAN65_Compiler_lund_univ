package lang.ast; // The generated scanner will belong to the package lang.ast

import lang.ast.LangParser.Terminals; // The terminals are implicitly defined in the parser
import lang.ast.LangParser.SyntaxError;

%%

// define the signature for the generated scanner
%public
%final
%class LangScanner
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%type beaver.Symbol 
%function nextToken 

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

// macros
WhiteSpace = [ ] | \t | \f | \n | \r
ID = [a-zA-Z]+
NUM = [0-9]+

%%

// discard whitespace information
{WhiteSpace}  { }

// token definitions
"("           {return sym(Terminals.LEFTPARA);}
")"           {return sym(Terminals.RIGHTPARA);}
"{"           {return sym(Terminals.LEFTCURLYBRACE);}
"}"           {return sym(Terminals.RIGHTCURLYBRACE);}
"int"         {return sym(Terminals.INT);}
";"           {return sym(Terminals.SEMICOLON);}
"="           {return sym(Terminals.ASSIGN);}
"+"           {return sym(Terminals.ADD);}
"-"           {return sym(Terminals.MINUS);}
"*"           {return sym(Terminals.MUL);}
"/"           {return sym(Terminals.DIV);}
"%"           {return sym(Terminals.MOD);}

">"           {return sym(Terminals.GREATERTHAN);}
">="          {return sym(Terminals.GREATERTHANEQUAL);}
"<"           {return sym(Terminals.LESSTHAN);}
"<="          {return sym(Terminals.LESSTHANEQUAL);}
"=="          {return sym(Terminals.EQUALS)}

{NUM}         { return sym(Terminals.NUM);}

{ID}          { return sym(Terminals.ID); }

<<EOF>>       { return sym(Terminals.EOF); }

/* error fallback */
[^]           { throw new SyntaxError("Illegal character <"+yytext()+">"); }
