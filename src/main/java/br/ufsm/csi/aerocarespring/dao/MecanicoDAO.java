package br.ufsm.csi.aerocarespring.dao;

import br.ufsm.csi.aerocarespring.model.Mecanico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MecanicoDAO {

    private final JdbcTemplate jdbcTemplate;

    public MecanicoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Mecanico> rowMapper = (rs, rowNum) -> {
        Mecanico m = new Mecanico();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setRegistroAnac(rs.getString("registroAnac")); // Confere se o nome no banco é exatamente esse
        m.setEspecialidade(rs.getString("especialidade"));
        m.setAtivo(rs.getBoolean("ativo"));
        return m;
    };

    public List<Mecanico> listar() {
        String sql = "SELECT * FROM Mecanico";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String inserir(Mecanico mecanico) {
        String sql = "INSERT INTO Mecanico(nome, registroAnac, especialidade, ativo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                mecanico.getNome(),
                mecanico.getRegistroAnac(),
                mecanico.getEspecialidade(),
                mecanico.isAtivo()
        );
        return "Mecânico inserido com sucesso!";
    }

    public String alterar(Mecanico mecanico) {
        String sql = "UPDATE Mecanico SET nome = ?, registroAnac = ?, especialidade = ?, ativo = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                mecanico.getNome(),
                mecanico.getRegistroAnac(),
                mecanico.getEspecialidade(),
                mecanico.isAtivo(),
                mecanico.getId()
        );
        return "Mecânico alterado com sucesso!";
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Mecanico WHERE id = ?";
        int affected = jdbcTemplate.update(sql, id);
        return affected > 0;
    }

    public Mecanico buscar(int id) {
        String sql = "SELECT * FROM Mecanico WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public boolean temOrdensDeServicoAssociadas(int mecanicoId) {
        String sql = "SELECT COUNT(*) FROM ordemdeservico WHERE mecanico_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mecanicoId);
        return count != null && count > 0;
    }

}
