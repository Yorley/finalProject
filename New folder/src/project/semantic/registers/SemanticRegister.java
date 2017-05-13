package project.semantic.registers;

import java_cup.runtime.Symbol;


public class SemanticRegister {
    private Symbol _Value;
    private String _RegisterType;
    
    public SemanticRegister(Symbol pValue, String pRegisterType) {
        _Value = pValue;
        _RegisterType = pRegisterType;
    }

    public Symbol getValue() {
        return _Value;
    }

    public void setValue(Symbol pValue) {
        _Value = pValue;
    }

    public String getRegisterType() {
        return _RegisterType;
    }
    
}
