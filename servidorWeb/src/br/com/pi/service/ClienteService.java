package br.com.pi.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.model.ClienteWeb;
import br.com.pi.model.LogAcesso;


public class ClienteService {

	ClienteDAO objectDAO;
	
	public ClienteService() throws SQLException {
		 ClienteDAO objectDAO = new ClienteDAO();
		   this.objectDAO = objectDAO;
	   }
		
		public void inserir(ClienteWeb serv) throws SQLException {
			objectDAO.inserir(serv);
		}
		
		public ArrayList<LogAcesso> nRequisicoes(String req) throws SQLException {
			return objectDAO.nRequisicoes(req);
		}
		
		public ArrayList<LogAcesso> nRequisicoesData(String req) throws SQLException {
			return objectDAO.nRequisicoes(req);
		}
	
	
}
