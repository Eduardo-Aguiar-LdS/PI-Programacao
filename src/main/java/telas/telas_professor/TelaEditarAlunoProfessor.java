package telas.telas_professor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.*;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.campos.RoundedTextField;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaEditarAlunoProfessor extends JFrame {
    private Professor professor_tela;
    private Coordenador coordenador_tela;
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEditarAlunoProfessor(Professor professor, Coordenador coordenador) throws Exception {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());
        this.professor_tela = professor;
        this.coordenador_tela = coordenador;

        Font headerFont = FontUtils.interOrSans(32);
        Font textFont = FontUtils.interOrSans(26);
        Font fieldFont = FontUtils.interOrSans(22);

        JLabel lblTurma = new JLabel("Turma");
        JLabel lblAluno = new JLabel("Aluno");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblSenha = new JLabel("Senha");
        for (JLabel lbl : new JLabel[] { lblTurma, lblAluno, lblEmail, lblSenha }) {
            lbl.setFont(textFont);
        }

        DAO dao = new DAO();
        String[] turmas = dao.exibirTurma();
        RoundedComboBox<String> cbTurma = new RoundedComboBox<>(turmas);
        cbTurma.setFont(fieldFont);
        cbTurma.setPlaceholder("Selecione a turma");

        RoundedTextField tfAluno = new RoundedTextField(20);
        tfAluno.setFont(fieldFont);
        tfAluno.setPlaceholder("Nome");

        RoundedTextField tfEmail = new RoundedTextField(30);
        tfEmail.setFont(fieldFont);
        tfEmail.setPlaceholder("Email@p4ed.com");

        RoundedTextField tfSenha = new RoundedTextField(20);
        tfSenha.setFont(fieldFont);
        tfSenha.setPlaceholder("Senha");

        Dimension prefFull = tfEmail.getPreferredSize();
        int fullW = prefFull.width;
        int fullH = prefFull.height;
        tfEmail.setPreferredSize(new Dimension(fullW, fullH));
        tfSenha.setPreferredSize(new Dimension(fullW, fullH));

        int halfW = (fullW - 30) / 2;
        cbTurma.setPreferredSize(new Dimension(halfW, fullH));
        tfAluno.setPreferredSize(new Dimension(halfW, fullH));

        RoundedButton btnSalvar = new RoundedButton("Salvar");
        RoundedButton btnExcluir = new RoundedButton("Excluir");
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnSalvar);
        ButtonUtils.estilizarPadrao(btnExcluir);
        ButtonUtils.estilizarPadrao(btnVoltar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "update Aluno set email = ?, senha = ? where id_aluno = ?;";
                try (Connection conexao = ConnectionFactory.obterConexao()) {
                    Aluno aluno = new Aluno(tfAluno.getText());
                    aluno.atributosDB(tfAluno.getText());
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    ps.setString(1, tfEmail.getText());
                    ps.setString(2, tfSenha.getText());
                    ps.setInt(3, aluno.getIdAluno());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Aluno atualizado");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "delete from Aluno where id_aluno = ?;";
                try (Connection conexao = ConnectionFactory.obterConexao()) {
                    Aluno aluno = new Aluno(tfAluno.getText());
                    aluno.atributosDB(tfAluno.getText());
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    ps.setInt(1, aluno.getIdAluno());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Aluno excluido");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                if (professor != null) {
                    new TelaGerenciarEditarProfessor(professor).setVisible(true);
                } else if (coordenador != null) {
                    new TelaGerenciarEditarProfessor(coordenador).setVisible(true);
                }
            }
        });

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(IconUtils.getAppIconLabel(), gbc);

        JLabel title = new JLabel("Editar - Aluno", SwingConstants.CENTER);
        title.setFont(headerFont);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 15);
        content.add(lblTurma, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 15, 5, 0);
        content.add(lblAluno, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, 15);
        content.add(cbTurma, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 15, 20, 0);
        content.add(tfAluno, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblEmail, gbc);
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(tfEmail, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblSenha, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(tfSenha, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, 15);
        content.add(btnSalvar, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 155, 10, 0);
        content.add(btnExcluir, gbc);

        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(btnVoltar, gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TelaEditarAlunoProfessor(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}