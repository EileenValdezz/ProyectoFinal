package Conexionbd; 

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            if (conn != null) {
                stmt = conn.createStatement();


                // Insertar datos de prueba para ver si se realizo la conexion a la base de datos
                String insertSQL = "INSERT INTO prueba (nombre, apellido, genero) VALUES ('Eileen', 'Valdez', 'F')";
                stmt.executeUpdate(insertSQL);

                // Realizar una consulta
                String selectSQL = "SELECT * FROM prueba";
                rs = stmt.executeQuery(selectSQL);

                // Mostrar resultados de la consulta
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String genero = rs.getString("genero");
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Genero: " + genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
