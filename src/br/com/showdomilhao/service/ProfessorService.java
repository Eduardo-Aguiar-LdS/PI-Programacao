package src.br.com.showdomilhao.service;
import java.sql.SQLException;

import BusinessException;
import src.br.com.showdomilhao.dao.interfac.ProfessorDAOInterface;
import src.br.com.showdomilhao.model.Professor;

public class ProfessorService {
    private final ProfessorDAOInterface professorDAO;
    

    public ProfessorService(ProfessorDAOInterface professorDAO) {
        this.professorDAO = professorDAO;
    }

    public void cadastrarProfessor(Professor professor) 
            throws BusinessException, SQLException {
        
        // Valida��es
        if (professor == null || professor.getEmail() == null) {
            throw new BusinessException("Dados inv�lidos");
        }

        if (professorDAO.existeComEmail(professor.getEmail())) {
            throw new BusinessException("Email j� existe");
        }

        professorDAO.inserir(professor);
    }

    public Professor buscarProfessorPorEmail(String email) 
            throws BusinessException, SQLException {
        
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("Email inv�lido");
        }

        Professor professor = professorDAO.buscarPorEmail(email);
        if (professor == null) {
            throw new BusinessException("Professor n�o encontrado");
        }

        return professor;
    }


    private boolean isEmailValido(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}

