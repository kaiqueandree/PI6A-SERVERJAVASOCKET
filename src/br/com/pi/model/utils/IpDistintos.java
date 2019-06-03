package br.com.pi.model.utils;

	import java.sql.SQLException;
	import java.util.ArrayList;

	import br.com.pi.model.LogAcesso;
	import br.com.pi.service.ClienteService;

	public class IpDistintos extends RelatorioAcesso{

		ArrayList<LogAcesso> ipDistintos;
		ClienteService cliente;
		String canvas;
		

		
		
		@Override
		public String consultarDB() throws SQLException {
            
			ClienteService cliente = new ClienteService();
			ArrayList<LogAcesso> ipDistintos = cliente.ipsDistintos();
			String canvas = "";
			
			for(LogAcesso log: ipDistintos) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
				System.out.println(log.getAtributo());
			}
			
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ClienteService cliente = new ClienteService();
			ArrayList<LogAcesso> ipDistintos = cliente.ipsDistintos();
			String canvas = "";
		
			for(LogAcesso log: ipDistintos) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			
			}
			System.out.println(canvas);
		}
	}