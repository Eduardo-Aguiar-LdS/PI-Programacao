package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Pergunta {
    private String pergunta;
    private int id_professor;
    private int id_pergunta;

    // Construtor
    public Pergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Pergunta(String pergunta, int id_professor, int id_pergunta) {
        this.pergunta = pergunta;
        this.id_professor = id_professor;
        this.id_pergunta = id_pergunta;
    }

    // Buscar atributos no banco de dados
    public Pergunta atributosDB(Pergunta pergunta) throws Exception {
        String sql = "select pe.id_pergunta, pe.id_professor from Professor as pr join Pergunta as pe on pr.id_professor = pe.id_professor where pergunta = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, pergunta.getPergunta());
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

    // Getters e Setters
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
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
        return "Pergunta\n"+getPergunta()+"\nID Pergunta: "+getId_pergunta()+"\nID Professor: "+getId_professor();
    }
}
