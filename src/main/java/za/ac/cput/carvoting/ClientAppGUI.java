/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.carvoting;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.carvoting.Dao;

/**
 *
 * @author DELL
 */
public class ClientAppGUI extends JFrame implements ActionListener {
   // private JMenu menu;
   // private JMenuBar bar;
    private JPanel north, center, south;
    private JLabel TypeOfCars;
    protected static JComboBox<String> comboBox;
    private JTable table;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private JButton voteBtn, viewBtn, exitBtn;
    private  Dao carDao ;
    public ClientAppGUI() throws SQLException {
       super("Voting system");
      
      TypeOfCars = new JLabel("Types of Cars");
      carDao = new Dao();
     comboBox = new JComboBox();
     populate();
     
     tableModel = new DefaultTableModel();
     table = new JTable(tableModel);
     tableModel.addColumn("Types of Cars");
      tableModel.addColumn("Number of Votes");
     scrollpane = new JScrollPane(table);
     
     voteBtn = new JButton("vote");
     viewBtn = new JButton("View");
     exitBtn = new JButton("Exit");
     
     north = new JPanel();
     center = new JPanel();
     south = new JPanel();
    }
    
    public void setGui(){
        
        north.setLayout(new GridLayout(1,2));
        north.add(TypeOfCars);
        north.add(comboBox);
        
        south.setLayout(new GridLayout(1,3));
        south.add(voteBtn);
        south.add(viewBtn);
        south.add(exitBtn);
        
        comboBox.addActionListener(this);
        voteBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        
        this.add(north, BorderLayout.NORTH);
        this.add(scrollpane, BorderLayout.CENTER );
        this.add(south, BorderLayout.SOUTH );
         
    }
    
    public void sendData(String msg){
    try{
        Client.out.writeObject(msg);
        Client.out.flush();
    }catch(IOException ioe){
    }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == voteBtn){
            
         String selectedItem = (String)comboBox.getSelectedItem();
         sendData(selectedItem);
        
    }
    }

public void populate() throws SQLException{

    Dao obj1 = new Dao();
    
    ArrayList<WorkerClass> list = obj1.Cars();
    
    for(WorkerClass list2:list){
        
        comboBox.addItem(list2.getCarName());
        
    }


}
}
