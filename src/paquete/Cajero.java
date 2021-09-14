package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Cajero {
	private static Usuario[] usuarios;
	// private Atraccion[] atracciones;
	// private Promocion[] promociones;

	public void creadorUsuarios(Usuario[] visitantes) {

		usuarios = Arrays.copyOf(visitantes, visitantes.length);
		// solo para comprobar que funcione, hay que sacarlo
		for (Usuario visitante : visitantes) {
			System.out.println(visitante);

		}

	}

	private static void recorredorUsuarios(Usuario[] usuarios) throws IOException {
		ArrayList<Atraccion> itinerario = new ArrayList<>();

		int c;

		for (c = 0; c < usuarios.length; c++) {

			itinerario.addAll(Oferta.creadorDeOfertas(usuarios[c]));

			Itinerario.creadorItinerario(usuarios[c], Archivo.obtenerAtraccionesDesdeArchivo(),
					Archivo.obtenerPromocionesDesdeArchivo());
		}

	}

	public static void main(String[] args) throws IOException {

		Oferta.creadorPaseos(Archivo.obtenerAtraccionesDesdeArchivo());

		Oferta.creadorPromociones(Archivo.obtenerPromocionesDesdeArchivo());

		ArrayList<Atraccion> itinerarioFinal = null;

		recorredorUsuarios(Archivo.obtenerUsuariosDesdeArchivo());

		// Leer por consola y mostrar por pantalla
		/*
		 * Scanner in = new Scanner(System.in); String entradaConsola = in.nextLine();
		 * System.err.println(entradaConsola); in.close(); }
		 */

	}
}