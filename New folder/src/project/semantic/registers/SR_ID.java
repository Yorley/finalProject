/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.registers;

import java_cup.runtime.Symbol;

public class SR_ID extends SemanticRegister{
    private int _Domain;
    
    public SR_ID(Symbol pValue) {
        super(pValue,"SR_ID");
    }

    public int getDomain() {
        return _Domain;
    }

    public void setDomain(int _Type) {
        this._Domain = _Type;
    }
}
