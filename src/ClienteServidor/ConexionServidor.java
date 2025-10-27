package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
		Scanner teclado = new Scanner(System.in);

		System.out.println("Escuchando: " + servidor);

		try {
			conexion = servidor.accept();
			System.out.println("Conexion aceptada: " + conexion);

			entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			salida = new PrintWriter(new OutputStreamWriter(conexion.getOutputStream()));

			while (true) {
				String mensajeCliente = entrada.readLine();

				if (mensajeCliente == null || mensajeCliente.equalsIgnoreCase("BYE")) {
					System.out.println("El cliente ha cerrado la conexión.");
					break;
				}

				System.out.println("Cliente: " + mensajeCliente);
				System.out.println("Escribe un mensaje para el cliente: ");
				String mensajeServidor = teclado.nextLine();
				salida.println(mensajeServidor);
				salida.flush();

				if (mensajeServidor.equals("BYE")) {
					System.out.println("Cerrando conexión...");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			salida.close();
			try {
				entrada.close();
				conexion.close();
				servidor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
