package paquete;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Itinerario {

	public static void creadorItinerario(Usuario usuarios, Atraccion[] atracciones, Promocion[] promociones) throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter("salida/Itinerario" + usuarios.getNombre() + ".txt"));

		
		salida.println(usuarios.toString());
		salida.println("Muchas gracias por su compra, usted adquirió las siguientes atracciones: ");
		int costoTotal = 0;
		int tiempoTotal = 0;
		
		for (int i = 0; i < atracciones.length; i++) {
			salida.println(atracciones[i]);
			costoTotal += atracciones[i].getCostoVisita();
			tiempoTotal += atracciones[i].getTiempoPromedio();
		}
		
		salida.println("También adquirió las siguientes promociones: ");
		
		for (int i = 0; i < promociones.length; i++) {
			salida.println(promociones[i]);
			costoTotal += promociones[i].calculoPromocion();
			tiempoTotal += promociones[i].getTiempoPromedio();
		}
		//Modificar el pasaje a horas
		double tiempoTotalHoras = tiempoTotal/60;
		salida.println("El costo total es de " + costoTotal + " monedas.");
		salida.println("Tiempo necesario es " + tiempoTotalHoras + " horas.");

		salida.close();

	}

}
