package br.ufsm.csi.aerocarespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashbordController {

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        // Retorna o caminho da view JSP sem extensão
        return "pages/dashbord";
    }
}
