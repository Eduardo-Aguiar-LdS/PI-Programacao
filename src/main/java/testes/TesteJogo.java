package testes;

import show_milhao.*;

public class TesteJogo {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("João", "joao@email.com", "senha123", 0, 0, 0);
        
        Jogo jogo = new Jogo(aluno);
        jogo.jogar();
    }
}