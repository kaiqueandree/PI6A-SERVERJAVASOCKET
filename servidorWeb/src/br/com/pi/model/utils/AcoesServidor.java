package br.com.pi.model.utils;

	import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ServidorService;

	public class AcoesServidor extends RelatorioAcesso{

		ArrayList<LogAcesso> acoesServidor;
		ServidorService servidor;
		String canvas;
		
		public AcoesServidor() throws SQLException {
		servidor = new ServidorService();
		ArrayList<LogAcesso> acoesServidor = servidor.acoesServidor();
		}
		
		
		@Override
		public String consultarDB() throws SQLException {
             
			ServidorService servidor = new ServidorService();
			ArrayList<LogAcesso> acoesServidor = servidor.acoesServidor();
			String canvas = "";
				
			for(LogAcesso log: acoesServidor) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
				System.out.println(log.getAtributo());
			}
			
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ServidorService servidor = new ServidorService();
			ArrayList<LogAcesso> acoesServidor = servidor.acoesServidor();
			String canvas = "";
		
			for(LogAcesso log: acoesServidor) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			
			}
			System.out.println(canvas);
		}
	}