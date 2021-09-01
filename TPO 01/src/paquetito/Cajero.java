package paquetito;

public class Cajero {
	private static Usuario[] visitante;

	public void creadorUsuarios() {
		visitante=new Usuario[6];
		
		visitante[0]= new Usuario("Sergio", 500, 600, TipoAtraccion.Paisajes);
		visitante[1]= new Usuario("Sebastian", 280, 500, TipoAtraccion.Aventura);
		visitante[2]= new Usuario("Soledad", 400, 120, TipoAtraccion.Paisajes);
		visitante[3]= new Usuario("Ivan", 1000, 700, TipoAtraccion.Degustacion);
		visitante[4]= new Usuario("Zacarias", 200, 500,TipoAtraccion.Aventura);
		visitante[5]= new Usuario("Valentina", 350, 200, TipoAtraccion.Degustacion);
		
	}
	
	public static void main(String [] Args) {
		 recorredorUsuarios();	
		 }

	private static void recorredorUsuarios() {
		
		int c;
		
		for(c=0;c<visitante.length;c++) {
			while(visitante[c].getPresupuesto()>0&&visitante[c].getTiempoDisponible()>0) {
				Oferta.creadorDeOfertas(visitante[c]);
			}
		}
		
	}

	
	

}
