package src.br.com.showdomilhao.dao.impl;

import src.br.com.showdomilhao.util.ConexaoBD;
import src.br.com.showdomilhao.model.Aluno;
import java.sql.*;

public class AlunoDAO {
    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO Aluno (nome_aluno, email, senha, pontuacao, respostas_corretas, respostas_erradas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getSenha());
            ps.setInt(4, aluno.getPontuacao());
            ps.setInt(5, aluno.getRespostasCorretas());
            ps.setInt(6, aluno.getRespostasErradas());
            ps.executeUpdate();
        }
    }

    public Aluno buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Aluno WHERE email = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id_aluno"));
                    aluno.setNome(rs.getString("nome_aluno"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setSenha(rs.getString("senha"));
                    aluno.setPontuacao(rs.getInt("pontuacao"));
                    aluno.setRespostasCorretas(rs.getInt("respostas_corretas"));
                    aluno.setRespostasErradas(rs.getInt("respostas_erradas"));
                    return aluno;
                }
            }
        }
        return null;
    }
}
