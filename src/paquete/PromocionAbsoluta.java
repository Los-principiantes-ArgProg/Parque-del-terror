package paquete;

public class PromocionAbsoluta extends Promocion {
	
	private int precioPromocion;

	public PromocionAbsoluta(String nombre, TipoAtraccion tipo, Atraccion[] atracciones, int precioPromocion) {
		super(nombre, tipo, atracciones);
		this.precioPromocion = precioPromocion;
	}
	
	public int calculoPromocion() {
		return precioPromocion;
	}

}