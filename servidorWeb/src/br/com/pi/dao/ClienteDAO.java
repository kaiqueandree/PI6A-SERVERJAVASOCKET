package br.com.pi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pi.model.ClienteWeb;


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
	


}
