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
public class SR_Op extends SemanticRegister{
    private int _Type;
    
    public SR_Op(Symbol pValue) {
        super(pValue,"SR_Op");
    }

    public int getType() {
        return _Type;
    }

    public void setType(int _Type) {
        this._Type = _Type;
    }
}
