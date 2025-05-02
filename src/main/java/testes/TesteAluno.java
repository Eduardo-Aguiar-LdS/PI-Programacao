package testes;

import java.sql.Connection;
import codigo.*;

public class TesteAluno {
    public static void main(String[] args) {
        // Criação de classe utilitária
        DAO dao = new DAO();
        try (Connection conn = ConnectionFactory.obterConexao()) {

            String nome_aluno = "Eduardo";
            String email_aluno = "testeA";
            Aluno aluno = new Aluno(nome_aluno, email_aluno);

            // Verifica a existência do aluno
            System.out.println("Aluno: " + dao.existeAluno(aluno));

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
