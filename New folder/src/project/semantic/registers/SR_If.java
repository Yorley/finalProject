/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.registers;

import java_cup.runtime.Symbol;

/**
 *
 * @author YM
 */
public class SR_If extends SemanticRegister{
    private static int _Count = 1;
    private String elseLabel = "";
    private String exitLabel = "";
    public SR_If(Symbol pValue) {
        super(pValue,"SR_If");
        elseLabel = "else_label"+_Count;
        exitLabel = "exitIf"+_Count;
        _Count++;
    }

    public String getElseLabel() {
        return elseLabel;
    }
    public String getExitLabel() {
        return exitLabel;
    }
}
