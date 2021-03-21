package com.cognizant.truyum.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler{
    public static Connection getConnection(){
        Connection con= null;
        try {
            con=DriverManager.getConnection(  
            		"jdbc:mysql://localhost:3306/employee","root","aravind6300@");  
            
       
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         return con;
    }
}