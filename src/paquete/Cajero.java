package paquete;

import java.util.ArrayList;
import java.util.Arrays;

public class Cajero {
	private static Usuario[] usuarios;
	private Atraccion[] atracciones;
	private Promocion[] promociones;

	public void creadorUsuarios(Usuario[] visitantes) {

		usuarios = Arrays.copyOf(visitantes, visitantes.length);
		// solo para comprobar que funcione, hay que sacarlo
		for (Usuario visitante : visitantes) {
			System.out.println(visitante);

		}

	}

	@Override
	public String toString() {
		return "Cajero [Usuarios = \n" + Arrays.toString(usuarios) + "] \n" + " [Atracciones = \n"
				+ Arrays.toString(atracciones) + "] \n" + " [Promociones = \n" + Arrays.toString(promociones) + "]";
	}

	private static void recorredorUsuarios(Usuario[] usuarios) {
		ArrayList<Atraccion> itinerario = null;

		int c;

		for (c = 0; c < usuarios.length; c++) {

			itinerario = Oferta.creadorDeOfertas(usuarios[c]);
			/*
			 * Aca cada vez que se devuelva el itinerario de un usuario se debe guardar en
			 * el archivo seprarndolo por el nombre del usuario
			 */
			// SACO ESTO UN RATITO
			/*
			 * for (int i = 0; i < usuarios.length; i++) {
			 * System.out.println(itinerario.get(i).getNombre()); }
			 */
		}

	}

	/*
	 * public static void main(String[] Args) { recorredorUsuarios(); }
	 * 
	 * private static void recorredorUsuarios() {
	 * 
	 * int c;
	 * 
	 * for (c = 0; c < usuarios.length; c++) { while (usuarios[c].getPresupuesto() >
	 * 0 && usuarios[c].getTiempoDisponible() > 0) {
	 * Oferta.creadorDeOfertas(usuarios[c]); } }
	 * 
	 * }
	 */

	public static void main(String[] args) {

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