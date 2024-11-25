package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pais {
	
	String nombrePais;
	String ruta;

	
	public Pais(String nombrePais, String ruta) {
		super();
		this.nombrePais = nombrePais;
		this.ruta = ruta;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ArrayList<Pais> paisesDeNivel1(String rutaFichero) throws NullPointerException{
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		String linea = null;
		try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))){
			while ((linea = br.readLine()) != null) {
				String[] separador = linea.split(";");
				if (separador.length == 2) {
					String nombre = separador[0];
					String rutaImagen = separador[1];
					Pais pais = new Pais(nombre, rutaImagen);
					listaPaises.add(pais);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPaises;
	}
	
}

