package telas.telas_coordenador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import telas.componentes.botoes.ButtonUtils;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.campos.RoundedTextField;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;

public class TelaEditarProfessor extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEditarProfessor() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font headerFont = FontUtils.interOrSans(32);
        Font textFont = FontUtils.interOrSans(26);
        Font fieldFont = FontUtils.interOrSans(22);

        
       
        JLabel lblEmail = new JLabel("Email");
        JLabel lblNome = new JLabel("Nome");
        JLabel lblSenha = new JLabel("Senha");
        for (JLabel lbl : new JLabel[]{ lblEmail, lblNome, lblSenha}) {
            lbl.setFont(textFont);
        }

        RoundedTextField tfEmail = new RoundedTextField(30);
        tfEmail.setFont(fieldFont);
        tfEmail.setPlaceholder("aaa@sistemapoliedro.com.br");

        RoundedTextField tfNome = new RoundedTextField(30);
        tfNome.setFont(fieldFont);
        tfNome.setPlaceholder("BBB");

        RoundedTextField tfSenha = new RoundedTextField(20);
        tfSenha.setFont(fieldFont);
        tfSenha.setPlaceholder("111");

        Dimension prefFull = tfEmail.getPreferredSize();
        int fullW = prefFull.width;
        int fullH = prefFull.height;
        tfEmail.setPreferredSize(new Dimension(fullW, fullH));
        tfNome.setPreferredSize(new Dimension(fullW, fullH));
        tfSenha.setPreferredSize(new Dimension(fullW, fullH));

        RoundedButton btnSalvar = new RoundedButton("Salvar");
        RoundedButton btnExcluir = new RoundedButton("Excluir");
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnSalvar);
        ButtonUtils.estilizarPadrao(btnExcluir);
        ButtonUtils.estilizarPadrao(btnVoltar);

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

        JLabel title = new JLabel("Editar - Professor", SwingConstants.CENTER);
        title.setFont(headerFont);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(title, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblEmail, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(tfEmail, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblNome, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(tfNome, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblSenha, gbc);
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(tfSenha, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, 15);
        content.add(btnSalvar, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 155, 10, 0);
        content.add(btnExcluir, gbc);

        gbc.gridy = 9;
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
        SwingUtilities.invokeLater(TelaEditarProfessor::new);
    }
}