package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JTable;
import project.semantic.SemanticStack;
import project.semantic.SymbolTable.SymbolTable;
import project.semantic.Writer;
import project.semantic.registers.SemanticRegister;

/**
 *
 * @author LuisPC
 */
public class MainWindow extends javax.swing.JFrame {

    //private static ArrayList<ArrayList<Symbol>> _TokensList = new ArrayList<ArrayList<Symbol>>();
    private static ArrayList<Symbol> _Tokens = new ArrayList<Symbol>();

    private static ArrayList<Symbol> _TokensList = new ArrayList<Symbol>();
    private static final String fileInputPath = "Entrada.txt";
    private static final String fileOutputPath = "Salida.txt";
    
    public MainWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnAnalize = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txbInput = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txbParser = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txbSemantic = new javax.swing.JTextArea();
        chkBox_ReadFile = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_tokens = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_Lexical_errors = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalize.setText("Analizar");
        btnAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizeActionPerformed(evt);
            }
        });

        txbInput.setColumns(20);
        txbInput.setRows(5);
        jScrollPane2.setViewportView(txbInput);

        jLabel1.setText("Errores Léxicos");

        jLabel2.setText("Errores Sintácticos");

        txbParser.setColumns(20);
        txbParser.setRows(5);
        jScrollPane3.setViewportView(txbParser);

        jLabel3.setText("Errores Semánticos");

        txbSemantic.setColumns(20);
        txbSemantic.setRows(5);
        jScrollPane4.setViewportView(txbSemantic);

        chkBox_ReadFile.setText("Analizar desde el archivo solamente");

        tbl_tokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Token", "Tipo", "Línea", "Posición en línea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbl_tokens);

        jTable_Lexical_errors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Token", "Tipo", "Posición en línea", "Línea"
            }
        ));
        jScrollPane5.setViewportView(jTable_Lexical_errors);

        jLabel4.setText("TOKENS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(chkBox_ReadFile)
                                .addGap(39, 39, 39)
                                .addComponent(btnAnalize)))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalize)
                    .addComponent(chkBox_ReadFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizeActionPerformed
        //En esta parte se obtienen los tokens que hay en el programa y los muestra, además muestra cuales tokens no pertenecen al programa.
        try {
            clearTableTokens();
            clearTableTokens(jTable_Lexical_errors);

            File dir =new File(".");
            String filePath =dir.getCanonicalPath()+"/src/project/";
            String inPath = filePath+ fileInputPath;
            System.out.println(inPath);
            
            //En el caso de que el usuario ingrese el codigo, lo que se escriba se guardará en un archivo y a la hora de la compilación se leerá ese archivo.
            if(!chkBox_ReadFile.isSelected()){
                writeFile(inPath,txbInput.getText());

            }
            scanFile(inPath);
            completeTableTokens(_Tokens);
            String lexicalErrors =tokensToString(_TokensList);
            String result = tokensToString(_Tokens);
            
            completeTableTokens(_TokensList,jTable_Lexical_errors);
            writeFile((filePath+fileOutputPath),result);
            SymbolTable.getInstance().clearAll();
            SemanticStack.getInstance().clearStack();
            Writer.getInstance().getCode().clear();
            parse(inPath);
            _TokensList.clear();
            _Tokens.clear();

        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizeActionPerformed
    

    public static void main(String args[]) throws IOException {
        File dir =new File(".");
        String filePath =dir.getCanonicalPath()+"/src/project/";
        String lexerPath = filePath+"Lexer.flex";
        String parserPath = filePath+"Parser.cup";
        generateLexer(lexerPath);
        generateParser(parserPath);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Generates .flex file
    //Función encargada de generar el archivo .flex
    private static void generateLexer(String pFilePath){
        File file = new File(pFilePath);
        jflex.Main.generate(file);
    }
    
    //Muestra los errores de que hay en el programa, es decir si la sintaxis es correcta.
    private  void showParserErrors(ArrayList<String> pExpectedTokens, ArrayList<String> pErrorsLine, ArrayList<String> pErrors){
        txbParser.setText("");
        for(int iError = 0; iError != pExpectedTokens.size(); iError++){
            txbParser.setText(txbParser.getText()+ pErrorsLine.get(iError));
            txbParser.setText(txbParser.getText()+ pExpectedTokens.get(iError)+"\n");
            txbParser.setText(txbParser.getText()+ "------------------------------------------------------------\n");
        }
        if (pExpectedTokens.size()==0){
            System.out.println(_TokensList.size() );
            
            if (_TokensList.size() == 0){
                txbParser.setText("Parseo completado sin errores");
            }else{                
                txbParser.setText("Existen erorres lexicos");
            }
            
        }
    }
    //Realiza el pareser, muestra los errores del parser y muestra los errores semanticos.
    private void parse(String pFileInputPath){
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(pFileInputPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnalizadorSintactico p = new AnalizadorSintactico(new AnalizadorLexico(bf));
        try {
            p.parse();
            System.out.println(p.getExpectedTokens().toString()+ p.getLineErrors().toString()+ p.getErrors());
            showParserErrors(p.getExpectedTokens(), p.getLineErrors(), p.getErrors());
            if (SymbolTable.getInstance().getErrors().size() != 0){
                for(int iError = 0; iError != SymbolTable.getInstance().getErrors().size(); iError++){
                    txbSemantic.setText(txbSemantic.getText()+SymbolTable.getInstance().getErrors().get(iError)+"\n");
                }
            }else{
                txbSemantic.setText("Compilación completada");
                  
                Writer.getInstance().writeAll();
            }
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void printRegisters(){
        for (SemanticRegister sr : SemanticStack.getInstance().getStack()){
            System.out.println(sr.getRegisterType().toString()+ sr.getValue().value.toString());
        }
    }
    //genera el .cup
    private static void generateParser(String pFilePath){
        String[] asintactico = {"-parser", "AnalizadorSintactico", pFilePath};
        try {
            java_cup.Main.main(asintactico);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        moverArch("AnalizadorSintactico.java");
        moverArch("sym.java");

    }
    
    //Función encargada de escribir un archivo.
    private static boolean writeFile(String pFilePath, String pText){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(pFilePath);
            writer.print(pText);
            writer.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String convertFileToString(BufferedReader bf){
        String result= null;
        String res = null;
        try {
            while ((result = bf.readLine())!=null){
                if (res!=null){
                   res+= (result+"\n");
                }else{
                    res= (result+"\n");
                }
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    
    }    

//Realiza el análisis de lossimbolos del programa.
    private  void scanFile(String pFileInputPath){
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(pFileInputPath));
            if(chkBox_ReadFile.isSelected()){
                txbInput.setText(convertFileToString(new BufferedReader(new FileReader(pFileInputPath))));
            }
            AnalizadorLexico a = new AnalizadorLexico(bf);
            Symbol token = null;
            boolean isEOF = false;
            
            do {
                token = a.next_token();
                if(token.right != -1|| token.left != -1){
                    
                    System.out.println("--------token---------");
                    System.out.println("Simbolo: "+(sym.terminalNames[token.sym])+ " ,numero:" +token.sym);
                    System.out.println("Linea: "+token.left);
                    System.out.println("Columna: "+token.right);
                    //System.out.println("Value: "+token.value.toString());                    
                    System.out.println("--------token---------");
                    _Tokens.add(token);

                    if (TOKENS.get(token.sym).equals("ERROR")){
                        System.out.println("aca va un error "+token.value.toString());
                        _TokensList.add(token);
                    }
                    //insertToken(token);
                }else{
                    isEOF = true;
                }
            } while (!isEOF);            
            bf.close();
        } catch (Exception ex) {
            //Logger.getLogger(jflex.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    private static void insertToken(Symbol pToken){
        if (pToken != null){
            
            for(int i = 0; i < _TokensList.size(); i++){
                Symbol tempToken = _TokensList.get(i).get(0);         
                if (pToken.sym == tempToken.sym && pToken.value.toString().equals(tempToken.value.toString())){
                    _TokensList.get(i).add(pToken);
                    return;
                }
            }
            
            _TokensList.add(new ArrayList<Symbol>());
            _TokensList.get(_TokensList.size()-1).add(pToken);
            
        }
    }
    
    private static String tokensToString(){
        String result = "Token\tTipo\tLineas \n";
        for(int iRow = 0; iRow < _TokensList.size(); iRow++){
            result += _TokensList.get(iRow).get(0).value.toString() + "\t";
            result += TOKENS.get(_TokensList.get(iRow).get(0).sym) + "\t";
            // Se averigua la cantidad que hay en esa misma
            ArrayList<Integer> linesList = new ArrayList<Integer>();
            String lines = "";
            for(int iToken = 0; iToken < _TokensList.get(iRow).size(); iToken++){
                linesList.add(_TokensList.get(iRow).get(iToken).left);
            }
            int ocurrences = 0;
            while (linesList.size() !=0 ){
                ocurrences = Collections.frequency(linesList, linesList.get(0));
                if (ocurrences != 1){
                    lines += linesList.get(0) + "(" +ocurrences+ "), ";
                }else{
                    lines += linesList.get(0) + ", ";
                }
                ocurrences = 0;
                linesList.removeAll(Collections.singleton(linesList.get(0)));
            }
            result += lines;
            result += "\n";
            
        }
        return result;
    }
    */
    
    //Completa la tabla donde se muestran los tokens del programa, es la primer tabla de la interfaz.
    private void completeTableTokens(ArrayList<Symbol> pList){
        for(int iRow = 0; iRow < pList.size(); iRow++){
            tbl_tokens.setValueAt(pList.get(iRow).value.toString(), iRow, 0);
            tbl_tokens.setValueAt(sym.terminalNames[(pList.get(iRow).sym)], iRow, 1);
            tbl_tokens.setValueAt((pList.get(iRow)).left, iRow, 2);
            tbl_tokens.setValueAt((pList.get(iRow)).right, iRow, 3);
        }
    }
    //Setea la tabla de tokens.
    private void clearTableTokens(){
        for(int iRow = 0; iRow < tbl_tokens.getRowCount(); iRow++){
            tbl_tokens.setValueAt(null, iRow, 0);
            tbl_tokens.setValueAt(null, iRow, 1);
            tbl_tokens.setValueAt(null, iRow, 2);
            tbl_tokens.setValueAt(null, iRow, 3);
        }
    }
    
        //Completa la tabla donde se muestran los tokens del programa, es la primer tabla de la interfaz.
    private void completeTableTokens(ArrayList<Symbol> pList,JTable pTable){
        for(int iRow = 0; iRow < pList.size(); iRow++){
            pTable.setValueAt(pList.get(iRow).value.toString(), iRow, 0);
            pTable.setValueAt(sym.terminalNames[(pList.get(iRow).sym)], iRow, 1);
            pTable.setValueAt((pList.get(iRow)).left, iRow, 2);
            pTable.setValueAt((pList.get(iRow)).right, iRow, 3);
        }
    }
    //Setea la tabla de tokens.
   //Setea la tabla de tokens.
    private void clearTableTokens(JTable pTable){
        for(int iRow = 0; iRow < tbl_tokens.getRowCount(); iRow++){
            pTable.setValueAt(null, iRow, 0);
            pTable.setValueAt(null, iRow, 1);
            pTable.setValueAt(null, iRow, 2);
            pTable.setValueAt(null, iRow, 3);
        }
    }
    
    //Convierte cada tken en un string, mostrando la línea, columna, el token y el tipo de token.
    private static String tokensToString(ArrayList<Symbol> pList){
        String result = "Token                    \tTipo                     \tLineas \n\n";
        for(int iRow = 0; iRow < pList.size(); iRow++){
            result += pList.get(iRow).value.toString() + "\t\t\t\t\t";
            result += sym.terminalNames[(pList.get(iRow).sym)] + "\t\t\t";
            result += pList.get(iRow).left;
            result += "\n";
 
        }
        return result;
        
    }
    
    //Mueve un archio de una carpeta a otra.
    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            //System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "project" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                //System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                //System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            //System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }
    public static final ArrayList<String> TOKENS= new ArrayList<String>(
            Arrays.asList("EOF","ERROR","LITERAL","LITERAL","LITERAL","LITERAL",
            "PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS",
            "PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS",
            "PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS",
            "PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS","PALABRAS_RESERVADAS",
            "IDENTIFICADOR",
            "OPERADOR","OPERADOR","OPERADOR","OPERADOR","OPERADOR","OPERADOR",
            "OPERADOR","OPERADOR","OPERADOR","OPERADOR","OPERADOR","OPERADOR",
            "OPERADOR","OPERADOR", "OPERADOR","OPERADOR" 
            ));
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalize;
    private javax.swing.JCheckBox chkBox_ReadFile;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable_Lexical_errors;
    private javax.swing.JTable tbl_tokens;
    private javax.swing.JTextArea txbInput;
    private javax.swing.JTextArea txbParser;
    private javax.swing.JTextArea txbSemantic;
    // End of variables declaration//GEN-END:variables
}
