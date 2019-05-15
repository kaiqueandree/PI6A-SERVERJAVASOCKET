package br.com.pi.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory{
   static{
      try{
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e){
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }
   
   public Connection getConnection() throws SQLException{
      return DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/pi6a?user=root&"
         + "password=Copa2022!");
   } 
   

}