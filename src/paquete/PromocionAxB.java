package paquete;

public class PromocionAxB extends Promocion {

	public PromocionAxB(String nombre, TipoAtraccion tipo, Atraccion[] atracciones) {
		super(nombre, tipo, atracciones);
		// TODO Auto-generated constructor stub
	}

	public int calculoPromocion() {
		int devolucionCalculo = 0;
		int largoAtracciones = atracciones.length;
		for (int c = 0; c < largoAtracciones - 1; c++) {
			devolucionCalculo += atracciones[c].getCostoVisita();
		}
		return devolucionCalculo;
	}

}