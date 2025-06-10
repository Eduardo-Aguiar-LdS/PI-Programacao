package telas.telas_professor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.Aluno;
import show_milhao.Coordenador;
import show_milhao.DAO;
import show_milhao.Professor;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaEstatisticaAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);
    private Professor jogadorAtivo;

    public TelaEstatisticaAdm(Professor professor, Coordenador coordenador, Object construtorTurmaSelecionada,
            boolean atualizouPagina, Aluno aluno) throws Exception {

        if (professor != null) {
            this.jogadorAtivo = professor;
        } else if (coordenador != null) {
            this.jogadorAtivo = coordenador;
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        // fonts
        Font headerFont = FontUtils.interOrSans(32);
        Font comboFont = FontUtils.interOrSans(24);
        Font textFont = FontUtils.interOrSans(26);

        // logo
        JLabel lblLogo = IconUtils.getAppIconLabel();

        // título
        JLabel title = new JLabel("Estatísticas", SwingConstants.CENTER);
        title.setFont(headerFont);

        // Classe dao para atualizar cadastros
        DAO dao = new DAO();
        String[] turmas;
        turmas = dao.exibirTurma();
        RoundedComboBox<String> cbTurma = new RoundedComboBox<>(turmas);
        cbTurma.setFont(comboFont);
        cbTurma.setPlaceholder("Selecione a turma para ver alunos");
        cbTurma.setPreferredSize(new Dimension(270, 50));

        if (atualizouPagina == true && construtorTurmaSelecionada!=null) {
            Object turmaSelecionadaObj = construtorTurmaSelecionada;
            String turmaSelecionada = turmaSelecionadaObj.toString();
            String[] alunos = dao.exibirAluno(turmaSelecionada);
            alunos = dao.exibirAluno(turmaSelecionada);
            RoundedComboBox<String> cbAluno = new RoundedComboBox<>(alunos);
            cbAluno.setFont(comboFont);
            cbAluno.setPlaceholder("Selecione o aluno");
            cbAluno.setPreferredSize(new Dimension(270, 50));

            String alunoSelecionado = cbAluno.getSelectedItem().toString();
            aluno.atributosDB(alunoSelecionado);

            // estatísticas
            JLabel lblTotal = new JLabel("Perguntas totais:", SwingConstants.CENTER);
            JLabel lblTotalVal = new JLabel(
                    (aluno.getRespostas_corretas() + aluno.getRespostas_erradas()) + " perguntas",
                    SwingConstants.CENTER);
            JLabel lblCorretas = new JLabel("Perguntas corretas:", SwingConstants.CENTER);
            JLabel lblCorretasVal = new JLabel(aluno.getRespostas_corretas() + " perguntas", SwingConstants.CENTER);
            JLabel lblErradas = new JLabel("Perguntas erradas:", SwingConstants.CENTER);
            JLabel lblErradasVal = new JLabel(aluno.getRespostas_erradas() + " perguntas", SwingConstants.CENTER);

            for (JLabel lbl : new JLabel[] {
                    lblTotal, lblTotalVal,
                    lblCorretas, lblCorretasVal,
                    lblErradas, lblErradasVal
            }) {
                lbl.setFont(textFont);
            }

            // botão Atualizar página
            RoundedButton btnAtualizar = new RoundedButton("Atualizar");
            ButtonUtils.estilizarPadrao(btnAtualizar);

            btnAtualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((JFrame) SwingUtilities.getWindowAncestor(btnAtualizar)).dispose();
                    try {
                        new TelaEstatisticaAdm(professor, coordenador, turmaSelecionadaObj, true, aluno)
                                .setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            // botão Voltar
            RoundedButton btnVoltar = new RoundedButton("Voltar");
            ButtonUtils.estilizarPadrao(btnVoltar);

            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                    new TelaPrincipalAdmin(professor, coordenador).setVisible(true);
                }
            });

            // painel principal
            JPanel content = new JPanel(new GridBagLayout());
            content.setBackground(Color.WHITE);
            content.setBorder(new EmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // logo
            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblLogo, gbc);

            // título
            gbc.gridy = 1;
            content.add(title, gbc);

            // combos
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 0, 20, 15);
            gbc.gridx = 0;
            content.add(cbTurma, gbc);
            gbc.gridx = 2;
            gbc.insets = new Insets(0, 15, 20, 0);
            content.add(cbAluno, gbc);

            // total
            gbc.gridy = 3;
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblTotal, gbc);
            gbc.gridy = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblTotalVal, gbc);

            // corretas
            gbc.gridy = 5;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblCorretas, gbc);
            gbc.gridy = 6;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblCorretasVal, gbc);

            // erradas
            gbc.gridy = 7;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblErradas, gbc);
            gbc.gridy = 8;
            gbc.insets = new Insets(0, 0, 30, 0);
            content.add(lblErradasVal, gbc);

            // botão Atualizar
            gbc.gridx = 2;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            content.add(btnAtualizar, gbc);

            // botão Voltar
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            content.add(btnVoltar, gbc);

            setContentPane(content);
            pack();
            setSize(NOTEBOOK_SIZE);
            setMinimumSize(NOTEBOOK_SIZE);
            setLocationRelativeTo(null);
            setVisible(true);
        } else {
            String[] alunos = dao.exibirAluno();
            RoundedComboBox<String> cbAluno = new RoundedComboBox<>(alunos);
            cbAluno.setFont(comboFont);
            cbAluno.setPlaceholder("Suas estatísticas");
            cbAluno.setPreferredSize(new Dimension(270, 50));

            // estatísticas
            JLabel lblTotal = new JLabel("Perguntas totais:", SwingConstants.CENTER);
            JLabel lblTotalVal = new JLabel(
                    (jogadorAtivo.getRespostas_corretas() + jogadorAtivo.getRespostas_erradas()) + " perguntas",
                    SwingConstants.CENTER);
            JLabel lblCorretas = new JLabel("Perguntas corretas:", SwingConstants.CENTER);
            JLabel lblCorretasVal = new JLabel(jogadorAtivo.getRespostas_corretas() + " perguntas",
                    SwingConstants.CENTER);
            JLabel lblErradas = new JLabel("Perguntas erradas:", SwingConstants.CENTER);
            JLabel lblErradasVal = new JLabel(jogadorAtivo.getRespostas_erradas() + " perguntas",
                    SwingConstants.CENTER);

            for (JLabel lbl : new JLabel[] {
                    lblTotal, lblTotalVal,
                    lblCorretas, lblCorretasVal,
                    lblErradas, lblErradasVal
            }) {
                lbl.setFont(textFont);
            }
            // botão Atualizar página
            RoundedButton btnAtualizar = new RoundedButton("Atualizar");
            ButtonUtils.estilizarPadrao(btnAtualizar);

            btnAtualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((JFrame) SwingUtilities.getWindowAncestor(btnAtualizar)).dispose();
                    try {
                        Object turmaSelecionadaObj = cbTurma.getSelectedItem();
                        new TelaEstatisticaAdm(professor, coordenador, turmaSelecionadaObj, true, aluno)
                                .setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            // botão Voltar
            RoundedButton btnVoltar = new RoundedButton("Voltar");
            ButtonUtils.estilizarPadrao(btnVoltar);

            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                    new TelaPrincipalAdmin(professor, coordenador).setVisible(true);
                }
            });

            // painel principal
            JPanel content = new JPanel(new GridBagLayout());
            content.setBackground(Color.WHITE);
            content.setBorder(new EmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // logo
            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblLogo, gbc);

            // título
            gbc.gridy = 1;
            content.add(title, gbc);

            // combos
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 0, 20, 15);
            gbc.gridx = 0;
            content.add(cbTurma, gbc);
            gbc.gridx = 2;
            gbc.insets = new Insets(0, 15, 20, 0);
            content.add(cbAluno, gbc);

            // total
            gbc.gridy = 3;
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblTotal, gbc);
            gbc.gridy = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblTotalVal, gbc);

            // corretas
            gbc.gridy = 5;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblCorretas, gbc);
            gbc.gridy = 6;
            gbc.insets = new Insets(0, 0, 20, 0);
            content.add(lblCorretasVal, gbc);

            // erradas
            gbc.gridy = 7;
            gbc.insets = new Insets(0, 0, 5, 0);
            content.add(lblErradas, gbc);
            gbc.gridy = 8;
            gbc.insets = new Insets(0, 0, 30, 0);
            content.add(lblErradasVal, gbc);

            // botão Atualizar
            gbc.gridx = 2;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            content.add(btnAtualizar, gbc);

            // botão Voltar
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            content.add(btnVoltar, gbc);

            setContentPane(content);
            pack();
            setSize(NOTEBOOK_SIZE);
            setMinimumSize(NOTEBOOK_SIZE);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TelaEstatisticaAdm(null, null, null, false, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}