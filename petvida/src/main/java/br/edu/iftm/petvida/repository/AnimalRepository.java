package br.edu.iftm.petvida.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.petvida.model.Animal;
import br.edu.iftm.petvida.model.Tutor;

@Repository
public class AnimalRepository {

    @Autowired
    private JdbcTemplate jdbc;

    // UMA unica consulta com JOIN. Aliases evitam colisao de nomes (nome existe nas duas tabelas)
    public Animal buscarPorId(int id) {
        String sql = "SELECT a.id_animal, a.nome AS nome_animal, a.especie, a.idade, "
                   + "t.id_tutor, t.nome AS nome_tutor, t.telefone "
                   + "FROM animal a JOIN tutor t ON a.tutor_id_tutor = t.id_tutor "
                   + "WHERE a.id_animal = ?";
        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Tutor tutor = new Tutor();
            tutor.setId(rs.getInt("id_tutor"));
            tutor.setNome(rs.getString("nome_tutor"));
            tutor.setTelefone(rs.getString("telefone"));

            Animal animal = new Animal();
            animal.setId(rs.getInt("id_animal"));
            animal.setNome(rs.getString("nome_animal"));
            animal.setEspecie(rs.getString("especie"));
            animal.setIdade(rs.getInt("idade"));
            animal.setTutor(tutor);
            return animal;
        }, id);
    }

    public int contarAnimais() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM animal", Integer.class);
    }

    // CAST evita a "armadilha do H2": AVG de coluna INT pode truncar as casas decimais
    public double mediaIdade() {
        String sql = "SELECT AVG(CAST(idade AS DOUBLE)) FROM animal";
        return jdbc.queryForObject(sql, Double.class);
    }

    public String animalMaisVelho() {
        String sql = "SELECT nome FROM animal ORDER BY idade DESC LIMIT 1";
        return jdbc.queryForObject(sql, String.class);
    }

    public int contarAnimaisDoTutor(int idTutor) {
        String sql = "SELECT COUNT(*) FROM animal WHERE tutor_id_tutor = ?";
        return jdbc.queryForObject(sql, Integer.class, idTutor);
    }

    public void salvar(Animal animal) {
        String sql = "INSERT INTO animal (id_animal, nome, especie, idade, tutor_id_tutor) "
                   + "VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql, animal.getId(), animal.getNome(), animal.getEspecie(),
                animal.getIdade(), animal.getTutor().getId());
    }
}
