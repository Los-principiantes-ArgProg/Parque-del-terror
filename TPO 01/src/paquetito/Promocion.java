package paquetito;

public class Promocion {
	
	

	public static double promocionPorcentual(Atraccion atraccion1, Atraccion atraccion2) {

		
		return (atraccion1.getCostoVisita() + atraccion2.getCostoVisita()) * 0.8;
	}

	public static boolean promocionAbsoluta(Atraccion atraccion1, Atraccion atraccion2, Atraccion atraccion3) {

		if(atraccion1.getTipoAtraccion()==atraccion2.getTipoAtraccion()&&atraccion2.getTipoAtraccion()==atraccion3.getTipoAtraccion()) {
			return true;
		}
		return false;
		
		}
	

}
