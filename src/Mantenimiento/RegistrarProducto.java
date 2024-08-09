package Mantenimiento;

import Clases.Producto;
import java.util.List;

public class RegistrarProducto {
    private ProductoDAO productoDAO;

    public RegistrarProducto() {
        productoDAO = new ProductoDAO();
    }

    public boolean registrarProducto(Producto producto) {
        return productoDAO.registrarProducto(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.obtenerTodosLosProductos();
    }
}
