package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import telas.componentes.botoes.RoundedButton;

public class DAO { // Classe criada para exibição nos cadastros e edições
    private String nomeTurma;
    private String perguntaEscolhida;
    private String nomeProfessor;

    // Exibir turma
    public String[] exibirTurma() throws Exception {
        try {
            String sql = "select * from Turma";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                List<String> nomesTurmas = new ArrayList<>();
                while (rs.next()) {
                    nomesTurmas.add(rs.getString("nome_turma"));
                }
                return nomesTurmas.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Erro em exibir turmas" };
        }
    }

    public String[] exibirAluno(String nomeTurma) throws Exception {
        try {
            String sql = "select * from Aluno as a join Turma as t on a.id_turma = t.id_turma where nome_turma = ?";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nomeTurma);
                ResultSet rs = ps.executeQuery();
                List<String> nomesAlunos = new ArrayList<>();
                while (rs.next()) {
                    nomesAlunos.add(rs.getString("nome_aluno"));
                }
                return nomesAlunos.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Erro em exibir nomes" };
        }
    }

    public String[] verPerguntas() throws Exception {
        try {
            String sql = "select pergunta from Pergunta order by id_pergunta;";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<String> perguntasList = new ArrayList<>();
                while (rs.next()) {
                    perguntasList.add(rs.getString("pergunta"));
                }
                return perguntasList.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Erro em exibir perguntas" };
        }
    }

    public String[] verRespostas(String perguntaSelecionada) throws Exception {
        try {
            String sql = "select r.resposta_correta, r.resposta_um, r.resposta_dois, r.resposta_tres from Pergunta as p join Resposta as r on p.id_pergunta = r.id_pergunta where p.pergunta = ? order by p.id_pergunta;";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<String> perguntasList = new ArrayList<>();
                while (rs.next()) {
                    perguntasList.add(rs.getString("pergunta"));
                }
                return perguntasList.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Erro em exibir perguntas" };
        }
    }

    public String[] exibirProfessor() throws Exception {
        try {
            String sql = "select nome_professor from Professor;";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                List<String> nomesProfessores = new ArrayList<>();
                while (rs.next()) {
                    nomesProfessores.add(rs.getString("nome_professor"));
                }
                return nomesProfessores.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Erro em exibir nomes de professores" };
        }
    }

    // Verifica quantidae de perguntas para embaralhar perguntas
    public int quantidadePerguntas() throws Exception {
        try {
            String sql = "select id_pergunta from Pergunta;";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                int quantPerguntas = 0;
                while (rs.next()) {
                    quantPerguntas++;
                }
                return quantPerguntas;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 30; // Padrão do banco de dados show_poliedro
        }
    }

    public void atualizarAluno(Aluno aluno) {
        try (Connection conexao = ConnectionFactory.obterConexao()){
            aluno.atributosDB(aluno);
            String sql = "update Aluno set nome_aluno = ?, email = ?, senha = ? where id_aluno = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEmail());
                ps.setString(3, aluno.getSenha());
                ps.setInt(4, aluno.getIdAluno());
                try {
                    ps.executeQuery();
                    JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em trazer atualizar aluno", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarPergunta(Pergunta pergunta) {
        try (Connection conexao = ConnectionFactory.obterConexao()){
            pergunta.atributosDB(pergunta);
            String sql = "update Pergunta set dificuldade = ?, id_professor = ? where pergunta = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setString(1, pergunta.getDificuldade());
                ps.setInt(2, pergunta.getId_professor());
                ps.setString(3, pergunta.getQuestao());
                try {
                    ps.executeQuery();
                    JOptionPane.showMessageDialog(null, "Pergunta atualizada com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em trazer atualizar pergunta", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarResposta(Resposta resposta) {

    }

    public void atualizarProfessor(Professor professor) {

    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getPerguntaEscolhida() {
        return perguntaEscolhida;
    }

    public void setPerguntaEscolhida(String perguntaEscolhida) {
        this.perguntaEscolhida = perguntaEscolhida;
    }

    public String getNomesProfessores() {
        return nomeProfessor;
    }

    public void setNomesProfessores(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    // Editar Aluno na TelaEditarAlunoProfessor
    // String[] alunos = dao.exibirAluno(turmaSelecionada);
    // RoundedComboBox<String> tfAluno = new RoundedComboBox<>(alunos);
}
