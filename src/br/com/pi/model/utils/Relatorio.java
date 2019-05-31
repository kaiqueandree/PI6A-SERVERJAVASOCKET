package br.com.pi.model.utils;

import java.util.ArrayList;

import br.com.pi.model.LogAcesso;

public abstract class Relatorio {

	RelatorioAcesso relAcesso;
	
	public Relatorio() {
		relAcesso = new RelatorioAcesso();
	}
	public void consulta() {
        relAcesso.consulta();
       
	}
	
	
}
