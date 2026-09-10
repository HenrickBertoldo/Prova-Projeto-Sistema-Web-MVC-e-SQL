package br.edu.iftm.petvida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.PetvidaApplication;
import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.repository.AnimalRepository;

@Controller
public class FichaController {

    @Autowired
    private AnimalRepository animalRepository;

    // a rota se monta sozinha a partir de PetvidaApplication.NN, entao so precisa editar NN em um lugar
    @GetMapping("/ficha_" + PetvidaApplication.NN)
    public String ficha(Model model) {
        int meuId = 100 + PetvidaApplication.NN;
        Animal animal = animalRepository.buscarPorId(meuId);

        model.addAttribute("nomeAnimal", animal.getNome());
        model.addAttribute("especieAnimal", animal.getEspecie());
        model.addAttribute("idadeAnimal", String.valueOf(animal.getIdade()));
        model.addAttribute("nomeTutor", animal.getTutor().getNome());
        model.addAttribute("telefoneTutor", animal.getTutor().getTelefone());
        return "ficha";
    }
}
