package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import telas.telas_gerais.TelaLogin;

public class Professor extends Aluno {
    private int id_professor;
    // Herda atributos, getters e setters dos alunos

    // Construtor
    public Professor(String email, String senha) {
        super(email, senha);
    }

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Professor(String nome, String email, String senha, int id_professor) {
        super(nome, email, senha);
        this.id_professor = id_professor;
    }

    public Professor(String nome, String email, String senha, int id_professor, int pontuacao) {
        super(nome, email, senha, pontuacao);
        this.id_professor = id_professor;
    }

    // Fazer login
    public void fazerLogin(Professor professor, TelaLogin tela) throws Exception {
        String sql = "select * from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getEmail());
            ps.setString(2, professor.getSenha());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Login de professor realizado com sucesso!" + "\nE-mail: " + professor.getEmail(), "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                tela.irTelaPrincipalAdmin(professor);
            } else {
                JOptionPane.showMessageDialog(null, "Professor não possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Buscar atributos no banco de dados
    public Professor atributosDB(Professor professor) throws Exception {
        String sql = "select nome_professor, id_professor from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getEmail());
            ps.setString(2, professor.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    professor.setNome(rs.getString("nome_professor"));
                    professor.setId_professor(rs.getInt("id_professor"));
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
        String sql = "insert into Aluno (nome_aluno, email, senha, id_turma) select ?, ?, ?, t.id_turma FROM Turma t WHERE t.nome_turma = ?;";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getSenha());
            ps.setString(4, aluno.getTurma());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null, "Aluno cadastrado");
            } catch (Exception e) {
                throw new RuntimeException("Aluno já possui cadastro");
            }
        }
    }

    // Função de administrador - cadastrar pergunta
    public void cadastrarPergunta(Professor professor, String pergunta, String dificuldade) throws Exception {
        String sql = "insert into Pergunta (pergunta, dificuldade, id_professor) values(?, ?, ?)";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, pergunta);
            ps.setString(2, dificuldade);
            ps.setInt(3, professor.getId_professor());
            try {
                ps.execute();
            } catch (Exception e) {
                throw new RuntimeException("Pergunta já existe");
            }
        }
    }

    public void cadastrarResposta(Pergunta pergunta, String resposta_correta, String resposta_um, String resposta_dois,
            String resposta_tres) throws Exception {
        String sql = "insert into Resposta (resposta_correta, resposta_um, resposta_dois, resposta_tres, id_pergunta) values(?, ?, ?, ?, ?)";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, resposta_correta);
            ps.setString(2, resposta_um);
            ps.setString(3, resposta_dois);
            ps.setString(4, resposta_tres);
            ps.setInt(5, pergunta.getId_pergunta());
            try {
                ps.execute();
            } catch (Exception e) {
                throw new RuntimeException("Erro em cadastrar resposta");
            }
        }
    }

    // Getters e Setters
    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    @Override
    public String toString() {
        return "Professor\nNome: " + getNome() + "\nEmail: " + getEmail() + "\nSenha: " + getSenha()
                + "\nID Professor: " + getId_professor();
    }
}
