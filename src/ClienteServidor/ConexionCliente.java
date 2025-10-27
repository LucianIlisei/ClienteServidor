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

			Scanner teclado = new Scanner(System.in);

			boolean enviando = true;

			while (true) {
				System.out.println("Escribe un mensaje para el servidor: ");
				String mensajeCliente = teclado.nextLine();
				salida.println(mensajeCliente);
				salida.flush();
				if (mensajeCliente.equalsIgnoreCase("BYE")) {
					System.out.println("Has cerrado la conexión.");
					break;
				}
				String respuestaServidor = entrada.readLine();
				if (respuestaServidor == null || respuestaServidor.equals("BYE")) {
					System.out.println("El servidor ha cerrado la conexión.");
					break;
				}
				System.out.println("SERVIDOR: " + respuestaServidor);
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
