package show_milhao;

import java.util.*;

public class Jogo {
    private Aluno aluno;
    private Scanner scanner = new Scanner(System.in);

    public Jogo(Aluno aluno) {
        this.aluno = aluno;
    }

    private void usarAjuda5050(Pergunta pergunta) {
        List<Integer> indicesErrados = new ArrayList<>();
        for (int i = 0; i < pergunta.getOpcoes().length; i++) {
            if (!pergunta.getOpcoes()[i].equals(pergunta.getOpcaoCorreta())) {
                indicesErrados.add(i);
            }
        }

        Collections.shuffle(indicesErrados);
        int remover1 = indicesErrados.get(0);
        int remover2 = indicesErrados.get(1);

        System.out.println("\nUsou ajuda 50/50! Restaram:");
        for (int i = 0; i < pergunta.getOpcoes().length; i++) {
            if (i != remover1 && i != remover2) {
                System.out.println((i + 1) + ") " + pergunta.getOpcoes()[i]);
            }
        }
    }


    public void jogar() {
        System.out.println("\n=== SHOW DO MILHÃO ===");
        System.out.println("Bem-vindo, " + aluno.getNome() + "!");

        boolean usouDica = false;
        boolean pulouPergunta = false;
        Scanner scanner = new Scanner(System.in);

        while (!BancoDePerguntas.acabou()) {
            Pergunta pergunta = BancoDePerguntas.obterPerguntaAleatoria();
            if (pergunta == null) break;

            usouDica = false;
            pulouPergunta = false;
            boolean respondeu = false;

            while (!respondeu) {
                if (!usouDica) {
                    System.out.println("\nPergunta:");
                    System.out.println(pergunta.getTexto());

                    for (int i = 0; i < pergunta.getOpcoes().length; i++) {
                        System.out.println((i + 1) + ") " + pergunta.getOpcoes()[i]);
                    }
                }

            System.out.println("\nEscolha sua resposta (1-4)");
            if (!usouDica) System.out.println("5) Usar ajuda 50/50");
            if (!pulouPergunta) System.out.println("6) Pular pergunta");

            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();

            if (escolha == 5 && !usouDica) {
                usarAjuda5050(pergunta);
                usouDica = true;
            } else if (escolha == 6 && !pulouPergunta) {
                pergunta = BancoDePerguntas.obterPerguntaAleatoria();
                pulouPergunta = true;
                usouDica = false;
            } else if (escolha >= 1 && escolha <= 4) {
                String opcaoEscolhida = pergunta.getOpcoes()[escolha - 1];
                if (opcaoEscolhida.equals(pergunta.getOpcaoCorreta())) {
                    if (!BancoDePerguntas.acabou()) {
                        System.out.println("\nResposta correta! Próxima pergunta!");
                    }
                    respondeu = true;    
                } else {
                    System.out.println("\nResposta incorreta.");
                    System.out.println("A resposta correta era: " + pergunta.getOpcaoCorreta());
                    return;
                }
            } else {
                System.out.println("Opção inválida!");
            }

            }
        }
        System.out.println("\nParabéns, " + aluno.getNome() + "! Você respondeu todas as perguntas corretamente e completou o quiz!");
    }
}
