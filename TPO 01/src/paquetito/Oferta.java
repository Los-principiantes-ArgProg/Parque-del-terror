package paquetito;

import java.util.ArrayList;

public class Oferta {

		Atraccion[] paseos;
	Promocion[] promociones;

	
	

	public void creadorPaseos() {
		paseos=new Atraccion[8];

		paseos[0]= new Atraccion("Bosque encantado",50, 240, 25,TipoAtraccion.Paisajes);
		paseos[1]= new Atraccion("Lago del terror", 40, 120, 10, TipoAtraccion.Paisajes);
		paseos[2]= new Atraccion("Castillo de Dracula", 25, 75, 10, TipoAtraccion.Aventura);
		paseos[3]= new Atraccion("Cueva de la resurreccion", 20, 60, 5, TipoAtraccion.Aventura);
		paseos[4]=new Atraccion("La posada de Hades", 50,60,50, TipoAtraccion.Degustacion);
		paseos[5]=new Atraccion("Nido de dragones", 35,100,2, TipoAtraccion.Paisajes);
		paseos[6]=new Atraccion("Laberinto", 50,20,4, TipoAtraccion.Aventura);
		paseos[7]=new Atraccion("Canibalismo y cervezas", 28,60,20, TipoAtraccion.Degustacion);
		
		ArrayList <Atraccion> listaAtraccion = new ArrayList();
		
		for(int c=0;c<paseos.length;c++) {
			listaAtraccion.add(paseos[c]);
		}
	}

	public void creadorPromociones() {

	}

	public static void creadorDeOfertas(Usuario visitante) {
		
		
		switch (visitante.getAtraccionPreferida()) {
		
		case Aventura: 		ofertasAventura(visitante);break;
		case Degustacion: 	ofertasDegustacion(visitante);break;
		case Paisajes:		ofertasPaisajes(visitante);break;
		
	}
}

	private static void ofertasPaisajes(Usuario visitante) {
		if (visitante.getPresupuesto()>=500&&visitante.getTiempoDisponible()>=500) {
			
		}
		
	}

	private static void ofertasDegustacion(Usuario visitante) {
		// TODO Auto-generated method stub
		
	}

	private static void ofertasAventura(Usuario visitante) {
		// TODO Auto-generated method stub
		
	}
}
