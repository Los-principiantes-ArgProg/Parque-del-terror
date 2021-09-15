package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public static void creadorDeOfertas(Usuario visitante) throws IOException {

		String datosUsuarioInicial = visitante.toString();

		int contador, x;
		boolean condicion = true;

		ArrayList<Atraccion> devolucionAtracciones = new ArrayList<>();
		ArrayList<Atraccion> listaAtraccionesPreferidas = new ArrayList<>();
		ArrayList<Atraccion> listaAtraccionesNOPreferidas = new ArrayList<>();

		ArrayList<Promocion> promocionesCompradas = new ArrayList<>();

		contador = 0;
		x = 0;
		
		//BIENVENIDA
		
		for (int i = 0; i < paseos.length; i++) {
			listaAtraccion.add(paseos[i]);
		}
		Collections.sort(listaAtraccion, new ComparaAtracciones());

		for (int i = 0; i < listaAtraccion.size(); i++) {
			if (listaAtraccion.get(i).getTipoAtraccion() == visitante.getAtraccionPreferida()) {
				listaAtraccionesPreferidas.add(listaAtraccion.get(i));

			} else {
				listaAtraccionesNOPreferidas.add(listaAtraccion.get(i));

			}
		}

		Collections.sort(listaAtraccionesPreferidas, new ComparaAtracciones());
		Collections.sort(listaAtraccionesNOPreferidas, new ComparaAtracciones());

		while (contador < promociones.length && condicion) {

			while (x < promociones[contador].getAtraccion().length && condicion) {
				if (promociones[contador].getAtraccionElemento(x).getCupoMaximoDiario() < 0) {
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
								for (int a = 0; a < promociones[contador].getAtraccion().length; a++) {

									listaAtraccionesPreferidas.remove(promociones[contador].getAtraccionElemento(a));

									for (int q = 0; q < paseos.length; q++) {
										if (paseos[q].getNombre().equalsIgnoreCase(
												promociones[contador].getAtraccionElemento(a).getNombre())) {
											paseos[q].setCupoMaximoDiario(paseos[q].getCupoMaximoDiario() - 1);
										}
									}
								}

								visitante.setPresupuesto(
										visitante.getPresupuesto() - promociones[contador].calculoPromocion());
								visitante.setTiempoDisponible(
										visitante.getTiempoDisponible() - promociones[contador].getTiempoPromedio());

							}
						}

					}
				}
			}

			contador++;
		}

		if (listaAtraccionesPreferidas.size() != 0) {
			for (int i = 0; i < listaAtraccionesPreferidas.size(); i++) {
				if (tieneDineroYTiempo(visitante, listaAtraccionesPreferidas, i)) {

					if (listaAtraccionesPreferidas.get(i).getCupoMaximoDiario() > 0) {
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
		contador = 0;
		x = 0;
		while (contador < promociones.length && condicion) {

			while (x < promociones[contador].getAtraccion().length && condicion) {
				if (promociones[contador].getAtraccionElemento(x).getCupoMaximoDiario() < 0) {
					condicion = false;

				}

				x++;
			}

			if (condicion) {

				if (visitante.getAtraccionPreferida() != promociones[contador].getTipo()) {

					if (visitante.getTiempoDisponible() >= promociones[contador].getTiempoPromedio()) {

						if (visitante.getPresupuesto() >= promociones[contador].calculoPromocion()) {

							if (ofertaPromocion(visitante.getAtraccionPreferida(), promociones[contador])) {

								promocionesCompradas.add(promociones[contador]);
								for (int a = 0; a < promociones[contador].getAtraccion().length; a++) {

									listaAtraccionesNOPreferidas.remove(promociones[contador].getAtraccionElemento(a));

									for (int q = 0; q < paseos.length; q++) {
										if (paseos[q].getNombre().equalsIgnoreCase(
												promociones[contador].getAtraccionElemento(a).getNombre())) {
											paseos[q].setCupoMaximoDiario(paseos[q].getCupoMaximoDiario() - 1);
										}
									}
								}

								visitante.setPresupuesto(
										visitante.getPresupuesto() - promociones[contador].calculoPromocion());
								visitante.setTiempoDisponible(
										visitante.getTiempoDisponible() - promociones[contador].getTiempoPromedio());

							}
						}

					}
				}
			}

			contador++;
		}

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

		Itinerario.creadorItinerario(datosUsuarioInicial, visitante, devolucionAtracciones, promocionesCompradas);

	}

	private static boolean tieneDineroYTiempo(Usuario visitante, ArrayList<Atraccion> lista, int w) {
		return (visitante.getPresupuesto() >= lista.get(w).getCostoVisita()
				&& visitante.getTiempoDisponible() >= lista.get(w).getTiempoPromedio());
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
		for (int i = 0; i < promociones.getAtraccion().length; i++) {
			System.out.println("Atraccion N°" + (i + 1) + ": " + promociones.getAtraccion()[i].getNombre());
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

}