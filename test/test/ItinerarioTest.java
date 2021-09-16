package test;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Archivo;
import paquete.Atraccion;
import paquete.Itinerario;
import paquete.PromocionPorcentual;
import paquete.TipoAtraccion;
import paquete.Usuario;

public class ItinerarioTest {

	@Test
	public void quePuedaCrearAtraccionArchivo() {
		Archivo archivoAtraccion = new Archivo();
		Atraccion[] atracciones = archivoAtraccion.obtenerAtraccionesDesdeArchivo();

		assertNotNull(atracciones);

	}

	public void quePuedaCrearUsuarioArchivo() {
		Archivo archivoUsuario = new Archivo();
		Atraccion[] usuarios = archivoUsuario.obtenerAtraccionesDesdeArchivo();

		assertNotNull(usuarios);

	}

	public void quePuedaCrearPromocionesArchivo() {
		Archivo archivoPromocion = new Archivo();
		Atraccion[] promociones = archivoPromocion.obtenerAtraccionesDesdeArchivo();

		assertNotNull(promociones);
	}
}
