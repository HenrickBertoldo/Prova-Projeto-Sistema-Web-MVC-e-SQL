package br.edu.iftm.petvida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.repository.AnimalRepository;

@Controller
public class ConsultaController {

    @Autowired
    AnimalRepository repository;

    @GetMapping("/consulta")
    public String consulta(Model model) {
        model.addAttribute("animal", repository.buscarPorId(146));
        return "consulta";
    }
}