package test;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Atraccion;
import paquete.TipoAtraccion;

public class AtraccionTest {

	Atraccion bosque = new Atraccion("Bosque encantado", 50, 240, 25, TipoAtraccion.PASEO);

	@Test
	public void obtenerNombreTest() {
		String esperado = "Bosque encantado";
		assertEquals(esperado, bosque.getNombre());
	}

	@Test
	public void obtenerCostoTest() {
		int esperado = 50;
		assertEquals(esperado, bosque.getCostoVisita());
	}

	@Test
	public void obtenerTiempoTest() {
		int esperado = 240;
		assertEquals(esperado, bosque.getTiempoPromedio());
	}

	@Test
	public void obtenerCupoTest() {
		int esperado = 25;
		assertEquals(esperado, bosque.getCupoMaximoDiario());

	}

}
