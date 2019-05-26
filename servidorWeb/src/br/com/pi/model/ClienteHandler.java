package br.com.pi.model;

import java.net.Socket;

public class ClienteHandler extends Thread {
	
	
	
		  private Socket socket;  // The accepted socket from the Webserver

		  // Start the thread in the constructor
		  public ClienteHandler(Socket s) {
		    socket=s;
		    start();
		  }
}
