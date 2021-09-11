package paquete;

import java.util.ArrayList;
import java.util.Collections;

public class Oferta {

	static Atraccion[] paseos;
	static Promocion[] promociones;
	static ArrayList<Atraccion> listaAtraccion = new ArrayList();

	public static void creadorPaseos() {
		paseos = new Atraccion[8];

		paseos[0] = new Atraccion("Bosque encantado", 50, 240, 25, TipoAtraccion.Paisajes);
		paseos[1] = new Atraccion("Lago del terror", 40, 120, 10, TipoAtraccion.Paisajes);
		paseos[2] = new Atraccion("Castillo de Dracula", 25, 75, 10, TipoAtraccion.Aventura);
		paseos[3] = new Atraccion("Cueva de la resurreccion", 20, 60, 5, TipoAtraccion.Aventura);
		paseos[4] = new Atraccion("La posada de Hades", 50, 60, 50, TipoAtraccion.Degustacion);
		paseos[5] = new Atraccion("Nido de dragones", 35, 100, 2, TipoAtraccion.Paisajes);
		paseos[6] = new Atraccion("Laberinto", 50, 20, 4, TipoAtraccion.Aventura);
		paseos[7] = new Atraccion("Canibalismo y cervezas", 28, 60, 20, TipoAtraccion.Degustacion);

	}

	public static void creadorPromociones() {
		promociones = new Promocion[3];
		Atraccion[] paisajes = { paseos[0], paseos[1], paseos[5] };
		Atraccion[] aventura = { paseos[2], paseos[3], paseos[6] };
		Atraccion[] degustacion = { paseos[4], paseos[7] };

		promociones[0] = new PromocionPorcentual("Pack paisajes", TipoAtraccion.Paisajes, paisajes);
		promociones[1] = new PromocionAbsoluta("Pack aventura", TipoAtraccion.Aventura, aventura);
		promociones[2] = new PromocionAxB("Pack degustacion", TipoAtraccion.Degustacion, degustacion);
	}

	public static ArrayList creadorDeOfertas(Usuario visitante) {
		int c, contador;
		boolean condicion = true;
		boolean condicionAtraccion = true;
		ArrayList<Atraccion> devolucion = null;
		ArrayList<Atraccion> listaPreferida = null;
		contador = 0;

		for (int r = 0; r < paseos.length; r++) {
			listaAtraccion.add(paseos[r]);
		}

		/*
		 * ArrayList<TipoAtraccion> tipos = null;
		 * 
		 * tipos.add(TipoAtraccion.Aventura); tipos.add(TipoAtraccion.Paisajes);
		 * tipos.add(TipoAtraccion.Degustacion);
		 */

		/*
		 * Antes de cada if de las promociones debemos comprobar que el usuario tiene el
		 * monto y el tiempo suficiente y que la atraccion tiene cupo. Tambien si las
		 * atracciones se repiten en las promos debemos comprobar que si ya se eligio
		 * una no se puede volver a elegir en otra promo, por lo tanto no ofrecerlas-
		 */

		while (contador < promociones.length && condicion) {

			/*
			 * Este while esta considerado para trabajar con solo una promocion por tipo de
			 * atraccion
			 */

			for (int x = 0; x < promociones[contador].atracciones.length; x++) {
				if (promociones[contador].atracciones[x].getCupoMaximoDiario() <= 0) {
					condicion = false;
				}

			}

			if (condicion) {
				if (visitante.getAtraccionPreferida() == promociones[contador].getTipo()) {
					if (visitante.getTiempoDisponible() >= promociones[contador].getTiempoPromedio()) {
						if (visitante.getPresupuesto() >= promociones[contador].calculoPromocion()) {

							if (ofertaPromocion(visitante.getAtraccionPreferida(), promociones[contador])) {

								for (int x = 0; x < promociones[contador].atracciones.length; x++) {
									devolucion.add(promociones[contador].atracciones[x]);
								}

								for (int z = 0; z < listaAtraccion.size(); z++) {
									if (listaAtraccion.get(z).getTipoAtraccion() == promociones[contador].getTipo()) {
										listaAtraccion.remove(z);
									}
								}
								for (int a = 0; a < promociones[contador].atracciones.length; a++) {
									for (int q = 0; q < paseos.length; q++) {
										if (paseos[q].getNombre()
												.equalsIgnoreCase(promociones[contador].atracciones[a].getNombre())) {
											paseos[q].setCupoMaximoDiario(paseos[q].getCupoMaximoDiario() - 1);
										}
									}
								}
								visitante.setPresupuesto(
										visitante.getPresupuesto() - promociones[contador].calculoPromocion());
								visitante.setTiempoDisponible(
										visitante.getTiempoDisponible() - promociones[contador].getTiempoPromedio());

								/*
								 * aca se agrega las atracciones que participan de la promocion al itinerario
								 * por medio de devolucion.add(), tambien se debe restar el tiempo y el monto al
								 * usuario.Y tambien el cupo por atraccion.
								 */

							}
						}
					}
				}

			}

			contador++;
		}

		/*
		 * Aca se debe continuar con las atracciones preferidas del usuario que no se
		 * han elegido en proociones y luego ofrecer las atracciones de los demas tipos,
		 * siempre comprobando si el usuario tiene monto, si tiene tiempo y si hay cupo
		 * en la atraccion
		 */

		Collections.sort(listaAtraccion, new ComparaAtracciones());

		for (int i = 0; i < listaAtraccion.size(); i++) {
			if (visitante.getAtraccionPreferida() == listaAtraccion.get(i).getTipoAtraccion()) {
				listaPreferida.add(listaAtraccion.get(i));
				listaAtraccion.remove(i);
			}
		}
		if (listaPreferida.size() != 0) {
			for (int w = 0; w < listaPreferida.size(); w++) {
				if (visitante.getPresupuesto() >= listaPreferida.get(w).getCostoVisita()) {
					if (visitante.getTiempoDisponible() >= listaPreferida.get(w).getTiempoPromedio()) {
						if (listaAtraccion.get(w).getCupoMaximoDiario() > 0) {
							if (ofertaAtraccion(visitante, listaPreferida.get(w))) {

								devolucion.add(listaPreferida.get(w));

								listaPreferida.get(w)
										.setCupoMaximoDiario(listaPreferida.get(w).getCupoMaximoDiario() - 1);
								visitante.setPresupuesto(
										visitante.getPresupuesto() - listaPreferida.get(w).getCostoVisita());
								visitante.setTiempoDisponible(
										visitante.getTiempoDisponible() - listaPreferida.get(w).getTiempoPromedio());
							}
						}
					}
				}
			}

		}

		/* Aca se avisa por consola que se seguira ofeciendo atracciones */

		if (listaAtraccion.size() != 0) {
			for (int w = 0; w < listaAtraccion.size(); w++) {
				if (visitante.getPresupuesto() >= listaAtraccion.get(w).getCostoVisita()) {
					if (visitante.getTiempoDisponible() >= listaAtraccion.get(w).getTiempoPromedio()) {
						if (listaAtraccion.get(w).getCupoMaximoDiario() > 0) {
							if (ofertaAtraccion(visitante, listaAtraccion.get(w))) {

								devolucion.add(listaAtraccion.get(w));

								listaAtraccion.get(w)
										.setCupoMaximoDiario(listaAtraccion.get(w).getCupoMaximoDiario() - 1);
								visitante.setPresupuesto(
										visitante.getPresupuesto() - listaAtraccion.get(w).getCostoVisita());
								visitante.setTiempoDisponible(
										visitante.getTiempoDisponible() - listaAtraccion.get(w).getTiempoPromedio());
							}
						}
					}
				}
			}
		}

		return devolucion;

	}

	private static boolean ofertaAtraccion(Usuario visitante, Atraccion paseo) {
		/*
		 * Aca se deberia ofrecer por pantalla al usuario la atraccion ingresada por
		 * parametro, y si acepta se debe devolver true.
		 */
		return false;
	}

	private static boolean ofertaPromocion(TipoAtraccion atraccionPreferida, Promocion promociones) {
		/*
		 * Aca se deberia ofrecer por pantalla al usuario la promocion ingresada por el
		 * parametro promociones, y si acepta se debe devolver true.
		 */
		return true;
	}

}