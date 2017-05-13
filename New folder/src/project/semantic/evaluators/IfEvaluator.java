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

/**
 *
 * @author Christian
 */
public class IfEvaluator {
    private static IfEvaluator _Instance = null;
    private IfEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new IfEvaluator();
    }
    public static synchronized IfEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    private ArrayList<SR_DO> getDOs(){
        ArrayList<SR_DO> DOs = new ArrayList<SR_DO>();
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_If")){
            SR_DO sr_do = (SR_DO)SemanticStack.getInstance().pop();
            String do_name = sr_do.getValue().value.toString();
            if(do_name.equals("true"))
                do_name = "0";
            else{
                do_name = "1";
            }
            sr_do.getValue().value = do_name;
            DOs.add(sr_do);
        }
        return DOs;
    }
    public void evalTestIf(){
        ArrayList<SR_DO> DOs = getDOs();
        if(DOs.size() == 1){
            String do_name = DOs.get(0).getValue().value.toString();
            if(do_name.equals("0") || do_name.equals("1")){
                Writer.getInstance().getCode().add("cmp "+DOs.get(0).getValue().value.toString()+" ,0");
            }else{
                SymbolTable.getInstance().getErrors().add("Error en la expresión If en la linea "+DOs.get(0).getValue().left);
            }
        }
        SR_If sr_if = (SR_If)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("je "+sr_if.getElseLabel());
    }
    
    public void evalEndIf(){
        SR_If sr_if = (SR_If)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().remove(Writer.getInstance().getCode().size()-1);
        Writer.getInstance().getCode().add("je "+sr_if.getExitLabel());
        SemanticStack.getInstance().pop();
    }
    public void evalEndIfElse(){
        SR_If sr_if = (SR_If)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("jump "+sr_if.getExitLabel());
    }
    
    public void evalStartElse(){
        SR_If sr_if = (SR_If)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("\n"+sr_if.getElseLabel()+":");
    }
    public void evalEndElse(){
        SR_If sr_if = (SR_If)SemanticStack.getInstance().getLast();
        Writer.getInstance().getCode().add("\n"+sr_if.getExitLabel()+":");
        SemanticStack.getInstance().pop();
    }
}
