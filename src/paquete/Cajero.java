package paquete;

import java.util.Arrays;

public class Cajero {
	private Usuario[] usuarios;
	private Atraccion[] atracciones;
	private Promocion[] promociones;

	public Cajero() {

	}

	public void agregarTodosUsuarios(Usuario[] usuarios) {
		this.usuarios = usuarios;
	}

	public void agregarTodasAtracciones(Atraccion[] atracciones) {
		this.atracciones = atracciones;
	}

	public void agregarTodasPromociones(Promocion[] promociones) {
		this.promociones = promociones;
	}

	@Override
	public String toString() {
		return "Cajero [Usuarios = \n" + Arrays.toString(usuarios) + "] \n" + " [Atracciones = \n"
				+ Arrays.toString(atracciones) + "] \n" + " [Promociones = \n" + Arrays.toString(promociones) + "]";
	}

	public Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	/*
	 * public static void main(String[] Args) { recorredorUsuarios(); }
	 * 
	 * private static void recorredorUsuarios() {
	 * 
	 * int c;
	 * 
	 * for (c = 0; c < usuarios.length; c++) { while (usuarios[c].getPresupuesto() >
	 * 0 && usuarios[c].getTiempoDisponible() > 0) {
	 * Oferta.creadorDeOfertas(usuarios[c]); } }
	 * 
	 * }
	 */

}