package br.edu.iftm.petvida.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.PetvidaApplication;
import br.edu.iftm.petvida.repository.AnimalRepository;

@Controller
public class ResumoController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/resumo_" + PetvidaApplication.NN)
    public String resumo(Model model) {
        int total = animalRepository.contarAnimais();
        double media = animalRepository.mediaIdade();
        String mediaFormatada = String.format(Locale.US, "%.2f", media);
        String maisVelho = animalRepository.animalMaisVelho();

        String dataHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        model.addAttribute("totalAnimais", String.valueOf(total));
        model.addAttribute("mediaIdade", mediaFormatada);
        model.addAttribute("animalMaisVelho", maisVelho);
        model.addAttribute("dataHora", dataHora);
        return "resumo";
    }
}
