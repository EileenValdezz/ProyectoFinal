package Formularios;

import Clases.Producto;
import Mantenimiento.ProductoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class ProductoLista extends JFrame {
	
	private Image backgroundImage;

    private static final long serialVersionUID = 1L;
    private JTable tableProductos;
    private DefaultTableModel model;
    private JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnVolver;

    public ProductoLista() {
        setTitle("Proyecto Final");
        setSize(620, 440);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
     // Cargar el icono de la ventana
        URL iconURL = getClass().getResource("/icono2.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }

        backgroundImage = new ImageIcon(getClass().getResource("/fondo1.jpg")).getImage();
        
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setLayout(null);
        setContentPane(panel);
        
        // Titulo de la lista de productos
        JLabel lblTitulo = new JLabel("LISTA DE PRODUCTOS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 10, getWidth(), 40);
        panel.add(lblTitulo);

        // Configuracion de la tabla de productos
        model = new DefaultTableModel(new String[]{"Nombre", "Marca", "Categoría", "Precio", "Cantidad Disponible"}, 0);
        tableProductos = new JTable(model);
        tableProductos.setBackground(new Color(230, 190, 255));
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        scrollPane.setBounds(30, 60, 540, 250);
        panel.add(scrollPane);
        
        // Botones de accion
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(75, 330, 100, 30);
        btnNuevo.setBackground(Color.GRAY);
        panel.add(btnNuevo);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(Color.GRAY);
        btnActualizar.setBounds(185, 330, 100, 30);
        panel.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(295, 330, 100, 30);
        btnEliminar.setBackground(Color.GRAY);
        panel.add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(405, 330, 100, 30);
        btnVolver.setBackground(Color.GRAY);
        panel.add(btnVolver);

       // Carga los productos en la tabla
        cargarProductos();

        // Accion para el boton "Nuevo"
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistroProducto();
            }
        });

        // Accion para el boton "Actualizar"
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        // Accion para el boton "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        // Accion para el boton "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenuPrincipal();
            }
        });
    }

    private void cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();

        model.setRowCount(0); // Limpia la tabla

        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getNombreProducto(), producto.getMarcaProducto(), producto.getCategoriaProducto(), producto.getPrecioProducto(), producto.getStockProducto()});
        }
    }

    private void abrirRegistroProducto() {
        new RegistroProducto().setVisible(true);
        dispose();
    }

    private void actualizarProducto() {
        int row = tableProductos.getSelectedRow();
        if (row >= 0) {
            String nombreProducto = (String) tableProductos.getValueAt(row, 0);
            ProductoDAO productoDAO = new ProductoDAO();
            Producto producto = productoDAO.obtenerTodosLosProductos().stream()
                    .filter(p -> p.getNombreProducto().equals(nombreProducto))
                    .findFirst()
                    .orElse(null);
            if (producto != null) {
                new EditarProducto(producto).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener el producto");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para actualizar");
        }
    }

    private void eliminarProducto() {
        int row = tableProductos.getSelectedRow();
        if (row >= 0) {
            String nombreProducto = (String) tableProductos.getValueAt(row, 0);
            ProductoDAO productoDAO = new ProductoDAO();
            Producto producto = productoDAO.obtenerTodosLosProductos().stream()
                    .filter(p -> p.getNombreProducto().equals(nombreProducto))
                    .findFirst()
                    .orElse(null);
            if (producto != null && productoDAO.eliminarProducto(producto.getIdProducto())) {
                JOptionPane.showMessageDialog(this, "Producto eliminado");
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
        }
    }
   
    // Metodo para volver al menu principal
    private void volverMenuPrincipal() {
        new MenuPrincipal().setVisible(true);
        dispose();
    }
}
