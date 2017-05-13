/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.registers;

import java_cup.runtime.Symbol;

/**
 *
 * @author Christian
 */
public class SR_While extends SemanticRegister{
    private static int _Count = 1;
    private String _WhileLabel = "";
    private String _ExitLabel = "";
    public SR_While(Symbol pValue) {
        super(pValue,"SR_While");
        _WhileLabel = "while_Label"+_Count;
        _ExitLabel = "exitWhile"+_Count;
        _Count++;
    }
    public String getWhileLabel() {
        return _WhileLabel;
    }

    public String getExitLabel() {
        return _ExitLabel;
    }
    
    
}
