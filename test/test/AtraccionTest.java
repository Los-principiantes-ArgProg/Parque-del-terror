package test;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Atraccion;
import paquete.TipoAtraccion;

public class AtraccionTest {

	Atraccion bosque = new Atraccion("Bosque encantado", 60, 70, 12, TipoAtraccion.PASEO);

	@Test
	public void obtenerNombreTest() {
		String esperado = "Bosque encantado";
		assertEquals(esperado, bosque.getNombre());
	}

	@Test
	public void obtenerCostoTest() {
		int esperado = 60;
		assertEquals(esperado, bosque.getCostoVisita());
	}

	@Test
	public void obtenerTiempoTest() {
		int esperado = 70;
		assertEquals(esperado, bosque.getTiempoPromedio());
	}

	@Test
	public void obtenerCupoTest() {
		int esperado = 12;
		assertEquals(esperado, bosque.getCupoMaximoDiario());

	}

}
