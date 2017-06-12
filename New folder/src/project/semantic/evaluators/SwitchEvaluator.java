/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.evaluators;

import java.util.ArrayList;
import project.semantic.SemanticStack;
import project.semantic.registers.SR_BREAK_DEFAULT;
import project.semantic.registers.SR_DEFAULT;
import project.semantic.registers.SR_DO;
import project.semantic.registers.SR_SWITCH;

/**
 *
 * @author ym
 */
public class SwitchEvaluator {
    
    
    private static SwitchEvaluator _Instance = null;
    private static int option_to_evaluate;
    private ArrayList<String> tempEtiqueta;
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
        while (!SemanticStack.getInstance().getLast().equals("SR_SWITCH")){
            SR_BREAK_DEFAULT breakDefault= (SR_BREAK_DEFAULT) SemanticStack.getInstance().pop();
            SR_DEFAULT defaults=(SR_DEFAULT) SemanticStack.getInstance().pop();
            tempEtiqueta.add(0,"BR /salirSwitch");
            
        }
    }
    
    
}
