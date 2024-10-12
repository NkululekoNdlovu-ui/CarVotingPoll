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
    
    public static Connection CreateConnection() throws SQLException{
        
        String url = "jdbc:derby://localhost:1527/ADP_Project_Assignment";
        String username = "Administractor";
        String password = "admin";
        
        con = DriverManager.getConnection(url,username,password);
        createTables();
        return con;
    }

    public static void createTables() throws SQLException{
        
        String sql = "CREATE TABLE CarVoting (Car VARCHAR(20) , Vote INT )";
        
        ps = con.prepareStatement(sql);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Table created ");
        
        
    }
    
    
    public void insertData() throws SQLException{
        
       String sql = "INSERT INTO CarVoting(Car , Vote ) Values('BMW',0) ,('Ford' , 0),('Toyota' , 0),('VW',0)";
       
       
       ps = con.prepareStatement(sql);
       ps.executeUpdate();
    }
    
    public int updateVotes(String carName, int newValue) throws SQLException{
        
        String sql = "UPDATE CarVoting Vote = ? WHERE Car = ? ";
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, newValue);
        ps.setString(2,carName);
       int changes =   ps.executeUpdate();
       
        if ( changes > 0) {
       
        String selectSql = "SELECT Vote FROM CarVoting WHERE Car = ?";
        
        ps = con.prepareStatement(selectSql);
        ps.setString(1, carName); 
        
        ResultSet rs = ps.executeQuery(); 
        
        if (rs.next()) {
           
            return rs.getInt("Vote");
        }
      
    }
    return -1;
      
    }
    
    public ArrayList Cars() throws SQLException{
        
        String sql = "Select * from Car ";
        ArrayList<WorkerClass> list = new ArrayList<>();
        ps = con.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            
            
         WorkerClass obj1 = new  WorkerClass(rs.getString("Car"),rs.getInt("Vote"));
         list.add(obj1);
        }
       return list;
    }
}