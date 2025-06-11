package telas.telas_professor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.Coordenador;
import show_milhao.DAO;
import show_milhao.Professor;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;

public class TelaEstatisticaAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);
    private Professor jogadorAtivo;

    public TelaEstatisticaAdm(Professor professor, Coordenador coordenador) throws Exception {
        if (professor != null) {
            this.jogadorAtivo = professor;
        } else if (coordenador != null) {
            this.jogadorAtivo = coordenador;
        }

        setTitle("Estatísticas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setIconImage(IconUtils.getAppIcon());

        // fontes
        Font headerFont = FontUtils.interOrSans(32);
        Font comboFont = FontUtils.interOrSans(20);
        Font textFont = FontUtils.interOrSans(18);

        // DAO e turmas
        DAO dao = new DAO();
        String[] turmas = dao.exibirTurma();

        // Título
        JLabel title = new JLabel("Estatísticas", SwingConstants.CENTER);
        title.setFont(headerFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ComboBox
        RoundedComboBox<String> cbTurma = new RoundedComboBox<>(turmas);
        cbTurma.setFont(comboFont);
        cbTurma.setMaximumSize(new Dimension(270, 35));
        cbTurma.setAlignmentX(Component.CENTER_ALIGNMENT);
        cbTurma.setToolTipText("Selecionar turma");

        // Resultado - lista de alunos
        JLabel resultado = new JLabel("Selecione uma turma para visualizar estatísticas");
        resultado.setFont(textFont);
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultado.setMaximumSize(new Dimension(1000, 50));

        // Estatísticas
        JLabel lblAcertos = new JLabel("Acertos: ");
        lblAcertos.setFont(textFont);
        lblAcertos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblErros = new JLabel("Erros: ");
        lblErros.setFont(textFont);
        lblErros.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão voltar
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        btnVoltar.setFont(comboFont);
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> {
            dispose(); // fecha a tela atual
            // aqui você pode redirecionar para outra tela, se necessário
        });

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(40, 40, 40, 40));
        painel.add(title);
        painel.add(Box.createVerticalStrut(20));
        painel.add(cbTurma);
        painel.add(Box.createVerticalStrut(20));
        painel.add(resultado);
        painel.add(Box.createVerticalStrut(10));
        painel.add(lblAcertos);
        painel.add(Box.createVerticalStrut(5));
        painel.add(lblErros);
        painel.add(Box.createVerticalStrut(30));
        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);

        // Lógica de atualização
        cbTurma.addActionListener(e -> {
            String turmaSelecionada = (String) cbTurma.getSelectedItem();
            if (turmaSelecionada != null && !turmaSelecionada.contains("Erro")) {
                try {
                    String[] alunos = dao.exibirAluno(turmaSelecionada);

                    if (alunos.length == 0) {
                        resultado.setText("Nenhum aluno cadastrado na turma " + turmaSelecionada + ".");
                    } else {
                        resultado.setText("<html>Alunos da turma <b>" + turmaSelecionada + "</b>:<br>"
                                + String.join(", ", alunos) + "</html>");
                    }

                    int acertos = dao.contarAcertosPorTurma(turmaSelecionada);
                    int erros = dao.contarErrosPorTurma(turmaSelecionada);

                    lblAcertos.setText("Acertos: " + acertos);
                    lblErros.setText("Erros: " + erros);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultado.setText("Erro ao carregar estatísticas.");
                    lblAcertos.setText("Acertos: -");
                    lblErros.setText("Erros: -");
                }
            } else {
                resultado.setText("Nenhuma turma válida selecionada.");
                lblAcertos.setText("Acertos: -");
                lblErros.setText("Erros: -");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TelaEstatisticaAdm(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}