
package project.semantic.SymbolTable;
import java.util.ArrayList;
import java.util.Hashtable;
 
public class SymbolTable {
    
    private static SymbolTable _Instance= null;
    private ArrayList<Symbol> _Symbols = new ArrayList<Symbol>();
    private ArrayList<String> _Errors = new ArrayList<String>();
    
    private SymbolTable() {
        _Symbols = new ArrayList<Symbol>();
    }
    private static void createInstance(){
        if (_Instance==null)
            _Instance = new SymbolTable();
    }
    public static synchronized SymbolTable getInstance(){
        createInstance();
        return _Instance;
    }
    
    public void addSymbol(Symbol symbol){
        System.out.println(symbol.getName()+symbol.getSymbolType()+symbol.getLine());
        if(!existSymbol(symbol))
            _Symbols.add(symbol);
        else{
            _Errors.add(symbol.getName()+" ya existe, linea "+symbol.getLine());
        }
    }

    public ArrayList<Symbol> getSymbols() {
        return _Symbols;
    }
    
    public boolean existSymbol(Symbol pSymbol){
        int size = _Symbols.size();
        for(int i=0; i<size;i++){
            if(_Symbols.get(i).getName().equals(pSymbol.getName()) && _Symbols.get(i).getSymbolType().equals(pSymbol.getSymbolType()))
                return true;
        }
        return false;
    }
    public boolean existSymbol(String pName, String pType){
        int size = _Symbols.size();
        for(int i=0; i<size;i++){
            if(_Symbols.get(i).getName().equals(pName) && _Symbols.get(i).getSymbolType().equals(pType))
                return true;
        }
        return false;
    }
    
    public Symbol getSymbol(String pName) {
        int size = _Symbols.size();
        for(int i = 0; i < size; i++) {
            Symbol actual = _Symbols.get(i);
            if(actual.getName().equals(pName))
                return actual;
        }
        return null;
    }

    public ArrayList<String> getErrors() {
        return _Errors;
    }
    public void clearAll(){
        _Symbols.clear();
        _Errors.clear();
    }
   
}
