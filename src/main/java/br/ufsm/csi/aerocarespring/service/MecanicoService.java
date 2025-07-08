package br.ufsm.csi.aerocarespring.service;

import br.ufsm.csi.aerocarespring.dao.MecanicoDAO;
import br.ufsm.csi.aerocarespring.model.Mecanico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicoService {

    private final MecanicoDAO dao;

    // Injeção via construtor (Spring faz automaticamente)
    public MecanicoService(MecanicoDAO dao) {
        this.dao = dao;
    }

    public List<Mecanico> listar() {
        return dao.listar();
    }

    public Mecanico buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(Mecanico mecanico) {
        if (mecanico.getId() != null && mecanico.getId() > 0) {
            return dao.alterar(mecanico); // edição
        } else {
            return dao.inserir(mecanico); // novo cadastro
        }
    }

    public String alterar(Mecanico mecanico) {
        return dao.alterar(mecanico);
    }

    public String excluir(int id) {
        // Verifica se o mecânico está associado a alguma ordem de serviço
        if (dao.temOrdensDeServicoAssociadas(id)) {
            return "Erro: não é possível excluir o mecânico pois ele está vinculado a ordens de serviço.";
        }

        if (dao.excluir(id)) {
            return "Mecânico excluído com sucesso!";
        } else {
            return "Erro ao excluir mecânico.";
        }
    }
}
