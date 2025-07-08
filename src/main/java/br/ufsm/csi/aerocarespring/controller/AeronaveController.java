package br.ufsm.csi.aerocarespring.controller;

import br.ufsm.csi.aerocarespring.model.Aeronave;
import br.ufsm.csi.aerocarespring.service.AeronaveService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/aeronaves")
public class AeronaveController {

    private final AeronaveService aeronaveService;

    // Injeção via construtor (Spring vai injetar automaticamente)
    public AeronaveController(AeronaveService aeronaveService) {
        this.aeronaveService = aeronaveService;
    }

    @GetMapping
    public String listaAeronave(Model model) {
        model.addAttribute("aeronaves", aeronaveService.listar());
        model.addAttribute("aeronave", new Aeronave());
        return "pages/aeronaves";
    }

    @PostMapping
    public String InserirAeronave(Aeronave aeronave, RedirectAttributes redirectAttributes) {
        String retorno = aeronaveService.inserir(aeronave);
        redirectAttributes.addFlashAttribute("msg", retorno);
        return "redirect:/aeronaves";
    }

    @GetMapping("editar/{aeronavesId}")
    public String editarAeronave(@PathVariable int aeronavesId, Model model) {
        Aeronave aeronave = aeronaveService.buscar(aeronavesId);
        model.addAttribute("aeronave", aeronave);
        model.addAttribute("aeronaves", aeronaveService.listar());
        return "pages/aeronaves";
    }

    @GetMapping("excluir/{id}")
    public String excluirAeronave(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String msg = aeronaveService.excluir(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/aeronaves";
    }
}

