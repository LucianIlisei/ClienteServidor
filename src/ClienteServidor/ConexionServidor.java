package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionServidor {
	public static final int PORT = 4242;

	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		
		try {
			servidor = new ServerSocket(PORT, 1000, InetAddress.getLocalHost());
		} catch (IOException e) {
			System.err.println("No podemos escuchar en el puerto: " + PORT);
			System.exit(-1);
		}
		
		Socket conexion = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		
		System.out.println("Escuchando: " + servidor);
		
		try {
			conexion = servidor.accept();
			System.out.println("Conexion aceptada: " + conexion);
			
			entrada  = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			salida = new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));
			
			while(true) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
