package test;

import paquete.*;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import paquete.Atraccion;

public class ItinerarioTest {

	@Test
	public void quePuedaCrearAtraccionArchivo() {
		
		Atraccion[] atracciones = Archivo.obtenerAtraccionesDesdeArchivo();

		assertNotNull(atracciones);

	}

	public void quePuedaCrearUsuarioArchivo() {
		
		Atraccion[] usuarios = Archivo.obtenerAtraccionesDesdeArchivo();

		assertNotNull(usuarios);

	}

	public void quePuedaCrearPromocionesArchivo() {
		
		Atraccion[] promociones = Archivo.obtenerAtraccionesDesdeArchivo();

		assertNotNull(promociones);
	}
}
