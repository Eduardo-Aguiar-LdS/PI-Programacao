package src.br.com.showdomilhao.dao.impl;

import src.br.com.showdomilhao.model.Aluno;
import src.br.com.showdomilhao.util.ConnectionFactory;
import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

public class AlunoDAO{//depois posso fazer herdando da classe interface
    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO Aluno (nome_aluno, email, senha, pontuacao, respostas_corretas, respostas_erradas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.obtemConexao();
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

    public boolean autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT 1 FROM Aluno WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.obtemConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeComEmail(Aluno aluno, String email) throws SQLException {
        String sql = "SELECT 1 FROM Aluno WHERE nome = ? and email = ?";
        try (Connection conn = ConnectionFactory.obtemConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}

    // public Aluno buscarPorEmail() throws SQLException {
    //     String sql = "SELECT * FROM Aluno WHERE email = ?";
    //     try (Connection conn = ConnectionFactory.obtemConexao();
    //             PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setString(1, email);
    //         try (ResultSet rs = ps.executeQuery()) {
    //             if (rs.next()) {
    //                 Aluno aluno = new Aluno();
    //                 aluno.setNome(rs.getString("nome_aluno"));
    //                 aluno.setEmail(rs.getString("email"));
    //                 aluno.setSenha(rs.getString("senha"));
    //                 aluno.setPontuacao(rs.getInt("pontuacao"));
    //                 aluno.setRespostasCorretas(rs.getInt("respostas_corretas"));
    //                 aluno.setRespostasErradas(rs.getInt("respostas_erradas"));
    //                 return aluno;
    //             }
    //         }
    //     }
    //     return null;
    // }

//     public List<Aluno> listarTodos() throws SQLException {
//         String sql = "SELECT * FROM Aluno";
//         List<Aluno> alunos = new ArrayList<>();
//         try (Connection conn = ConnectionFactory.obtemConexao();
//                 PreparedStatement ps = conn.prepareStatement(sql);
//                 ResultSet rs = ps.executeQuery()) {
//             while (rs.next()) {
//                 Aluno aluno = new Aluno();
//                 aluno.setId(rs.getInt("id_aluno"));
//                 aluno.setNome(rs.getString("nome_aluno"));
//                 aluno.setEmail(rs.getString("email"));
//                 aluno.setSenha(rs.getString("senha"));
//                 aluno.setPontuacao(rs.getInt("pontuacao"));
//                 aluno.setRespostasCorretas(rs.getInt("respostas_corretas"));
//                 aluno.setRespostasErradas(rs.getInt("respostas_erradas"));
//                 alunos.add(aluno);
//             }
//         }
//         return alunos;
//     }

//     public void deletarPorEmail(String email) throws SQLException {
//         String sql = "DELETE FROM Aluno WHERE email = ?";
//         try (Connection conn = ConnectionFactory.obtemConexao();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//             ps.setString(1, email);
//             ps.executeUpdate();
//         }
//     }

//     public void atualizarPontuacao(int id, int novaPontuacao) throws SQLException {
//         String sql = "UPDATE Aluno SET pontuacao = ? WHERE id_aluno = ?";
//         try (Connection conn = ConnectionFactory.obtemConexao();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//             ps.setInt(1, novaPontuacao);
//             ps.setInt(2, id);
//             ps.executeUpdate();
//         }
//     }

//     public void atualizarRespostas(int id, int respostasCorretas, int respostasErradas) throws SQLException {
//         String sql = "UPDATE Aluno SET respostas_corretas = ?, respostas_erradas = ? WHERE id_aluno = ?";
//         try (Connection conn = ConnectionFactory.obtemConexao();
//                 PreparedStatement ps = conn.prepareStatement(sql)) {
//             ps.setInt(1, respostasCorretas);
//             ps.setInt(2, respostasErradas);
//             ps.setInt(3, id);
//             ps.executeUpdate();
//         }
//     }
// }
