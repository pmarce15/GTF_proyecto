package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Paises {
	
	private Map<String, String> mapaPaises;
	
	public Paises() {
		mapaPaises = new HashMap<>();
	}
	
	public void cargarPaises(String rutaArchivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Separar el nombre del país y la ruta usando el delimitador ';'
                String[] partes = linea.split(";");
                if (partes.length == 2) { // Asegurarse de que hay exactamente dos partes
                    String nombrePais = partes[0].trim(); // Nombre del país
                    String rutaImagen = partes[1].trim(); // Ruta de la imagen

                    // Agregar al HashMap
                    mapaPaises.put(nombrePais, rutaImagen);
                } else {
                    System.err.println("Línea mal formateada: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al leer el archivo: " + rutaArchivo);
        }
	}
	
	 public HashMap<String, String> getmapaPaises() {
	        return (HashMap<String, String>) mapaPaises;
	    }
	

}

