package paquete;

import java.util.Arrays;

public abstract class Promocion {

	private String nombre;
	private TipoAtraccion tipo;
	protected Atraccion[] atracciones;

	public Promocion(String nombre, TipoAtraccion tipo, Atraccion[] atracciones) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public int getTiempoPromedio() {
		int devolucionTiempo = 0;

		for (int c = 0; c < atracciones.length; c++) {
			devolucionTiempo += atracciones[c].getTiempoPromedio();
		}

		return devolucionTiempo;
	}

	protected abstract int calculoPromocion();

	@Override
	public String toString() {
		String nombreAtracciones = "";

		for (int i = 0; i < atracciones.length; i++) {
			nombreAtracciones += atracciones[i].getNombre();

			if (!(i == atracciones.length - 1)) {
				nombreAtracciones += ", ";
			} else {
				nombreAtracciones += ".";
			}
		}
		return "Promocion: " + nombre + ", de tipo: " + tipo + ", atracciones: " + nombreAtracciones
				+ ", tiempo promedio: " + getTiempoPromedio() + ", que cuesta " + calculoPromocion() + "\n";
	}

	public String getNombre() {
		return nombre;
	}

	public Atraccion getAtraccionElemento(int i) {
		return atracciones[i];
	}

	public Atraccion[] getAtraccion() {
		return atracciones;
	}

}