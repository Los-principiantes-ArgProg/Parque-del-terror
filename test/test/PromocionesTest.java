package test;

import static org.junit.Assert.*;

import org.junit.Test;

import paquete.Atraccion;
import paquete.PromocionAbsoluta;
import paquete.PromocionAxB;
import paquete.PromocionPorcentual;
import paquete.TipoAtraccion;

public class PromocionesTest {

	@Test
	public void PromocionAbsolutaTest() {
		Atraccion atracciones[] = { new Atraccion("Castillo de Drácula", 25, 50, 100, TipoAtraccion.ADRENALINA),
				new Atraccion("Nido de Dragones", 35, 40, 12, TipoAtraccion.ADRENALINA),
				new Atraccion("Laberinto", 50, 30, 30, TipoAtraccion.ADRENALINA) };
		PromocionAbsoluta promocion = new PromocionAbsoluta("Pack Adrenalina", TipoAtraccion.ADRENALINA, atracciones,
				90);
		int costoEsperado = 90;
		int tiempoEsperado = 120;
		assertEquals(costoEsperado, promocion.calculoPromocion());
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());
	}

	@Test
	public void PromocionAxBTest() {
		Atraccion atracciones[] = { new Atraccion("Bosque encantado", 60, 70, 12, TipoAtraccion.PASEO),
				new Atraccion("Lago del terror", 50, 50, 40, TipoAtraccion.PASEO),
				new Atraccion("Tren Fantasma", 20, 4, 7, TipoAtraccion.PASEO), };

		PromocionAxB promocion = new PromocionAxB("Pack Paseo", TipoAtraccion.PASEO, atracciones);
		int costoEsperado = 110;
		int tiempoEsperado = 124;
		assertEquals(costoEsperado, promocion.calculoPromocion());
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());
	}

	@Test
	public void PromosionPorcentualTest() {
		Atraccion atracciones[] = { new Atraccion("La Posada de Hades", 45, 80, 15, TipoAtraccion.DEGUSTACION),
				new Atraccion("Canibalismo y cervezas", 70, 75, 40, TipoAtraccion.DEGUSTACION) };
		PromocionPorcentual promocion = new PromocionPorcentual("Pack Degustacion", TipoAtraccion.DEGUSTACION,
				atracciones, 20);
		int costoEsperado = 92;
		int tiempoEsperado = 155;
		assertEquals(costoEsperado, promocion.calculoPromocion(), 0.001);
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());

	}
}
