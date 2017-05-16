/*Seccion de codigo de usuario.
En esta sección se declaran los que se utilizarán en el programa*/
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
/*En esta sección se declaran todas las expresiones regulares utilizadas en el compilador*/
//Declaraciones









EXP_ALPHA = [A-Za-z]                                                            //Expresión utilizada para formar palabras; principalmente para los identificadores
DIGIT = [0-9]                                                                   //Expresión utilizada para lo números de 1-9; con elos se pueden formar números más grandes
SPACE = " "                                                                     //Expresión que determina como será el espacio en el compilador
JUMP = \n|\r|\r\n                                                               //Expresión que señala los saltos de línea, como el enter.
WHITESPACE = {JUMP} | [ \t\f] | {SPACE}                                         //Expresión que determina los espacios en blanco, permitiendo que hayan espacios seguidos.
EXP_ALPHANUMERIC = {EXP_ALPHA}|{DIGIT}                                          //Expresión que permite tener tanto números como letras en una palabra.
SIGN = ("+"|"-")                                                                //Signos permitidos en el compilador
INTEGER = ([1-9]{DIGIT}*)(("u"|"l"|"ul")?) | "0"                                //Expresión que permite crear enters; para ello se utiliza la expresión DIGIT.

IDENTIFIER = {EXP_ALPHA}(_*)({EXP_ALPHANUMERIC}|(_))*                           //Expresión que permite declarar variable, para ello se utiliza la expresión EXP_ALPHA, la cúal permite tener letras y números
CHARACTER = \'{EXP_ALPHANUMERIC}?\'                                             //Expresión que permite crear un caracter.

//Operadores y delimitadores
DELIMITERS = "(" | ")" | "{" | "}"                                              //Expresión que permite definir los delimitadores, es decir los que me indican el inicio y fin de una condición o el programa.
ARITHMETIC = "+" | "-" | "*" | "/"                                              //Expresión que permite definr los simbolos aritmeticos que se podrán utilizar en el compilador.
RELATIONAL = "<" | ">" | "==" | "¡="                                            //Expresión que permite definir los simbolos relacionales que se podrán utilizar en el compilador, es decir los signos utilizados para realzar comparaciones.
LOGIC = "||" | "&&"                                                             //Expresión que perite definir los operadores lógico utilizados en el compilador.
ASSIGN= "="                                                                     //Signo de asignación para identificadores.

//Constantes
CONSTANT=  \"[^\n\r\"]*\" | \'[^\n\r\']*\'                                      

//Identificadores y palabras reservadas
KEYWORD = "main" | "else" | "puts" | "int" | "break"                            //Palabras claves utilizadas en elcompilador.
TYPE_INT = "int"                                                                //Tipo de dato aceptado en el compilador.
TYPE_FLOW_CONTROL = "while" | "if" | "else" | "then"                            //Otras palabras claves, sin embargo estas son para las estructuras de control.
//FLOAT = ({DIGITO}+)"."({DIGITO}*)("e"({SIGNO}?)({DIGITO}+))?(L|l|f|F)?

//PALABRAS RESERVADAS
TYPE_NUM = "int"
//| "long"|"short"
//TYPE_CHAR = "char"
//TYPE_STR = "string"

// LITERALS
INVALID_CHARACTERS = "á"|"é"|"í"|"ó"|"ú"|"Á"|"É"|"Í"|"Ó"|"Ú"|"ñ"|"Ñ"|"¿"        //Los caracteres que no son permitidos en el compilador.
LITERAL_NUM = {INTEGER}                                                         //Se refiere a los números enteros.
// {FLOAT}| ESTO IRIA ARRIBA SI SE IMPLEMENTAN MAS TIPOS
//LITERAL_BOOL = "true"|"false"
LITERAL_CONSTANT = {CONSTANT}
//LITERAL_CHAR = {CHARACTER}



//LITERAL = {FLOAT}|{INTEGER}|{OCTAL}|{HEXADECIMAL}|"true"|"false"|{STRING}|{CHARACTER}
//STRING = \"[^\n\r\"]*\" | \'[^\n\r\']*\'


// Expresiones de Error 
ERROR = {DIGIT}({EXP_ALPHA}+) | {CONSTANT_ERROR}| {INVALID_CHAR_ERROR}          //Detecta los errores.
//COMMENT_ERROR = "/*"([^"*/"])+
CONSTANT_ERROR = \"[^\n\r\"]*{JUMP} | \'[^\n\r\']*{JUMP}
//FLOAT_ERROR = ({DIGIT}+)"."[^0-9]*

INVALID_CHAR_ERROR = {INVALID_CHARACTERS}


//fin declaraciones

/* Seccion de reglas lexicas */
//En esta parte se describe las reglas léxicas para cada simbolo, estas vann en orden de priorización.
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

"<" {
    contador++;
    Symbol t = new Symbol(sym.LOWER, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
">" {
    contador++;
    Symbol t = new Symbol(sym.HIGHER, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"==" {
    contador++;
    Symbol t = new Symbol(sym.EQUALS, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"¡=" {
    contador++;
    Symbol t = new Symbol(sym.DIFFERENT, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"&&" {
    contador++;
    Symbol t = new Symbol(sym.AND, yyline,yycolumn, yytext());
    tokens.add(t);
    return t;}
"||" {
    contador++;
    Symbol t = new Symbol(sym.OR, yyline,yycolumn, yytext());
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

