package show_milhao;

public class TesteAluno {
    public static void main(String[] args) {
        testarCriacaoAluno();
        testarJogoBasico();
    }

    public static void testarCriacaoAluno() {
        System.out.println("=== TESTE DE CRIAÇÃO DE ALUNO ===");
        Aluno aluno = new Aluno("Maria", "maria@email.com", "senha123", 0, 0, 0);
        
        if(aluno.getNome().equals("Maria")) {
            System.out.println("Teste de criação - OK");
        } else {
            System.out.println("Teste de criação - FALHOU");
        }
    }

    public static void testarJogoBasico() {
        System.out.println("\n=== TESTE DE JOGO BÁSICO ===");
        Aluno aluno = new Aluno("João", "joao@email.com", "senha123", 0, 0, 0);
        Jogo jogo = new Jogo(aluno);
        
        System.out.println("Iniciando teste do jogo...");
        jogo.jogar(); // Isso mostrará a pergunta e opções
        
        System.out.println("\nTeste de jogo básico concluído");
        System.out.println("(Verifique manualmente se a pergunta foi exibida corretamente)");
    }
}