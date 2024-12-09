package domain;

public class Usuarios {
	String usuario;
	String contrasenya;
	int puntuacion;
	int aciertos;
	int fallos;
	
	private String dificultad;
	private String modoJuego;
	
	public Usuarios() {
		
	}

	public Usuarios(String usuario, int puntuacion, String dificultad, String modoJuego) {
		this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.dificultad = dificultad;
        this.modoJuego = modoJuego;
	}
	
	public Usuarios(String usuario, int puntuacion) {
		
	}
	
	public Usuarios(String usuario, String contrasenya) {
		
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenya() {
		return contrasenya;
	}


	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public int getFallos() {
		return fallos;
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;
	}
	
	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getModoJuego() {
		return modoJuego;
	}

	public void setModoJuego(String modoJuego) {
		this.modoJuego = modoJuego;
	}
	
	
	

}
