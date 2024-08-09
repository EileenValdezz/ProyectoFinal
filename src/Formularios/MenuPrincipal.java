package Formularios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuPrincipal extends JFrame {
	
	private Image backgroundImage;

    private static final long serialVersionUID = 1L;
    private JButton btnGestionUsuarios;
    private JButton btnGestionProductos;
    private JButton btnCerrarSesion;

    public MenuPrincipal() {
        setTitle("Proyecto Final");
        setSize(450, 400); // Ajuste para el tamaño de la ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // No permite maximizar

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

        JLabel lblTitulo = new JLabel("Menú Principal", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 36));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 20, getWidth(), 40);
        panel.add(lblTitulo);

        // Ajuste para centrar los botones con iconos y nombres centrados debajo
        int buttonWidth = 130;
        int buttonHeight = 130;
        int spacing = 30;
        int totalWidth = buttonWidth * 2 + spacing;
        int xPos = (getWidth() - totalWidth) / 2;
        int yPos = 100;

        // Boton para gestionar usuarios
        btnGestionUsuarios = new JButton("Usuarios");
        btnGestionUsuarios.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGestionUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGestionUsuarios.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/usuario.png"))
                .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))); 
        btnGestionUsuarios.setBounds(xPos, yPos, buttonWidth, buttonHeight);
        btnGestionUsuarios.setBackground(Color.GRAY);
        panel.add(btnGestionUsuarios);

        // Boton para gestionar productos
        btnGestionProductos = new JButton("Productos");
        btnGestionProductos.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGestionProductos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGestionProductos.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/producto.png"))
                .getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))); 
        btnGestionProductos.setBounds(xPos + buttonWidth + spacing, yPos, buttonWidth, buttonHeight);
        btnGestionProductos.setBackground(Color.GRAY);
        panel.add(btnGestionProductos);

        // Boton para cerrar sesion
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds((getWidth() - 150) / 2, yPos + buttonHeight + spacing, 150, 40);
        btnCerrarSesion.setBackground(Color.GRAY);
        panel.add(btnCerrarSesion);

        // Accion para el boton "Gestion Usuarios"
        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionUsuarios();
            }
        });

        // Accion para el boton "Gestion Productos"
        btnGestionProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionProductos();
            }
        });

        // Accion para el boton "Cerrar Sesion"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private void abrirGestionUsuarios() {
        new UsuarioLista().setVisible(true);
        dispose();
    }

    private void abrirGestionProductos() {
        new ProductoLista().setVisible(true);
        dispose();
    }

    private void cerrarSesion() {
        new Login().setVisible(true);
        dispose();
    }
}
