package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pais {
    private String nombrePais;
    private String ruta;

    public Pais(String nombrePais, String ruta) {
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

   
    public static Map<String, String> cargarPaises(String path) throws IOException {
        Map<String, String> mapaPaises = new HashMap<>();

        
        try (BufferedReader br = new BufferedReader(new FileReader(Pais.class.getResource(path).getPath()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String ruta = partes[1].trim();
                    mapaPaises.put(nombre, ruta);
                } else {
                    System.err.println("Línea ignorada (formato incorrecto): " + linea);
                }
            }
        }

        return mapaPaises;
    }
}