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
 * @author YM
 */
public class FunctionEvaluator {
    private static FunctionEvaluator _Instance = null;
    private String function;
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
        function="\n"+symbol.getName()+":"+"\n";
        //Writer.getInstance().getFunctions().add(0,"\n"+symbol.getName()+":");
    }
    public void evalReturn(){
        if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
            SR_DO sr_do = (SR_DO)SemanticStack.getInstance().pop();
            String value =sr_do.getValue().value.toString();
            Writer.getInstance().getFunctions().add(function+"   MOVE #"+value+", .A \n   RET");
        }
        else{
            if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
                if(SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                    String value = String.valueOf(SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString()).getName());
                    Writer.getInstance().getFunctions().add(0,function+"   MOVE /"+value+", .A \n   RET");
                }
                else{
                    SymbolTable.getInstance().getErrors().add("La variable de retorno no ha sido declarada, verifique... :)");
                }
            }
            else{
                SymbolTable.getInstance().getErrors().add("El valor de retorno no es valido :)");
            }
        }
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
