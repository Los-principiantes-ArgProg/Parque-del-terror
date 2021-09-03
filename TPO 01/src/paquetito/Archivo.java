package paquetito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Archivo {

	//Importa de txt y devuelve un array de Objetos tipo Atraccion
	public static Atraccion[] importarAtracciones() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		Atraccion[] atracciones = null;
		
		try {
			archivo = new File("entrada/atracciones.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			int cantidad = Integer.parseInt(br.readLine());
			atracciones = new Atraccion[cantidad];
			
			int indice = 0;
			String linea;
			while ((linea = br.readLine()) != null) {
					String [] datosAtraccion = linea.split(",");
					
					String nombre = datosAtraccion[0];
					int costoVisita = Integer.parseInt(datosAtraccion[1]);
					int tiempoPromedio = Integer.parseInt(datosAtraccion[2]);
					int cupoMaximoDiario = Integer.parseInt(datosAtraccion[3]);
					TipoAtraccion tipo = TipoAtraccion.valueOf(datosAtraccion[4]);
					
					atracciones[indice++] = new Atraccion(nombre, costoVisita, tiempoPromedio, cupoMaximoDiario, tipo);
					linea = br.readLine();
					}
				
				return atracciones;
				
			} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return atracciones;
	}
	
	public static Usuario[] importarUsuarios() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		Usuario[] usuarios = null;
		
		try {
			archivo = new File("entrada/usuarios.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			int cantidad = Integer.parseInt(br.readLine());
			usuarios = new Usuario[cantidad];
			
			int indice = 0;
			String linea = br.readLine();
			while (linea != null) {
				String[] datosUsuarios = linea.split(",");
				
				String nombre = datosUsuarios[0];
				int presupuesto = Integer.parseInt(datosUsuarios[1]);
				int tiempoDisponible = Integer.parseInt(datosUsuarios[2]);
				TipoAtraccion atraccionPreferida = TipoAtraccion.valueOf(datosUsuarios[3]);
				
				usuarios[indice++] = new Usuario(nombre,presupuesto,tiempoDisponible,atraccionPreferida);
				linea = br.readLine();
			}
			return usuarios;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return usuarios;
	}
}
	