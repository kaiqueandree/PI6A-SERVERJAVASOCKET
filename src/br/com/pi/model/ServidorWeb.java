package br.com.pi.model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;

public class ServidorWeb {

	private int codigo;
	private String status;
	private boolean ativo;

	private Timestamp dataAcesso;
	private ServerSocket servidor;
	private Socket cliente;
	private PrintWriter envia;
	private ClienteHandler handler;
	private FileHandler fh;

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
		servidor = new ServerSocket(8080);
		System.out.println("Servidor Iniciado.");


	}

	public void clienteListener() throws IOException {

		while (true) {
			try {
				cliente = servidor.accept();
				handler = new ClienteHandler(cliente);
				System.out.println("Conectado A  " + cliente.getRemoteSocketAddress());

			} catch (SocketException e) {
				System.out.println("Servidor parado!");
			}

			OutputStream saida = cliente.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintStream out = new PrintStream(new BufferedOutputStream(cliente.getOutputStream()));

			String request = in.readLine();
			System.out.println(request);
			
			String arquivo = "";
			StringTokenizer st = new StringTokenizer(request);

			try {

				if (st.hasMoreElements()
						&& (st.nextToken().equalsIgnoreCase("GET") || st.nextToken().equalsIgnoreCase("HEAD"))
						&& st.hasMoreElements()) {
					arquivo = st.nextToken();
				} else if (!st.nextToken().equalsIgnoreCase("GET") && !st.nextToken().equalsIgnoreCase("HEAD")) {

					out.println("HTTP/1.0 501 Not Implemented");
					out.println("Server: Java HTTP Server 1.0");
					out.println("Date: " + new Date());
					out.println("Content-Type: text/html");
					out.println(); // blank line between headers and content
					out.println("<HTML>");
					out.println("<HEAD><TITLE>Not Implemented</TITLE>" + "</HEAD>");
					out.println("<BODY>");
					out.println("<H2>501 Not Implemented: <H2>");
					out.println("</BODY></HTML>");
					out.flush();
				} else {
					throw new FileNotFoundException();
				}

				System.out.println(st.nextToken());

				if (arquivo.endsWith("/")) {
					arquivo += "index.html";
					System.out.println("PÃ¡gina inicial");
				}

				while (arquivo.indexOf("/") == 0) {
					arquivo = arquivo.substring(1);

				}

				InputStream f = new FileInputStream(arquivo);

				String type = "text/plain";
				if (arquivo.endsWith(".html") || arquivo.endsWith(".htm")) {
					type = "text/html";
					
				} else if (arquivo.endsWith(".css")) {
					type = "text/css";
				}  else if (arquivo.endsWith(".js")) {
					type = "text/javascript";
				}

				else if (arquivo.endsWith(".jpg") || arquivo.endsWith(".jpeg")) {
					type = "image/jpeg";
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

				out.print("HTTP/1.1 200 OK\r\nContent-type: " + type + "\r\n\r\n");

				byte[] a = new byte[4096];
				int i;
				while ((i = f.read(a)) > 0) {
					out.write(a, 0, i);
				}

				out.close();
			}

			catch (FileNotFoundException x) {

				out.println("HTTP/1.1 404 Not Found\r\n" + "Content-type: text/html\r\n\r\n" + "<html>" + "<head>"
						+ "</head>"
						+ "<body><img src=\"404.jpg\" style=\"width:500px; height:400px\" title=\"error_404\" alt=\"error_404\">"
						+ "</body></html>\n");
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

	// Lista o valor de todas as aï¿½ï¿½es uma a uma para gerar a lista.
//	public ArrayList<ServidorWeb> listarAcoes(Connection conn) throws SQLException {
//		String sqlSelect = "SELECT id,hora_data,acao FROM LogServidor";
//		ArrayList<ServidorWeb> lista = new ArrayList<>();
//		try (PreparedStatement stm = conn.prepareStatement(sqlSelect); ResultSet rs = stm.executeQuery();) {
//			while (rs.next()) {
//				ServidorWeb servidor = new ServidorWeb(codigo, status, dataAcesso);
//				servidor.setCodigo(rs.getInt("id"));
//				servidor.setDataAcesso(rs.getTimestamp("hora_data"));
//				servidor.setStatus(rs.getString("acao"));
//				lista.add(servidor);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return lista;
//	}
//
//	// Separa particularmente acao por acao para cada objeto do array.
//	public void carregaAcoes(Connection conn) throws SQLException {
//		String sqlSelect = "SELECT hora_data,acao FROM LogServidor WHERE id=?";
//		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
//			stm.setInt(1, codigo);
//			try (ResultSet rs = stm.executeQuery();) {
//				if (rs.next()) {
//					dataAcesso = rs.getTimestamp("hora_data");
//					status = rs.getString("acao");
//				} else {
//					dataAcesso = null;
//					status = null;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void parar() throws IOException {

		servidor.close();
		cliente = null;
		servidor = null;

	}
	
	public void reiniciar() throws IOException, InterruptedException {

	     parar();
	     Thread.sleep(3000);
	     iniciar();
	}

}
