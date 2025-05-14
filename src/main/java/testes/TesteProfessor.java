package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteProfessor {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obterConexao()) {
            // Teste de validação Professor
            String email_professor = "testeP@sistemapoliedro.com.br";
            String senha = "professor";
            Professor professor = new Professor(email_professor, senha);
            professor.fazerLogin(professor);
            professor.atributosDB(professor);
            System.out.println(professor);

            // Teste de cadastrar pergunta
            String teste_pergunta = "Teste de pergunta";
            Pergunta pergunta = new Pergunta(teste_pergunta);
            professor.cadastrarPergunta(professor, teste_pergunta);
            pergunta.atributosDB(pergunta);
            System.out.println(pergunta);

            // Teste de cadastrar resposta
            String alternativa_correta = "Certo";
            String alternativa_um = "Um";
            String alternativa_dois = "Dois";
            String alternativa_tres = "Tres";
            Resposta resposta = new Resposta(pergunta.getId_pergunta());
            professor.cadastrarResposta(pergunta, alternativa_correta, alternativa_um, alternativa_dois, alternativa_tres);
            resposta.atributosDB(resposta, pergunta);
            System.out.println(resposta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
