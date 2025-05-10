package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Professor extends Aluno{ 
    // Herda atributos, getters e setters dos alunos

    // Construtor
    public Professor(String email, String senha) {
        super(email, senha);
    }
    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    public Professor(String nome, String email, String senha, int pontuacao) {
        super(nome, email, senha, pontuacao);
    }

    // Fazer login
    public void fazerLogin(Professor professor) throws Exception {
        String sql = "select * from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getEmail());
            ps.setString(2, professor.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null,
                        "Login realizado com sucesso!"+ "\nE-mail: " + professor.getEmail(), "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Professor não possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Buscar o nome no banco de dados
     public Professor nomeDB(Professor professor) throws Exception {
        String sql = "select nome_professor from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getEmail());
            ps.setString(2, professor.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    professor.setNome(rs.getString("nome_professor"));
                }
                return professor;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer nome_professor do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer nome_professor do DB");
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
    @Override
    public String toString() {
        return "Professor\nNome: " + getNome() + "\nEmail: " + getEmail() + "\nSenha: " + getSenha();
    }
}
