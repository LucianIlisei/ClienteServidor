package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConexionCliente {
	public static final int PORT = 4242;
	
	public static void main(String[] args) {
		
		Socket conexion = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		
		try {
			conexion = new Socket(InetAddress.getLocalHost(), PORT);
			entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			salida = new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));
			
			Scanner sc = new Scanner(System.in);
			
			boolean enviando = true;
			
			while(enviando) {
				System.out.println("Escribe un mensaje para el servidor: ");
				String mensajeCliente = sc.nextLine();
				salida.println(mensajeCliente);
				salida.flush();
				String respuestaServidor = entrada.readLine();
				System.out.println("SERVIDOR: " + respuestaServidor);
				enviando = !mensajeCliente.equals("BYE");
			}
		} catch (IOException e) {
			System.err.println("No se ha podido conectar...");
			System.exit(-1);
		} finally {
			salida.close();
			try {
				entrada.close();
				conexion.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
