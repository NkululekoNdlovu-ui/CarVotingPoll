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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static za.ac.cput.carvoting.Client.in;
import static za.ac.cput.carvoting.Client.out;
import za.ac.cput.carvoting.Dao;

/**
 *
 * @author DELL
 */
public class ClientAppGUI extends JFrame implements ActionListener {
    private JPanel north, center, south;
    private JLabel TypeOfCars;
    protected static JComboBox<String> comboBox;
    private JTable table;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private JButton voteBtn, viewBtn, exitBtn;
    private  Dao carDao ;
    
    //Server connection
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;
    private Socket serverConn;
    private String response;
    
    public ClientAppGUI() throws SQLException {
       super("Voting system");
      
      TypeOfCars = new JLabel("Types of Cars");
      
      carDao = new Dao();
     comboBox = new JComboBox();
 
     
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
    
    public void setGui() throws SQLException{
        
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
        
       this.pack();
       this.setSize(500,500);
       this.setVisible(true);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("hi");
        communicateWihServer();
        System.out.println("hi");
        populate();
         
    }
    
    
    public void communicateWihServer(){
         try{
            serverConn = new Socket("127.0.0.1", 6666);
            
            getStreams();
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void getStreams() throws IOException{
        out = new ObjectOutputStream(serverConn.getOutputStream());
        out.flush();
        in = new ObjectInputStream(serverConn.getInputStream());
    }
    
    
    public void closeStreamAndConnection() throws IOException{
        out.close();
        in.close();
        serverConn.close();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == voteBtn){
            
         String selectedItem = (String)comboBox.getSelectedItem();
         
        
    }
    }

public void populate() throws SQLException{
    
        try {
            out.writeObject("getAll");
            out.flush();
            
            ArrayList<VotingPole> votes = (ArrayList) in.readObject();
            System.out.println(votes);
            for (int i = 0; i < votes.size(); i++) {
                comboBox.addItem(votes.get(i).getCarName());
                               
                Object[] rowData = {votes.get(i).getCarName(), votes.get(i).getNumberOfVotes()};
                tableModel.addRow(rowData);
                
                
            }  
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientAppGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientAppGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
  


}
    
}
