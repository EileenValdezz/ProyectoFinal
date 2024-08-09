package Mantenimiento; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Clases.Producto;
import Conexionbd.Conexion;

    //Aqui se hace uso de un patrón de diseño: DAO (Data Access Object) que permite abstraer la logica de acceso a datos de nuestro proyecto

    public class ProductoDAO {  
    
    private Conexion conexion;

    public ProductoDAO() {
        this.conexion = new Conexion();
    }
 
    //Registra un nuevo producto en la base de datos
    //Retorna true si el registro fue exitoso, false en caso contrario
    
    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (idProducto, NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getMarcaProducto());
            ps.setString(4, producto.getCategoriaProducto());
            ps.setDouble(5, producto.getPrecioProducto());
            ps.setInt(6, producto.getStockProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    //Obtiene todos los productos de la base de datos
    //Retorna una lista de objetos Producto
    
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("NombreProducto"),
                        rs.getString("MarcaProducto"),
                        rs.getString("CategoriaProducto"),
                        rs.getInt("PrecioProducto"),
                        rs.getInt("StockProducto")
                );
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    //Actualiza la informacion de un producto que ya existe en la base de datos
    //Retorna true si la actualizacion fue exitosa y false en caso contrario
    
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET NombreProducto = ?, MarcaProducto = ?, CategoriaProducto = ?, PrecioProducto = ?, StockProducto = ? WHERE idProducto = ?";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getMarcaProducto());
            ps.setString(3, producto.getCategoriaProducto());
            ps.setDouble(4, producto.getPrecioProducto());
            ps.setInt(5, producto.getStockProducto());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Elimina un producto de la base de datos por su ID
    // Retorna true si la eliminacion fue exitosa y false si no lo fue
    
    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE idProducto = ?";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
