package br.com.pi.model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import br.com.pi.model.utils.Relatorio;
import br.com.pi.model.utils.RelatorioGenerator;
import br.com.pi.service.ClienteService;

public class ServidorWeb {

	private int codigo;
	private String status;
	private boolean ativo;

	private Timestamp dataAcesso;
	private ServerSocket servidor;
	private Socket cliente;
	private PrintWriter envia;
	private ClienteHandler handler;
	private ClienteWeb cliWeb;
	private ClienteService cliServ;
	private File file;
	private RelatorioGenerator relatorios;

	public ServidorWeb(int codigo, String status, Timestamp dataAcesso) {
		this.codigo = codigo;
		this.status = status;
		this.dataAcesso = dataAcesso;
	}

	TelaServidorWeb frame;

	public ServidorWeb(TelaServidorWeb frame) {
		this.frame = frame;
		file = new File("../../../../PI/");
	}
	
	

	public void iniciar() throws IOException {
		servidor = new ServerSocket(8080);
		System.out.println("Servidor Iniciado.");
		
		
	        
	        try {
	        	System.out.println(file.getCanonicalPath());
	        }
	        catch(IOException e) {}

	}

	public void clienteListener() throws IOException, SQLException {

		while (true) {
			try {
				cliente = servidor.accept();
				handler = new ClienteHandler(cliente);
				System.out.println("Conectado à " + cliente.getRemoteSocketAddress());
				OutputStream saida = cliente.getOutputStream();

			} catch (SocketException e) {
				System.out.println("Servidor parado!");
			} 
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintStream out = new PrintStream(new BufferedOutputStream(cliente.getOutputStream()));
			String request;
			relatorios = new RelatorioGenerator();
			
			request = in.readLine();
			try {
				

				
				
				
				System.out.println("Teste: " + request);
				

				String arquivo = "";
				
			StringTokenizer st = new StringTokenizer(request);
			
			if (st.hasMoreElements()
						&& (st.nextToken().equalsIgnoreCase("GET") || st.nextToken().equalsIgnoreCase("HEAD"))
						&& st.hasMoreElements()) {
					arquivo = st.nextToken();
				} else if (!st.nextToken().equalsIgnoreCase("GET") && !st.nextToken().equalsIgnoreCase("HEAD")) {

					out.println("HTTP/1.0 501 Not Implemented");
					out.println("Server: Java HTTP Server 1.0");
					out.println("Date: " + new Date());
					out.println("Content-Type: text/html");
					out.println();
					out.println("<HTML>");
					out.println("<HEAD><TITLE>Not Implemented</TITLE>" + "</HEAD>");
					out.println("<BODY>");
					out.println("<H2>501 Not Implemented: <H2>");
					out.println("</BODY></HTML>");
					out.flush();
					
					cliServ = new ClienteService();
					Date data = new Date();
					Timestamp ts=new Timestamp(data.getTime());
					String arq = request.substring(4);
					arq = arq.substring(0,arq.indexOf(" "));
					cliWeb =  new ClienteWeb(arq, "GET", "127.0.0.1", 501, ts);
					cliServ.inserir(cliWeb);
					
				} else {
					throw new FileNotFoundException();
				}

				System.out.println(st.nextToken());

				if (arquivo.endsWith("/")) {
					arquivo += "index.html";
					System.out.println("Página inicial");
				}
				

				while (arquivo.indexOf("/") == 0) {
					arquivo = arquivo.substring(1);

				}

				InputStream f = new FileInputStream(arquivo);
                 System.out.println("Arquivo: " + arquivo);
                 System.out.println("FileInputStream: " + f);
                 
				String type = "text/plain";
				if (arquivo.endsWith(".html") || arquivo.endsWith(".htm")) {
					type = "text/html";
					System.out.println("enviando texto");
				} else if (arquivo.endsWith(".css")) {
					type = "text/css";
					System.out.println("enviando css");
				}  else if (arquivo.endsWith(".js")) {
					type = "text/javascript";
					
				}

				else if (arquivo.endsWith(".jpg") || arquivo.endsWith(".jpeg")) {
					type = "image/jpeg";
					System.out.println("enviando jpeg");
				}
				
				else if (arquivo.endsWith(".png")) {
					type = "image/gif";
				}

				else if (arquivo.endsWith(".gif")) {
					type = "image/gif";
				}

				else if (arquivo.endsWith(".class")) {
					type = "application/octet-stream";
				} else {
					type = "text/plain";
				}
                String resp = "HTTP/1.1 200 OK\r\nContent-type: " + type + "\r\n\r\n";
				out.print(resp);
				System.out.println("\n"+resp);
				
				
				cliServ = new ClienteService();
				Date data = new Date();
				Timestamp ts=new Timestamp(data.getTime());
				String arq = request.substring(4);
				arq = arq.substring(0,arq.indexOf(" "));
				
				
				cliWeb =  new ClienteWeb(arq, "GET", "127.0.0.1", 200, ts);
				cliServ.inserir(cliWeb);
				
				int i;
				if(arquivo.contains("relatorio.html")) {
					
					byte[] b = new byte[4096];
					System.out.println("Teste byte...");
					InputStream stream = new ByteArrayInputStream(relatorios.GenerateFile().getBytes(StandardCharsets.UTF_8));
					while ((i = stream.read(b)) > 0) {
						out.write(b, 0, i);
					}
					out.close();
					} else {
				byte[] a = new byte[4096];
				System.out.println("Teste byte...");

				while ((i = f.read(a)) > 0) {
					out.write(a, 0, i);
				}

				out.close();
				}
			}
			

			catch (FileNotFoundException x) {

				out.println("HTTP/1.1 404 Not Found\r\n" + "Content-type: text/html\r\n\r\n" + "<html>" + "<head>"
						+ "</head>"
						+ "<body><img src=" + file.getCanonicalPath() + "\404.jpg\" style=\"width:500px; height:400px\" title=\"error_404\" alt=\"error_404\">"
						+ "</body></html>\n");
				
				cliServ = new ClienteService();
				Date data = new Date();
				Timestamp ts=new Timestamp(data.getTime());
				String arq = request.substring(4);
				arq = arq.substring(0,arq.indexOf(" "));
				
				
				cliWeb =  new ClienteWeb(arq, "GET", "127.0.0.1", 200, ts);
				cliServ.inserir(cliWeb);
				
				out.close();
			}
			catch(NullPointerException ex) {
				System.out.println("Sem apontamento válido.");
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

	// Lista o valor de todas as acoes uma a uma para gerar a lista.
	public ArrayList<ServidorWeb> listarAcoes(Connection conn) throws SQLException {
		String sqlSelect = "SELECT id,hora_data,acao FROM LogServidor order by hora_data";
		ArrayList<ServidorWeb> lista = new ArrayList<>();
		try (PreparedStatement stm = conn.prepareStatement(sqlSelect); ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				ServidorWeb servidor = new ServidorWeb(codigo, status, dataAcesso);
				servidor.setCodigo(rs.getInt("id"));
				servidor.setDataAcesso(rs.getTimestamp("hora_data"));
				servidor.setStatus(rs.getString("acao"));
				lista.add(servidor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Separa particularmente acao por acao para cada objeto do array.
	public void carregaAcoes(Connection conn) throws SQLException {
		String sqlSelect = "SELECT hora_data,acao FROM LogServidor WHERE id=?";
		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, codigo);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					dataAcesso = rs.getTimestamp("hora_data");
					status = rs.getString("acao");
				} else {
					dataAcesso = null;
					status = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void parar() throws IOException {

		servidor.close();
		cliente = null;
		servidor = null;

	}
	
	public void reiniciar() throws IOException, InterruptedException {

	     parar();
	     Thread.sleep(10000);
	     iniciar();
	}
	
	public String getIpAddr(Socket cli) {
		InetSocketAddress sockaddr = (InetSocketAddress)cli.getRemoteSocketAddress();
		InetAddress inaddr = sockaddr.getAddress();
		
		Inet4Address in4addr = (Inet4Address)inaddr;
		byte[] ip4bytes = in4addr.getAddress();
		return in4addr.toString();
	}

}
