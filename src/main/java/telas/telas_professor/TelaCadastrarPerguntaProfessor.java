package telas.telas_professor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.Coordenador;
import show_milhao.Professor;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.campos.RoundedTextField;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaCadastrarPerguntaProfessor extends JFrame {
    private Professor professor_tela;
    private Coordenador coordenador_tela;
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaCadastrarPerguntaProfessor(Professor professor, Coordenador coordenador) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font headerFont = FontUtils.interOrSans(32);
        Font textFont = FontUtils.interOrSans(26);
        Font fieldFont = FontUtils.interOrSans(22);

        JLabel lblPergunta = new JLabel("Pergunta");
        lblPergunta.setFont(textFont);
        RoundedTextField tfPergunta = new RoundedTextField(40);
        tfPergunta.setFont(fieldFont);
        tfPergunta.setPlaceholder("Insira sua pergunta");

        RoundedComboBox<String> cbDificuldade = new RoundedComboBox<>(new String[] { "Fácil", "Médio", "Difícil" });
        cbDificuldade.setFont(fieldFont);
        cbDificuldade.setPlaceholder("Selecione a dificuldade");

        RoundedTextField tfAlt1 = new RoundedTextField(20);
        tfAlt1.setFont(fieldFont);
        tfAlt1.setPlaceholder("Alternativa 1");
        RoundedTextField tfAlt2 = new RoundedTextField(20);
        tfAlt2.setFont(fieldFont);
        tfAlt2.setPlaceholder("Alternativa 2");
        RoundedTextField tfAlt3 = new RoundedTextField(20);
        tfAlt3.setFont(fieldFont);
        tfAlt3.setPlaceholder("Alternativa 3");
        RoundedTextField tfAlt4 = new RoundedTextField(20);
        tfAlt4.setFont(fieldFont);
        tfAlt4.setPlaceholder("Alternativa 4");

        Dimension prefPerg = tfPergunta.getPreferredSize();
        int width = prefPerg.width, height = prefPerg.height;
        tfPergunta.setPreferredSize(new Dimension(width, height));
        cbDificuldade.setPreferredSize(new Dimension(width, height));

        RoundedButton btnCadastrar = new RoundedButton("Cadastrar");
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnCadastrar);
        ButtonUtils.estilizarPadrao(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                if (professor!=null){
                    new TelaGerenciarCadastrarProfessor(professor).setVisible(true);
                } // Coordenador
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
        JLabel title = new JLabel("Cadastrar - Pergunta", SwingConstants.CENTER);
        title.setFont(headerFont);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(title, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
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
        gbc.insets = new Insets(0, 0, 10, -70);
        content.add(tfAlt1, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 105, 10, 0);
        content.add(tfAlt2, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, -70);
        content.add(tfAlt3, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 105, 20, 0);
        content.add(tfAlt4, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 75, 10, 75);
        content.add(btnCadastrar, gbc);
        gbc.gridy = 8;
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
                new TelaCadastrarPerguntaProfessor(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}