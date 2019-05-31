package br.com.pi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.ClienteWeb;
import br.com.pi.model.LogAcesso;


public class ClienteDAO {
	
	Connection conn;
	
	public ClienteDAO() throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		conn = cf.getConnection();
	
	}
	

	public void inserir(ClienteWeb cli) throws SQLException{
		
		  
	      String sqlInsert = 
	         "INSERT INTO LOGACESSO (arquivo, metodo_http, ip, codigo_resposta, hora_data) VALUES (?,?,?,?,?)";
	      try(PreparedStatement stm = conn.prepareStatement(sqlInsert);){
	         stm.setString(1, cli.getArquivo());
	         stm.setString(2, cli.getArquivoHttp());
	         stm.setString(3, cli.getIp());
	         stm.setInt(4, cli.getCodigoResposta());
	         stm.setTimestamp(5, cli.getHoraData());
	         stm.execute();
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	         conn.rollback();
	      }
	   }
	
	public ArrayList<LogAcesso> nRequisicoes(String req) throws SQLException{
	      String sqlSelect = 
	         "SELECT count(1) conta," + req + " atributo FROM LOGACESSO GROUP BY " + req;
	      
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("atributo"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	
	public ArrayList<LogAcesso> nRequisicoesData(String req) throws SQLException{
	      String sqlSelect = 
	         " SELECT count(1) conta, " + req + " atributo, month(hora_data) mes, year(hora_data) ano FROM LOGACESSO GROUP BY atributo";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("atributo"));
	        	logserv.setMes(rs.getString("mes"));
	        	logserv.setAno(rs.getString("ano"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }

	public ArrayList<LogAcesso> nRequisicoes404() throws SQLException{
	      String sqlSelect = 
	         " SELECT count(1) conta, arquivo FROM LOGACESSO where codigo_resposta = 404 and arquivo != '/PI' GROUP BY arquivo";
	      ArrayList<LogAcesso> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);  
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	        	 LogAcesso logserv = new LogAcesso();
	        	logserv.setContador(rs.getInt("conta"));
	        	logserv.setAtributo(rs.getString("arquivo"));
	            lista.add(logserv);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }

}
