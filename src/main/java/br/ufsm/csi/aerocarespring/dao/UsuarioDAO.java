package br.ufsm.csi.aerocarespring.dao;

import br.ufsm.csi.aerocarespring.model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> rowMapper = new BeanPropertyRowMapper<>(Usuario.class);

    public List<Usuario> listar() {
        String sql = "SELECT * FROM Usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, senha, ativo) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, usuario.getEmail(), usuario.getSenha(), usuario.isAtivo());
            return "Inserido com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return "Erro ao inserir usuário";
        }
    }

    public String alterar(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, ativo = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, usuario.getEmail(), usuario.getSenha(), usuario.isAtivo(), usuario.getId());
            return "Usuário alterado com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao alterar usuario: " + e.getMessage());
            return "Erro ao alterar usuário";
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscar(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario por ID: " + e.getMessage());
            return null;
        }
    }

    public Usuario buscar(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, email);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario por e-mail: " + e.getMessage());
            return null;
        }
    }
}
