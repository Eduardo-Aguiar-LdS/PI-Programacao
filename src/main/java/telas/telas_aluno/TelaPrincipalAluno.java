package telas.telas_aluno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import telas.componentes.botoes.ButtonUtils;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;

public class TelaPrincipalAluno extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaPrincipalAluno() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font btnFont   = FontUtils.interOrSans(24);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Insets defaultInsets = new Insets(10,0,10,0);
        Insets titleInsets   = new Insets(0,0,0,0);

        gbc.insets = defaultInsets;
        gbc.gridy = 0;
        JLabel icon = new JLabel(new ImageIcon(IconUtils.getAppIcon()));
        content.add(icon, gbc);

        gbc.insets = titleInsets;
        gbc.gridy = 1;
        JLabel lblCadastrar = new JLabel("Show do Milhão Acadêmico", SwingConstants.CENTER);
        lblCadastrar.setFont(titleFont);
        content.add(lblCadastrar, gbc);

        RoundedButton btnJogar    = new RoundedButton("Jogar");
        RoundedButton btnStats   = new RoundedButton("Estatísticas");
        RoundedButton btnSair    = new RoundedButton("Sair da Conta");
        ButtonUtils.estilizarPadrao(btnJogar,    new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnStats,    new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnSair,    new Dimension(250, 50));
        btnJogar.setFont(btnFont);
        btnStats.setFont(btnFont);
        btnSair.setFont(btnFont);

        gbc.insets = new Insets(60,0,10,0);
        gbc.gridy = 2; content.add(btnJogar, gbc);
        gbc.insets = defaultInsets;
        gbc.gridy = 3; content.add(btnStats, gbc);
        gbc.gridy = 4; content.add(btnSair, gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipalAluno::new);
    }
}