package br.ufsm.csi.aerocarespring.service;

import br.ufsm.csi.aerocarespring.dao.UsuarioDAO;
import br.ufsm.csi.aerocarespring.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO dao;

    public UsuarioService(UsuarioDAO dao) {
        this.dao = dao;
    }

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir usuário";
        } else {
            return "Erro ao excluir usuário";
        }
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public Usuario buscar(int usuarioId) {
        return dao.buscar(usuarioId);
    }

    public String inserir(Usuario usuario) {
        if (usuario.getId() != null && usuario.getId() > 0) {
            return dao.alterar(usuario); // Edição
        } else {
            return dao.inserir(usuario); // Novo cadastro
        }
    }

    public String alterar(Usuario usuario) {
        return dao.alterar(usuario);
    }
}
