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
import project.semantic.registers.SR_ID;
import project.semantic.registers.SR_Read;
import project.semantic.registers.SR_Write;

/**
 *
 * @author Christian
 */
public class RWEvaluator {
    private static RWEvaluator _Instance = null;
    private RWEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new RWEvaluator();
    }
    public static synchronized RWEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    private ArrayList<SR_DO> getDOs(){
        ArrayList<SR_DO> DOs = new ArrayList<SR_DO>();
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_Write")){
            DOs.add((SR_DO)SemanticStack.getInstance().pop());
        }
        return DOs;
    }
    public void evalEndWrite(){
        ArrayList<SR_DO> DOs = getDOs();
        for (int iDO = 0; iDO != DOs.size(); iDO++){
            SR_DO sr_do = DOs.get(iDO);
            Writer.getInstance().getCode().add("mov eax, 4");
            Writer.getInstance().getCode().add("mov ebx, 1");
            Writer.getInstance().getCode().add("mov eax, "+sr_do.getValue().value.toString());
            Writer.getInstance().getCode().add("mov eax, equ "+ sr_do.getValue().value.toString());
            Writer.getInstance().getCode().add("int 80h\n");
        }
        SR_Write sr_write = (SR_Write)SemanticStack.getInstance().pop();
    }
    public void evalEndRead(){
        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
        String id = sr_id.getValue().value.toString();
        if (SymbolTable.getInstance().existSymbol(id,"Var")){
            Writer.getInstance().getCode().add("mov eax, 3");
            Writer.getInstance().getCode().add("mov ebx, 0");
            Writer.getInstance().getCode().add("mov eax, "+id);
            Writer.getInstance().getCode().add("mov eax, 1");
            Writer.getInstance().getCode().add("int 80h\n");
            SR_Read sr_read = (SR_Read)SemanticStack.getInstance().pop();
        }else{
            SymbolTable.getInstance().getErrors().add("No existe la variable: "+id+", linea "+sr_id.getValue().left);
        }
    }
}
