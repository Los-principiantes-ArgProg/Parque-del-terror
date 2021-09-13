package paquete;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Cajero cajero = new Cajero();
		
		cajero.agregarTodosUsuarios(Archivo.obtenerUsuariosDesdeArchivo());
		// XXX La siguiente linea esta FEA. No debería pasarle concesionaria así
		cajero.agregarTodasAtracciones(Archivo.obtenerAtraccionesDesdeArchivo());
		
		cajero.agregarTodasPromociones(Archivo.obtenerPromocionesDesdeArchivo(cajero));
	
		
		// Listo todas las "atracciones" y las promos con su contenido
		System.out.println(cajero.toString());

		// Leer por consola y mostrar por pantalla
		/*Scanner in = new Scanner(System.in);
		String entradaConsola = in.nextLine();
		System.err.println(entradaConsola);
		in.close();*/
	}
}