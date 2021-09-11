package paquetito;

import java.io.*;
import java.io.IOException;

public abstract class Archivo {

	// public static Cajero cajero = new Cajero();

	// Importa de txt y devuelve un array de Objetos tipo Atraccion
	public static Atraccion[] obtenerAtraccionesDesdeArchivo() {
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
			String linea = br.readLine();
			while (linea != null) {
				String[] datosAtraccion = linea.split(",");

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

	public static Usuario[] obtenerUsuariosDesdeArchivo() {
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

				usuarios[indice++] = new Usuario(nombre, presupuesto, tiempoDisponible, atraccionPreferida);
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

	public static Promocion[] obtenerPromocionesDesdeArchivo(Cajero cajero) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		Promocion[] promociones = null;

		try {
			archivo = new File("entrada/promociones.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			int cantidad = Integer.parseInt(br.readLine());
			promociones = new Promocion[cantidad];

			int indice = 0;
			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromocion = linea.split(",");

				String tipoPromocion = datosPromocion[0];
				String nombre = datosPromocion[1];
				TipoAtraccion tipo = TipoAtraccion.valueOf(datosPromocion[2]);
				String[] atraccionesString = datosPromocion[3].split(";");
				Atraccion[] atracciones = new Atraccion[atraccionesString.length];

				for (int i = 0; i < atraccionesString.length; i++) {
					atracciones[i] = cajero.obtenerAtraccionPorNombre(atraccionesString[i]);
				}

				if (tipoPromocion.equals("Porcentual")) {

					double descuento = Double.parseDouble(datosPromocion[4]);

					promociones[indice++] = new PromocionPorcentual(nombre, tipo, atracciones, descuento);
				}
				if (tipoPromocion.equals("Absoluta")) {

					promociones[indice++] = new PromocionAbsoluta(nombre, tipo, atracciones);
				}
				if (tipoPromocion.equals("AxB")) {

					promociones[indice++] = new PromocionAxB(nombre, tipo, atracciones);
				}

				linea = br.readLine();
			}

			return promociones;

		} catch (

		IOException e) {
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
		return promociones;
	}

}