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
import project.semantic.evaluators.RWEvaluator;
import project.semantic.evaluators.VariableEvaluator;

public class Writer {
    private static int cont=0;
    private static Writer _Instance = null;
    private String _Filename = "result";
    private String file1=null;
    private ArrayList<String> _Code = new ArrayList<String>();
    private ArrayList<String> functions=new ArrayList<String>(); 
    private String data=null;    
    private String dataLenght=null;
    
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
            
            File file = new File(_Filename+cont+".ens");  
            this.file1=_Filename+cont;
            FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(pContent+"\n");
            bufferWritter.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    public void write(String pContent, String pFileName) {
        try {
            File file = new File(pFileName+".ens");            
            FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(pContent+"\n");
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAll(){
        
        write(";Este es mi programa:");
        for (int iCode = 0; iCode !=_Code.size(); iCode++ ){
            write(_Code.get(iCode),file1);
        }
        ArrayList<String> operations = VariableEvaluator.getInstance().getOperations();
        for (int iOperation = 0; iOperation !=operations.size(); iOperation++ ){
            write(operations.get(iOperation),file1);
        }
        
        for (String f: this.getFunctions() ){
            System.out.println("esta escribiendo f: "+f);
            write(f,file1);
        }
        write(data,file1);
        Object f=" \"Fin switch\" ";
        write("defaultmsg: DATA "+ f,file1);

        ArrayList<Symbol> init = VariableEvaluator.getInstance().getInitializedVar();
//        write("SECTION .data");
        
        for(int iVariable = 0; iVariable != init.size(); iVariable++){
            Symbol_Var symbol = (Symbol_Var)init.get(iVariable);
            
            write(symbol.getName()+": EQU "+ symbol.getValue(),file1);
        }
        ArrayList<Symbol> uninit = VariableEvaluator.getInstance().getUninitializedVar();
//        write("\nSEECTION .bss");
        for(int iVariable = 0; iVariable != uninit.size(); iVariable++){
            Symbol_Var symbol = (Symbol_Var)uninit.get(iVariable);
            write(symbol.getName()+": RES "+VariableEvaluator.getInstance().getLenght(symbol.getType()),file1);
        }

//        write("\nSECTION .txt");
//        write("global _start");
//        write("\n_start:");
        
        
        cont++;
        resetValues();
    }
    public void resetValues(){
        VariableEvaluator.getInstance().getOperations().clear();
        VariableEvaluator.getInstance().getInitializedVar().clear();
        VariableEvaluator.getInstance().getUninitializedVar().clear();
        this.data=null;
        this.functions.clear();
        this.dataLenght=null;
        this._Code.clear();
        RWEvaluator.setCont(0);
    }
    public ArrayList<String> getCode() {
        return _Code;
    }

    public void setCode(ArrayList<String> _Code) {
        this._Code = _Code;
    }
    public ArrayList<String> getFunctions() {
        return functions;
    }

    public String getData() {
        return data;
    }
    public void concat(String section, String Content){
        section.concat(Content);
    }
    public String getDataLenght() {
        return dataLenght;
    }
    public String getFile(){
        return file1;
    }

    

    public void setData(String pData) {
        if (data==null){
            this.data=pData;

        }else{
            this.data = data+pData;

        }
    }

    public void setDataLenght(String pDataLenght) {
        this.dataLenght = dataLenght+pDataLenght;
    }
    
}
