package br.com.pi.model.utils;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Relatorio {

	RelatorioGenerator relGerador;
	
	public Relatorio() throws SQLException {
		
	}
	public  String consulta() throws SQLException, IOException 
	{
		return null;
	}

	
	
}
