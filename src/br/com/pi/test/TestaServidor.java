package br.com.pi.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.pi.dao.ConnectionFactory;
import br.com.pi.model.TelaServidorWeb;



public class TestaServidor {
	
	public static void main(String[] args) {
		
		 ConnectionFactory cf = new ConnectionFactory();
		 
		try {
	        Connection conn = cf.getConnection();
			@SuppressWarnings("unused")
			TelaServidorWeb tela =  new TelaServidorWeb(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
