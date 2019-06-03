package br.com.pi.model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ClienteService;

public class RelatorioGenerator extends Relatorio{
	

	public RelatorioGenerator() throws SQLException {
		super();
	}

	AcoesServidor 			acoes;
	FileNotFoundRequisicoes fnf;
	IpDistintos 			ipDistintos;
	PicoAcessoServidor 		picoServidor;
	RelatorioAcesso 		relAcesso;
	RelatorioPico 			picoCliente;
	RelatorioIPs 			relIps;
	RequisicoesDia			reqDiaCliente;
	RequisicoesMes			reqMesCliente;
	RequisicoesDiaServidor	reqDiaServidor;
	RequisicoesMesServidor	reqMesServidor;
	StatusCodeRequisicao    statusCode;
	
	

	
	
	public String GenerateFile() throws SQLException, IOException {
				
		FileInputStream fs = new FileInputStream("C:\\Users\\A685503\\eclipse-workspace\\servidorWeb\\PI\\html\\relatorio.html");
		int i = 0;
		String response = "";
		
		acoes            = new AcoesServidor();
		fnf              = new FileNotFoundRequisicoes();
		ipDistintos      = new IpDistintos();
		picoServidor     = new PicoAcessoServidor();
		relAcesso        = new RelatorioAcesso();
		picoCliente      = new RelatorioPico();
		relIps           = new RelatorioIPs();
		reqDiaCliente    = new RequisicoesDia();
		reqMesCliente    = new RequisicoesMes();
		reqDiaServidor   = new RequisicoesDiaServidor();
		reqMesServidor   = new RequisicoesMesServidor();
		statusCode       = new StatusCodeRequisicao();
		
		 while((i=fs.read())!=-1){    
            response += (char)i;    
            }

		    String replace =  response.replaceAll("<!--Acoes-->", 		   	    relAcesso.consultarDB());
	 	    replace = replace.replaceAll("<!--Pico-cliente-->",   			picoCliente.consultarDB());
	 	    replace = replace.replaceAll("<!--IP-Requisicoes-->",           relIps.consultarDB());
	 	    replace = replace.replaceAll("<!--Erro-404-->", 	            fnf.consultarDB());
	 	    replace = replace.replaceAll("<!--Total-Req-Cliente-->",        reqDiaCliente.consultarDB());
	 	    replace = replace.replaceAll("<!--total-Mes-Cliente-->", 		reqMesCliente.consultarDB());
	 	    replace = replace.replaceAll("<!--Ips--Distintos-->", 			ipDistintos.consultarDB());
	 	    replace = replace.replaceAll("<!--eventos-Servidor-->", 	    acoes.consultarDB());
	 	    replace = replace.replaceAll("<!--status-Codes-->", 			statusCode.consultarDB());
	 	    replace = replace.replaceAll("<!--pico-Servidor-->", 			picoServidor.consultarDB());
	 	    replace = replace.replaceAll("<!--Total-acoes-dia-->",          reqDiaServidor.consultarDB());
	 	    replace = replace.replaceAll("<!--total-Acoes-Mes-->", 		 	reqMesServidor.consultarDB());
	 	    System.out.println(replace);
	 	    fs.close(); 
  
	 	    byte[] b = replace.getBytes();
            System.out.println(replace);
	 	    return replace;

	}
	
	public static void main(String[] args) throws IOException, SQLException {

//	    AcoesServidor 			acoes          = new AcoesServidor();
//		FileNotFoundRequisicoes fnf            = new FileNotFoundRequisicoes();
//		IpDistintos 			ipDistintos    = new IpDistintos();
//		PicoAcessoServidor 		picoServidor   = new PicoAcessoServidor();
//		RelatorioAcesso 		relAcesso      = new RelatorioAcesso();
//		RelatorioPico 			picoCliente    = new RelatorioPico();
//		RelatorioIPs 			relIps         = new RelatorioIPs();
//		RequisicoesDia			reqDiaCliente  = new RequisicoesDia();
//		RequisicoesMes			reqMesCliente  = new RequisicoesMes();
//		RequisicoesDiaServidor	reqDiaServidor = new RequisicoesDiaServidor();
//		RequisicoesMesServidor	reqMesServidor = new RequisicoesMesServidor();
//		StatusCodeRequisicao    statusCode     = new StatusCodeRequisicao();
//           
//		FileInputStream fs = new FileInputStream("C:\\Users\\A685503\\eclipse-workspace\\servidorWeb\\PI\\html\\relatorios\\relatorio_teste.html");
//		int i = 0;
//		String response = "";
//		
//		 while((i=fs.read())!=-1){    
//            response += (char)i;    
//            }
//		 
//		 
//		 
//		 String replace =  response.replaceAll("<!--Acoes-->", 		   			 relAcesso.consultarDB());
//		 	    replace = replace.replaceAll("<!--Pico-cliente-->",   			 picoCliente.consultarDB());
//		 	    replace = replace.replaceAll("<!--IP-Requisicoes-->",           relIps.consultarDB());
//		 	    replace = replace.replaceAll("<!--Erro-404-->", 	             fnf.consultarDB());
//		 	    replace = replace.replaceAll("<!--Total-Req-Cliente-->",        reqDiaCliente.consultarDB());
//		 	    replace = replace.replaceAll("<!--total-Mes-Cliente-->", 		 reqMesCliente.consultarDB());
//		 	    replace = replace.replaceAll("<!--Ips--Distintos-->", 			 ipDistintos.consultarDB());
//		 	    replace = replace.replaceAll("<!--eventos-Servidor-->", 	     acoes.consultarDB());
//		 	    replace = replace.replaceAll("<!--status-Codes-->", 			 statusCode.consultarDB());
//		 	    replace = replace.replaceAll("<!--pico-Servidor-->", 			 picoServidor.consultarDB());
//		 	    replace = replace.replaceAll("<!--Total-acoes-dia-->",          reqDiaServidor.consultarDB());
//		 	    replace = replace.replaceAll("<!--total-Acoes-Mes-->", 		 reqMesServidor.consultarDB());
//	     System.out.println(replace);
//         fs.close(); 
//         
//         byte[] b = replace.getBytes();]
		RelatorioGenerator rel = new RelatorioGenerator();
		System.out.println(rel.GenerateFile());
	}
}
