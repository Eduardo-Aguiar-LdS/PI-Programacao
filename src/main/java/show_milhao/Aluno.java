package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import telas.componentes.botoes.RoundedButton;
import telas.telas_aluno.TelaPrincipalAluno;
import telas.telas_gerais.TelaJogar;
import telas.telas_gerais.TelaLogin;

public class Aluno {
    private String nome;
    private String email;
    private String senha;
    private String turma;
    private int idAluno;
    private int pontuacao;
    private int respostas_corretas;
    private int respostas_erradas;
    private boolean usouDica;
    private boolean usouPular;

    // Construtores
    public Aluno(String nome){
        this.nome = nome;
    }

    public Aluno(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Aluno(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Aluno(String nome, String email, String senha, String turma) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.turma = turma;
    }

    public Aluno(String nome, String email, String senha, int idAluno) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idAluno = idAluno;
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
    public void fazerLogin(Aluno aluno, TelaLogin tela) throws Exception {
        String sql = "select * from Aluno where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getEmail());
            ps.setString(2, aluno.getSenha());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Login realizado com sucesso!" + "\nE-mail: " + aluno.getEmail(), "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                tela.irTelaPrincipalAluno(aluno);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Buscar todos os atributos do banco de dados
    public Aluno atributosDB(Aluno aluno) throws Exception {
        String sql = "select a.nome_aluno, a.id_aluno, t.nome_turma, a.respostas_corretas, a.respostas_erradas from Aluno as a join Turma as t on a.id_turma = t.id_turma where email = ? and senha = ?;";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getEmail());
            ps.setString(2, aluno.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    aluno.setNome(rs.getString("nome_aluno"));
                    aluno.setIdAluno(rs.getInt("id_aluno"));
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

    // Atributos para exibir a estatística para adms
    public Aluno atributosDB(String nomeSelecionado) throws Exception {
        String sql = "select a.respostas_corretas, a.respostas_erradas from Aluno as a join Turma as t on a.id_turma = t.id_turma where nome_aluno = ?;";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nomeSelecionado);
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    setRespostas_corretas(rs.getInt("respostas_corretas"));
                    setRespostas_erradas(rs.getInt("respostas_erradas"));
                }
                return this;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer atributos do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer atributos do DB");
            }
        }
    }

    public void jogar(Pergunta pergunta, Resposta resposta, int cont, RoundedButton btn) throws Exception {
        if (cont >= 30) {
            JOptionPane.showMessageDialog(null,
                    "Pontuacao: " + this.getPontuacao(), "Venceu",
                    JOptionPane.INFORMATION_MESSAGE);
            atualizarEstatisticas();
            ((JFrame) SwingUtilities.getWindowAncestor(btn)).dispose();
            new TelaPrincipalAluno(this).setVisible(true);

        } else {
            cont++;
            pergunta.exibir(pergunta, resposta, cont);
            ((JFrame) SwingUtilities.getWindowAncestor(btn)).dispose();
            new TelaJogar(this, null, null, cont, false).setVisible(true);
        }
    }

    public void atualizarEstatisticas() {
        try (Connection conexao = ConnectionFactory.obterConexao()) {
            String sql = "update Aluno set pontuacao = ?, respostas_corretas = ?, respostas_erradas = ? where id_aluno = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setInt(1, getPontuacao());
                ps.setInt(2, getRespostas_corretas());
                ps.setInt(3, getRespostas_erradas());
                ps.setInt(4, getIdAluno());
                try {
                    ps.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em atualizar pontuação", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
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

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean isUsouDica() {
        return usouDica;
    }

    public void setUsouDica(boolean usouDica) {
        this.usouDica = usouDica;
    }

    public boolean isUsouPular() {
        return usouPular;
    }

    public void setUsouPular(boolean usouPular) {
        this.usouPular = usouPular;
    }

    @Override
    public String toString() {
        return "Aluno\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + senha + "\nTurma: " + turma
                + "\nPontuacao: "
                + pontuacao + "\nRespostas_corretas: " + respostas_corretas + "\nRespostas_erradas: "
                + respostas_erradas;
    }
}
