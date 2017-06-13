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
import project.semantic.registers.SR_BREAK_DEFAULT;
import project.semantic.registers.SR_DEFAULT;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_ID;
import project.semantic.registers.SR_OPT;
import project.semantic.registers.SR_PUTS;
import project.semantic.registers.SR_PUTW;
import project.semantic.registers.SR_SWITCH;

/**
 *
 * @author ym
 */
public class SwitchEvaluator {
    
    
    private static SwitchEvaluator _Instance = null;
    private static int option_to_evaluate;
    private ArrayList<String> tempTag= new ArrayList<String>() ;
    private ArrayList<String> tempCmp= new ArrayList<String>() ;
    private SwitchEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new SwitchEvaluator();
    }
    public static synchronized SwitchEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    
    public void evalSwitch(){
        while (!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_SWITCH")){
            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_BREAK_DEFAULT")){
                SR_BREAK_DEFAULT breakDefault= (SR_BREAK_DEFAULT) SemanticStack.getInstance().pop();
                SR_DEFAULT defaults=(SR_DEFAULT) SemanticStack.getInstance().pop();

                tempCmp.add("   BZ /Default"+"\n");

                tempTag.add("salirSwitch: \n   WRSTR /defaultmsg");

                tempTag.add(0,"Default: \n   BR /salirSwitch");
            
            }                      
            ArrayList<String> tempCase =  new ArrayList<String>();
            while(!SemanticStack.getInstance().getLast().getRegisterType().equals("SR_OPT")){
                if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_BREAK_TAG")){
                    SemanticStack.getInstance().pop();
                    tempCase.add("   BR /salirSwitch");
                }else{
                    if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_DO")){
                        SR_DO sr=(SR_DO) SemanticStack.getInstance().pop();
                        if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                            Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                            tempCase.add(0,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                            RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                            SR_PUTS sr_puts=(SR_PUTS) SemanticStack.getInstance().pop();
                        }
                        else{
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                tempCase.add(0,"WRINT #"+sr.getValue().value.toString()+"\n");
                                SR_PUTW sr_putw=(SR_PUTW) SemanticStack.getInstance().pop();
                            }
                            else{
                                System.out.println("Aqui iria evaluacion de identificadores en switch");
                                break;
                            }
                        }
                    }
                    else{
                        if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_ID")){
                            SR_ID sr=(SR_ID) SemanticStack.getInstance().pop();
                            if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTS")){
                                Writer.getInstance().setData("mens"+RWEvaluator.getInstance().getCont()+":   DATA "+sr.getValue().value+"\n");
                                tempCase.add(0,"WRSTR /mens"+RWEvaluator.getInstance().getCont()+"\n");
                                RWEvaluator.setCont(RWEvaluator.getInstance().getCont()+1);
                                SR_PUTS sr_puts=(SR_PUTS) SemanticStack.getInstance().pop();
                            }
                            else{
                                if(SemanticStack.getInstance().getLast().getRegisterType().equals("SR_PUTW")){
                                    tempCase.add(0,"WRINT #"+sr.getValue().value.toString()+"\n");
                                    SR_PUTW sr_putw=(SR_PUTW) SemanticStack.getInstance().pop();
                                }
                                else{
                                    System.out.println("Aqui iria evaluacion de identificadores en switch");
                                    break;
                                }
                            }
                        }
                        else{
                            SymbolTable.getInstance().getErrors().add("Hay un error en switch: Est[a escribiendo instrucciones no permitidas en el case, verifiquelo.");
                            break;
                        }
                    }
                }
            }
            String res=null;
            for (String i: tempCase){
                if (!(res==null)){
                    res+=i+"\n";
                }
                else{
                    res=i;
                }
            }
            SR_OPT opt=(SR_OPT) SemanticStack.getInstance().pop();
            if (SemanticStack.getInstance().getLast().getRegisterType().equals("SR_CASE")){
                tempTag.add(0,"case"+opt.getValue().value.toString()+": \n"+"   "+res);
                tempCase.clear();
                SemanticStack.getInstance().pop();
                tempCmp.add(0,"   CMP #"+option_to_evaluate+", #"+opt.getValue().value.toString()+"\n   BZ $case"+opt.getValue().value.toString());
            }
            else{
                SymbolTable.getInstance().getErrors().add("Hay un error en switch: No escribio el case, verifiquelo.");
                break;
            }
        }
        SemanticStack.getInstance().pop();
        for (String bucket: tempCmp){
                VariableEvaluator.getInstance().getOperations().add(bucket);

        }
        for (String bucket: tempTag){
                VariableEvaluator.getInstance().getOperations().add(bucket);

        }
        tempCmp.clear();
        tempTag.clear();
    }

    public static void setOption_to_evaluate(int option_to_evaluate) {
        SwitchEvaluator.option_to_evaluate = option_to_evaluate;
        
    }
    
    
    
}
