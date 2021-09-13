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

	protected abstract int getTiempoPromedio();

	protected abstract int calculoPromocion();

	@Override
	public String toString() {
		return "Promocion [nombre = " + nombre + ", tipoDePromocion = " + tipo + ", atracciones = "
				+ Arrays.toString(atracciones) + "]";
	}

}