package clases;

import java.util.HashMap;
import java.util.Map;

public class Banderas {
	
	private Map<String, String> banderas;
	
	public Banderas() {
		banderas = new HashMap<>();
	}
	
	public void agregarBandera(String pais, String url) {
		banderas.put(pais, url);
	}
	
	public void eliminarBandera(String pais) {
		banderas.remove(pais);
	}
	
	public void mostrarBanderas() {
		for (Map.Entry<String, String> entrada : banderas.entrySet()) {
			System.out.println("País: " + entrada.getKey() + " - URL: " + entrada.getValue());
		}
	}

}
