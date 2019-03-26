package br.com.pi.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;


public class ServidorWeb {
	
	private int codigo;
	private String status;
	private Timestamp dataAcesso;
	private ServerSocket servidor;
	private Socket cliente;

	
	public ServidorWeb(int codigo, String status, Timestamp dataAcesso) {
		this.codigo = codigo;
		this.status = status;
		this.dataAcesso = dataAcesso;
	}
	
	public ServidorWeb() {
	}
	
	
	public void iniciar() throws IOException {
        servidor = new ServerSocket(12345);

        cliente = servidor.accept();
        Scanner scanner = new Scanner(cliente.getInputStream());
        while (true) {
    
           BufferedReader in = new BufferedReader(
                           new InputStreamReader(cliente.getInputStream())
                          ); 
            PrintWriter out = new PrintWriter(
                         new BufferedWriter(
                            new OutputStreamWriter(cliente.getOutputStream())), 
                         true);   
            String s;
            while ((s = in.readLine()) != null) {
            	
            System.out.println(s);   
            
            out.write("\r\n");
            out.write("<TITLE>Teste</TITLE>");
            out.write("<P>Hello World</P>");

            System.err.println("Erro");
            out.close();
            in.close();
            cliente.close();
            }
        }
    }
    
    public void enviarMensagem(String mensagem) throws IOException {
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(mensagem);
    }
	
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	

	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Timestamp dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public String getStatus() {
		return status;
	}
	
	 public String isActive() {
		 // Metodo para implementar a ativação do servidor, próximo passo.
		// if(Connection conn != null) { // Metodo incorreto, não valido.
			 return "Ativo"; 
		// }
		// else {
		//	 return "Inativo";
		// }
		
	 }
	
	
	// Lista o valor de todas as ações uma a uma para gerar a lista.
	 public ArrayList<ServidorWeb> listarAcoes(Connection conn) throws SQLException{
	      String sqlSelect = 
	         "SELECT id,hora_data,acao FROM LogServidor";
	      ArrayList<ServidorWeb> lista = new ArrayList<>();
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);
	          ResultSet rs = stm.executeQuery();){
	         while(rs.next()){
	            ServidorWeb servidor = new ServidorWeb(codigo, status, dataAcesso);
	            servidor.setCodigo(rs.getInt("id"));
	            servidor.setDataAcesso(rs.getTimestamp("hora_data"));
	            servidor.setStatus(rs.getString("acao"));
	            lista.add(servidor);
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	      return lista;
	   }
	 

	 // Separa particularmente acao por acao para cada objeto do array.
	 public void carregaAcoes(Connection conn) throws SQLException{
	      String sqlSelect = 
	         "SELECT hora_data,acao FROM LogServidor WHERE id=?";
	      try(PreparedStatement stm = conn.prepareStatement(sqlSelect);){
	         stm.setInt(1, codigo);
	         try(ResultSet rs = stm.executeQuery();){
	            if(rs.next()){
	              dataAcesso = rs.getTimestamp("hora_data");
	              status     = rs.getString("acao");
	            } 
	            else {
	            	dataAcesso = null;
	            	status = null;
	            }
	         } 
	         catch (SQLException e){
	            e.printStackTrace();
	         }
	      } 
	      catch (SQLException e){
	         e.printStackTrace();
	      }
	   }
	 
	 public static void main(String[] args) throws IOException {

		        ServerSocket servidor = new ServerSocket(12345);
		        System.out.println("Porta 12345 aberta!");

		        Socket cliente = servidor.accept();
		        System.out.println("Nova conexão com o cliente " +     
		            cliente.getInetAddress().getHostAddress()
		        );

		        Scanner s = new Scanner(cliente.getInputStream());
		        while (s.hasNextLine()) {
		            System.out.println(s.nextLine());
		        }

		        s.close();
		        servidor.close();
		        cliente.close();
		    }
	
}
