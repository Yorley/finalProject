/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.evaluators;

/**
 *
 * @author Christian
 */
public class ExpressionEvaluator {
    private static ExpressionEvaluator _Instance = null;
    private ExpressionEvaluator(){}
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new ExpressionEvaluator();
    }
    public static synchronized ExpressionEvaluator getInstance(){
        createInstance();
        return _Instance;
    }
    public void eval(){
        
    }
}
