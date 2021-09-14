package paquete;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Itinerario {

	public static void creadorItinerario(Usuario usuarios, ArrayList<Atraccion> devolucionAtracciones, ArrayList<Promocion> promocionesCompradas) throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter("salida/Itinerario" + usuarios.getNombre() + ".txt"));

		
		salida.println(usuarios.toString());
		salida.println("Muchas gracias por su compra, usted adquirió las siguientes atracciones: ");
		int costoTotal = 0;
		int tiempoTotal = 0;
		
		for (int i = 0; i < devolucionAtracciones.size(); i++) {
			salida.println(devolucionAtracciones.get(i));
			costoTotal += devolucionAtracciones.get(i).getCostoVisita();
			tiempoTotal += devolucionAtracciones.get(i).getTiempoPromedio();
		}
		
		salida.println("También adquirió las siguientes promociones: ");
		
		for (int i = 0; i < promocionesCompradas.size(); i++) {
			salida.println(promocionesCompradas.get(i));
			costoTotal += promocionesCompradas.get(i).calculoPromocion();
			tiempoTotal += promocionesCompradas.get(i).getTiempoPromedio();
		}
		//Modificar el pasaje a horas
		double tiempoTotalHoras = tiempoTotal/60;
		salida.println("El costo total es de " + costoTotal + " monedas.");
		salida.println("Tiempo necesario es " + tiempoTotalHoras + " horas.");

		salida.close();

	}

}
