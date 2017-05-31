package project.semantic.SymbolTable;

public class Symbol {
    
    private String name;
    private String _SymbolType;
    private int line;

    Symbol(String name, String pType, int pLine){
        this.name=name;
        this._SymbolType = pType;
        this.line = pLine;
        
    }
    
    public String getName() {
        return name;
    } 

    public String getSymbolType() {
        return _SymbolType;
    }

    public int getLine() {
        return line;
    }
    
}
