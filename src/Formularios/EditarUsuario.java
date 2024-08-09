package Formularios;

import Clases.Usuario;
import Mantenimiento.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class EditarUsuario extends JFrame {

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
    private JButton btnActualizar;
    private JButton btnCancelar;
    private Usuario usuario;

    public EditarUsuario(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Editar Usuario");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 190, 255)); 
        setResizable(false); // No permite maximizar la pantalla
        
        URL iconURL = getClass().getResource("/icono2.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(50, 30, 150, 25);
        lblNombreUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField(usuario.getUserName());
        txtNombreUsuario.setBounds(200, 30, 150, 25);
        add(txtNombreUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 70, 150, 25);
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblNombre);

        txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setBounds(200, 70, 150, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        lblApellido.setBounds(50, 110, 150, 25);
        add(lblApellido);

        txtApellido = new JTextField(usuario.getApellido());
        txtApellido.setBounds(200, 110, 150, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 150, 150, 25);
        lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblTelefono);

        txtTelefono = new JTextField(usuario.getTelefono());
        txtTelefono.setBounds(200, 150, 150, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 190, 150, 25);
        lblEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblEmail);

        txtEmail = new JTextField(usuario.getEmail());
        txtEmail.setBounds(200, 190, 150, 25);
        add(txtEmail);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(50, 230, 150, 25);
        lblContraseña.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        add(lblContraseña);

        txtContraseña = new JPasswordField(usuario.getPassword());
        txtContraseña.setBounds(200, 230, 150, 25);
        add(txtContraseña);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(85, 280, 100, 25);
        btnActualizar.setBackground(Color.GRAY);
        btnActualizar.setForeground(Color.WHITE);
        add(btnActualizar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(215, 280, 100, 25);
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        add(btnCancelar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
            }
        });
    }

    private void actualizarUsuario() { 
        String nombreUsuario = txtNombreUsuario.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String contraseña = new String(txtContraseña.getPassword());

        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        Usuario usuarioActualizado = new Usuario(usuario.getIdUser(), nombreUsuario, nombre, apellido, telefono, email, contraseña);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.actualizarUsuario(usuarioActualizado)) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente");
            new UsuarioLista().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario");
        }
    }

    private void cancelarEdicion() {
        new UsuarioLista().setVisible(true);
        dispose();
    }
}
