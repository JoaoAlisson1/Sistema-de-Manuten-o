package br.ufsm.csi.aerocarespring.controller;

import br.ufsm.csi.aerocarespring.model.Mecanico;
import br.ufsm.csi.aerocarespring.service.MecanicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final MecanicoService mecanicoService;

    // O Spring vai injetar automaticamente o MecanicoService
    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @GetMapping
    public String listaMecanico(Model model) {
        model.addAttribute("mecanicos", mecanicoService.listar());
        model.addAttribute("mecanico", new Mecanico());
        return "pages/mecanicos";
    }

    @PostMapping
    public String inserirMecanico(Mecanico mecanico, RedirectAttributes redirectAttributes) {
        String retorno = mecanicoService.inserir(mecanico);
        redirectAttributes.addFlashAttribute("msg", retorno);
        return "redirect:/mecanicos";
    }

    @GetMapping("editar/{mecanicoId}")
    public String editarMecanico(@PathVariable int mecanicoId, Model model) {
        Mecanico mecanico = mecanicoService.buscar(mecanicoId);
        model.addAttribute("mecanico", mecanico);
        model.addAttribute("mecanicos", mecanicoService.listar());
        return "pages/mecanicos";
    }

    @GetMapping("excluir/{id}")
    public String excluirMecanico(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String msg = mecanicoService.excluir(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/mecanicos";
    }
}
