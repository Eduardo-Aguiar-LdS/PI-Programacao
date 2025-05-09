package show_milhao;

import java.util.Scanner;

public class Aluno {
    private String nome;
    private String email;
    private String senha;
    private int pontuacao;
    private int respostas_corretas;
    private int respostas_erradas;

    public Aluno(String nome, String email, String senha, int pontuacao, int respostas_corretas,
            int respostas_erradas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.respostas_corretas = respostas_corretas;
        this.respostas_erradas = respostas_erradas;

    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = 0;
    }

    public int getRespostas_corretas() {
        return respostas_corretas;
    }

    public void setRespostas_corretas(int respostas_corretas) {
        this.respostas_corretas = respostas_corretas;
    }

    public int getRespostas_erradas() {
        return respostas_erradas;
    }

    public void setRespostas_erradas(int respostas_erradas) {
        this.respostas_erradas = respostas_erradas;
    }


}
