package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

	public static String[] obtenerDatos(String path) throws IOException {
	        ArrayList<String> lineas = new ArrayList<>();
	        
	    
	        try (BufferedReader br = new BufferedReader(new FileReader(Pais.class.getResource(path).getPath()))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                lineas.add(linea);
	            }
	        }

	        if (lineas.size() < 3) {
	            throw new IllegalArgumentException("El archivo debe contener al menos 3 líneas.");
	        }

	       
	        Random random = new Random();
	        ArrayList<String> seleccionadas = new ArrayList<>();
	        while (seleccionadas.size() < 3) {
	            int indice = random.nextInt(lineas.size());
	            if (!seleccionadas.contains(lineas.get(indice))) {
	                seleccionadas.add(lineas.get(indice));
	            }
	        }

	        
	        String[] datos = new String[4]; 
	        for (int i = 0; i < seleccionadas.size(); i++) {
	            String[] partes = seleccionadas.get(i).split(";");
	            if (partes.length == 2) {
	                String nombre = partes[0].trim();
	                String ruta = partes[1].trim();

	                if (i == 0) {
	                    datos[0] = ruta;  
	                    datos[1] = nombre; 
	                } else {
	                    datos[i + 1] = nombre; 
	                }
	            }
	        }

	        return datos;
		 }
}



