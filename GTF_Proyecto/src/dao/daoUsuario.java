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
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO usuarios VALUES(?,?)");
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getContrasenya());
			ps.executeUpdate();
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
	  
	  public boolean actualizarUsuario (String usuario, String nuevoNombre){
		  PreparedStatement ps = null;
		  
		  try {
			ps = cx.conectar().prepareStatement("UPDATE usuarios SET usuario = ? WHERE id_usuario = ?");
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
	}
