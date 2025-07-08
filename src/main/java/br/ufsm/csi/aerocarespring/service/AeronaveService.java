package br.ufsm.csi.aerocarespring.service;

import br.ufsm.csi.aerocarespring.dao.AeronaveDAO;
import br.ufsm.csi.aerocarespring.model.Aeronave;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AeronaveService {

    private final AeronaveDAO dao;

    // Injeção via construtor
    public AeronaveService(AeronaveDAO dao) {
        this.dao = dao;
    }

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir aeronave";
        } else {
            return "Erro ao excluir aeronave";
        }
    }

    public List<Aeronave> listar() {
        return dao.listar();
    }

    public Aeronave buscar(int aeronaveId) {
        return dao.buscar(aeronaveId);
    }

    public String inserir(Aeronave aeronave) {
        if (aeronave.getId() != null && aeronave.getId() > 0) {
            return dao.alterar(aeronave); // edição
        } else {
            return dao.inserir(aeronave); // novo cadastro
        }
    }

    public String alterar(Aeronave aeronave) {
        return dao.alterar(aeronave);
    }
}
