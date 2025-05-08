package testes;

import java.sql.Connection;
import show_milhao.*;

public class TesteAluno {
    public static void main(String[] args) {
        DAO dao = new DAO();
        try (Connection conn = ConnectionFactory.obterConexao()) {
            String nome_aluno = "Eduardo";
            String email_aluno = "testeA";
            Aluno aluno = new Aluno(nome_aluno, email_aluno);

            boolean alunoExiste = dao.existeAluno(aluno);
            if (alunoExiste) {
                System.out.println("Aluno encontrado: " + aluno);
            } else {
                System.out.println("Aluno não encontrado: " + aluno);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
