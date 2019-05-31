package br.com.pi.model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class RelatorioAcesso extends Relatorio {
	

	ClienteService cliente;
	String canvas;
	
	@Override
	public void consulta() {
	
	
	}
	
	public void GenerateFile() {
		
		try {
			
			cliente = new ClienteService();
			ArrayList<LogAcesso> lista = cliente.nRequisicoes("arquivo");
			String canvas = "";
			
			
			for(LogAcesso log: lista) {
				canvas += "{ axisx: " + log.getAtributo() + " , axisy: " + log.getContador() + " }\n";
			}
			System.out.println(canvas);
			//FileInputStream fs = new FileInputStream("\\PI\\html\\relatorios\\arquivos-mais-acessados.html");
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

	}
	
	public static void main(String[] args) throws IOException, SQLException {

		
           
			
			ClienteService cliente = new ClienteService();
			ArrayList<LogAcesso> lista = cliente.nRequisicoes("arquivo");
			String canvas = "";
		
			for(LogAcesso log: lista) {
				canvas += "{ axisX: " + log.getAtributo() + " , axisY: " + log.getContador() + " }\n";
			}
			//System.out.println(canvas);
			//FileInputStream fs = new FileInputStream("\\PI\\html\\relatorios\\arquivos-mais-acessados.html");
			
			

		FileInputStream fs = new FileInputStream("C:\\Users\\A685503\\eclipse-workspace\\servidorWeb\\PI\\html\\relatorios\\arquivos-mais-acessados.html");
		int i = 0;
		String response = "";
		
		 while((i=fs.read())!=-1){    
            response += (char)i;    
            }    
		 String replace = response.replaceAll("<!--Dados-pizza-->", canvas);
	      System.out.println(replace);
         fs.close(); 
         byte[] b = replace.getBytes();
         //System.out.println("Array " + b);
    
	}

}
