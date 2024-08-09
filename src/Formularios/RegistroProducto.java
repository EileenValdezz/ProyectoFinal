package Formularios; 

import Clases.Producto;
import Mantenimiento.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RegistroProducto extends JFrame {
	
	private Image backgroundImage;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtCategoria;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroProducto() {
    	// Configuración de la ventana principal del formulario de registro de producto
        setTitle("Proyecto Final");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Configuracion del icono de la ventana
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

        JLabel lblTitulo = new JLabel("Registro de Producto", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 5, getWidth(), 40);
        panel.add(lblTitulo);
        
        // Etiquetas y campos de texto para los datos del producto
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 60, 150, 25);
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(200, 60, 150, 25);
        add(txtNombre);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(50, 100, 150, 25);
        lblMarca.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(200, 100, 150, 25);
        add(txtMarca);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(50, 140, 150, 25);
        lblCategoria.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(200, 140, 150, 25);
        add(txtCategoria);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 180, 150, 25);
        lblPrecio.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(200, 180, 150, 25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Cantidad Disponible:");
        lblStock.setBounds(50, 220, 150, 25);
        lblStock.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(200, 220, 150, 25);
        add(txtStock);

        // Botones para guardar y cancelar
        btnRegistrar = new JButton("Guardar");
        btnRegistrar.setBounds(50, 280, 100, 25);
        btnRegistrar.setBackground(Color.GRAY);
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220, 280, 100, 25);
        btnCancelar.setBackground(Color.GRAY);
        add(btnCancelar);

        // Accion para el boton Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });

        // Accion para el boton Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarRegistro();
            }
        });
    }

    private void registrarProducto() {
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        String categoria = txtCategoria.getText();
        String precioStr = txtPrecio.getText();
        String stockStr = txtStock.getText();

        if (nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
            return;
        }

        int precio;
        int stock;

        try {
            precio = (int) Double.parseDouble(precioStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio y stock deben ser numeros validos");
            return;
        }

        Producto producto = new Producto(0, nombre, marca, categoria, precio, stock);
        ProductoDAO productoDAO = new ProductoDAO();

        // Registro del producto en la base de datos
        if (productoDAO.registrarProducto(producto)) {
            JOptionPane.showMessageDialog(this, "Producto registrado exitosamente");
            new ProductoLista().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el producto");
        }
    }

    private void cancelarRegistro() {
        new ProductoLista().setVisible(true);
        dispose();
    } 
}
