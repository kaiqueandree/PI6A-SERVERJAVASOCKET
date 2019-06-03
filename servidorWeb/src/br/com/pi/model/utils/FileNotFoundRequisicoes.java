package br.com.pi.model.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class FileNotFoundRequisicoes extends RelatorioAcesso{

	ArrayList<LogAcesso> fileNotFound;
	ClienteService cliente;
	String canvas;
	
	
	
	
	@Override
	public String consultarDB() throws SQLException {
         
		cliente = new ClienteService();
		ArrayList<LogAcesso> fileNotFound = cliente.nRequisicoes404();
		String canvas = "";
		
		for(int i = 0; i<5; i++) {
			LogAcesso log = fileNotFound.get(i);
			canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
		}
		
		return canvas;
	}
	
	public static void main(String[] args) throws SQLException {
		
		ClienteService cliente = new ClienteService();
		ArrayList<LogAcesso> fileNotFound = cliente.nRequisicoes404();
		String canvas = "";
	
		for(int i = 0; i<5; i++) {
			LogAcesso log = fileNotFound.get(i);
			canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
		}
		System.out.println(canvas);
	}
}
