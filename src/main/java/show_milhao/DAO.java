package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    public boolean existeAluno(Aluno aluno) throws Exception {
        String sql = "select * from Aluno where nome_aluno = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeProfessor(Professor professor) throws Exception {
        String sql = "select * from Professor where nome_professor = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // private void usarAjuda(Pergunta pergunta) {
    // System.out.println("\nOpções de ajuda:");
    // System.out.println("1) Eliminar duas opções (50/50)");
    // System.out.println("2) Pular pergunta");
    // implementar logica

    public boolean salvarProgresso(Aluno aluno) {
        String sql = "UPDATE alunos SET pontuacao = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.obterConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, aluno.getPontuacao());
            ps.setString(2, aluno.getEmail());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar progresso: " + e.getMessage());
            return false;
        }
    }
    // public void testarJogoCompleto() throws Exception {
    // Aluno aluno = new Aluno("Aluno Jogo", "alunojogo@teste.com", "senha123", 0,
    // 0, 0);
    // if (!existeAluno(aluno)) {
    // Professor admin = new Professor("Admin", "admin@escola.com");
    // admin.cadastrarAluno(aluno);
    // }
    // aluno.jogar();

    // System.out.println("Pontuação final: " + aluno.getPontuacao());
    // }

    // Exibir pontuaacao durante o jogo
    // public boolean exibirPontuacao( // Jogo iniciado retornar pontuacao)
    // throws Exception {
    //
    // }
    //
}
