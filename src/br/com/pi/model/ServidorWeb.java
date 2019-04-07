package br.com.pi.model;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import java.util.StringTokenizer;




public class ServidorWeb {
	
	private int codigo;
	private String status;
	private boolean ativo;
	
	private Timestamp dataAcesso;
	private ServerSocket servidor;
	private Socket cliente;
	PrintWriter envia;
	
	public ServidorWeb(int codigo, String status, Timestamp dataAcesso) {
		this.codigo = codigo;
		this.status = status;
		this.dataAcesso = dataAcesso;
	}
	
	TelaServidorWeb frame;
	
	public ServidorWeb(TelaServidorWeb frame) {
		this.frame = frame;
	}
	
public void iniciar() throws IOException {
    	servidor = new ServerSocket(12345);
    	System.out.println("Servidor Iniciado.");
   
	}	

public void clienteListener() throws IOException {
	
	while(true) {
        cliente = servidor.accept();
	    System.out.println("Connected to " + cliente.getRemoteSocketAddress());
	    
	    OutputStream saida = cliente.getOutputStream();
	    BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
	    PrintStream out = new PrintStream(new BufferedOutputStream(cliente.getOutputStream()));

	    String request=in.readLine();
	    System.out.println(request);
    
	    String arquivo = "";
	    StringTokenizer st = new StringTokenizer(request);
	    
	    try {

	         if (st.hasMoreElements() && st.nextToken().equalsIgnoreCase("GET") && st.hasMoreElements()) {
	        	 arquivo=st.nextToken();
	         }
	    	 else {
	    	          throw new FileNotFoundException();
	    	        }
	    	        // Append trailing "/" with "index.html"
	    	        if (arquivo.endsWith("/")) {
	    	        	arquivo+="index.html";
	    	        	System.out.println("Página Inicial");
	    	        }
	    	        
	    	       
	    	        while (arquivo.indexOf("/") == 0) {
	    	        	arquivo = arquivo.substring(1);
	    	        	
	    	        }

	    	        InputStream f = new FileInputStream(arquivo);

	    	        String type="text/plain";
	    	        if (arquivo.endsWith(".html") || arquivo.endsWith(".htm")) {
	    	        	type = "text/html";
	    	        }
	    	          
	    	        else if (arquivo.endsWith(".jpg") || arquivo.endsWith(".jpeg")) {
	    	        	type = "image/jpeg";
	    	        }
	    	          
	    	        else if (arquivo.endsWith(".gif")) {
	    	        	type = "image/gif";
	    	        }
	    	        	
	    	        else if (arquivo.endsWith(".class")) {
	    	        	type = "application/octet-stream";
	    	        }
	    	        	
	    	        out.print("HTTP/1.0 200 OK\r\nContent-type: "+type+"\r\n\r\n");

	    	        byte[] a = new byte[4096];
	    	        int i;
	    	        while ((i = f.read(a)) > 0) {
		    	          out.write(a, 0, i);
	    	        }
	    	        
		    	    out.close();
		    	      }
	    
	    	      catch (FileNotFoundException x) {
	    	    	  
	    	        out.println("HTTP/1.0 404 Not Found\r\n"+
	    	          "Content-type: text/html\r\n\r\n"+
	    	          "<html>"
	    	          + "<head>"
	    	          + "</head>"
	    	          + "<body><img src=\"404.jpg\" style=\"width:500px; height:400px\" title=\"error_404\" alt=\"error_404\">"+"</body></html>\n");
	    	        out.close();
	}    
	}
	}
	


public void enviarMensagem(String Data) {
	try {
		
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		saida.println(Data);
		
		System.out.println(Data);
		
	} catch (IOException e) {
		e.printStackTrace();
	}  
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
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public void parar() throws IOException {
		
				//envia.close();
				//out.close();
				//cliente.close();
		        //servidor.close();
		        //cliente = null;
		        //servidor = null;
				

	}
	
}
