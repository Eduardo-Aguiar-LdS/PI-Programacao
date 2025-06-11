package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import telas.telas_gerais.TelaLogin;

public class Coordenador extends Professor {

    // Construtor
    public Coordenador(String nome, String email) {
        super(nome, email);
    }

    public Coordenador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Coordenador(String nome, String email, String senha, int id_professor) {
        super(nome, email, senha, id_professor);
    }

    public Coordenador(String nome, String email, String senha, int id_professor, int pontuacao) {
        super(nome, email, senha, id_professor, pontuacao);
    }

    // Fazer login
    public void fazerLogin(Coordenador coordenador, TelaLogin tela) throws Exception {
        String sql = "select * from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, coordenador.getEmail());
            ps.setString(2, coordenador.getSenha());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Login de coordenador realizado com sucesso!" + "\nE-mail: " + coordenador.getEmail(),
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                tela.irTelaPrincipalAdmin(coordenador);
            } else {
                JOptionPane.showMessageDialog(null, "Senha inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Buscar atributos no banco de dados
    public Coordenador atributosDB(Coordenador coordenador) throws Exception {
        String sql = "select nome_professor, id_professor from Professor where email = ? and senha = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, coordenador.getEmail());
            ps.setString(2, coordenador.getSenha());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    coordenador.setNome(rs.getString("nome_professor"));
                    coordenador.setId_professor(rs.getInt("id_professor"));
                }
                return coordenador;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer nome_email do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer nome_professor do DB");
            }
        }
    }

    // Função do Coordenador - Cadastrar Professor
    public void cadastrarProfessor(Professor professor) throws Exception {
        String sql = "insert into Professor (nome_professor, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null, "Professor cadastrado");
            } catch (Exception e) {
                throw new RuntimeException("Professor já possui cadastro");
            }
        }
    }

    @Override
    public String toString() {
        return "Coordenador\nNome: " + getNome() + "\nEmail: " + getEmail() + "\nSenha: " + getSenha()
                + "\nID Coordenador: " + getId_professor();
    }
}