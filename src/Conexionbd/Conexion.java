package Conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://almacenitla-db.ctam6uiuy8ez.us-east-1.rds.amazonaws.com:3306/almacenitlafinal?useSSL=false";
	private static final String USUARIO = "estuditlafinal";
	private static final String CONTRASEÑA = "itla123.";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}

	}

	public Connection conectar() {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
			System.out.println("Conexion realizada con exito");

		} catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		
		return conexion;
	}
}