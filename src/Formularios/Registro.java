package Formularios; 

import Clases.Usuario;
import Mantenimiento.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Registro extends JFrame {
	
	private Image backgroundImage;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JPasswordField txtContraseña;
    private JPasswordField txtConfirmarContraseña;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public Registro() {
    	// Configuracion de la ventana principal del formulario de registro
        setTitle("Proyecto Final");
        setSize(400, 450);
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
        
        JLabel lblTitulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 5, getWidth(), 40);
        panel.add(lblTitulo);

        // Etiquetas y campos de texto para los datos del usuario
        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(50, 60, 150, 25);
        lblNombreUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 60, 150, 25);
        add(txtNombreUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 100, 150, 25);
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(200, 100, 150, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 140, 150, 25);
        lblApellido.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(200, 140, 150, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 180, 150, 25);
        lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(200, 180, 150, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 220, 150, 25);
        lblEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(200, 220, 150, 25);
        add(txtEmail);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(50, 260, 150, 25);
        lblContraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblContraseña);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(200, 260, 150, 25);
        add(txtContraseña);

        JLabel lblConfirmarContraseña = new JLabel("Confirmar Contraseña:");
        lblConfirmarContraseña.setBounds(50, 300, 150, 25);
        lblConfirmarContraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblConfirmarContraseña);

        txtConfirmarContraseña = new JPasswordField();
        txtConfirmarContraseña.setBounds(200, 300, 150, 25);
        add(txtConfirmarContraseña);

        // Boton para registrar un usuario
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(80, 350, 100, 25);
        btnRegistrar.setBackground(Color.GRAY);
        add(btnRegistrar);

        // Boton para cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220, 350, 100, 25);
        btnCancelar.setBackground(Color.GRAY);
        add(btnCancelar);

        // Accion para el boton Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
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

    private void registrarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String contraseña = new String(txtContraseña.getPassword());
        String confirmarContraseña = new String(txtConfirmarContraseña.getPassword());
        
         // Verificar que no hayan campos vacios
        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            String campoFaltante = "";
            if (nombreUsuario.isEmpty()) campoFaltante = "Nombre de Usuario";
            else if (nombre.isEmpty()) campoFaltante = "Nombre";
            else if (apellido.isEmpty()) campoFaltante = "Apellido";
            else if (telefono.isEmpty()) campoFaltante = "Teléfono";
            else if (email.isEmpty()) campoFaltante = "Email";
            else if (contraseña.isEmpty()) campoFaltante = "Contraseña";
            else if (confirmarContraseña.isEmpty()) campoFaltante = "Confirmar Contraseña";

            JOptionPane.showMessageDialog(this, "El campo " + campoFaltante + " es obligatorio");
            return;
        }
        
        // Validacion de coincidencia de contraseñas
        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }

        Usuario usuario = new Usuario(0, nombreUsuario, nombre, apellido, telefono, email, contraseña);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Registro del usuario en la base de datos
        if (usuarioDAO.registrarUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
            new Login().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario");
        }
    }

    private void cancelarRegistro() {
        new Login().setVisible(true);
        dispose();
    }
} 