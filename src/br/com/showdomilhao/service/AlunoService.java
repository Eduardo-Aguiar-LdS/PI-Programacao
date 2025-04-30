package src.br.com.showdomilhao.service;

import src.br.com.showdomilhao.dao.impl.AlunoDAO;
import src.br.com.showdomilhao.model.Aluno;
import java.sql.SQLException;
import java.util.List;

public class AlunoService {

    private AlunoDAO alunoDAO;

    public AlunoService() {
        this.alunoDAO = new AlunoDAO();
    }

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        if (aluno.getNome() == null || aluno.getEmail() == null || aluno.getSenha() == null) {
            throw new IllegalArgumentException("Nome, email e senha são obrigatórios");
        }

        if (alunoDAO.existeComEmail(aluno.getEmail())) {
            throw new IllegalArgumentException("Já existe um aluno com esse email");
        }

        alunoDAO.inserir(aluno);
    }

    public boolean autenticarAluno(String email, String senha) throws SQLException {
        if (email == null || senha == null) {
            throw new IllegalArgumentException("Email e senha são obrigatórios");
        }
        return alunoDAO.autenticar(email, senha);
    }

    public Aluno buscarAlunoPorEmail(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        return alunoDAO.buscarPorEmail(email);
    }

    public List<Aluno> listarTodosOsAlunos() throws SQLException {
        return alunoDAO.listarTodos();
    }

    public void deletarAlunoPorEmail(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        alunoDAO.deletarPorEmail(email);
    }

    public void atualizarPontuacaoAluno(int id, int novaPontuacao) throws SQLException {
        if (novaPontuacao < 0) {
            throw new IllegalArgumentException("Pontuação não pode ser negativa");
        }
        alunoDAO.atualizarPontuacao(id, novaPontuacao);
    }

    public void atualizarRespostasAluno(int id, int respostasCorretas, int respostasErradas) throws SQLException {
        if (respostasCorretas < 0 || respostasErradas < 0) {
            throw new IllegalArgumentException("Respostas corretas e erradas não podem ser negativas");
        }
        alunoDAO.atualizarRespostas(id, respostasCorretas, respostasErradas);
    }
}
//essa classe lida com as regras de negocio, validação de dados, processamento das informações...