package paquete;

import java.io.IOException;
import java.util.Arrays;

public class Cajero {
	private static Usuario[] usuarios;

	public void creadorUsuarios(Usuario[] visitantes) {

		usuarios = Arrays.copyOf(visitantes, visitantes.length);

	}

	private static void recorredorUsuarios(Usuario[] usuarios) throws IOException {

		int c;

		for (c = 0; c < usuarios.length; c++) {
			Oferta.creadorDeOfertas(usuarios[c]);
		}

	}

	public static void main(String[] args) throws IOException {

		Oferta.creadorPaseos(Archivo.obtenerAtraccionesDesdeArchivo());

		Oferta.creadorPromociones(Archivo.obtenerPromocionesDesdeArchivo());

		recorredorUsuarios(Archivo.obtenerUsuariosDesdeArchivo());

	}

}