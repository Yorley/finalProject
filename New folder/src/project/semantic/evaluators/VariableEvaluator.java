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
import project.semantic.SymbolTable.Symbol_Var;
import project.semantic.Writer;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_ID;
import project.semantic.registers.SR_Op;
import project.semantic.registers.SR_Type;
import project.semantic.registers.SemanticRegister;

/**
 *
 * @author YM
 */
public class VariableEvaluator {
    private static VariableEvaluator _Instance = null;
    private final int INT = 4;
    private final int CHAR = 2;
    private final int LONG = 32;
    private final int SHORT = 16;
    private final int BOOL = 1;
    private ArrayList<Symbol> _UninitializedVar = new ArrayList<Symbol>();
    private ArrayList<Symbol> _InitializedVar = new ArrayList<Symbol>();
    private ArrayList<String> _Operations = new ArrayList<String>();
    private VariableEvaluator(){
    }
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new VariableEvaluator();
    }
    public static synchronized VariableEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    public void evalUn(){
        Boolean isSwitch= false;
        for(SemanticRegister i : SemanticStack.getInstance().getStack()){
            if (i.getRegisterType().equals("SR_SWITCH")){
                isSwitch=true;
                break;
            }
        }
        
       if(isSwitch){
            
        
        }else{
        
            SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
            SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
            Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),"0", sr_id.getValue().left);
            SymbolTable.getInstance().addSymbol(symbol);
            _UninitializedVar.add(symbol);
        }
    }
    
     public void evalIn(){
        Boolean isSwitch= false;
        for(SemanticRegister i : SemanticStack.getInstance().getStack()){
            if (i.getRegisterType().equals("SR_SWITCH")){
                isSwitch=true;
                break;
            }
        }
        
       if(isSwitch){
            
        
        }else{
            if (SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_ID")){              
                SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
                SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
                Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),sr_do.getValue().value.toString(),sr_id.getValue().left);
                SymbolTable.getInstance().addSymbol(symbol);
                //_Operations.add("   MOVE #"+sr_do.getValue().value+", /"+sr_id.getValue().value);

                //Writer.getInstance().getCode().add("   MOVE #"+sr_do.getValue().value+", /"+sr_id.getValue().value);
                _InitializedVar.add(symbol);
            }else{
                String result= doOperations(String.valueOf(SemanticStack.getInstance().getLast().getValue().left),_Operations);
                SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
                SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
                _Operations.add("   MOVE #"+ result+", /"+sr_id.getValue().value.toString());
                Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),"0", sr_id.getValue().left);
                SymbolTable.getInstance().addSymbol(symbol);
                _UninitializedVar.add(symbol);
            }
        }
     }
    public void Exist(){
        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
        if(!SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
           SymbolTable.getInstance().getErrors().add("La variable "+ sr_id.getValue().value +" no existe");
        }
        else {
            Symbol_Var symb=((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString()));
            SwitchEvaluator.setOption_to_evaluate(Integer.valueOf(symb.getValue()));
        //aca va el codigo de ensamblador
        }
        
        
    
    }
    public void evalAssign(){
        Boolean isSwitch= false;
        for(SemanticRegister i : SemanticStack.getInstance().getStack()){
            if (i.getRegisterType().equals("SR_SWITCH")){
                isSwitch=true;
                break;
            }
        }
       if(isSwitch){
        
        }else{
        if(SemanticStack.getInstance().getStack().size()==1){
            SR_ID sr = (SR_ID) SemanticStack.getInstance().pop();
            SymbolTable.getInstance().getErrors().add("La variable "+sr.getValue().value.toString()+" no cumple con el tipo de dato en el que fue declarado. Linea: "+ sr.getValue().left);
        }else{
            try{
                    if (SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_ID")){
                        if ((SemanticStack.getInstance().getLast().getRegisterType()).equals("SR_ID")){
                            SR_ID sr_id = (SR_ID) SemanticStack.getInstance().pop();
                            SR_ID sr_id1 = (SR_ID)SemanticStack.getInstance().pop();
                            if (SymbolTable.getInstance().existSymbol(sr_id1.getValue().value.toString(), "Var")){
                                if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                                    System.out.println("Valor que tiene el sr_id antes de evaluar si es null: "+((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id1.getValue().value.toString())).getValue());
                                    
                                }else{
                                      SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+" no ha sido declarada. Linea: "+ sr_id.getValue().left);
                                }
                            }else{
                                SymbolTable.getInstance().getErrors().add("La variable "+sr_id1.getValue().value.toString()+" no ha sido declarada. Linea: "+ sr_id.getValue().left);
                            }
                        }else{
                            SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                            SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
                            if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                                ((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).setValue(sr_do.getValue().value.toString());
                                _Operations.add("   MOVE #"+sr_do.getValue().value.toString()+", /"+sr_id.getValue().value.toString()); 
//Writer.getInstance().getCode().add("MOVE "+sr_do.getValue().value.toString()+", /"+sr_id.getValue().value.toString());
                            }else{
                                SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+" no ha sido inicializada. Linea: "+ sr_id.getValue().left);
                            }
                        }
                    }else{
                        String result= doOperations(String.valueOf(SemanticStack.getInstance().getLast().getValue().left),Writer.getInstance().getCode());

                        SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                        SR_ID sr_id = (SR_ID) SemanticStack.getInstance().pop();
                        if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                            ((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).setValue(sr_do.getValue().value.toString());
                            //Writer.getInstance().getCode().add("MOVE "+ result+" , /"+sr_id.getValue().value.toString());
                            _Operations.add("   MOVE "+ result+" , /"+sr_id.getValue().value.toString()); 
                        }else{
                            SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+" no ha sido inicializada. Linea: "+ sr_id.getValue().left);
                        }
                }
            }
            catch ( Error e ){
            }
        }
    }        
              
        
    }
    public int getLenght(String pType){
        int len;
        switch (pType){
                case "int":
                    len = INT;
                    break;
                case "char":
                    len = CHAR;
                    break;
                case "long":
                    len = LONG;
                    break;
                case "bool":
                    len = BOOL;
                    break;
                case "short":
                    len = SHORT;
                    break;
                default:
                    len = 2;
                    break;
            }
        return len;
    }
    public ArrayList<Symbol> getUninitializedVar() {
        return _UninitializedVar;
    }

    public ArrayList<String> getOperations() {
        return _Operations;
    }
    
    public ArrayList<Symbol> getInitializedVar() {
        return _InitializedVar;
    }    
    private String doOperations(String pLine,ArrayList<String> pList){
        String result = "";
        int res = 0;
        int cont=0;
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
            try {
                SR_DO sr_do2 = (SR_DO)SemanticStack.getInstance().pop();
                SR_Op sr_op = (SR_Op)SemanticStack.getInstance().pop();
                SR_DO sr_do1 = (SR_DO)SemanticStack.getInstance().pop();
                cont++;
                switch(sr_op.getValue().value.toString()){
                    case "+":
                        _Operations.add("   MOVE #"+sr_do1.getValue().value.toString()+",.R1");
                        _Operations.add("   ADD .R1,#"+sr_do2.getValue().value.toString());
                        result = ".A";
                        res=res+(Integer.parseInt(sr_do1.getValue().value.toString()) + Integer.parseInt(sr_do2.getValue().value.toString()) );
                        break;
                    case "-":
                        _Operations.add("   MOVE #"+sr_do1.getValue().value.toString()+",.R1");
                        _Operations.add("   SUB .R1,#"+sr_do2.getValue().value.toString());
                        result = ".A";
                        break;
                    case "*":
                        _Operations.add("   MOVE #"+sr_do1.getValue().value.toString()+",.R1");
                        _Operations.add("   MUL .R1,#"+sr_do2.getValue().value.toString());
                        result = ".A";
                        break;
                    case "/":
                        _Operations.add("   MOVE #"+sr_do1.getValue().value.toString()+",.R1");
                        _Operations.add("   DIV .R1,#"+sr_do2.getValue().value.toString());
                        result = ".A";
                        break;
                }
                sr_do1.getValue().value = String.valueOf(res);
                if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                    SemanticStack.getInstance().push(sr_do1);
                    break;
                }
            }catch(Error e){
                SymbolTable.getInstance().getErrors().add("Error en la expresion, linea "+pLine);
            }
        }
//        SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
//                        SR_ID sr_id = (SR_ID) SemanticStack.getInstance().pop();
//                        if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
//                            ((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).setValue(sr_do.getValue().value.toString());
//                            //Writer.getInstance().getCode().add("MOVE "+ result+" , /"+sr_id.getValue().value.toString());
//                            _Operations.add("   MOVE #"+ result+" , /"+sr_id.getValue().value.toString()); 
//                        }else{
//                            SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+" no ha sido inicializada. Linea: "+ sr_id.getValue().left);
//                        }
        return result;
    }
}
