package main;

import java.io.IOException;
import java.util.Arrays;
import paquete.Archivo;
import paquete.Oferta;
import paquete.Usuario;

public class Cajero {

	private static Usuario[] visitantes;

	private static void creadorUsuarios(Usuario[] usuarios) {
		visitantes = Arrays.copyOf(usuarios, usuarios.length);
	}

	private static void recorredorUsuarios(Usuario[] visitantes) throws IOException {

		int c;

		for (c = 0; c < visitantes.length; c++) {
			Oferta.creadorDeOfertas(visitantes[c]);
		}

	}

	public static void main(String[] args) throws IOException {
		
		Cajero.creadorUsuarios(Archivo.obtenerUsuariosDesdeArchivo());

		Oferta.creadorPaseos(Archivo.obtenerAtraccionesDesdeArchivo());

		Oferta.creadorPromociones(Archivo.obtenerPromocionesDesdeArchivo());

		recorredorUsuarios(visitantes);

	}

}