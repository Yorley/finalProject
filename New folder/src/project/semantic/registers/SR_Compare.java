/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.registers;

import java_cup.runtime.Symbol;

/**
 *
 * @author ym
 */
public class SR_Compare extends SemanticRegister {
    private int _Type;

    public SR_Compare(Symbol pValue, String pRegisterType) {
        super(pValue, "SR_Compare");
    }

    public int getType() {
        return _Type;
    }

    public void setType(int _Type) {
        this._Type = _Type;
    }
    
}
