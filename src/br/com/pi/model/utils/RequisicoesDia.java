 package br.com.pi.model.utils;

	import java.sql.SQLException;
	import java.util.ArrayList;

	import br.com.pi.model.LogAcesso;
	import br.com.pi.service.ClienteService;

	public class RequisicoesDia extends RelatorioAcesso{

		ArrayList<LogAcesso> reqDia;
		ClienteService cliente;
		String canvas;
		
		
		
		@Override
		public String consultarDB() throws SQLException {

			cliente = new ClienteService();
			reqDia = cliente.requesicaoPorDia();	
			String canvas = "";
			
			for(LogAcesso log: reqDia) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			
			return canvas;
		}
		
		public static void main(String[] args) throws SQLException {
			
			ClienteService cliente = new ClienteService();
			ArrayList<LogAcesso> reqDia = cliente.requesicaoPorDia();
			String canvas = "";
		
			for(LogAcesso log: reqDia) {
				canvas += "	      { y: " + log.getContador() + ", name: \""  + log.getAtributo() + "\"" + ", indexLabel: \"" +  log.getAtributo()  + "\"," + " legendText: \"" + log.getAtributo() +  "\"},\n";
			}
			System.out.println(canvas);
		}
	}



