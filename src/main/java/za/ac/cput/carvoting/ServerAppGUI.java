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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DELL
 */
public class ServerAppGUI extends JFrame implements ActionListener {
    
    private ServerSocket server ;
    Socket socket ;
    private Socket listenForConn;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private JTextArea serverTxtArea = new JTextArea(5,40);
    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private String userActivity;
    private Dao db;
    
    public ServerAppGUI(){
        try{
            topPanel.add(serverTxtArea);
           this.add(topPanel);
           
            server = new ServerSocket(6666,1);
            db = new Dao();
            listen();
            
            
          
           
        }catch(IOException  ioe){
            ioe.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ServerAppGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listen() throws SQLException{
        try{
            System.out.println("Server ;isterning");
            listenForConn = server.accept();
        
            System.out.println("Connted");
            Dao.CreateConnection();
            
            getStreams();
            communication();
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
    
    public void communication() {
        try{
            while(true){
                Object recievedObject = in.readObject();
                
                if(recievedObject instanceof String){
                  recievedObject = (String) recievedObject;
                  
                    if(recievedObject.equals("getAll")){
                        System.out.println(db.getCarVotes());
                        out.writeObject(db.getCarVotes());
                        out.flush();
                    }
                }     
                
            }
            
 
        }catch(IOException | ClassNotFoundException ioe){
        
        } catch (SQLException ex) {
            Logger.getLogger(ServerAppGUI.class.getName()).log(Level.SEVERE, null, ex);
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
           
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
   
    
    
}
