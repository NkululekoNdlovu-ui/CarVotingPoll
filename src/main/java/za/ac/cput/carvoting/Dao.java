///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package za.ac.cput.carvoting;

import java.sql.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author hloni
 */
public class Dao {
     private static Connection con;
    private static PreparedStatement ps;

    public Dao() {
    }
    
    public static Connection CreateConnection() throws SQLException{
        
        String url = "jdbc:derby://localhost:1527/ADP_Project_Assignment";
        String username = "Administractor";
        String password = "admin";
        
        con = DriverManager.getConnection(url,username,password);

        
        return con;
    }

 
    public static void insertData() throws SQLException{
        
       String sql = "INSERT INTO CarVoting(Car , Vote ) Values('BMW',0) ,('Ford' , 0),('Toyota' , 0),('VW',0)";
      
       
       ps = con.prepareStatement(sql);
       ps.executeUpdate();
    }
    
    public int updateVotes(String carName, int newValue) throws SQLException{
        
        String sql = "UPDATE CarVoting SET  Vote = vote + 1 WHERE Car = ? ";
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, newValue);
        ps.setString(2,carName);
       int changes =   ps.executeUpdate();
       
        if ( changes < 1) {
         return 0;

      
    }
    return 1;
      
    }
    
    public ArrayList getCarVotes() throws SQLException{
        
        String sql = "Select * from carvoting ";
        ArrayList<VotingPole> list = new ArrayList<>();
        ps = con.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            
            
         VotingPole obj1 = new  VotingPole(rs.getString("Car"),rs.getInt("Vote"));
         list.add(obj1);
            System.out.println(obj1);
        }
       return list;
    }
}