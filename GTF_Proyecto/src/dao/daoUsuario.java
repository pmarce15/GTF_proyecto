package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import modelo.Usuarios;

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
			
			psReto= cx.conectar().prepareStatement("INSERT INTO retoDiario VALUES(?,0,0,0,0,0)");
			psReto.setString(1, user.getUsuario());
			psReto.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	  public boolean comprobarUsuario(Usuarios user) {       ////////////////////////////COMENTADO EN CLASE (HUGO)
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
	  
	  public boolean actualizarUsuario (String usuario, String nuevoNombre){
		  PreparedStatement ps = null;
		  
		  try {
			ps = cx.conectar().prepareStatement("UPDATE usuarios SET usuario = ? WHERE usuario = ?");
			ps.setString(1, nuevoNombre);
			ps.setString(2, usuario);
			
			int rowsAffected = ps.executeUpdate();
			cx.desconectar();
			
			return rowsAffected > 0; // si se actualiza al menos una fila, devuelve true
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	  }
	  
	  public boolean actualizarContrasena(String usuario, String nuevaContrasena) {
		    PreparedStatement ps = null;

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

		
	}
