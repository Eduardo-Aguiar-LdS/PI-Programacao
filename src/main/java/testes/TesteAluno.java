package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteAluno {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obterConexao()) {
            String email_aluno = "testeA@p4ed.com";
            String senha = "aluno";
            Aluno aluno = new Aluno(email_aluno, senha);
            aluno.fazerLogin(aluno, null);
            aluno.atributosDB(aluno);
            System.out.println(aluno);
            System.out.println("Perguntas respondidas: "+(aluno.getRespostas_corretas()+aluno.getRespostas_erradas()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
