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
import project.semantic.registers.SR_PUTS;
import project.semantic.registers.SR_PUTW;

/**
 *
 * @author YM
 */
public class RWEvaluator {
    private static int cont=0;



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
    public void evalPuts(){
        if(!(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS"))){
            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
                SR_DO str_to_print= (SR_DO) SemanticStack.getInstance().pop();
                SR_PUTS sr_puts= (SR_PUTS) SemanticStack.getInstance().pop();
                Writer.getInstance().setData("mens"+cont+":   DATA "+str_to_print.getValue().value+"\n");                
                VariableEvaluator.getInstance().getOperations().add("   WRSTR /mens"+cont+"\n");
                cont++;

            }else{
                SymbolTable.getInstance().getErrors().add("hAY UN ERROR EN EL PUTS");

            }
        }else{
            SR_PUTS sr_puts= (SR_PUTS) SemanticStack.getInstance().pop();
            SymbolTable.getInstance().getErrors().add("Dentro del puts solo deben venir literal_constant. Linea: "+sr_puts.getValue().left);

        }
        
    
    }
    public void evalPutw(){
        if(!(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW"))){
            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
                SR_DO str_to_print= (SR_DO) SemanticStack.getInstance().pop();
                SR_PUTW sr_puts= (SR_PUTW) SemanticStack.getInstance().pop();
                               
                VariableEvaluator.getInstance().getOperations().add("   WRINT #"+str_to_print.getValue().value);
                
            }else{
                if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                    System.out.println("voy a escribir wrint");
                    SR_ID int_to_print= (SR_ID) SemanticStack.getInstance().pop();

                    if(SymbolTable.getInstance().existSymbol(int_to_print.getValue().value.toString(), "Var")){
                        SR_PUTW sr_putw= (SR_PUTW) SemanticStack.getInstance().pop();
                        VariableEvaluator.getInstance().getOperations().add("   WRINT /"+int_to_print.getValue().value);

                    }else{
                         SymbolTable.getInstance().getErrors().add("La variable: "+int_to_print.getValue().value+"no ha sido declarada.");
    
                    }
                    
                
                }else{
                    SymbolTable.getInstance().getErrors().add("HAY UN ERROR EN EL PUTW");

                }
            }
        }else{
            SR_PUTW sr_putw= (SR_PUTW) SemanticStack.getInstance().pop();
            SymbolTable.getInstance().getErrors().add("Dentro del putw solo deben venir literal_num. Linea: "+sr_putw.getValue().left);

        }
        
    
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
        SR_PUTS sr_write = (SR_PUTS)SemanticStack.getInstance().pop();
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

    public static void setCont(int cont) {
        RWEvaluator.cont = cont;
    }
    

    
    
}
