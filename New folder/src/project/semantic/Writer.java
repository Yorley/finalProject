/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.semantic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import project.semantic.SymbolTable.Symbol;
import project.semantic.SymbolTable.Symbol_Var;
import project.semantic.evaluators.VariableEvaluator;

public class Writer {
    
    private static Writer _Instance = null;
    private String _Filename = "result";
    private ArrayList<String> _Code = new ArrayList<String>();
    
    private Writer() {}
    
    private static void createInstance(){
        if (_Instance == null)
            _Instance = new Writer();
    }
    public static Writer getInstance() {
        createInstance();
        return _Instance;
    }
    
    public void write(String pContent) {
        try {
            File file = new File(_Filename+".asm");
            FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(pContent+"\n");
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAll(){
        ArrayList<Symbol> init = VariableEvaluator.getInstance().getInitializedVar();
        write("SECTION .data");
        for(int iVariable = 0; iVariable != init.size(); iVariable++){
            Symbol_Var symbol = (Symbol_Var)init.get(iVariable);
            write(symbol.getName()+": dw "+ symbol.getValue());
            write(symbol.getName()+"Len: equ $ - "+ symbol.getName());
        }
        ArrayList<Symbol> uninit = VariableEvaluator.getInstance().getUninitializedVar();
        write("\nSEECTION .bss");
        for(int iVariable = 0; iVariable != uninit.size(); iVariable++){
            Symbol_Var symbol = (Symbol_Var)uninit.get(iVariable);
            write(symbol.getName()+" resb "+VariableEvaluator.getInstance().getLenght(symbol.getType()));
        }
        write("\nSECTION .txt");
        write("global _start");
        write("\n_start:");
        ArrayList<String> operations = VariableEvaluator.getInstance().getOperations();
        for (int iOperation = 0; iOperation !=operations.size(); iOperation++ ){
            write(operations.get(iOperation));
        }
        for (int iCode = 0; iCode !=_Code.size(); iCode++ ){
            write(_Code.get(iCode));
        }
    }
    public ArrayList<String> getCode() {
        return _Code;
    }

    public void setCode(ArrayList<String> _Code) {
        this._Code = _Code;
    }
    
}
