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
import project.semantic.Writer;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_ID;
import project.semantic.registers.SR_If;
import project.semantic.registers.SR_Op;
import project.semantic.registers.SR_PUTS;
import project.semantic.registers.SR_PUTW;
import project.semantic.registers.SR_While;
import project.semantic.registers.SemanticRegister;
import project.sym;

/**
 *
 * @author YM
 */
public class WhileEvaluator {
    private static WhileEvaluator _Instance = null;
    private ArrayList<String> whileStruct= new ArrayList<String>();
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
        //Writer.getInstance().getCode().add("\n"+sr_while.getWhileLabel()+":");
        whileStruct.add("\n"+sr_while.getWhileLabel()+":");
        
    }
    public void evalTest(){
        convertDOs();
        SR_While sr_while = null;
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_While")){
            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
        //if(SemanticStack.getInstance().getStack().get(SemanticStack.getInstance().getStack().size()-2).getRegisterType().equals("SR_While")){
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
                String j;
                j = doOperations("",new ArrayList<String>(),sr_while);
                //Writer.getInstance().getCode().add("cmp "+sr_do1.getValue().value.toString()+", "+sr_do2.getValue().value.toString());
                
                //String cond = getCond(sr_Op);
                //Writer.getInstance().getCode().add("je "+sr_while.getExitLabel());
            }
        }
    }
    
    public void evalEnd(){
                   
        while(!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_While")){
                if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
                        SR_DO sr=(SR_DO) SemanticStack.getInstance().pop();
                        if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                            Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                            whileStruct.add(1,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                            RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                            SemanticStack.getInstance().pop();
                            break;
                        }
                        else{
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                whileStruct.add(1,"WRINT #"+sr.getValue().value.toString()+"\n");
                                SR_PUTW sr_putw=(SR_PUTW) SemanticStack.getInstance().pop();
                                break;
                            }
                            else{
                                System.out.println("Aqui iria evaluacion de identificadores en while");
                                break;
                            }
                        }
                    }
                    else{
                        if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                            SR_ID sr=(SR_ID) SemanticStack.getInstance().pop();
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                                Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                                whileStruct.add(1,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                                RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                                SR_PUTS sr_puts=(SR_PUTS) SemanticStack.getInstance().pop();
                                break;
                            }
                            else{
                                if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                    whileStruct.add(1,"WRINT #"+sr.getValue().value.toString()+"\n");
                                    SR_PUTW sr_putw=(SR_PUTW) SemanticStack.getInstance().pop();
                                    break;
                                }
                                else{
                                    System.out.println("Aqui iria evaluacion de identificadores en while");
                                    break;
                                }
                            }
                        }
                        else{
                            SymbolTable.getInstance().getErrors().add("Hay un error en while: Esta escribiendo instrucciones no permitidas en el while, verifiquelo.");
                            break;
                        }
                    }
                }
                SemanticStack.getInstance().pop();
                for(String whileStructitem:whileStruct){
                    Writer.getInstance().getWhiles().add("   "+whileStructitem);
                }
            //SR_While sr_while = (SR_While)SemanticStack.getInstance().getLast();
            //Writer.getInstance().getCode().add("jump "+sr_while.getWhileLabel());
            //Writer.getInstance().getCode().add("\n"+sr_while.getExitLabel()+":");
        
       
    }
    private String getCond(SR_Op pSR){
        
        return "";
    }
    private String doOperations(String pLine,ArrayList<String> pList,SR_While sr_while){
        String result = "";
        int res = 0;
        int cont=0;
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_While")){
            try {
                SemanticRegister p= SemanticStack.getInstance().getLast();
                SR_DO sr_do2 = (SR_DO)SemanticStack.getInstance().pop();
                SR_Op sr_op = (SR_Op)SemanticStack.getInstance().pop();
                SemanticRegister pp= SemanticStack.getInstance().getLast();
                SR_DO sr_do1 = (SR_DO)SemanticStack.getInstance().pop();
                cont++;
                sr_while = (SR_While)SemanticStack.getInstance().getLast();
                switch(sr_op.getType()){
                    case "<":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getLiteralValue()+", #"+sr_do2.getLiteralValue()+"\n"
                        + "   BN   $"+sr_while.getWhileLabel()+"\n"+sr_while.getExitLabel()+":");
                        whileStruct.add("CMP #"+sr_do1.getLiteralValue()+", #"+sr_do2.getLiteralValue()+"\n"
                        + "   BN   $"+sr_while.getWhileLabel()+"\n"+ "   BR   $"+sr_while.getExitLabel());
                        break;
                    case ">":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BP   $"+sr_while.getWhileLabel());
                        whileStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BP   $"+sr_while.getWhileLabel()+"\n"+ "   BR   $"+sr_while.getExitLabel());
                        break;
                    case "==":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BZ   $"+sr_while.getWhileLabel());
                        whileStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BZ   $"+sr_while.getWhileLabel()+"\n"+ "   BR   $"+sr_while.getExitLabel());
                        
                        break;
                    case "¡=":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BNZ   $"+sr_while.getWhileLabel());
                        whileStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BNZ   $"+sr_while.getWhileLabel()+"\n"+ "   BR   $"+sr_while.getExitLabel());
                        
                        break;
                    default:
                        whileStruct.add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BR   $"+sr_while.getWhileLabel());
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
        return result;
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
