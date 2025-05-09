package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Aluno {
    private String nome;
    private String email;
    private String senha;
    private int pontuacao;
    private int respostas_corretas;
    private int respostas_erradas;

    // Construtores
    public Aluno(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    public Aluno(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Função de realizar cadastro
    public void fazerCadastro(Aluno aluno) throws Exception {
        String sql = "insert into Aluno (nome_aluno, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\nNome: " + nome + "\nE-mail: " + email, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Aluno já possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
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
}
