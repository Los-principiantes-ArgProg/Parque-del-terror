package paquete;

public class Atraccion {

	private String nombre = "";
	private int costoVisita = 0;
	private int tiempoPromedio = 0;
	private int cupoMaximoDiario = 0;
	private TipoAtraccion atraccion;

	public Atraccion(String nombre, int costoVisita, int tiempoPromedio, int cupoMaximoDiario, TipoAtraccion tipo) {

		this.nombre = nombre;
		this.costoVisita = costoVisita;
		this.tiempoPromedio = tiempoPromedio;
		this.cupoMaximoDiario = cupoMaximoDiario;
		this.atraccion = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoAtraccion getTipoAtraccion() {
		return atraccion;
	}

	public int getCostoVisita() {
		return costoVisita;
	}

	public int getTiempoPromedio() {
		return tiempoPromedio;
	}

	public int getCupoMaximoDiario() {
		return cupoMaximoDiario;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre = " + nombre + ", tipo de atraccion = " + atraccion + ", costo de visita = "
				+ costoVisita + ", tiempo promedio = " + tiempoPromedio + "]\n";
	}

}
