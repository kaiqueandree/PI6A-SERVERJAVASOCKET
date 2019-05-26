package br.com.pi.test;


import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;

public class TestaServidor 
{

	    public static void main(String [] args) {
	        File f = new File("../../../../PI/"+"teste");
	        
	        try {
	        	System.out.println(f.getCanonicalPath());
	        }
	        catch(IOException e) {}
	    }
	}
