/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.evaluators;

import java.util.ArrayList;
import project.semantic.SemanticStack;
import project.semantic.SymbolTable.Symbol;
import project.semantic.SymbolTable.SymbolTable;
import project.semantic.SymbolTable.Symbol_Func;
import project.semantic.Writer;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_ID;
import project.semantic.registers.SR_Type;

/**
 *
 * @author Christian
 */
public class FunctionEvaluator {
    private static FunctionEvaluator _Instance = null;
    private FunctionEvaluator(){
    }
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new FunctionEvaluator();
    }
    public static synchronized FunctionEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    public void evalDeclaration(){
        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
        SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
        Symbol symbol = new Symbol_Func(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),sr_id.getValue().left);
        SymbolTable.getInstance().addSymbol(symbol);
        Writer.getInstance().getCode().add("\n"+symbol.getName()+":");
    }
    public void evalReturn(){
        SR_DO sr_do = (SR_DO)SemanticStack.getInstance().pop();
        String value =sr_do.getValue().toString();
        Writer.getInstance().getCode().add("mov eax, "+value);
        Writer.getInstance().getCode().add("ret");
    }
    public void evalCall(){
        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
        String idFunc = sr_id.getValue().value.toString();
        if (SymbolTable.getInstance().existSymbol(idFunc, "Func")){
            Writer.getInstance().getCode().add("call "+sr_id.getValue().value.toString());
        }else{
            SymbolTable.getInstance().getErrors().add("La funcion :"+idFunc+" no existe");
        }
       
    }
}
