package br.ufsm.csi.aerocarespring.service;

import br.ufsm.csi.aerocarespring.dao.UsuarioDAO;
import br.ufsm.csi.aerocarespring.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioDAO dao;

    // Aqui o Spring injeta o UsuarioDAO automaticamente
    public LoginService(UsuarioDAO dao) {
        this.dao = dao;
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuario = dao.buscar(email);

        if (usuario != null && usuario.getEmail() != null) {
            if (usuario.isAtivo() && usuario.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;
    }
}
