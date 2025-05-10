package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteProfessor {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obterConexao()) {
            String email_professor = "testeP@sistemapoliedro.com.br";
            String senha = "professor";
            Professor professor = new Professor(email_professor, senha);
            professor.fazerLogin(professor);
            professor.nomeDB(professor);
            System.out.println(professor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
