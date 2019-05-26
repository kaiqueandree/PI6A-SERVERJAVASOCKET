package br.com.pi.service;

import java.sql.SQLException;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.model.ClienteWeb;


public class ClienteService {

	ClienteDAO objectDAO;
	
	public ClienteService() throws SQLException {
		 ClienteDAO objectDAO = new ClienteDAO();
		   this.objectDAO = objectDAO;
	   }
		
		public void inserir(ClienteWeb serv) throws SQLException {
			objectDAO.inserir(serv);
		}
	
	
}
