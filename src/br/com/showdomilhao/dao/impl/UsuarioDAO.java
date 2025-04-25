package src.br.com.showdomilhao.dao.impl;

import src.br.com.showdomilhao.model.Usuario;
import src.br.com.showdomilhao.util.ConexaoBD;
import java.sql.*;
public class UsuarioDAO {

    public boolean autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT 1 FROM Usuario WHERE email = ? AND senha = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setPontuacao(rs.getInt("pontuacao"));
                    usuario.setRespostasCorretas(rs.getInt("respostas_corretas"));
                    usuario.setRespostasErradas(rs.getInt("respostas_erradas"));
                    return usuario;
                }
            }
        }
        return null;
    }
}