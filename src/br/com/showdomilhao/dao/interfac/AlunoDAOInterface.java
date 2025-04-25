package src.br.com.showdomilhao.dao.interfac;

import java.util.List;
import src.br.com.showdomilhao.model.Aluno;
import java.sql.SQLException;
public interface AlunoDAOInterface {
    void inserir(Aluno aluno) throws SQLException;
    Aluno buscarPorId(int id) throws SQLException;
    List<Aluno> listarTodos() throws SQLException;
    void atualizar(Aluno aluno) throws SQLException;
    void deletar(int id) throws SQLException;
    boolean autenticar(String email, String senha) throws SQLException;
    boolean existeComEmail(String email) throws SQLException;
    Aluno buscarPorEmail(String email) throws SQLException;
    void incrementarPontuacao(int id, int pontos);
    void incrementarAcertos(int id);
    void incrementarErros(int id);
    List<Aluno> listarRanking();

}

