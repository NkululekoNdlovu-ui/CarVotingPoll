/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.carvoting;

import java.io.*;
import java.net.*;

/**
 *
 * @author DELL
 */
public class Client {
    
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;
    private Socket serverConn;
    private String response;
    
    public Client(){
       
    }
    
    
    public void getStreams() throws IOException{
        out = new ObjectOutputStream(serverConn.getOutputStream());
        out.flush();
        in = new ObjectInputStream(serverConn.getInputStream());
    }
    
    public void communicateWihServer(){
         try{
            serverConn = new Socket("127.0.0.1", 6666);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void closeStreamAndConnection() throws IOException{
        out.close();
        in.close();
        serverConn.close();
    }
    
    
}
