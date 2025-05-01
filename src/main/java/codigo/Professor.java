package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Professor { // Testar herança da classe aluno com extends
    private String nome;
    private String email;
    private String senha;
    private int pontuacao;
    private int respostas_corretas;
    private int respostas_erradas;

    // Construtor
    public Professor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Função de administrador - cadastrar aluno
    public String cadastrarAluno(Aluno aluno) throws Exception {
        String sql = "insert into Aluno (nome_aluno, email, senha) values (?, ?, ?);";
        try (Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getSenha());
            ps.execute();
            String cadastro = "Cadastro realizado com sucesso\nAluno: "+aluno.getNome()+"\nEmail: "+aluno.getEmail()+"\nSenha: "+aluno.getSenha();
            return cadastro;
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
