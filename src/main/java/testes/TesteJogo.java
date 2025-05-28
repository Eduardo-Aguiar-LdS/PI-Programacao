// package testes;

// import show_milhao.*;

// import java.sql.Connection;
// import java.util.ArrayList;
// import java.util.List;

// public class TesteJogo {
//     public static void main(String[] args) {
//         try (Connection conn = ConnectionFactory.obterConexao()) {
//             String email_aluno = "testeA@p4ed.com";
//             String senha = "aluno";
//             Aluno aluno = new Aluno(email_aluno, senha);
//             aluno.atributosDB(aluno);

//             List<Pergunta> perguntasFacil = new ArrayList<>();
//             List<Pergunta> perguntasMedio = new ArrayList<>();
//             List<Pergunta> perguntasDificil = new ArrayList<>();
//             List<Resposta> respostasFacil = new ArrayList<>();
//             List<Resposta> respostasMedio = new ArrayList<>();
//             List<Resposta> respostasDificil = new ArrayList<>();

//             Pergunta pergunta = new Pergunta();

//             for (int cont = 0; cont <= 9; cont++) {
//                 System.out.println(cont);
//                 pergunta.exibirFacil(perguntasFacil, respostasFacil);
//                 pergunta.exibirMedio(perguntasMedio, respostasMedio);
//                 pergunta.exibirDificil(perguntasDificil, respostasDificil);
//                 System.out.println(perguntasFacil.get(cont));
//                 System.out.println(respostasFacil.get(cont));
//                 System.out.println(perguntasMedio.get(cont));
//                 System.out.println(respostasMedio.get(cont));
//                 System.out.println(perguntasDificil.get(cont));
//                 System.out.println(respostasDificil.get(cont));
//             }

//             // Mini teste para quebrar while
//             boolean escolhaErrada = false;
//             while (escolhaErrada != true) {
//                 System.out.println("Antes");
//                 escolhaErrada = true;
//                 System.out.println("Depois");
//             }
//             escolhaErrada = false;
//             // Jogo jogo = new Jogo(aluno);
//             // jogo.jogar();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }