/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.carvoting;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.*;
import static za.ac.cput.carvoting.Dao.CreateConnection;

/**
 *
 * @author DELL
 */
public class ServerAppGUI extends JFrame implements ActionListener {
    
    private ServerSocket server ;
    private Socket listenForConn;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private JTextArea serverTxtArea = new JTextArea(5,40);
    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private String userActivity;
    
    public ServerAppGUI(){
        try{
            server = new ServerSocket(6666,1);
           
           topPanel.add(serverTxtArea);
           this.add(topPanel);
           
        }catch(IOException  ioe){
            ioe.printStackTrace();
        }
    }
    
    public void listen(){
        try{
            listenForConn = server.accept();
//             Dao.CreateConnection();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void getStreams() throws IOException{
        out = new ObjectOutputStream(listenForConn.getOutputStream());
        out.flush();
        in = new ObjectInputStream(listenForConn.getInputStream());
    }
    
    public void sendData( ){}
    
    public void communication(){
        try{
            getStreams();
            
            userActivity = (String)in.readObject();
            serverTxtArea.append("Client>>> "+ userActivity);
            
 
        }catch(IOException | ClassNotFoundException ioe){
        
        }
    }
    
    public void closeStreamsAndConn() throws IOException{
        out.close();
        in.close();
        server.close();
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ServerAppGUI runServerGui = new ServerAppGUI();
        
        runServerGui.pack();
        runServerGui.setSize(500, 500);
        runServerGui.setVisible(true);
        runServerGui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        runServerGui.listen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
   
    
    
}
