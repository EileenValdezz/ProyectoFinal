package Formularios;

import Clases.Usuario;
import Mantenimiento.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Login extends JFrame {
	
	private Image backgroundImage;

    private static final long serialVersionUID = 1L;
    private JTextField txtNombreUsuario; // Campo de texto para ingresar el nombre de usuario
    private JPasswordField txtContraseña; // Campo de contraseña
    private JButton btnIniciarSesion; // Boton para iniciar sesion
    private JButton btnRegistrarse; // Boton para abrir el formulario de registro

    public Login() {
        setTitle("Proyecto Final"); // Establecer el titulo de la ventana
        setSize(400, 400);         // Establecer el tamaño de la ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setLocationRelativeTo(null);
        setResizable(false); // Deshabilita la posibilidad de maximizar la ventana

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
        
        // logo en la parte superior
        JLabel lblLogo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/logo.png"))
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))); // Ajuste del tamaño del logo
        lblLogo.setBounds((getWidth() - 100) / 2, 20, 100, 100);
        panel.add(lblLogo);

        JLabel lblTitulo = new JLabel("Login", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 38));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 120, getWidth(), 50);
        panel.add(lblTitulo);
        
        // Campos de nombre de usuario y contraseña
        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(50, 190, 150, 25); 
        lblNombreUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        panel.add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 190, 150, 25);
        panel.add(txtNombreUsuario);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(50, 230, 150, 25);
        lblContraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        panel.add(lblContraseña);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(200, 230, 150, 25);
        panel.add(txtContraseña);
        
        // Boton para iniciar sesion
        btnIniciarSesion = new JButton("Entrar");
        btnIniciarSesion.setBounds(80, 280, 110, 30);
        btnIniciarSesion.setBackground(Color.GRAY); // Establecer el color de fondo del boton
        panel.add(btnIniciarSesion);

        // Boton para abrir el formulario de registro
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(210, 280, 110, 30); 
        btnRegistrarse.setBackground(Color.GRAY);
        panel.add(btnRegistrarse);

        // Accion para el boton "Iniciar Sesion"
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(); // Llamar al metodo para iniciar sesion
            }
        });

     // Accion para el boton "Registrarse"
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });
    }

    // Metodo para iniciar sesion
    private void iniciarSesion() {
        String nombreUsuario = txtNombreUsuario.getText();
        String contraseña = new String(txtContraseña.getPassword());

        // Verifica si los campos estan vacíos
        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar su usuario y contraseña, si no esta registrado debe registrarse");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(); // Acceso a datos de usuario
        Usuario usuario = usuarioDAO.obtenerUsuario(nombreUsuario, contraseña); // Obtiene el usuario de la base de datos

        // Verifica si el usuario existe
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            dispose();
            new MenuPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "El usuario no está registrado.");
        }
    }

   // Metodo para abrir el formulario de registro
    private void abrirRegistro() {
        new Registro().setVisible(true);
        dispose();
    }

    // Metodo principal para ejecutar la aplicación
    public static void main(String[] args) {
    	System.out.println("Este es un mensaje nuevo");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true); // Crea y muestra la ventana de inicio de sesion
            }
        });
    }
}
