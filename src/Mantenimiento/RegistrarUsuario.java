package Mantenimiento;

import Clases.Usuario;

public class RegistrarUsuario {
    private UsuarioDAO usuarioDAO;

    public RegistrarUsuario() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDAO.registrarUsuario(usuario);
    }

    public Usuario iniciarSesion(String userName, String password) {
        return usuarioDAO.obtenerUsuario(userName, password);
    }
}
