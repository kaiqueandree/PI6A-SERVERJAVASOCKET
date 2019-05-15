package br.com.pi.model;

import java.sql.Timestamp;

public class ClienteWeb {

	private String arquivo;
	private String arquivoHttp;
	private String ip;
	private int codigoResposta;
	private Timestamp horaData;
	
	public ClienteWeb (String arquivo, String arquivoHttp, String ip, int codigoResposta, Timestamp horaData) {
		this.arquivo = arquivo;
		this.arquivoHttp = arquivoHttp;
		this.ip = ip;
		this.codigoResposta = codigoResposta;
		this.horaData = horaData;
	}
	
	public ClienteWeb () {
		
	}
		

	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	public String getArquivoHttp() {
		return arquivoHttp;
	}
	public void setArquivoHttp(String arquivoHttp) {
		this.arquivoHttp = arquivoHttp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCodigoResposta() {
		return codigoResposta;
	}
	public void setCodigoResposta(int codigoResposta) {
		this.codigoResposta = codigoResposta;
	}
	public Timestamp getHoraData() {
		return horaData;
	}
	public void setHoraData(Timestamp horaData) {
		this.horaData = horaData;
	}
	

	
	
	
}
