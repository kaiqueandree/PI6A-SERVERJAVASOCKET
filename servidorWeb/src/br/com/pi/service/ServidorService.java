package br.com.pi.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.dao.ServidorDAO;
import br.com.pi.model.LogAcesso;
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
		
		public ArrayList<LogAcesso> acoesServidor() throws SQLException {
			return objectDAO.acoesServidor();
		}
		
		public ArrayList<LogAcesso> horaPicoAcesso() throws SQLException {
			return objectDAO.horaPicoAcesso();
		}
		
		public ArrayList<LogAcesso> requesicaoPorDia() throws SQLException {
			return objectDAO.requesicaoPorDia();
		}
		
		public ArrayList<LogAcesso> requesicaoPorMes() throws SQLException {
			return objectDAO.requesicaoPorMes();
		}

}
