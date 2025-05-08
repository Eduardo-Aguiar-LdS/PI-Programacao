package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    public boolean existeAluno(Aluno aluno) throws SQLException {
        String sql = "select * from Aluno where nome_aluno = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeProfessor(Professor professor) throws SQLException {
        String sql = "select * from Professor where nome_professor = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT 1 FROM Aluno WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeComEmail(String email) throws SQLException {
        String sqlAluno = "SELECT 1 FROM Aluno WHERE email = ?";
        String sqlProfessor = "SELECT 1 FROM Professor WHERE email = ?";

        try (Connection conn = ConnectionFactory.obterConexao()) {
            try (PreparedStatement ps = conn.prepareStatement(sqlAluno)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next())
                        return true;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlProfessor)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public buscarUsuarioPorEmail(String email) throws SQLException {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email n�o pode ser nulo ou vazio");
        }

        String sqlAluno = "SELECT * FROM Aluno WHERE email = ?";
        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sqlAluno)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getString("nome_aluno"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getInt("pontuacao"),
                            rs.getInt("respostas_corretas"),
                            rs.getInt("respostas_erradas"));
                }
            }
        }

        String sqlProfessor = "SELECT * FROM Professor WHERE email = ?";
        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sqlProfessor)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Professor(
                            rs.getString("nome_professor"),
                            rs.getString("email"),
                            rs.getString("senha"));
                }
            }
        }

        return null;
    }

    /*
     * Exibir pontuação durante o jogo
     * public boolean exibirPontuacao( // Jogo iniciado retornar pontuação) throws
     * Exception {
     * 
     * }
     */
}
