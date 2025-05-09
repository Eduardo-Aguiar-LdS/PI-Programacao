package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Professor extends Aluno{ 
    // Herda atributos dos alunos

    // Construtor
    public Professor(String nome, String email) {
        super(nome, email);
    }
    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void fazerCadastro(Professor professor) throws Exception {
        String sql = "insert into Professor (nome_professor, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\nNome: " + professor.getNome() + "\nE-mail: " + professor.getSenha(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Professor já possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Função de administrador - cadastrar aluno
    public void cadastrarAluno(Aluno aluno) throws Exception {
        String sql = "insert into Aluno (nome_aluno, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getSenha());
            try {
                ps.execute();
            } catch (Exception e) {
                throw new RuntimeException("Aluno já possui cadastro");
            }
        }
    }

    // Getters e Setters
    // public String getNome() {
    //     return nome;
    // }

    // public void setNome(String nome) {
    //     this.nome = super(nome);
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

}
