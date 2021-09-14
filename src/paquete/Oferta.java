package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Oferta {

	private static Atraccion[] paseos;
	private static Promocion[] promociones;
	private static ArrayList<Atraccion> listaAtraccion = new ArrayList<>();

	public static void creadorPaseos(Atraccion[] atracciones) {
		paseos = Arrays.copyOfRange(atracciones, 0, atracciones.length);

	}

	public static void creadorPromociones(Promocion[] promos) {
		promociones = Arrays.copyOfRange(promos, 0, promos.length);

	}

	public static Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Atraccion atraccion : paseos) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	public static ArrayList<Atraccion> creadorDeOfertas(Usuario visitante) throws IOException {

		int contador, x;
		boolean condicion = true;
		Promocion[] promocionesCompradasDevolucion;
		Atraccion[] atraccionesComparadasDevolucion;

		ArrayList<Atraccion> devolucionAtracciones = new ArrayList<>();
		ArrayList<Atraccion> listaAtraccionesPreferidas = new ArrayList<>();
		ArrayList<Atraccion> listaAtraccionesNOPreferidas = new ArrayList<>();

		ArrayList<Atraccion> listaAtraccionOrdenada = new ArrayList<>();
		ArrayList<Promocion> promocionesCompradas = new ArrayList<>();

		contador = 0;
		x = 0;

		listaAtraccion.clear();

		for (int r = 0; r < paseos.length; r++) {
			listaAtraccion.add(paseos[r]);
		}

		listaAtraccionOrdenada.addAll(OrdenaientoAtracciones(listaAtraccion));

		/*
		 * Antes de cada if de las promociones debemos comprobar que el usuario tiene el
		 * monto y el tiempo suficiente y que la atraccion tiene cupo. Tambien si las
		 * atracciones se repiten en las promos debemos comprobar que si ya se eligio
		 * una no se puede volver a elegir en otra promo, por lo tanto no ofrecerlas-
		 */

		while (contador < promociones.length && condicion) {

			while (x < promociones[contador].atracciones.length && condicion) {
				if (promociones[contador].atracciones[x].getCupoMaximoDiario() < 0) {
					condicion = false;
				}
				x++;
			}

			if (condicion) {
				if (visitante.getAtraccionPreferida() == promociones[contador].getTipo()) {
					if (visitante.getTiempoDisponible() >= promociones[contador].getTiempoPromedio()) {
						if (visitante.getPresupuesto() >= promociones[contador].calculoPromocion()) {

							if (ofertaPromocion(visitante.getAtraccionPreferida(), promociones[contador])) {
								promocionesCompradas.add(promociones[contador]);
								for (int a = 0; a < promociones[contador].atracciones.length; a++) {

									listaAtraccionOrdenada.remove(promociones[contador].atracciones[a]);

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

							} else {
								for (int c = 0; c < promociones[contador].atracciones.length; c++) {
									listaAtraccionesPreferidas.add(promociones[contador].atracciones[c]);
									listaAtraccionOrdenada.remove(promociones[contador].atracciones[c]);
								}
							}

						}
					}
				}

			}

			contador++;
		}

		for (int i = 0; i < listaAtraccionOrdenada.size(); i++) {
			if (listaAtraccionOrdenada.get(i).getTipoAtraccion() == visitante.getAtraccionPreferida()) {
				listaAtraccionesPreferidas.add(listaAtraccionOrdenada.get(i));

			} else {
				listaAtraccionesNOPreferidas.add(listaAtraccionOrdenada.get(i));

			}
		}

		/*
		 * Aca se debe continuar con las atracciones preferidas del usuario que no se
		 * han elegido en proociones y luego ofrecer las atracciones de los demas tipos,
		 * siempre comprobando si el usuario tiene monto, si tiene tiempo y si hay cupo
		 * en la atraccion
		 */

		for (int z = 0; z < listaAtraccionesPreferidas.size(); z++) {
			System.out.println(listaAtraccionesPreferidas.get(z).getNombre());
		}
		System.out.println("-----");
		for (int y = 0; y < listaAtraccionesNOPreferidas.size(); y++) {
			System.out.println(listaAtraccionesNOPreferidas.get(y).getNombre());
		}
		System.out.println("-----");
		for (int w = 0; w < promocionesCompradas.size(); w++) {
			System.out.println(promocionesCompradas.get(w).getNombre());
		}

		if (listaAtraccionesPreferidas.size() != 0) {
			for (int i = 0; i < listaAtraccionesPreferidas.size(); i++) {
				if (tieneDineroYTiempo(visitante, listaAtraccionesNOPreferidas, i)) {

					if (listaAtraccionOrdenada.get(i).getCupoMaximoDiario() > 0) {
						if (ofertaAtraccion(visitante, listaAtraccionesPreferidas.get(i))) {

							devolucionAtracciones.add(listaAtraccionesPreferidas.get(i));

							listaAtraccionesPreferidas.get(i)
									.setCupoMaximoDiario(listaAtraccionesPreferidas.get(i).getCupoMaximoDiario() - 1);
							visitante.setPresupuesto(
									visitante.getPresupuesto() - listaAtraccionesPreferidas.get(i).getCostoVisita());
							visitante.setTiempoDisponible(visitante.getTiempoDisponible()
									- listaAtraccionesPreferidas.get(i).getTiempoPromedio());

						}
					}
				}
			}

		}

		/* aca se debe ofrecer promociones que no le gustan */

		if (listaAtraccionesNOPreferidas.size() != 0) {
			for (int w = 0; w < listaAtraccionesNOPreferidas.size(); w++) {
				if (tieneDineroYTiempo(visitante, listaAtraccionesNOPreferidas, w)) {

					if (listaAtraccionesNOPreferidas.get(w).getCupoMaximoDiario() > 0) {
						if (ofertaAtraccion(visitante, listaAtraccionesNOPreferidas.get(w))) {

							devolucionAtracciones.add(listaAtraccionesNOPreferidas.get(w));

							listaAtraccionesNOPreferidas.get(w)
									.setCupoMaximoDiario(listaAtraccionesNOPreferidas.get(w).getCupoMaximoDiario() - 1);
							visitante.setPresupuesto(
									visitante.getPresupuesto() - listaAtraccionesNOPreferidas.get(w).getCostoVisita());
							visitante.setTiempoDisponible(visitante.getTiempoDisponible()
									- listaAtraccionesNOPreferidas.get(w).getTiempoPromedio());
						}

					}
				}
			}
		}

		/* Aca se debe llamar a Archivo.creadorItinerario() y este metodo */
		Itinerario.creadorItinerario(visitante, devolucionAtracciones, promocionesCompradas);

		return devolucionAtracciones;

	}

	private static boolean tieneDineroYTiempo(Usuario visitante, ArrayList<Atraccion> listaAtraccionesNOPreferidas,
			int w) {
		return visitante.getPresupuesto() >= listaAtraccionesNOPreferidas.get(w).getCostoVisita()
				&& visitante.getTiempoDisponible() >= listaAtraccionesNOPreferidas.get(w).getTiempoPromedio();
	}

	private static boolean ofertaAtraccion(Usuario visitante, Atraccion paseo) {

		char entradaUs = ' ';

		System.out.println("Quiere comprar el pase a la atraccion " + paseo.getNombre() + "? ");
		System.out.println("La cual cuesta " + paseo.getCostoVisita() + ", y dura " + paseo.getTiempoPromedio() + "?");

		while (!(entradaUs == 's' || entradaUs == 'n')) {
			System.out.println(
					"Ingrese la letra 's' si quiere realizar la atraccion, de lo contrario ingrese la letra 'n'");

			entradaUs = entradaCar();

		}

		if (entradaUs == 's') {
			return true;
		} else {
			return false;
		}
	}

	private static char entradaCar() {
		Scanner in = new Scanner(System.in);
		char entradaUs = ' ';

		entradaUs = in.next().charAt(0);

		in.nextLine();
		entradaUs = Character.toLowerCase(entradaUs);

		return entradaUs;

	}

	private static boolean ofertaPromocion(TipoAtraccion atraccionPreferida, Promocion promociones) {

		char entradaUsuario = ' ';

		System.out.println("Quiere comprar el pase a la promocion: " + promociones.getNombre() + "? ");
		System.out.println("La cual incluye las siguientes atracciones:");
		for (int i = 0; i < promociones.atracciones.length; i++) {
			System.out.println("Atraccion N°" + (i + 1) + ": " + promociones.atracciones[i].getNombre());
		}
		System.out.println("La cual cuesta " + promociones.calculoPromocion() + ", y dura "
				+ promociones.getTiempoPromedio() + "?");

		System.out.println("");
		while (!(entradaUsuario == 's' || entradaUsuario == 'n')) {
			System.out.println(
					"Ingrese la letra 's' si quiere comprar la promocion, de lo contrario ingrese la letra 'n'");
			entradaUsuario = entradaCar();

		}

		if (entradaUsuario == 's') {
			return true;
		} else {
			return false;
		}
	}

	private static ArrayList<Atraccion> OrdenaientoAtracciones(ArrayList<Atraccion> prueba) {
		Collections.sort(listaAtraccion, new ComparaAtracciones());

		ArrayList<Atraccion> devolucion = new ArrayList<>();
		Atraccion[] cambio = new Atraccion[prueba.size()];
		Atraccion comodin;

		for (int j = 0; j < prueba.size(); j++) {

			cambio[j] = prueba.get(j);
		}

		for (int i = 0; i < cambio.length; i++) {

			if (i < cambio.length - 1) {
				if (cambio[i].getCostoVisita() == cambio[i + 1].getCostoVisita()) {

					if (cambio[i + 1].getTiempoPromedio() > cambio[i].getTiempoPromedio()) {
						comodin = cambio[i];
						cambio[i] = cambio[i + 1];
						cambio[i + 1] = comodin;

					}

				}
			}
		}

		for (int i = 0; i < cambio.length; i++) {
			devolucion.add(cambio[i]);
		}

		return devolucion;
	}

}