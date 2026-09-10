package br.edu.iftm.petvida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.petvida.PetvidaApplication;
import br.edu.iftm.petvida.model.Tutor;
import br.edu.iftm.petvida.repository.AnimalRepository;
import br.edu.iftm.petvida.repository.TutorRepository;

@Controller
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/tutor_" + PetvidaApplication.NN)
    public String tutor(Model model) {
        int meuId = 100 + PetvidaApplication.NN;
        Tutor tutor = tutorRepository.buscarPorId(meuId);
        int quantidade = animalRepository.contarAnimaisDoTutor(meuId);

        model.addAttribute("nomeTutor", tutor.getNome());
        model.addAttribute("telefoneTutor", tutor.getTelefone());
        model.addAttribute("quantidadeAnimais", String.valueOf(quantidade));
        return "tutor";
    }
}
