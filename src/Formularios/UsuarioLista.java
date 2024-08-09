package Formularios;

import Clases.Usuario;
import Mantenimiento.UsuarioDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class UsuarioLista extends JFrame {
	
	private Image backgroundImage;

    private static final long serialVersionUID = 1L;
    private JTable tableUsuarios;
    private DefaultTableModel model;
    private JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnVolver;

    public UsuarioLista() {
    	// Configuracion de la ventana principal del formulario de lista de usuarios
        setTitle("Proyecto Final");
        setSize(620, 440);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Configuracion del ícono de la ventana
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
        
        // Titulo de la lista de usuarios
        JLabel lblTitulo = new JLabel("LISTA DE USUARIOS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 10, getWidth(), 40);
        panel.add(lblTitulo);

        // Configuracion de la tabla de usuarios
        model = new DefaultTableModel(new String[]{"Usuario", "Nombre", "Apellido", "Teléfono", "Email"}, 0);
        tableUsuarios = new JTable(model);
        tableUsuarios.setBackground(new Color(230, 190, 255));
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        scrollPane.setBounds(30, 60, 540, 250);
        add(scrollPane);

         // Botones de accion nuevo, actualizar, eliminar y volver
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(75, 330, 100, 30);
        btnNuevo.setBackground(Color.GRAY);
        add(btnNuevo);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(185, 330, 100, 30);
        btnActualizar.setBackground(Color.GRAY);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(295, 330, 100, 30);
        btnEliminar.setBackground(Color.GRAY);
        add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(405, 330, 100, 30);
        btnVolver.setBackground(Color.GRAY);
        add(btnVolver);

       // Cargar los usuarios en la tabla
        cargarUsuarios();

        // Accion para el boton "Nuevo"
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistroUsuario();
            }
        });

         // Accion para el boton "Actualizar"
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        // Accion para el boton "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
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

    private void cargarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

        model.setRowCount(0); // Limpia la tabla

        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{usuario.getUserName(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail()});
        }
    }

    private void abrirRegistroUsuario() {
        new Registro().setVisible(true);
        dispose();
    }

    private void actualizarUsuario() {
        int row = tableUsuarios.getSelectedRow();
        if (row >= 0) {
            String userName = (String) tableUsuarios.getValueAt(row, 0);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerTodosLosUsuarios().stream()
                    .filter(u -> u.getUserName().equals(userName))
                    .findFirst()
                    .orElse(null);
            if (usuario != null) {
                new EditarUsuario(usuario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener el usuario");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar");
        }
    }

    private void eliminarUsuario() {
        int row = tableUsuarios.getSelectedRow();
        if (row >= 0) {
            String userName = (String) tableUsuarios.getValueAt(row, 0);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerTodosLosUsuarios().stream()
                    .filter(u -> u.getUserName().equals(userName))
                    .findFirst()
                    .orElse(null);
            if (usuario != null && usuarioDAO.eliminarUsuario(usuario.getIdUser())) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado");
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
        }
    }
  
    // Metodo para volver al menu principal
    private void volverMenuPrincipal() {
        new MenuPrincipal().setVisible(true); 
        dispose();
    }
}
