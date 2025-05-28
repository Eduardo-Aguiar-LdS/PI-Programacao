package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Resposta {
    private String resposta_correta;
    private String resposta_um;
    private String resposta_dois;
    private String resposta_tres;
    private int id_pergunta;
    private int id_resposta;

    // Construtor
    public Resposta(){
    }

    public Resposta(int id_pergunta){
        this.id_pergunta = id_pergunta;
    }

    public Resposta(String resposta_correta, String resposta_um, String resposta_dois, String resposta_tres) {
        this.resposta_correta = resposta_correta;
        this.resposta_um = resposta_um;
        this.resposta_dois = resposta_dois;
        this.resposta_tres = resposta_tres;
    }

    public Resposta(String resposta_correta, String resposta_um, String resposta_dois, String resposta_tres, int id_pergunta) {
        this.resposta_correta = resposta_correta;
        this.resposta_um = resposta_um;
        this.resposta_dois = resposta_dois;
        this.resposta_tres = resposta_tres;
        this.id_pergunta = id_pergunta;
    }

    public Resposta(String resposta_correta, String resposta_um, String resposta_dois, String resposta_tres, int id_pergunta, int id_resposta) {
        this.resposta_correta = resposta_correta;
        this.resposta_um = resposta_um;
        this.resposta_dois = resposta_dois;
        this.resposta_tres = resposta_tres;
        this.id_pergunta = id_pergunta;
        this.id_resposta = id_resposta;
    }

    // Buscar atributos no banco de dados
    public Resposta atributosDB(Resposta resposta, Pergunta pergunta) throws Exception {
        String sql = "select id_resposta, resposta_correta, resposta_um, resposta_dois, resposta_tres from Resposta where id_pergunta = ?";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, resposta.getId_pergunta());
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    resposta.setId_resposta(rs.getInt("id_resposta"));
                    resposta.setResposta_correta(rs.getString("resposta_correta"));
                    resposta.setResposta_um(rs.getString("resposta_um"));
                    resposta.setResposta_dois(rs.getString("resposta_dois"));
                    resposta.setResposta_tres(rs.getString("resposta_tres"));
                }
                return resposta;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro em trazer atributos do DB", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Erro em trazer atributos do DB");
            }
        }
    }

    // Função de exibir Respostas

    // Getters e Setters

    public String getResposta_correta() {
        return resposta_correta;
    }

    public void setResposta_correta(String resposta_correta) {
        this.resposta_correta = resposta_correta;
    }

    public String getResposta_um() {
        return resposta_um;
    }

    public void setResposta_um(String resposta_um) {
        this.resposta_um = resposta_um;
    }

    public String getResposta_dois() {
        return resposta_dois;
    }

    public void setResposta_dois(String resposta_dois) {
        this.resposta_dois = resposta_dois;
    }

    public String getResposta_tres() {
        return resposta_tres;
    }

    public void setResposta_tres(String resposta_tres) {
        this.resposta_tres = resposta_tres;
    }

    public int getId_pergunta() {
        return id_pergunta;
    }

    public void setId_pergunta(int id_pergunta) {
        this.id_pergunta = id_pergunta;
    }
    
    public int getId_resposta() {
        return id_resposta;
    }

    public void setId_resposta(int id_resposta) {
        this.id_resposta = id_resposta;
    }

    @Override
    public String toString() {
        return "Resposta"+"\nResposta correta: "+getResposta_correta()+"\nResposta um: "+getResposta_um()+"\nResposta dois: "+getResposta_dois()+"\nResposta tres: "+getResposta_tres();
    }

}
