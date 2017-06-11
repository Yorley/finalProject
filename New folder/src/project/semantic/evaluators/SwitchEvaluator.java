/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.evaluators;

/**
 *
 * @author ym
 */
public class SwitchEvaluator {
    
    
    private static SwitchEvaluator _Instance = null;
    private SwitchEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new SwitchEvaluator();
    }
    public static synchronized SwitchEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    
    
    
}
