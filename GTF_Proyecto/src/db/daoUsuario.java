package db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import domain.Usuarios;

public class daoUsuario {
	Conexion cx;
	
	public daoUsuario() {
		cx= new Conexion();
	}
	
	public boolean insertarUsuario(Usuarios user) {
		PreparedStatement ps=null;
		PreparedStatement psPuntuaciones=null;
		PreparedStatement psReto=null;
		
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO usuarios VALUES(?,?)");
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getContrasenya());
			ps.executeUpdate();
			
			psPuntuaciones= cx.conectar().prepareStatement("INSERT INTO puntuaciones VALUES(?,0)");
			psPuntuaciones.setString(1, user.getUsuario());
			psPuntuaciones.executeUpdate();
			
			psReto= cx.conectar().prepareStatement("INSERT INTO retoDiario VALUES(?,0,0)");
			psReto.setString(1, user.getUsuario());
			psReto.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	  public boolean comprobarUsuario(Usuarios user) {      
		  String sql = "SELECT contrasenya FROM usuarios WHERE usuario = ?";

	        try (Connection connection = cx.conectar()) { 
	            if (connection == null) {
	                System.out.println("Error: No se pudo conectar a la base de datos.");
	                return false; 
	            }

	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
	                ps.setString(1, user.getUsuario());
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    return true; 
	                } else {
	                    return false; 
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
	            return false; 
	        }
	    }
	  
	  public boolean comprobarContrasena(Usuarios user) {
		    String sql = "SELECT contrasenya FROM usuarios WHERE usuario = ?";

		    try (Connection connection = cx.conectar()) {
		        if (connection == null) {
		            System.out.println("Error: No se pudo conectar a la base de datos.");
		            return false;
		        }

		        try (PreparedStatement ps = connection.prepareStatement(sql)) {
		            ps.setString(1, user.getUsuario());  
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                String contrasenaBD = rs.getString("contrasenya");
		                return contrasenaBD.equals(user.getContrasenya());  
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al acceder a la base de datos: " + e.getMessage());
		    }
		    return false;
		}
	   
	  
	  
	  public boolean actualizarUsuario(String usuarioAntiguo, String nuevoNombre) {
		    PreparedStatement ps = null;

		    try (Connection connection = cx.conectar()) { //////////// CITAR COMO IAG
		        if (connection == null) {
		            System.out.println("Error: No se pudo conectar a la base de datos.");
		            return false;
		        }

		        String verificarSQL = "SELECT usuario FROM usuarios WHERE usuario = ?";
		        try (PreparedStatement psVerificar = connection.prepareStatement(verificarSQL)) {
		            psVerificar.setString(1, nuevoNombre);
		            ResultSet rs = psVerificar.executeQuery();
		            if (rs.next()) {
		                System.out.println("Error: El nombre de usuario ya está en uso.");
		                return false; 
		            }
		        }

		        String updateSQL = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
		        try (PreparedStatement psActualizar = connection.prepareStatement(updateSQL)) {
		            psActualizar.setString(1, nuevoNombre);
		            psActualizar.setString(2, usuarioAntiguo);

		            int rowsAffected = psActualizar.executeUpdate();
		            return rowsAffected > 0;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	  
	  public boolean actualizarContrasena(String usuario, String nuevaContrasena) {
		    PreparedStatement ps = null;										///////PASARLE USER USUARIO DESPUES DE CAMBIARLO EN AJUSTES 

		    try {
		        ps = cx.conectar().prepareStatement("UPDATE usuarios SET contrasenya = ? WHERE usuario = ?");
		        ps.setString(1, nuevaContrasena); 
		        ps.setString(2, usuario); 

		        int rowsAffected = ps.executeUpdate();
		        cx.desconectar();

		        return rowsAffected > 0; 
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		}
	  
	  public boolean actualizarPuntuacion(Usuarios usuario) {
		    PreparedStatement ps = null;
		    try {
		        ps = cx.conectar().prepareStatement("UPDATE puntuaciones SET puntuacion = ? WHERE usuario = ?");
		        ps.setInt(1, usuario.getPuntuacion());
		        ps.setString(2, usuario.getUsuario());
		        ps.executeUpdate();
		        cx.desconectar();
		        return true;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		   }
	  
	  public boolean actualizarAciertosYFallos(Usuarios usuario) {
		    PreparedStatement psAciertos = null;
		    PreparedStatement psFallos = null;
		    try {
		        psAciertos = cx.conectar().prepareStatement("UPDATE retoDiario SET aciertos = ? WHERE usuario = ?");
		        psAciertos.setInt(1, usuario.getAciertos());
		        psAciertos.setString(2, usuario.getUsuario());
		        psAciertos.executeUpdate();
		        
		        psFallos = cx.conectar().prepareStatement("UPDATE retoDiario SET fallos = ? WHERE usuario = ?");
		        psFallos.setInt(1, usuario.getFallos());
		        psFallos.setString(2, usuario.getUsuario());
		        psFallos.executeUpdate();
		        cx.desconectar();
		        return true;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		   }
	  public int obtenerPuntuacion(String usuario) {       
		  String sql = "SELECT puntuacion FROM puntuaciones WHERE usuario = ?";

	        try (Connection connection = cx.conectar()) { 
	            if (connection == null) {
	                System.out.println("Error: No se pudo conectar a la base de datos.");
	                return 0; 
	            }

	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
	                ps.setString(1, usuario);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    return rs.getInt("puntuacion"); 
	                } else {
	                    return 0; 
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
	            return 0; 
	        }
	    }
	  
	  public int obtenerAciertos(String usuario) {       
		  String sql = "SELECT aciertos FROM retoDiario WHERE usuario = ?";
		 

	        try (Connection connection = cx.conectar()) { 
	            if (connection == null) {
	                System.out.println("Error: No se pudo conectar a la base de datos.");
	                return 0; 
	            }

	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
	                ps.setString(1, usuario);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    return rs.getInt("aciertos"); 
	                } else {
	                    return 0; 
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
	            return 0; 
	        }
	        
	    }
	  
	  public int obtenerFallos(String usuario) {       
		  String sql = "SELECT fallos FROM retoDiario WHERE usuario = ?";
		 

	        try (Connection connection = cx.conectar()) { 
	            if (connection == null) {
	                System.out.println("Error: No se pudo conectar a la base de datos.");
	                return 0; 
	            }

	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
	                ps.setString(1, usuario);
	                ResultSet rs = ps.executeQuery();
	                if (rs.next()) {
	                    return rs.getInt("fallos"); 
	                } else {
	                    return 0; 
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
	            return 0; 
	        }
	        
	    }
	  
	  public void agregarPartidaAlHistorial(Usuarios user, int puntuacionPartida, String dificultad, String modo) {
		    String archivoHistorial = "historial_partidas_" + user.getUsuario() + ".txt";
		    
		    String registro = String.format(
		        "Fecha: %s, Usuario: %s, Dificultad: %s, Modo: %s, Puntuación: %d",
		        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
		        user.getUsuario(),
		        dificultad,
		        modo,
		        puntuacionPartida
		    );

		    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoHistorial, true))) {
		        writer.write(registro);
		        writer.newLine();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		
	}
