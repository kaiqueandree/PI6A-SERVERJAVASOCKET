package br.com.model.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class RelatorioRequests extends Relatorio {
	

	ClienteService cliente;
	String canvas;
	
	@Override
	public void consulta(String request) {
		try {
			ArrayList<LogAcesso> lista = cliente.nRequisicoes(request);
			
			for(LogAcesso log : lista) {
				canvas+=  "{ label: '" + log.getAtributo() + "' , y: " + log.getContador() + " },"; 
				System.out.println(canvas);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
	}

	@Override
	public void consultaData(String request) {
		try {
			ArrayList<LogAcesso> lista = cliente.nRequisicoes(request);
			
			for(LogAcesso log : lista) {
				canvas+=  "{ label: '" + log.getAtributo() + "' , y: " + log.getContador() + " },"; 
				System.out.println(canvas);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
	}
}
