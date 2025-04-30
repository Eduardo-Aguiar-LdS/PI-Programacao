package src.br.com.showdomilhao.service;

import src.br.com.showdomilhao.dao.impl.ProfessorDAO;
import src.br.com.showdomilhao.model.Professor;
import java.sql.SQLException;
import java.util.List;

public class ProfessorService {

    private ProfessorDAO professorDAO;

    public ProfessorService() {
        this.professorDAO = new ProfessorDAO();
    }

    public void cadastrarProfessor(Professor professor) throws SQLException {
        if (professor.getNome() == null || professor.getEmail() == null || professor.getSenha() == null) {
            throw new IllegalArgumentException("Nome, email e senha são obrigatórios");
        }

        if (professorDAO.existeComEmail(professor.getEmail())) {
            throw new IllegalArgumentException("Já existe um professor com esse email");
        }

        professorDAO.inserir(professor);
    }

    public boolean autenticarProfessor(String email, String senha) throws SQLException {
        if (email == null || senha == null) {
            throw new IllegalArgumentException("Email e senha são obrigatórios");
        }
        return professorDAO.autenticar(email, senha);
    }

    public Professor buscarProfessorPorEmail(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        return professorDAO.buscarPorEmail(email);
    }

    public List<Professor> listarTodosOsProfessores() throws SQLException {
        return professorDAO.listarTodos();
    }

    public void deletarProfessorPorEmail(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        professorDAO.deletarPorEmail(email);
    }
}
//essa classe lida com as regras de negocio, validação de dados, processamento das informações...