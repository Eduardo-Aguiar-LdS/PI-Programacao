package show_milhao;

import java.util.Scanner;

public class Jogo {
    private Aluno aluno;
    
    public Jogo(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== SHOW DO MILHÃO ===");
        System.out.println("Bem-vindo, " + aluno.getNome() + "!");

        Pergunta pergunta = BancoDePerguntas.obterPerguntaAleatoria();
        
        System.out.println("\nPergunta:");
        System.out.println(pergunta.getTexto());
        
        for(int i = 0; i < pergunta.getOpcoes().length; i++) {
            System.out.println((i+1) + ") " + pergunta.getOpcoes()[i]);
        }
        
        System.out.print("\nEscolha sua resposta (1-4): ");
        int resposta = scanner.nextInt();
        
        String opcaoEscolhida = pergunta.getOpcoes()[resposta-1];
        if(opcaoEscolhida.equals(pergunta.getOpcaoCorreta())) {
            System.out.println("\nParabéns! Resposta correta!");
        } else {
            System.out.println("\nQue pena! Resposta incorreta.");
            System.out.println("A resposta correta era: " + pergunta.getOpcaoCorreta());
        }
    }
}