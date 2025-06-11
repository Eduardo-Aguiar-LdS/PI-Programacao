package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

    public int contarAcertosPorTurma(String nomeAluno) throws Exception {
        String sql = "select respostas_corretas from Aluno where nome_aluno = ?";
        try (
                Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nomeAluno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public int contarErrosPorTurma(String nomeAluno) throws Exception {
        String sql = "select respostas_erradas from Aluno where nome_aluno = ?";
        try (
                Connection conexao = ConnectionFactory.obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, nomeAluno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public String[] exibirAluno() throws Exception {
        try {
            String sql = "select * from Aluno as a join Turma as t on a.id_turma = t.id_turma where nome_turma = '1A';";
            try (Connection conexao = ConnectionFactory.obterConexao();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<String> nomesAlunos = new ArrayList<>();
                while (rs.next()) {
                    nomesAlunos.add(rs.getString("nome_aluno"));
                }
                return nomesAlunos.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "Insira a turma primeiro" };
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
            return new String[] { "Insira a turma primeiro" };
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
        try (Connection conexao = ConnectionFactory.obterConexao()) {
            aluno.atributosDB(aluno);
            String sql = "update Aluno set email = ?, senha = ? where id_aluno = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setString(1, aluno.getEmail());
                ps.setString(2, aluno.getSenha());
                ps.setInt(3, aluno.getIdAluno());
                try {
                    ps.executeUpdate();
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
        try (Connection conexao = ConnectionFactory.obterConexao()) {
            pergunta.atributosDB(pergunta);
            String sql = "update Pergunta set dificuldade = ?, id_professor = ? where pergunta = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setString(1, pergunta.getDificuldade());
                ps.setInt(2, pergunta.getId_professor());
                ps.setString(3, pergunta.getQuestao());
                try {
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pergunta atualizada com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em atualizar pergunta", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarResposta(Pergunta pergunta, Resposta resposta) {
        try (Connection conexao = ConnectionFactory.obterConexao()) {
            pergunta.atributosDB(pergunta);
            resposta.atributosDB(resposta, pergunta);
            String sqlPergunta = "update Pergunta set dificuldade = ?, id_professor = ? where pergunta = ?;";
            try (PreparedStatement psPergunta = conexao.prepareStatement(sqlPergunta);) {
                psPergunta.setString(1, pergunta.getDificuldade());
                psPergunta.setInt(2, pergunta.getId_professor());
                psPergunta.setString(3, pergunta.getQuestao());
                try {
                    psPergunta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pergunta atualizada com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em atualizar pergunta e resposta", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            String sqlResposta = "update Resposta set resposta_correta = ?, resposta_um = ?, resposta_dois = ?, resposta_tres = ? where pergunta = ?;";
            try (PreparedStatement psResposta = conexao.prepareStatement(sqlResposta);) {
                psResposta.setString(1, resposta.getResposta_correta());
                psResposta.setString(2, resposta.getResposta_um());
                psResposta.setString(3, resposta.getResposta_dois());
                psResposta.setString(4, resposta.getResposta_tres());
                psResposta.setString(5, pergunta.getQuestao());
                try {
                    psResposta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Resposta atualizada com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em atualizar resposta e pergunta", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarProfessor(Professor professor) {
        try (Connection conexao = ConnectionFactory.obterConexao()) {
            professor.atributosDB(professor);
            String sql = "update Professor set email = ?, senha = ? where nome_professor = ?;";
            try (PreparedStatement ps = conexao.prepareStatement(sql);) {
                ps.setString(1, professor.getEmail());
                ps.setString(2, professor.getSenha());
                ps.setString(3, professor.getNome());
                try {
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Professor atualizado com sucesso");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro em trazer atualizar professor", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
