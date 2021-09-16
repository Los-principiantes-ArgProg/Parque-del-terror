package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Itinerario {

	public static void creadorItinerario(String datosUsuarioInicial, Usuario usuario,
			ArrayList<Atraccion> devolucionAtracciones, ArrayList<Promocion> promocionesCompradas) throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter("salida/Itinerario" + usuario.getNombre() + ".txt"));

		salida.println("-------------------------------- ITINERARIO --------------------------------\n");
		salida.println("Estado inicial\n" + datosUsuarioInicial);
		salida.println("¡Muchas gracias por su compra " + usuario.getNombre() + "!\n");

		int costoTotal = 0;
		int tiempoTotal = 0;

		if (devolucionAtracciones.size() != 0) {
			salida.println("Adquirió las siguientes atracciones: \n");

			for (int i = 0; i < devolucionAtracciones.size(); i++) {
				salida.println("\t" + devolucionAtracciones.get(i));
				costoTotal += devolucionAtracciones.get(i).getCostoVisita();
				tiempoTotal += devolucionAtracciones.get(i).getTiempoPromedio();
			}
		}

		if (promocionesCompradas.size() != 0) {

			salida.println("Adquirió las siguientes promociones: \n");

			for (int i = 0; i < promocionesCompradas.size(); i++) {
				salida.println("\t" + promocionesCompradas.get(i));
				costoTotal += promocionesCompradas.get(i).calculoPromocion();
				tiempoTotal += promocionesCompradas.get(i).getTiempoPromedio();
			}
		}

		int horas = tiempoTotal / 60;
		int minutos = tiempoTotal % 60;

		salida.println("El costo total es de " + costoTotal + " monedas.\n");
		salida.println("El tiempo necesario para su itinerario es " + horas + " horas y " + minutos + " minutos.\n");
		salida.println("Estado final\n" + usuario.toString() + "\n");
		salida.println("Disfrute de su paseo.");
		salida.println("----------------------------------------------------------------------------\n");
		salida.close();

		imprimirItinerario("salida/Itinerario" + usuario.getNombre() + ".txt");

	}

	public static void imprimirItinerario(String archivo) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			StringBuilder sb = new StringBuilder();
			String linea = br.readLine();

			while (linea != null) {
				sb.append(linea);
				sb.append(System.lineSeparator());
				linea = br.readLine();
			}
			String textoCompleto = sb.toString();
			System.out.println(textoCompleto);
		}
	}

}
