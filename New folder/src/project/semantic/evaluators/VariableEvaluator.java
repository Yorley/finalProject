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

/**
 *
 * @author Christian
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
        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
        SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
        Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),"0", sr_id.getValue().left);
        SymbolTable.getInstance().addSymbol(symbol);
        _UninitializedVar.add(symbol);
    }
    
     public void evalIn(){
        if (SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_ID")){              
            SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
            SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
            SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
            Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),sr_do.getValue().value.toString(),sr_id.getValue().left);
            SymbolTable.getInstance().addSymbol(symbol);
            _InitializedVar.add(symbol);
        }else{
            String result= doOperations(String.valueOf(SemanticStack.getInstance().getLast().getValue().left),_Operations);
            SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
            SR_Type sr_type = (SR_Type) SemanticStack.getInstance().pop();
            _Operations.add("mov "+sr_id.getValue().value.toString()+" ,"+ result);
            Symbol symbol = new Symbol_Var(sr_id.getValue().value.toString(),sr_type.getValue().value.toString(),"0", sr_id.getValue().left);
            SymbolTable.getInstance().addSymbol(symbol);
            _UninitializedVar.add(symbol);
        }
    }
    public void evalAssign(){
        System.out.println("Entra al evalAssign");
            try{
                    if (SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_ID")){
                        System.out.println("Entra al if");

                        SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                        SR_ID sr_id = (SR_ID)SemanticStack.getInstance().pop();
                        if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                            System.out.println("este es el valor de el sr_do: "+ sr_do.getValue().value.toString());
                            ((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).setValue(sr_do.getValue().value.toString());
                            System.out.println("perro: "+((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).getValue());
                            Writer.getInstance().getCode().add("mov "+sr_id.getValue().value.toString()+", "+sr_do.getValue().value.toString());
                        }else{
                            SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+"no ha sido inicializada"+ sr_id.getValue().left);
                        }

                    }else{
                            System.out.println("Entra else");
                            System.out.println(String.valueOf(SemanticStack.getInstance().getLast().getValue().left));

                        String result= doOperations(String.valueOf(SemanticStack.getInstance().getLast().getValue().left),Writer.getInstance().getCode());
                        System.out.println(SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getSize()-1).getValue().value);
                        System.out.println(SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getSize()-2).getValue().value);
                        SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                        SR_ID sr_id = (SR_ID) SemanticStack.getInstance().pop();
                        if (SymbolTable.getInstance().existSymbol(sr_id.getValue().value.toString(), "Var")){
                            System.out.println("este es el valor de el sr_do: "+ sr_do.getValue().value.toString());
                            ((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).setValue(sr_do.getValue().value.toString());
                            System.out.println("perro: "+((Symbol_Var) SymbolTable.getInstance().getSymbol(sr_id.getValue().value.toString())).getValue());
                            Writer.getInstance().getCode().add("mov "+sr_id.getValue().value.toString()+" ,"+ result); 
                        }else{
                            SymbolTable.getInstance().getErrors().add("La variable "+sr_id.getValue().value.toString()+" no ha sido inicializada. Linea: "+ sr_id.getValue().left);
                        }
                }
            }
            catch ( Error e ){
                    System.out.println("lfvdsdfslkjhgfdsa");
            }
            
                
              
        
    }
    public int getLenght(String pType){
        int len;
        switch (pType){
                case "int":
                    System.out.println("si entrarrrrrrrr");
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
                System.out.print(cont);
                SR_DO sr_do1 = (SR_DO)SemanticStack.getInstance().pop();
                System.out.println(sr_do1.getValue().value);
                SR_Op sr_op = (SR_Op)SemanticStack.getInstance().pop();
                System.out.println(sr_op.getValue().value);

                SR_DO sr_do2 = (SR_DO)SemanticStack.getInstance().pop();
                System.out.println(sr_do2.getValue().value);
                cont++;

                switch(sr_op.getValue().value.toString()){
                    case "+":
                        pList.add("mov ebx, "+sr_do1.getValue().value.toString());
                        pList.add("add ebx, "+sr_do2.getValue().value.toString());
                        result = "ebx";
                        res=res+(Integer.parseInt(sr_do1.getValue().value.toString()) + Integer.parseInt(sr_do2.getValue().value.toString()) );
                        break;
                    case "-":
                        pList.add("mov ebx, "+sr_do1.getValue().value.toString());
                        pList.add("sub ebx, "+sr_do2.getValue().value.toString());
                        result = "ebx";
                        break;
                    case "*":
                        pList.add("mov eax, "+sr_do1.getValue().value.toString());
                        pList.add("mul eax, "+sr_do2.getValue().value.toString());
                        result = "eax";
                        break;
                    case "/":
                        pList.add("mov eax, "+sr_do1.getValue().value.toString());
                        pList.add("div eax, "+sr_do2.getValue().value.toString());
                        result = "eax";
                        break;
                }
                sr_do1.getValue().value = String.valueOf(res);
                if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                    System.out.println("entra al ultimo if ... push");
                    SemanticStack.getInstance().push(sr_do1);
                    break;
                }
            }catch(Error e){
                SymbolTable.getInstance().getErrors().add("Error en la expresion, linea "+pLine);
            }
        }
        return result;
    }
}
