package br.com.pi.model.utils;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Relatorio {

	RelatorioGenerator relGerador;
	
	public Relatorio() throws SQLException {
		relGerador = new RelatorioGenerator();
	}
	public byte[] consulta() throws SQLException, IOException {

	return relGerador.GenerateFile();	 
	}
	
	
}
