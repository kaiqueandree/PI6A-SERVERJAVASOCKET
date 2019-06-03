 package br.com.pi.model.utils;

	import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ServidorService;

	public class RequisicoesDiaServidor extends RelatorioAcesso{

		ArrayList<LogAcesso> reqDia;
		ServidorService servidor;
		String canvas;
		

		@Override
		public String consultarDB() throws SQLException {
           
			 servidor = new ServidorService();
			 reqDia = servidor.requesicaoPorDia();
			 String canvas = "";	
			 
			for(LogAcesso log: reqDia) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ServidorService servidor = new ServidorService();
			ArrayList<LogAcesso> reqDia = servidor.requesicaoPorDia();
			String canvas = "";
		
			for(LogAcesso log: reqDia) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			System.out.println(canvas);
		}
	}



