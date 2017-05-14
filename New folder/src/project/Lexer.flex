/*Seccion de codigo de usuario*/
package project;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import project.sym;

/* Seccion de opciones y declaraciones de JFlex */
%% //inicio de opciones
%public
%class AnalizadorLexico
%unicode
%{    
    private int contador;
    private ArrayList<Symbol> tokens;
    private ArrayList<Symbol> errorTokens;

    private void writeOutputFile() throws IOException{
            
    }
%}
%init{
    contador = 0;
    tokens = new ArrayList<Symbol>();
        errorTokens = new ArrayList<Symbol>();
%init}
%eof{
    try{
        this.writeOutputFile();
    }catch(IOException ioe){
        ioe.printStackTrace();
    }
%eof}
%line
%column
%cup
%eofval{
    return new Symbol(sym.EOF, new String("Fin de Archivo"));
%eofval}
//Fin de opciones

//Expresiones regulares
//Declaraciones









EXP_ALPHA = [A-Za-z]
DIGIT = [0-9]
SPACE = " "
JUMP = \n|\r|\r\n
WHITESPACE = {JUMP} | [ \t\f] | {SPACE}
EXP_ALPHANUMERIC = {EXP_ALPHA}|{DIGIT}
SIGN = ("+"|"-")
INTEGER = ([1-9]{DIGIT}*)(("u"|"l"|"ul")?) | "0"

IDENTIFIER = {EXP_ALPHA}(_*)({EXP_ALPHANUMERIC}|(_))*
CHARACTER = \'{EXP_ALPHANUMERIC}?\'

//Operadores y delimitadores
DELIMITERS = "(" | ")" | "{" | "}"
ARITHMETIC = "+" | "-" | "*" | "/"
RELATIONAL = "<" | ">" | "==" | "¡="
LOGIC = "||" | "&&"
ASSIGN= "="

//Constantes
CONSTANT=  \"[^\n\r\"]*\" | \'[^\n\r\']*\'

//Identificadores y palabras reservadas
KEYWORD = "main" | "else" | "puts" | "int" | "break"
TYPE_INT = "int"
TYPE_FLOW_CONTROL = "while" | "if" | "else" | "then"
//FLOAT = ({DIGITO}+)"."({DIGITO}*)("e"({SIGNO}?)({DIGITO}+))?(L|l|f|F)?

//PALABRAS RESERVADAS
TYPE_NUM = "int"
//| "long"|"short"
//TYPE_CHAR = "char"
//TYPE_STR = "string"

// LITERALS
INVALID_CHARACTERS = "á"|"é"|"í"|"ó"|"ú"|"Á"|"É"|"Í"|"Ó"|"Ú"|"ñ"|"Ñ"|"¿"
LITERAL_NUM = {INTEGER} 
// {FLOAT}| ESTO IRIA ARRIBA SI SE IMPLEMENTAN MAS TIPOS
//LITERAL_BOOL = "true"|"false"
LITERAL_CONSTANT = {CONSTANT}
//LITERAL_CHAR = {CHARACTER}



//LITERAL = {FLOAT}|{INTEGER}|{OCTAL}|{HEXADECIMAL}|"true"|"false"|{STRING}|{CHARACTER}
//STRING = \"[^\n\r\"]*\" | \'[^\n\r\']*\'


// Expresiones de Error 
ERROR = {DIGIT}({EXP_ALPHA}+) | {CONSTANT_ERROR}| {INVALID_CHAR_ERROR}
//COMMENT_ERROR = "/*"([^"*/"])+
CONSTANT_ERROR = \"[^\n\r\"]*{JUMP} | \'[^\n\r\']*{JUMP}
//FLOAT_ERROR = ({DIGIT}+)"."[^0-9]*

INVALID_CHAR_ERROR = {INVALID_CHARACTERS}


//fin declaraciones

/* Seccion de reglas lexicas */
%% 
//Reglas
<YYINITIAL> {

{ERROR} {
    contador++;
    Symbol t = new Symbol(sym.error, yyline,yycolumn,  yytext());
    tokens.add(t);
    return t;
}
{WHITESPACE} {
    //ignorar
}

{LITERAL_NUM} {
    contador++;
    Symbol t = new Symbol(sym.LITERAL_NUM,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}


{LITERAL_CONSTANT} {
    contador++;
    Symbol t = new Symbol(sym.LITERAL_CONSTANT,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}
{TYPE_NUM} {
    contador ++;
    Symbol t = new Symbol(sym.TYPE_NUM,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}

"if" {
    contador ++;
    Symbol t = new Symbol(sym.IF,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}
"else" {
    contador ++;
    Symbol t = new Symbol(sym.ELSE,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}
"while" {
    contador ++;
    Symbol t = new Symbol(sym.WHILE,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}
"break" {
    contador ++;
    Symbol t = new Symbol(sym.BREAK,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}

"main" {
    contador ++;
    Symbol t = new Symbol(sym.MAIN,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}
"putw" {
    contador ++;
    Symbol t = new Symbol(sym.PUTW,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}

"puts" {
    contador ++;
    Symbol t = new Symbol(sym.PUTS,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}

{KEYWORD} {
    contador++;
    Symbol t = new Symbol(sym.KEYWORD,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}
{IDENTIFIER}   {
    contador++;
    Symbol t = new Symbol(sym.IDENTIFIER,yyline,yycolumn, yytext());
    tokens.add(t);
    return t;
}
"+" {
    contador++;
    Symbol t = new Symbol(sym.PLUS, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"-" {
    contador++;
    Symbol t = new Symbol(sym.MINUS, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"/" {
    contador++;
    Symbol t = new Symbol(sym.DIV, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"*" {
    contador++;
    Symbol t = new Symbol(sym.MULT, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"," {
    contador++;
    Symbol t = new Symbol(sym.COMMA, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
{ARITHMETIC} {
    contador ++;
    Symbol t = new Symbol(sym.ARITHMETIC,yyline,yycolumn, yytext());
    tokens.add(t);
    return t; 
}
{ASSIGN} {
    contador++;
    Symbol t = new Symbol(sym.ASSIGN, yyline, yycolumn,yytext());
    tokens.add(t);
    return t;
}

";" {
    contador++;
    Symbol t = new Symbol(sym.SEMICOLON, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}

"(" {
    contador++;
    Symbol t = new Symbol(sym.LPAR, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
")" {
    contador++;
    Symbol t = new Symbol(sym.RPAR, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"{" {
    contador++;
    Symbol t = new Symbol(sym.BEGIN, yyline, yycolumn,yytext());
    tokens.add(t);
    return t;}
"}" {
    contador++;
    Symbol t = new Symbol(sym.END, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
}
[^] {
    contador++;
    Symbol t = new Symbol(sym.error,yycolumn, yyline, yytext());
    tokens.add(t);
    return t;
}

