package codigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


// Classe Data Acess Object
public class DAO {
    // Verificação da existência do cadastro do Aluno
    public boolean existeAluno(Aluno aluno) throws Exception {
        String sql = "select * from Aluno where nome_aluno = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    // Verificação da existência do cadastro do Professor
    public boolean existeProfessor(Professor professor) throws Exception {
        String sql = "select * from Professor where nome_professor = ? and email = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    /*  Exibir pontuação durante o jogo
    public boolean exibirPontuacao( // Jogo iniciado retornar pontuação) throws Exception {

    }*/
}
