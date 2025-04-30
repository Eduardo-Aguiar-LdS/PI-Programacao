package src.br.com.showdomilhao.dao.impl;

import src.br.com.showdomilhao.model.Professor;
import src.br.com.showdomilhao.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    public void inserir(Professor professor) throws SQLException {
        String sql = "INSERT INTO Professor (nome_professor, email, senha, pontuacao, respostas_corretas, respostas_erradas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getSenha());
            ps.setInt(4, professor.getPontuacao());
            ps.setInt(5, professor.getRespostasCorretas());
            ps.setInt(6, professor.getRespostasErradas());
            ps.executeUpdate();
        }
    }

    public boolean autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT 1 FROM Professor WHERE email = ? AND senha = ?";
        try (Connection conn = ConexaoBD.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeComEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM Professor WHERE email = ?";
        try (Connection conn = ConexaoBD.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Professor buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Professor WHERE email = ?";
        try (Connection conn = ConexaoBD.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("id_professor"));
                    professor.setNome(rs.getString("nome_professor"));
                    professor.setEmail(rs.getString("email"));
                    professor.setSenha(rs.getString("senha"));
                    professor.setPontuacao(rs.getInt("pontuacao"));
                    professor.setRespostasCorretas(rs.getInt("respostas_corretas"));
                    professor.setRespostasErradas(rs.getInt("respostas_erradas"));
                    return professor;
                }
            }
        }
        return null;
    }

    public List<Professor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Professor";
        List<Professor> professores = new ArrayList<>();
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id_professor"));
                professor.setNome(rs.getString("nome_professor"));
                professor.setEmail(rs.getString("email"));
                professores.add(professor);
            }
        }
        return professores;
    }
    
    
    public void deletarPorEmail(String email) throws SQLException {
        String sql = "DELETE FROM Professor WHERE email = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }

}
