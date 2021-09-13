package paquete;

public class PromocionPorcentual extends Promocion {

	private int descuento;

	public PromocionPorcentual(String nombre, TipoAtraccion tipo, Atraccion[] atracciones, int descuento) {
		super(nombre, tipo, atracciones);
		this.descuento = descuento;
	}

	public int calculoPromocion() {
		int devolucionCalculo = 0;
		for (int c = 0; c < atracciones.length; c++) {
			devolucionCalculo += atracciones[c].getCostoVisita();
		}
		// aplicar el cambio para el descuento
		double calculoDescuento = (devolucionCalculo / 100) * descuento;

		return (int) (devolucionCalculo - calculoDescuento);

	}

}