/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic.registers;

import java_cup.runtime.Symbol;
import project.semantic.SymbolTable.SymbolTable;
import project.sym;

/**
 *
 * @author YM
 */
public class SR_DO extends SemanticRegister{

    private String _Type = "";
    private String _Value="";
    public SR_DO(Symbol pValue) {
        super(pValue,"SR_DO");
        project.semantic.SymbolTable.Symbol symbol = SymbolTable.getInstance().getSymbol(pValue.value.toString());
        if (symbol != null){
            this._Type = symbol.getSymbolType();
        }else{
            SymbolTable.getInstance().getErrors().add("No se encuentra el identificador: "+pValue.value.toString()+" ,linea "+pValue.left);

        }
        
    }
    public SR_DO(Symbol pValue, String pType) {
        super(pValue,"SR_DO");
        _Type = pType;
        
    }
    public SR_DO(Symbol pValue, String pType, String operation) {
        super(pValue,"SR_DO");
        _Type = pType;
        _Value= pValue.value.toString();
        
    }

    public String getType() {
        return _Type;
    }

    public void setType(String _Type) {
        this._Type = _Type;
    }

    public String getLiteralValue() {
        return _Value;
    }

    public void setValue(String _Value) {
        this._Value = _Value;
    }
    
    
}
