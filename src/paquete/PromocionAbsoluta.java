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

	/*public int calculoPromocion() {
		int devolucionCalculo = 0;
		for (int c = 0; c < atracciones.length; c++) {
			devolucionCalculo += atracciones[c].getCostoVisita();
		}
		devolucionCalculo = devolucionCalculo % 2;
		return devolucionCalculo;
	}*/

	public int getTiempoPromedio() {
		int devolucionTiempo = 0;

		for (int c = 0; c < atracciones.length; c++) {
			devolucionTiempo += atracciones[c].getTiempoPromedio();
		}

		return devolucionTiempo;
	}

}