/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.evaluators;

import java.util.ArrayList;
import project.semantic.SemanticStack;
import project.semantic.SymbolTable.SymbolTable;
import project.semantic.Writer;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_If;
import project.semantic.registers.SR_Op;
import project.semantic.registers.SR_While;
import project.semantic.registers.SemanticRegister;

/**
 *
 * @author Christian
 */
public class WhileEvaluator {
    private static WhileEvaluator _Instance = null;
    private WhileEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new WhileEvaluator();
    }
    public static synchronized WhileEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    public void evalStart(){
        SR_While sr_while = (SR_While)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("\n"+sr_while.getWhileLabel()+":");
    }
    public void evalTest(){
        convertDOs();
        SR_While sr_while = null;
        if(SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_While")){
            SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
            sr_while = (SR_While)SemanticStack.getInstance().getLast();            
            String do_name = sr_do.getValue().value.toString();
            if(do_name.equals("0") || do_name.equals("1")){
                Writer.getInstance().getCode().add("cmp "+sr_do.getValue().value.toString()+" ,0");
                Writer.getInstance().getCode().add("je "+sr_while.getExitLabel());
            }else{
                SymbolTable.getInstance().getErrors().add("Error en la expresión While en la linea "+sr_do.getValue().left);
            }
        }
        else{
            SR_DO sr_do1 = (SR_DO) SemanticStack.getInstance().pop();
            SR_Op sr_Op = (SR_Op) SemanticStack.getInstance().pop();
            SR_DO sr_do2 = (SR_DO) SemanticStack.getInstance().pop();
            sr_while = (SR_While)SemanticStack.getInstance().getLast();
            Writer.getInstance().getCode().add("cmp "+sr_do1.getValue().value.toString()+", "+sr_do2.getValue().value.toString());
            String cond = getCond(sr_Op);
            Writer.getInstance().getCode().add("je "+sr_while.getExitLabel());
        }
    }
    public void evalEnd(){
        SR_While sr_while = (SR_While)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("jump "+sr_while.getWhileLabel());
        Writer.getInstance().getCode().add("\n"+sr_while.getExitLabel()+":");
        SemanticStack.getInstance().pop();
    }
    private String getCond(SR_Op pSR){
        
        return "";
    }
    private void convertDOs(){
        int i = 1;
        while (!SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-i).getRegisterType().equals("SR_While")){
            SemanticRegister sr_do = SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-i);
            String do_name = sr_do.getValue().value.toString();
            if(do_name.equals("true"))
                sr_do.getValue().value = "0";
            else{
                sr_do.getValue().value = "1";
            }
            i++;
        }
    }
   
}
