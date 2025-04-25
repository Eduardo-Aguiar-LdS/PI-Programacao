package src.br.com.showdomilhao.service;
import java.sql.SQLException;

import src.br.com.showdomilhao.model.Aluno;
public class AlunoService {
    private final AlunoDAOInterface alunoDAO;

    public AlunoService(AlunoDAOInterface alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void cadastrarAluno(String nome, String email, String senha) throws Exception {
        if (alunoDAO.existeComEmail(email)) {
            throw new Exception("Email já cadastrado!");
        }
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setSenha(senha);
        aluno.setPontuacao(0);
        alunoDAO.inserir(aluno);
    }

    public void incrementarPontuacao(int idAluno, int pontos) throws SQLException {
        Aluno aluno = alunoDAO.buscarPorId(idAluno);
        aluno.setPontuacao(aluno.getPontuacao() + pontos);
        alunoDAO.atualizar(aluno);
    }
}