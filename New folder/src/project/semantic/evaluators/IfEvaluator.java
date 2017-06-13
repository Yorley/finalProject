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
import project.semantic.registers.SR_If;
import project.semantic.registers.SR_Op;
import project.semantic.registers.SR_PUTS;
import project.semantic.registers.SR_PUTW;
import project.semantic.registers.SemanticRegister;

/**
 *
 * @author YM
 */
public class IfEvaluator {
    private ArrayList<String> ifStruct= new ArrayList<String>();
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
    public void evalTest(){
        SR_If sr_if = null;
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_If")){
            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                SR_DO sr_do = (SR_DO) SemanticStack.getInstance().pop();
                sr_if = (SR_If)SemanticStack.getInstance().getLast();            
                String do_name = sr_do.getValue().value.toString();
                if(do_name.equals("0") || do_name.equals("1")){
                    Writer.getInstance().getCode().add("cmp "+sr_do.getValue().value.toString()+" ,0");
                    Writer.getInstance().getCode().add("je "+sr_if.getExitLabel());
                }else{
                    SymbolTable.getInstance().getErrors().add("Error en la expresión if en la linea "+sr_do.getValue().left);
                }
            }
            else{
                String j;
                j = doOperations("",new ArrayList<String>(),sr_if);
            }
        }
    }
    private String doOperations(String pLine,ArrayList<String> pList,SR_If sr_if){
        String result = "";
        int res = 0;
        int cont=0;
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_If")){
            try {
                SemanticRegister p= SemanticStack.getInstance().getLast();
                SR_DO sr_do2 = (SR_DO)SemanticStack.getInstance().pop();
                SR_Op sr_op = (SR_Op)SemanticStack.getInstance().pop();
                SemanticRegister pp= SemanticStack.getInstance().getLast();
                SR_DO sr_do1 = (SR_DO)SemanticStack.getInstance().pop();
                cont++;
                sr_if = (SR_If)SemanticStack.getInstance().getLast();
                switch(sr_op.getType()){
                    case "<":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getLiteralValue()+", #"+sr_do2.getLiteralValue()+"\n"
                        + "   BN   $"+sr_if.getIfLabel()+"\n"+sr_if.getExitLabel()+":");
                        ifStruct.add("CMP #"+sr_do1.getLiteralValue()+", #"+sr_do2.getLiteralValue()+"\n"
                        + "   BN   $"+sr_if.getIfLabel()+"\n"+ "   BR   $"+sr_if.getExitLabel());
                        break;
                    case ">":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BP   $"+sr_if.getExitLabel());
                        ifStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BP   $"+sr_if.getIfLabel()+"\n"+ "   BR   $"+sr_if.getExitLabel());
                        break;
                    case "==":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BZ   $"+sr_if.getExitLabel());
                        ifStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BZ   $"+sr_if.getIfLabel()+"\n"+ "   BR   $"+sr_if.getExitLabel());
                        
                        break;
                    case "¡=":
                        VariableEvaluator.getInstance().getOperations().add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BNZ   $"+sr_if.getExitLabel());
                        ifStruct.add("CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BNZ   $"+sr_if.getIfLabel()+"\n"+ "   BR   $"+sr_if.getExitLabel());
                        
                        break;
                    default:
                        ifStruct.add("\n   CMP #"+sr_do1.getValue().value.toString()+", #"+sr_do2.getValue().value.toString()+"\n"
                        + "   BR   $"+sr_if.getExitLabel());
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
    public void evalEnd(){
        while(!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_IF")){
                if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
                        SR_DO sr=(SR_DO) SemanticStack.getInstance().pop();
                        if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                            Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                            ifStruct.add(1,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                            RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                            SemanticStack.getInstance().pop();
                            break;
                        }
                        else{
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                ifStruct.add(1,"WRINT #"+sr.getValue().value.toString()+"\n");
                                SemanticStack.getInstance().pop();
                                break;
                            }
                            else{
                                System.out.println("Aqui iria evaluacion de identificadores en if");
                                break;
                            }
                        }
                    }
                    else{
                        if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                            SR_ID sr=(SR_ID) SemanticStack.getInstance().pop();
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                                Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                                ifStruct.add(1,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                                RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                                SemanticStack.getInstance().pop();
                                break;
                            }
                            else{
                                if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                    ifStruct.add(1,"WRINT #"+sr.getValue().value.toString()+"\n");
                                    SemanticStack.getInstance().pop();
                                    break;
                                }
                                else{
                                    System.out.println("Aqui iria evaluacion de identificadores en if");
                                    break;
                                }
                            }
                        }
                        else{
                            SymbolTable.getInstance().getErrors().add("Hay un error en if: Esta escribiendo instrucciones no permitidas en el if, verifiquelo.");
                            break;
                        }
                    }
                }
                SemanticStack.getInstance().pop();
                for(String ifStructitem:ifStruct){
                    Writer.getInstance().getIfs().add("   "+ifStructitem);
                }
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
    public void evalStart(){
        SR_If sr_while = (SR_If)SemanticStack.getInstance().getLast();
        ifStruct.add("\n"+sr_while.getIfLabel()+":");
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
