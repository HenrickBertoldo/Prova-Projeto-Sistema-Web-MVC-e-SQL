package br.edu.iftm.petvida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.model.Tutor;
import br.edu.iftm.petvida.repository.AnimalRepository;
import br.edu.iftm.petvida.repository.TutorRepository;

@SpringBootApplication
public class PetvidaApplication implements CommandLineRunner {

    // ================================================================
    // >>>>>> EDITE AQUI: troque pelos SEUS dados (Secao 3 da prova) <<<<<<
    public static final int NN = 34;                // dois ultimos digitos da SUA matricula
    private static final String MEU_NOME = "Ana";   // SEU primeiro nome
    // ================================================================

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public static void main(String[] args) {
        SpringApplication.run(PetvidaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // --- dados fixos da Secao 2 (tutores primeiro: FK exige que ja existam) ---
        Tutor marina = new Tutor(1, "Marina Alves", "34 99101-0001");
        Tutor carlos = new Tutor(2, "Carlos Prado", "34 99101-0002");
        tutorRepository.salvar(marina);
        tutorRepository.salvar(carlos);

        animalRepository.salvar(new Animal(2, "Mimi", "gato", 3, marina));
        animalRepository.salvar(new Animal(3, "Thor", "cao", 1, carlos));
        animalRepository.salvar(new Animal(4, "Lila", "gato", 11, carlos));

        // --- seu registro pessoal (Secao 3) ---
        int meuId = 100 + NN;
        String nn2 = String.format("%02d", NN);
        String meuTelefone = "34 9" + nn2 + nn2 + "-" + nn2 + nn2; // ex.: NN=34 -> "34 93434-3434"

        Tutor meuTutor = new Tutor(meuId, MEU_NOME, meuTelefone);
        tutorRepository.salvar(meuTutor);

        Animal meuAnimal = new Animal(meuId, "Pet_" + NN, "cao", NN, meuTutor);
        animalRepository.salvar(meuAnimal);

        System.out.println(">>> Carga inicial concluida. Seu id = " + meuId
                + " | Suas rotas: /ficha_" + NN + "  /tutor_" + NN + "  /resumo_" + NN);
    }
}
