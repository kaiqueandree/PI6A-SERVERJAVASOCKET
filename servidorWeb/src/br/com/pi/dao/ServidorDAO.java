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
	
	public ArrayList<LogAcesso> acoesServidor() throws SQLException{
	      String sqlSelect = 
	         "select count(1) conta, acao from logservidor group by acao;";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("acao"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	
	public ArrayList<LogAcesso> horaPicoAcesso() throws SQLException{
	      String sqlSelect = 
	         "select count(1) conta, DATE_FORMAT(hora_data , '%d/%m/%y %H:00:00') data from logservidor group by data;";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("data"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	
	public ArrayList<LogAcesso> requesicaoPorDia() throws SQLException{
	      String sqlSelect = 
	         "select count(1) conta, DATE_FORMAT(hora_data , '%d/%m/%y') data from logservidor group by data;";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("data"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	
	public ArrayList<LogAcesso> requesicaoPorMes() throws SQLException{
	      String sqlSelect = 
	         "select count(1) conta, DATE_FORMAT(hora_data , '%m/%y') data from logservidor group by data;";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("data"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	
}
