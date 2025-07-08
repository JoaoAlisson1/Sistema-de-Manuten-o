package br.ufsm.csi.aerocarespring.dao;

import br.ufsm.csi.aerocarespring.model.Aeronave;
import br.ufsm.csi.aerocarespring.model.Mecanico;
import br.ufsm.csi.aerocarespring.model.OrdemDeServico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class OrdemDeServicoDAO {

    private final JdbcTemplate jdbcTemplate;

    public OrdemDeServicoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrdemDeServico> listar() {
        String sql = "SELECT os.*, " +
                "a.id AS aid, a.modelo, a.fabricante, a.anoFabricacao, a.registroNacional, " +
                "m.id AS mid, m.nome, m.registroAnac, m.especialidade, m.ativo " +
                "FROM OrdemDeServico os " +
                "JOIN Aeronave a ON os.aeronave_id = a.id " +
                "JOIN Mecanico m ON os.mecanico_id = m.id";

        return jdbcTemplate.query(sql, ordemMapper);
    }

    public OrdemDeServico buscar(int id) {
        String sql = "SELECT os.*, " +
                "a.id AS aid, a.modelo, a.fabricante, a.anoFabricacao, a.registroNacional, " +
                "m.id AS mid, m.nome, m.registroAnac, m.especialidade, m.ativo " +
                "FROM OrdemDeServico os " +
                "JOIN Aeronave a ON os.aeronave_id = a.id " +
                "JOIN Mecanico m ON os.mecanico_id = m.id " +
                "WHERE os.id = ?";

        return jdbcTemplate.queryForObject(sql, ordemMapper, id);
    }

    public String inserir(OrdemDeServico ordem) {
        String sql = "INSERT INTO OrdemDeServico (descricaoServico, tipoManutencao, dataSolicitacao, dataConclusao, status, aeronave_id, mecanico_id) " +
                "VALUES (?, ?::tipomanutencao, ?, ?, ?::statusordem, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    ordem.getDescricaoServico(),
                    ordem.getTipoManutencao(),
                    ordem.getDataSolicitacao(),
                    ordem.getDataConclusao(),
                    ordem.getStatus(),
                    ordem.getAeronave().getId(),
                    ordem.getMecanico().getId());
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inserir ordem de serviço: " + e.getMessage();
        }

        return "Ordem de serviço inserida com sucesso!";
    }

    public String alterar(OrdemDeServico ordem) {
        String sql = "UPDATE OrdemDeServico SET descricaoServico = ?, tipoManutencao = ?::tipomanutencao, dataSolicitacao = ?, dataConclusao = ?, status = ?::statusordem, aeronave_id = ?, mecanico_id = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sql,
                    ordem.getDescricaoServico(),
                    ordem.getTipoManutencao(),
                    ordem.getDataSolicitacao(),
                    ordem.getDataConclusao(),
                    ordem.getStatus(),
                    ordem.getAeronave().getId(),
                    ordem.getMecanico().getId(),
                    ordem.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao alterar ordem de serviço: " + e.getMessage();
        }

        return "Ordem de serviço alterada com sucesso!";
    }

    public String excluir(int id) {
        String sql = "DELETE FROM OrdemDeServico WHERE id = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows > 0 ? "Ordem de serviço excluída com sucesso!" : "Ordem de serviço não encontrada para exclusão.";
        } catch (Exception e) {
            return "Erro ao excluir ordem de serviço: " + e.getMessage();
        }
    }

    // RowMapper para mapear os dados do banco para OrdemDeServico
    private final RowMapper<OrdemDeServico> ordemMapper = (rs, rowNum) -> {
        OrdemDeServico ordem = new OrdemDeServico();
        ordem.setId(rs.getInt("id"));
        ordem.setDescricaoServico(rs.getString("descricaoServico"));
        ordem.setTipoManutencao(rs.getString("tipoManutencao"));
        ordem.setDataSolicitacao(rs.getDate("dataSolicitacao").toLocalDate());

        if (rs.getDate("dataConclusao") != null) {
            ordem.setDataConclusao(rs.getDate("dataConclusao").toLocalDate());
        }

        ordem.setStatus(rs.getString("status"));

        Aeronave aeronave = new Aeronave();
        aeronave.setId(rs.getInt("aid"));
        aeronave.setModelo(rs.getString("modelo"));
        aeronave.setFabricante(rs.getString("fabricante"));
        aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
        aeronave.setRegistroNacional(rs.getString("registroNacional"));
        ordem.setAeronave(aeronave);

        Mecanico mecanico = new Mecanico();
        mecanico.setId(rs.getInt("mid"));
        mecanico.setNome(rs.getString("nome"));
        mecanico.setRegistroAnac(rs.getString("registroAnac"));
        mecanico.setEspecialidade(rs.getString("especialidade"));
        mecanico.setAtivo(rs.getBoolean("ativo"));
        ordem.setMecanico(mecanico);

        return ordem;
    };
}
