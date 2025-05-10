package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Coordenador extends Professor{
    // Construtor
    public Coordenador(String nome, String email){
        super(nome, email);
    }
    public Coordenador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

        // Função do Coordenador - Cadastrar Professor
        public void cadastrarProfessor(Professor professor) throws Exception {
        String sql = "insert into Professor (nome_professor, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getSenha());
            try {
                ps.execute();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!\nNome: " + professor.getNome() + "\nE-mail: " + professor.getEmail()+"\nSenha: "+professor.getSenha(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Professor já possui cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}