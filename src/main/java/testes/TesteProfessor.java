package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteProfessor {
    public static void main(String[] args) {
        // Criação de classe utilitária
        DAO dao = new DAO();
        try (Connection conn = ConnectionFactory.obterConexao()) {
            String nome_professor = "Eduardo";
            String email_professor = "testeP";
            Professor professor = new Professor(nome_professor, email_professor);
            // Verifica a existência do professor
            System.out.println("Professor: " + dao.existeProfessor(professor));

            String nome_aluno = "Teste";
            String email_aluno = "cadastro_professor";
            String senha_aluno = "senha";
            Aluno aluno = new Aluno(nome_aluno, email_aluno, senha_aluno);

            // Teste professor cadastrar aluno
            System.out.println(professor.cadastrarAluno(aluno));

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
