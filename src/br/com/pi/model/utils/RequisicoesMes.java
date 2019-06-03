package br.com.pi.model.utils;

	import java.sql.SQLException;
	import java.util.ArrayList;

	import br.com.pi.model.LogAcesso;
	import br.com.pi.service.ClienteService;

	public class RequisicoesMes extends RelatorioAcesso{

		ArrayList<LogAcesso> reqMes;
		ClienteService cliente;
		String canvas;
		
	
		
		@Override
		public String consultarDB() throws SQLException {
			cliente = new ClienteService();
			reqMes = cliente.requesicaoPorMes();
			String canvas = "";
				
			for(LogAcesso log: reqMes) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
				System.out.println(log.getAtributo());
			}
			
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ClienteService cliente = new ClienteService();
			ArrayList<LogAcesso> reqMes = cliente.requesicaoPorMes();
			String canvas = "";
		
			for(LogAcesso log: reqMes) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			
			}
			System.out.println(canvas);
		}
	}