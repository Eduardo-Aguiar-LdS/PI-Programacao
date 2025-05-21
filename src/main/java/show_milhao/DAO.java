package show_milhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO { // Classe criada para exibição nos cadastros e edições
    private String nomeTurma;

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

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    // Editar Aluno na TelaEditarAlunoProfessor
    // String[] alunos = dao.exibirAluno(turmaSelecionada);
    // RoundedComboBox<String> tfAluno = new RoundedComboBox<>(alunos);
}
