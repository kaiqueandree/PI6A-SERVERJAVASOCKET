package br.com.pi.model.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class RelatorioAcesso extends RelatorioConsulta {

	ArrayList<LogAcesso> listaArq;
	ClienteService cliente;
	String canvas;
	int total;
	

	
	@Override
	public String consultarDB() throws SQLException {
		
		cliente = new ClienteService();
		listaArq = cliente.nRequisicoes("arquivo");
		String canvas = "";
		
		int total = 0;
		// Gera o top 10 valores de arquivos mais acessados pelo servidor. Gráfico esperado: Barras
		for(LogAcesso logAcesso: listaArq) {
		 total += logAcesso.getContador();
		}
			
		for(int i = 0; i<10; i++) {
			LogAcesso log = listaArq.get(i);
			float valor = (log.getContador()*100)/total;
			canvas += "\n	       { y: " + valor + " , label: \"" +  log.getAtributo()  +" "+ valor + "%\" "+ "},";
		}
		
		
		return canvas;
	}
	
	public static void main(String[] args) throws SQLException {
		
		ClienteService cliente = new ClienteService();
		ArrayList<LogAcesso> listaArq = cliente.nRequisicoes("arquivo");
		String canvas = "";
		int total = 0;
		
		// Gera o top 10 valores de arquivos mais acessados pelo servidor. Gráfico esperado: Barras
				for(LogAcesso logAcesso: listaArq) {
				 total += logAcesso.getContador();
				}
					
				for(int i = 0; i<10; i++) {
					LogAcesso log = listaArq.get(i);
					canvas += "\n	       { y: " + log.getContador() + " , label: "+ "\"" +  log.getAtributo()  + "\"" + ", indexLabel: \" \" },";
				}
				System.out.println(canvas);
	}
}
