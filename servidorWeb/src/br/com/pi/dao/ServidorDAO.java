package br.com.pi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.model.ServidorWeb;


public class ServidorDAO {
	
	Connection conn;
	
	public ServidorDAO() throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		conn = cf.getConnection();
	
	}
	

	public void inserir(ServidorWeb serv) throws SQLException{
		
		  
	      String sqlInsert = 
	         "INSERT INTO LOGSERVIDOR (hora_data, acao) VALUES (?,?)";
	      try(PreparedStatement stm = conn.prepareStatement(sqlInsert);){
	         stm.setTimestamp(1, serv.getDataAcesso());
	         stm.setString(2, serv.getStatus());
	         stm.execute();
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	         conn.rollback();
	      }
	   }
	
		
	
}
