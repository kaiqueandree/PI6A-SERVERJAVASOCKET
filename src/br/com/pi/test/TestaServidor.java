package br.com.pi.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




public class TestaServidor {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket servidor = new ServerSocket(12345);
    	System.out.println("Works.");
    	while(true) {
    System.out.println("While true...");
    	Socket cliente = servidor.accept();
    	System.out.println("Connected to " + cliente.getRemoteSocketAddress());
    	OutputStream out = cliente.getOutputStream();
    	PrintWriter envia = new PrintWriter(out);
        envia.println("HTTP/1.0 200 OK");
        envia.println("Content-Length: 12");
        envia.println("");
        envia.println("Hello World!");
        envia.flush();
        System.out.println("Sent.");
        cliente.close();
	}

}
}
