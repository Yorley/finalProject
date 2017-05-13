
package project.semantic;

import java.util.ArrayList;
import project.semantic.registers.SemanticRegister;


public class SemanticStack {
    private static SemanticStack _Instance = null;
    private ArrayList<SemanticRegister> _Stack = new ArrayList<SemanticRegister>(); 
    
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new SemanticStack();
    }
    public static synchronized SemanticStack getInstance(){
        createInstance();
        return _Instance;
    }
    public void push(SemanticRegister pSemanticRegister){
        _Stack.add(pSemanticRegister);
    } 
    public SemanticRegister pop(){
        SemanticRegister register = _Stack.get(_Stack.size()-1);
        _Stack.remove(register);
        return register;
    }
    public SemanticRegister search(SemanticRegister pSemanticRegister){
        for(int iRegister = 0; iRegister != _Stack.size(); iRegister++){
            if (_Stack.get(iRegister).equals(pSemanticRegister)){
                return _Stack.get(iRegister);
            }
        }
        return null;
    }
    public int getSize(){
        return _Stack.size();
    }
    public void clearStack(){
        _Stack.clear();
    }
    

    public ArrayList<SemanticRegister> getStack() {
        return _Stack;
    }
    public SemanticRegister getLast(){
            SemanticRegister register = _Stack.get(_Stack.size()-1);
        return register;
    }
    
}
