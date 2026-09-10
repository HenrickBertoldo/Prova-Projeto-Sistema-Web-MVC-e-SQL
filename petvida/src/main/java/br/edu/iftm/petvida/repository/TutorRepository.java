package br.edu.iftm.petvida.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.petvida.model.Tutor;

@Repository
public class TutorRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public Tutor buscarPorId(int id) {
        String sql = "SELECT * FROM tutor WHERE id_tutor = ?";
        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Tutor t = new Tutor();
            t.setId(rs.getInt("id_tutor"));
            t.setNome(rs.getString("nome"));
            t.setTelefone(rs.getString("telefone"));
            return t;
        }, id);
    }

    public void salvar(Tutor tutor) {
        String sql = "INSERT INTO tutor (id_tutor, nome, telefone) VALUES (?, ?, ?)";
        jdbc.update(sql, tutor.getId(), tutor.getNome(), tutor.getTelefone());
    }
}
