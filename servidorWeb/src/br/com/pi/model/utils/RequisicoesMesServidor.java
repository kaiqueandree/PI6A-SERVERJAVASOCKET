 package br.com.pi.model.utils;

	import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ServidorService;

	public class RequisicoesMesServidor extends RelatorioAcesso{

		ArrayList<LogAcesso> reqMes;
		ServidorService servidor;
		String canvas;
		
	
		
		
		@Override
		public String consultarDB() throws SQLException {
			
			servidor = new ServidorService();
			 reqMes = servidor.requesicaoPorMes();
			 String canvas = "";
				
			for(LogAcesso log: reqMes) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ServidorService servidor = new ServidorService();
			ArrayList<LogAcesso> reqMes = servidor.requesicaoPorMes();
			String canvas = "";
		
			for(LogAcesso log: reqMes) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			System.out.println(canvas);
		}
	}



