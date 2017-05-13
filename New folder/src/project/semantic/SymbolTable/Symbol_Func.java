package project.semantic.SymbolTable;

import java.util.HashMap;

public class Symbol_Func extends Symbol {
    private HashMap<String,String> parameters; //type and value
    private String returnValue;
    private String returnType;
        
    public Symbol_Func(String name,String pReturnValue, int pLine) {
        super(name, "Func",pLine);
        parameters= new HashMap<String,String>();
        returnValue = pReturnValue;
    }
    
    public void setReturnValue(String pReturnValue){
        this.returnValue = pReturnValue;
    }
    
    public void setReturnType(String pReturnType){
        this.returnType = pReturnType;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void addParameter(String type, String value) {
       parameters.put(type, value);
    }

    public String getReturnValue() {
        return returnValue;
    }

    public String getReturnType() {
        return returnType;
    }
    
    public int getCountParameters(){
        return parameters.size();
    }
}
