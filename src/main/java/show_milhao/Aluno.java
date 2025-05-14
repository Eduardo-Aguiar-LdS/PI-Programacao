package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Aluno {
    private String nome;
    private String email;
    private String senha;
    private String turma;
    private int pontuacao;
    private int respostas_corretas;
    private int respostas_erradas;

    // Construtores
    public Aluno(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Aluno(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Aluno(String nome, String email, String senha, int pontuacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
    }

    public Aluno(String nome, String email, String senha, int respostas_corretas, int respostas_erradas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.respostas_corretas = respostas_corretas;
        this.respostas_erradas = respostas_erradas;
    }

    public Aluno(String nome, String email, String senha, String turma, int respostas_corretas, int respostas_erradas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.turma = turma;
        this.respostas_corretas = respostas_corretas;
        this.respostas_erradas = respostas_erradas;
    }

    public Aluno(String nome, String email, String senha, int pontuacao, int respostas_corretas,
            int respostas_erradas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.respostas_corretas = respostas_corretas;
        this.respostas_erradas = respostas_erradas;
    }

    // Fazer login
    public void fazerLogin(Aluno aluno) throws Exception {
        String sql = "select * from Aluno where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getEmail());
            ps.setString(2, aluno.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null,
                        "Login realizado com sucesso!" + "\nE-mail: " + aluno.getEmail(), "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Aluno não possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Buscar todos os atributos do banco de dados
    public Aluno atributosDB(Aluno aluno) throws Exception {
        String sql = "select a.nome_aluno, t.nome_turma, a.respostas_corretas, a.respostas_erradas from Aluno as a join Turma as t on a.id_turma = t.id_turma where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getEmail());
            ps.setString(2, aluno.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    aluno.setNome(rs.getString("nome_aluno"));
                    aluno.setTurma(rs.getString("nome_turma"));
                    aluno.setRespostas_corretas(rs.getInt("respostas_corretas"));
                    aluno.setRespostas_erradas(rs.getInt("respostas_erradas"));
                }
                return aluno;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer atributos do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer atributos do DB");
            }
        }
    }

    // Verificar se este método é necessário após implementar a tela de estatística
    /* Exibir perguntas respondidas (corretas e erradas)
    public Aluno exibirPerguntasRespondidas(Aluno aluno) throws Exception {
        String sql = "select respostas_corretas, respostas_erradas from Aluno where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getEmail());
            ps.setString(2, aluno.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    aluno.setRespostas_corretas(rs.getInt("respostas_corretas"));
                    aluno.setRespostas_erradas(rs.getInt("respostas_erradas"));
                }
                return aluno;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em exibir perguntas respondidas", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em exibir perguntas respondidas");
            }
        }
    } */

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

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
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

    @Override
    public String toString() {
        return "Aluno\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + senha + "\nTurma: " + turma
                + "\nPontuacao: "
                + pontuacao + "\nRespostas_corretas: " + respostas_corretas + "\nRespostas_erradas: "
                + respostas_erradas;
    }

}
