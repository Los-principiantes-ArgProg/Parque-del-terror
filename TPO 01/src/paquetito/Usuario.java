package paquetito;

public class Usuario {

	private String nombre="";
	private int presupuesto=0;
	private int tiempoDisponible=0;
	private TipoAtraccion atraccionPreferida;
	
	public Usuario(String nombre, int presupuesto, int tiempoDisponible, TipoAtraccion atraccionPreferida) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setTiempoDisponible(int tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public int getTiempoDisponible() {
		return tiempoDisponible;
	}

	public TipoAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}
	
	
	

}
