package br.ufsm.csi.aerocarespring.controller;

import br.ufsm.csi.aerocarespring.model.OrdemDeServico;
import br.ufsm.csi.aerocarespring.service.AeronaveService;
import br.ufsm.csi.aerocarespring.service.MecanicoService;
import br.ufsm.csi.aerocarespring.service.OrdemDeServicoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicos")
public class OrdemDeServicoController {

    private final OrdemDeServicoService ordemDeServicoService;
    private final MecanicoService mecanicoService;
    private final AeronaveService aeronaveService;

    // Injeção de dependência via construtor
    public OrdemDeServicoController(
            OrdemDeServicoService ordemDeServicoService,
            MecanicoService mecanicoService,
            AeronaveService aeronaveService
    ) {
        this.ordemDeServicoService = ordemDeServicoService;
        this.mecanicoService = mecanicoService;
        this.aeronaveService = aeronaveService;
    }

    @GetMapping
    public String listarOrdens(Model model) {
        model.addAttribute("ordens", ordemDeServicoService.listar());
        model.addAttribute("ordem", new OrdemDeServico());
        model.addAttribute("mecanicos", mecanicoService.listar());
        model.addAttribute("aeronaves", aeronaveService.listar());
        return "pages/servicos";
    }

    @PostMapping
    public String inserirOrdem(@ModelAttribute OrdemDeServico ordem, RedirectAttributes redirectAttributes) {
        String msg = ordemDeServicoService.inserir(ordem);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/servicos";
    }

    @GetMapping("/editar/{id}")
    public String editarOrdem(@PathVariable int id, Model model) {
        OrdemDeServico ordem = ordemDeServicoService.buscar(id);

        model.addAttribute("ordem", ordem);
        model.addAttribute("ordens", ordemDeServicoService.listar());
        model.addAttribute("mecanicos", mecanicoService.listar());
        model.addAttribute("aeronaves", aeronaveService.listar());

        return "pages/servicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirOrdem(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String msg = ordemDeServicoService.excluir(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/servicos";
    }
}
