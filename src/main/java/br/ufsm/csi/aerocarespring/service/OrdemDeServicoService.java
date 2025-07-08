package br.ufsm.csi.aerocarespring.service;

import br.ufsm.csi.aerocarespring.dao.OrdemDeServicoDAO;
import br.ufsm.csi.aerocarespring.model.OrdemDeServico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemDeServicoService {

    private final OrdemDeServicoDAO dao;

    public OrdemDeServicoService(OrdemDeServicoDAO dao) {
        this.dao = dao;
    }

    public List<OrdemDeServico> listar() {
        return dao.listar();
    }

    public OrdemDeServico buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(OrdemDeServico ordem) {
        if (ordem.getId() != null && ordem.getId() > 0) {
            return dao.alterar(ordem); // Atualização
        } else {
            return dao.inserir(ordem); // Novo cadastro
        }
    }

    public String alterar(OrdemDeServico ordem) {
        return dao.alterar(ordem);
    }

    public String excluir(int id) {
        return dao.excluir(id);
    }
}
