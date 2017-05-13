package project.semantic.SymbolTable;

public class Symbol_Var extends Symbol{

    private String type;
    private String value;
    
    public Symbol_Var(String name,String type,String value, int pLine) {
        super(name,"Var",pLine);
        this.type=type;
        this.value=value;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }     
    
}
