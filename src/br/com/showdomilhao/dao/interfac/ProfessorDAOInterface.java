package src.br.com.showdomilhao.dao.interfac;

import src.br.com.showdomilhao.model.Professor;
import src.br.com.showdomilhao.exception.BusinessException;
import java.sql.SQLException;
import java.util.List;
public interface ProfessorDAOInterface {
    void inserir(Professor professor) throws SQLException;
    void cadastrarProfessor(Professor professor, String senha) throws SQLException, BusinessException;
    // Professor buscarPorId(int id) throws SQLException;
    List<Professor> listarTodos() throws SQLException;
    void atualizar(Professor professor) throws SQLException;
    void deletar(int id) throws SQLException;
    boolean autenticar(String email, String senha) throws SQLException;
    boolean existeComEmail(String email) throws SQLException;
    Professor buscarPorEmail(String email) throws SQLException;


}
//classe interface serve para definir os metodos, onde serão implementados de fato, na classe DAO normal