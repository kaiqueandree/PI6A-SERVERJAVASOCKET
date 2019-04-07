package br.com.pi.service;

import java.sql.SQLException;

import br.com.pi.dao.ServidorDAO;
import br.com.pi.model.ServidorWeb;


public class ServidorService {

	ServidorDAO objectDAO;
	
	public ServidorService() throws SQLException {
		 ServidorDAO objectDAO = new ServidorDAO();
		   this.objectDAO = objectDAO;
	   }
		
		public void inserir(ServidorWeb serv) throws SQLException {
			objectDAO.inserir(serv);
		}
	
	
}
