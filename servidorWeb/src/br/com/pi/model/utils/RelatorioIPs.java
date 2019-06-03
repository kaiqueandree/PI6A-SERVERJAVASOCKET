package br.com.pi.model.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class RelatorioIPs extends RelatorioConsulta{

	ArrayList<LogAcesso> listaIps;
	ClienteService cliente;
	String canvas;
	
	public RelatorioIPs() throws SQLException {
	cliente = new ClienteService();
	listaIps = cliente.IpsAcesso();
	}
	
	
	@Override
	public String consultarDB() {

		String canvas = "";
		for(LogAcesso log: listaIps) {
			canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
		}
		
		return canvas;
	}
	
	public static void main(String[] args) throws SQLException {
		
		ClienteService cliente = new ClienteService();
		ArrayList<LogAcesso> listaIps = cliente.IpsAcesso();
		String canvas = "";
		
		for(LogAcesso log: listaIps) {
			canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
		}
		System.out.println(canvas);
	}
}
