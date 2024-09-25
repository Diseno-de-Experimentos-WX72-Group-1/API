package com.example.gestion_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bienvenido a la aplicación de gestión de citas");
        return "index"; // Asegúrate de que index.html esté en templates
    }
}
