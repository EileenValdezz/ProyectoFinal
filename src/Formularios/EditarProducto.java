package Formularios;

import Clases.Producto;
import Mantenimiento.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class EditarProducto extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtCategoria;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnActualizar;
    private JButton btnCancelar;
    private Producto producto;

    public EditarProducto(Producto producto) {
        this.producto = producto;

        setTitle("Editar Producto");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 190, 255));
        setResizable(false); // No permite maximizar la ventana
        
        URL iconURL = getClass().getResource("/icono2.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 30, 150, 25);
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombre);

        txtNombre = new JTextField(producto.getNombreProducto());
        txtNombre.setBounds(200, 30, 150, 25);
        add(txtNombre);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(50, 70, 150, 25);
        lblMarca.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblMarca);

        txtMarca = new JTextField(producto.getMarcaProducto());
        txtMarca.setBounds(200, 70, 150, 25);
        add(txtMarca);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(50, 110, 150, 25);
        lblCategoria.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblCategoria);

        txtCategoria = new JTextField(producto.getCategoriaProducto());
        txtCategoria.setBounds(200, 110, 150, 25);
        add(txtCategoria);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 150, 150, 25);
        lblPrecio.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblPrecio);

        txtPrecio = new JTextField(String.valueOf(producto.getPrecioProducto()));
        txtPrecio.setBounds(200, 150, 150, 25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Cantidad Disponible:");
        lblStock.setBounds(50, 190, 150, 25);
        lblStock.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblStock);

        txtStock = new JTextField(String.valueOf(producto.getStockProducto()));
        txtStock.setBounds(200, 190, 150, 25);
        add(txtStock);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(85, 240, 100, 25);
        btnActualizar.setBackground(Color.GRAY);
        btnActualizar.setForeground(Color.WHITE);
        add(btnActualizar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(215, 240, 100, 25);
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        add(btnCancelar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
            }
        });
    }

    private void actualizarProducto() {
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        String categoria = txtCategoria.getText();
        String precioStr = txtPrecio.getText();
        String stockStr = txtStock.getText();

        if (nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        int precio;
        int stock;

        try {
            precio = (int) Double.parseDouble(precioStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio y stock deben ser números válidos");
            return;
        }

        Producto productoActualizado = new Producto(producto.getIdProducto(), nombre, marca, categoria, precio, stock);
        ProductoDAO productoDAO = new ProductoDAO();

        if (productoDAO.actualizarProducto(productoActualizado)) {
            JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente");
            new ProductoLista().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto");
        }
    }

    private void cancelarEdicion() {
        new ProductoLista().setVisible(true);
        dispose();
    }
} 
