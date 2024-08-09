package Mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Clases.Usuario;
import Conexionbd.Conexion;

    //Aqui se hace uso de un patrón de diseño: DAO (Data Access Object) que permite abstraer la logica de acceso a datos de nuestro proyecto
 
    public class UsuarioDAO {

    private Conexion conexion;

    public UsuarioDAO() {
        this.conexion = new Conexion();
    }

    //Registra un nuevo usuario en la base de datos
    //Retorna true si el registro fue exitoso, false en caso contrario
    
    public boolean registrarUsuario(Usuario usuario) {
        Connection con = conexion.conectar();
        String sql = "INSERT INTO usuarios (idUser, UserName, Nombre, Apellido, Telefono, Email, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getIdUser());
            ps.setString(2, usuario.getUserName());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     
    // Obtiene un usuario de la base de datos por nombre de usuario y contraseña
    // Retorna un objeto Usuario si se encuentra el usuario, null en caso contrario
    
    public Usuario obtenerUsuario(String nombreUsuario, String contraseña) {
        Connection con = conexion.conectar();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE UserName = ? AND Password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("idUser"),
                        rs.getString("UserName"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    //Obtiene todos los usuarios de la base de datos
    //Retorna una lista de objetos Usuario
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        Connection con = conexion.conectar();
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUser"),
                        rs.getString("UserName"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
    
    //Actualiza la informacion de un usuario que ya existe en la base de datos
    //Retorna true si la actualizacion se realizo con exito y false en caso contrario

    public boolean actualizarUsuario(Usuario usuario) {
        Connection con = conexion.conectar();
        String sql = "UPDATE usuarios SET UserName = ?, Nombre = ?, Apellido = ?, Telefono = ?, Email = ?, Password = ? WHERE idUser = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUserName());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getPassword());
            ps.setInt(7, usuario.getIdUser());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Elimina un usuario de la base de datos por su ID y retorna true si la eliminaci0n fue exitosa y false en caso contrario

    public boolean eliminarUsuario(int idUser) {
        Connection con = conexion.conectar();
        String sql = "DELETE FROM usuarios WHERE idUser = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
