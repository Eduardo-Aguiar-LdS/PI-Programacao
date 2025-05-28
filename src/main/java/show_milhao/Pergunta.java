package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Pergunta {
    private String questao;
    private int id_professor;
    private int id_pergunta;

    // Construtor
    public Pergunta() {
    }

    public Pergunta(String questao) {
        this.questao = questao;
    }

    public Pergunta(String questao, int id_professor, int id_pergunta) {
        this.questao = questao;
        this.id_professor = id_professor;
        this.id_pergunta = id_pergunta;
    }

    // Buscar atributos no banco de dados
    public Pergunta atributosDB(Pergunta pergunta) throws Exception {
        String sql = "select pe.id_pergunta, pe.id_professor from Professor as pr join Pergunta as pe on pr.id_professor = pe.id_professor where pergunta = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, pergunta.getQuestao());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    pergunta.setId_pergunta(rs.getInt("id_pergunta"));
                    pergunta.setId_professor(rs.getInt("id_professor"));
                }
                return pergunta;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer atributos do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer atributos do DB");
            }
        }
    }

    // Função de exibir Pergunta
    public void exibir(Pergunta p, Resposta r, int contadorPergunta) throws Exception {
        try {
            String sql = "select p.pergunta, r.resposta_correta, r.resposta_um, r.resposta_dois, r.resposta_tres from Pergunta as p join Resposta as r on p.id_pergunta = r.id_pergunta where p.id_pergunta = ?";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, contadorPergunta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    p.setQuestao(rs.getString("pergunta"));
                    r.setResposta_correta(rs.getString("resposta_correta"));
                    r.setResposta_um(rs.getString("resposta_um"));
                    r.setResposta_dois(rs.getString("resposta_dois"));
                    r.setResposta_tres(rs.getString("resposta_tres"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro em exibir pergunta", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro em exibir pergunta fácil do DB");
        }
    }

    // Getters e Setters
    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    public int getId_pergunta() {
        return id_pergunta;
    }

    public void setId_pergunta(int id_pergunta) {
        this.id_pergunta = id_pergunta;
    }

    @Override
    public String toString() {
        return "Pergunta\n" + getQuestao();
    }
}
