package telas.telas_professor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.ConnectionFactory;
import show_milhao.Coordenador;
import show_milhao.Pergunta;
import show_milhao.Professor;
import show_milhao.Resposta;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.campos.RoundedTextField;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.telas_coordenador.TelaEditarDoAdm;
import telas.componentes.botoes.ButtonUtils;

public class TelaEditarPerguntaProfessor extends JFrame {
    private Professor professor_tela;
    private Coordenador coordenador_tela;
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEditarPerguntaProfessor(Professor professor, Coordenador coordenador) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font fieldFont = FontUtils.interOrSans(22);

        JLabel lblPergunta = new JLabel("Pergunta");
        lblPergunta.setFont(fieldFont);
        RoundedTextField tfPergunta = new RoundedTextField(40);
        tfPergunta.setFont(fieldFont);
        tfPergunta.setPlaceholder("Insira sua pergunta");
        RoundedComboBox<String> cbDificuldade = new RoundedComboBox<>(new String[] { "Fácil", "Médio", "Difícil" });
        cbDificuldade.setFont(fieldFont);
        cbDificuldade.setPlaceholder("Selecione a dificuldade");
        RoundedTextField tfAlt1 = new RoundedTextField(15);
        tfAlt1.setFont(fieldFont);
        tfAlt1.setPlaceholder("Alternativa 1");
        RoundedTextField tfAlt2 = new RoundedTextField(15);
        tfAlt2.setFont(fieldFont);
        tfAlt2.setPlaceholder("Alternativa 2");
        RoundedTextField tfAlt3 = new RoundedTextField(15);
        tfAlt3.setFont(fieldFont);
        tfAlt3.setPlaceholder("Alternativa 3");
        RoundedTextField tfAlt4 = new RoundedTextField(15);
        tfAlt4.setFont(fieldFont);
        tfAlt4.setPlaceholder("Alternativa 4");

        Dimension pref = tfPergunta.getPreferredSize();
        int w = pref.width, h = pref.height;
        tfPergunta.setPreferredSize(new Dimension(w, h));
        cbDificuldade.setPreferredSize(new Dimension(w, h));

        RoundedButton btnSalvar = new RoundedButton("Salvar");
        RoundedButton btnExcluir = new RoundedButton("Excluir");
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnSalvar);
        ButtonUtils.estilizarPadrao(btnExcluir);
        ButtonUtils.estilizarPadrao(btnVoltar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dificuldadeSelecionada = cbDificuldade.getSelectedItem().toString();
                String sqlResposta = "update Resposta set resposta_correta = ?, resposta_um = ?, resposta_dois = ?, resposta_tres = ? where id_pergunta = ?;";
                String sqlPergunta = "update Pergunta set pergunta = ?, dificuldade = ? where id_pergunta = ?;";
                try (Connection conexao = ConnectionFactory.obterConexao()) {
                    Pergunta pergunta = new Pergunta(tfPergunta.getText(), dificuldadeSelecionada);
                    Resposta resposta = new Resposta(tfAlt1.getText(), tfAlt2.getText(), tfAlt3.getText(), tfAlt4.getText());
                    try {
                        pergunta.atributosDB(pergunta);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    PreparedStatement psResposta = conexao.prepareStatement(sqlResposta);
                    psResposta.setString(1, resposta.getResposta_correta());
                    psResposta.setString(2, resposta.getResposta_um());
                    psResposta.setString(3, resposta.getResposta_dois());
                    psResposta.setString(4, resposta.getResposta_tres());
                    psResposta.setInt(5, pergunta.getId_pergunta());
                    psResposta.executeUpdate();
                    PreparedStatement psPergunta = conexao.prepareStatement(sqlPergunta);
                    psPergunta.setString(1, pergunta.getQuestao());
                    psPergunta.setString(2, dificuldadeSelecionada);
                    psPergunta.setInt(3, pergunta.getId_pergunta());
                    psPergunta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pergunta e respostas atualizadas");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                } 
            }
        });
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dificuldadeSelecionada = cbDificuldade.getSelectedItem().toString();
                String sqlResposta = "delete from Resposta where id_pergunta = ?;";
                String sqlPergunta = "delete from Pergunta where id_pergunta = ?;";
                try (Connection conexao = ConnectionFactory.obterConexao()) {
                    Pergunta pergunta = new Pergunta(tfPergunta.getText(), dificuldadeSelecionada);
                    try {
                        pergunta.atributosDB(pergunta);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    PreparedStatement psResposta = conexao.prepareStatement(sqlResposta);
                    psResposta.setInt(1, pergunta.getId_pergunta());
                    psResposta.executeUpdate();
                    PreparedStatement psPergunta = conexao.prepareStatement(sqlPergunta);
                    psPergunta.setInt(1, pergunta.getId_pergunta());
                    psPergunta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Perguntas e respostas excluídas");
                } catch (SQLException e1) {
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
                    new TelaEditarDoAdm(coordenador).setVisible(true);
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
        JLabel title = new JLabel("Editar - Pergunta", SwingConstants.CENTER);
        title.setFont(titleFont);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(title, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 5, 0);
        content.add(lblPergunta, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(tfPergunta, gbc);
        gbc.gridy = 4;
        content.add(cbDificuldade, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, -50);
        content.add(tfAlt1, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 90, 10, 0);
        content.add(tfAlt2, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, -50);
        content.add(tfAlt3, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 90, 20, 0);
        content.add(tfAlt4, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, 15);
        content.add(btnSalvar, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 155, 10, 0);
        content.add(btnExcluir, gbc);
        gbc.gridy = 8;
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
                new TelaEditarPerguntaProfessor(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}