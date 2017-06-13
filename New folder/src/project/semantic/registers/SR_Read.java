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
public class SR_Read extends SemanticRegister{
    public SR_Read(Symbol pValue) {
        super(pValue,"SR_Read");
    }
}
