package src.br.com.showdomilhao.model;
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int pontuacao;
    private int respostasCorretas;
    private int respostasErradas;
    
    public Usuario() {
        this.pontuacao = 0;
        this.respostasCorretas = 0;
        this.respostasErradas = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
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

    public int getRespostasCorretas() {
        return respostasCorretas;
    }

    public void setRespostasCorretas(int respostasCorretas) {
        this.respostasCorretas = 0;
    }

    public int getRespostasErradas() {
        return respostasErradas;
    }

    public void setRespostasErradas(int respostasErradas) {
        this.respostasErradas = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Pontuação: %d", nome, email, pontuacao);
    }
}    