package br.ufsm.csi.aerocarespring.controller;

import br.ufsm.csi.aerocarespring.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, String email, String senha) {
        System.out.println("Realizando o login do usuario: " + email);

        if(loginService.autenticar(email, senha)) {
            return "redirect:/dashbord";
        } else {
            model.addAttribute("msg", "login ou senha incorreto");
            return "redirect:/index";
        }
    }

    @GetMapping("/dashbord")
    public String dashbord() {
        return "pages/dashbord";
    }
}

