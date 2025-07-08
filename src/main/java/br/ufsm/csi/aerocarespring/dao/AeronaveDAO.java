package br.ufsm.csi.aerocarespring.dao;

import br.ufsm.csi.aerocarespring.model.Aeronave;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AeronaveDAO {

    private final JdbcTemplate jdbcTemplate;

    public AeronaveDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Aeronave> rowMapper = (rs, rowNum) -> {
        Aeronave a = new Aeronave();
        a.setId(rs.getInt("id"));
        a.setModelo(rs.getString("modelo"));
        a.setFabricante(rs.getString("fabricante"));
        a.setAnoFabricacao(rs.getInt("anoFabricacao"));
        a.setRegistroNacional(rs.getString("registroNacional"));
        return a;
    };

    public List<Aeronave> listar() {
        String sql = "SELECT * FROM Aeronave";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String inserir(Aeronave aeronave) {
        String sql = "INSERT INTO aeronave(modelo, fabricante, anoFabricacao, registroNacional) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                aeronave.getModelo(),
                aeronave.getFabricante(),
                aeronave.getAnoFabricacao(),
                aeronave.getRegistroNacional());
        return "Aeronave inserida com sucesso!";
    }

    public String alterar(Aeronave aeronave) {
        String sql = "UPDATE Aeronave SET modelo = ?, fabricante = ?, anoFabricacao = ?, registroNacional = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                aeronave.getModelo(),
                aeronave.getFabricante(),
                aeronave.getAnoFabricacao(),
                aeronave.getRegistroNacional(),
                aeronave.getId());
        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Aeronave WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public Aeronave buscar(int id) {
        String sql = "SELECT * FROM Aeronave WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    public Aeronave buscar(String registroNacional) {
        String sql = "SELECT * FROM Aeronave WHERE registroNacional = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, registroNacional);
    }
}
