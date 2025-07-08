package br.ufsm.csi.aerocarespring.controller;

import br.ufsm.csi.aerocarespring.model.Usuario;
import br.ufsm.csi.aerocarespring.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Injeção de dependência via construtor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listaUsuario(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("usuario", new Usuario());

        return "pages/usuarios";
    }

    @PostMapping
    public String criarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        String retorno = usuarioService.inserir(usuario);
        redirectAttributes.addFlashAttribute("msg", retorno);

        return "redirect:/usuario";
    }

    @GetMapping("editar/{usuariosId}")
    public String editarUsuario(@PathVariable int usuariosId, Model model) {
        Usuario usuario = usuarioService.buscar(usuariosId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioService.listar());

        return "pages/usuarios";
    }

    @GetMapping("excluir/{id}")
    public String excluirUsuario(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String msg = usuarioService.excluir(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/usuario";
    }
}
