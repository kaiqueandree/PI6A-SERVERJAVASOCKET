package br.com.pi.model.utils;

import java.sql.SQLException;

public abstract class RelatorioConsulta {

	public RelatorioConsulta() {
		
	}
	
	public abstract String consultarDB() throws SQLException;
}
