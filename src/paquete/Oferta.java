package paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Oferta {

	private static Atraccion[] paseos;
	private static Promocion[] promociones;
	private static ArrayList<Atraccion> listaAtraccion = new ArrayList<>();

	/*public static void creadorPaseos() {
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
	}*/

	public static ArrayList<Atraccion> creadorDeOfertas(Usuario visitante) {
		int c, contador, x;
		boolean condicion = true;
		//boolean condicionAtraccion = true;
		ArrayList<Atraccion> devolucion = new ArrayList<>();
		ArrayList<Atraccion> listaPreferida = new ArrayList<>();
		ArrayList<Atraccion> listaAtraccionOrdenada = new ArrayList<>();
		contador = 0;
		x = 0;

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

			while (x < promociones[contador].atracciones.length && condicion) {
				if (promociones[contador].atracciones[x].getCupoMaximoDiario() <= 0) {
					condicion = false;
				}
				x++;

			}

			if (condicion) {
				if (visitante.getAtraccionPreferida() == promociones[contador].getTipo()) {
					if (visitante.getTiempoDisponible() >= promociones[contador].getTiempoPromedio()) {
						if (visitante.getPresupuesto() >= promociones[contador].calculoPromocion()) {

							if (ofertaPromocion(visitante.getAtraccionPreferida(), promociones[contador])) {

								for (int a = 0; a < promociones[contador].atracciones.length; a++) {
									devolucion.add(promociones[contador].atracciones[a]);
								}

								for (int z = 0; z < listaAtraccion.size(); z++) {
									if (listaAtraccion.get(z).getTipoAtraccion() == promociones[contador].getTipo()) {
										listaAtraccion.remove(z);
										System.out.println("SI");
									}
									
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

		

		contador++;
		}

		/*
		 * Aca se debe continuar con las atracciones preferidas del usuario que no se
		 * han elegido en proociones y luego ofrecer las atracciones de los demas tipos,
		 * siempre comprobando si el usuario tiene monto, si tiene tiempo y si hay cupo
		 * en la atraccion
		 */
		listaAtraccionOrdenada.clear();
		listaAtraccionOrdenada = OrdenaientoAtracciones(listaAtraccion);
		//listaAtraccionOrdenada=  (ArrayList<Atraccion>)OrdenaientoAtracciones(listaAtraccion).clone();

		for (int i = 0; i < listaAtraccionOrdenada.size(); i++) {
			if (visitante.getAtraccionPreferida() == listaAtraccionOrdenada.get(i).getTipoAtraccion()) {
				listaPreferida.add(listaAtraccionOrdenada.get(i));
				listaAtraccionOrdenada.remove(i);
			}
		}

		if (listaPreferida.size() != 0) {
			for (int w = 0; w < listaPreferida.size(); w++) {
				if (visitante.getPresupuesto() >= listaPreferida.get(w).getCostoVisita()) {
					if (visitante.getTiempoDisponible() >= listaPreferida.get(w).getTiempoPromedio()) {
						if (listaAtraccionOrdenada.get(w).getCupoMaximoDiario() > 0) {
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

		if (listaAtraccionOrdenada.size() != 0) {
			for (int w = 0; w < listaAtraccionOrdenada.size(); w++) {
				if (visitante.getPresupuesto() >= listaAtraccionOrdenada.get(w).getCostoVisita()) {
					if (visitante.getTiempoDisponible() >= listaAtraccionOrdenada.get(w).getTiempoPromedio()) {
						if (listaAtraccionOrdenada.get(w).getCupoMaximoDiario() > 0) {
							if (ofertaAtraccion(visitante, listaAtraccionOrdenada.get(w))) {

								devolucion.add(listaAtraccionOrdenada.get(w));

								listaAtraccionOrdenada.get(w)
										.setCupoMaximoDiario(listaAtraccionOrdenada.get(w).getCupoMaximoDiario() - 1);
								visitante.setPresupuesto(
										visitante.getPresupuesto() - listaAtraccionOrdenada.get(w).getCostoVisita());
								visitante.setTiempoDisponible(visitante.getTiempoDisponible()
										- listaAtraccionOrdenada.get(w).getTiempoPromedio());
							}
						}
					}
				}
			}
		}

		return devolucion;

	}

	private static boolean ofertaAtraccion(Usuario visitante, Atraccion paseo) {
		Scanner entrada = new Scanner(System.in);
		char entradaUsuario = ' ';

		System.out.println("Quiere comprar el pase a la atraccion " + paseo.getNombre() + "? ");
		System.out.println("La cual cuesta " + paseo.getCostoVisita() + ", y dura " + paseo.getTiempoPromedio() + "?");

		while (!(entradaUsuario == 's' || entradaUsuario == 'n')) {
			System.out.println(
					"Ingrese la letra 's' si quiere realizar la atraccion, de lo contrario ingrese la letra 'n'");
			entradaUsuario = entrada.next().charAt(0);
			entradaUsuario = Character.toLowerCase(entradaUsuario);
			entrada.close();
		}
		if (entradaUsuario == 's') {
			return true;
		} else {
			return false;
		}
	}

	private static boolean ofertaPromocion(TipoAtraccion atraccionPreferida, Promocion promociones) {

		Scanner entrada = new Scanner(System.in);
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
			entradaUsuario = entrada.next().charAt(0);
			entradaUsuario = Character.toLowerCase(entradaUsuario);
			entrada.close();
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