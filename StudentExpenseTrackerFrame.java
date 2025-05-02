
package tut.ac.za.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import tut.ac.za.exp.Expense;

/**
 *
 * @author Olama
 */
public class StudentExpenseTrackerFrame extends JFrame{
 
    private JPanel headingPnl, detailsPnl, namePnl, categoryPnl,   
            costPnl, displayPnl, btnsPnl, headingCombinePnl, mainPnl;
    private JLabel headingLbl, nameLbl, categoryLbl, costLbl;   
           
    private JTextField nameTxtFld, catTxtFld, costTxtFld;
    private JTextArea displayArea;
    private JButton saveBtn, displayBtn, exitBtn;
    private JScrollPane scrollablePane;
   
    public StudentExpenseTrackerFrame() {
        
        setTitle("Student Expense Tracker");
        setSize(550, 400);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        headingPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        detailsPnl = new JPanel(new GridLayout(4,1,1,1));
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameTxtFld = new JTextField(10);
        categoryPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        catTxtFld = new JTextField(10); 
        costPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        costTxtFld = new JTextField(10);
        
        displayPnl = new JPanel(new BorderLayout());
        btnsPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headingCombinePnl = new JPanel(new GridLayout(2,1,1,1));
        mainPnl = new JPanel(new BorderLayout());
        
        headingLbl = new JLabel("Student Expense Tracker");
        headingLbl.setFont(new Font(Font.SERIF, Font.PLAIN + Font.BOLD,12));
        headingLbl.setBorder(new BevelBorder(BevelBorder.RAISED));
        nameLbl = new JLabel("Name :");
        categoryLbl = new JLabel("Category (food,rent.,etc):"); 
        costLbl = new JLabel("Cost R: ");
        
        displayArea = new JTextArea(15,30);
        displayArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Monthly summaries"));
        displayArea.setEditable(false);
        scrollablePane = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new SaveButtonListener());
        displayBtn = new JButton("Display");
        displayBtn.addActionListener(new DisplayButtonListener());
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ExitButtonListener());
        
        headingPnl.add(headingLbl);
        
        namePnl.add(nameLbl);
        namePnl.add(nameTxtFld);
        
       
        
        categoryPnl.add(categoryLbl);
        categoryPnl.add(catTxtFld);
        
        costPnl.add(costLbl);
        costPnl.add(costTxtFld);
        
        detailsPnl.add(namePnl);
        detailsPnl.add(categoryPnl);
        detailsPnl.add(costPnl);
        
        displayPnl.add(scrollablePane);
        btnsPnl.add(saveBtn);
        btnsPnl.add(displayBtn);
        btnsPnl.add(exitBtn);
        
        headingCombinePnl.add(headingPnl,BorderLayout.NORTH);
        headingCombinePnl.add(detailsPnl,BorderLayout.CENTER);
        
        mainPnl.add(headingCombinePnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(btnsPnl, BorderLayout.SOUTH);
         
        add(mainPnl); 
        
        pack();
        setVisible(true);
        
    }
    
    private class ExitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           System.exit(0);
        }
    }
     
    private class DisplayButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            File file;
            BufferedReader br;
            String line , record = "";
            JFileChooser fc;
            int value;
           
            fc = new JFileChooser();
            value = fc.showOpenDialog(StudentExpenseTrackerFrame.this);
            
            if(value == JFileChooser.APPROVE_OPTION){
             try{
                 
                    file = fc.getSelectedFile();
                    br = new BufferedReader(new FileReader(file));
                    
                    while((line = br.readLine())!= null){ 
                       record = record + line + "\n";
                    }
                     br.close(); 
                     displayArea.setText(record);
                     
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error reading file!:" + ex.getMessage());
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, "Error reading file!:" + ex.getMessage());
                }
                
            }
            
            
        }   
    }
     
    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name, surname, category, stuName;
            double cost;
            BufferedWriter bw;
            JFileChooser fc;
            File file;
            int value;
            
            
            stuName = nameTxtFld.getText();
            category = catTxtFld.getText();
            cost =  Double.parseDouble(costTxtFld.getText());
            Expense stuExpense = new Expense(stuName, category, cost);
            
            fc = new JFileChooser();
            value = fc.showSaveDialog(StudentExpenseTrackerFrame.this);
            try {
                if(value == JFileChooser.APPROVE_OPTION){
                
                 file = fc.getSelectedFile();
                 bw = new  BufferedWriter(new FileWriter(file, true));
                 bw.write(stuExpense.toString());
                 bw.newLine();  
                 bw.close();
                 JOptionPane.showMessageDialog(null, "Saved successfully...");
                 
                 nameTxtFld.setText("");
                 catTxtFld.setText("");
                 costTxtFld.setText("");
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to open save dialog!");
                    
                }
            } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving!:" + ex.getMessage());
            }
          
            
            
        }   
    }
     
     
     
}
