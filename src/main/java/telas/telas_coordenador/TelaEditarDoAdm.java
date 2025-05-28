package telas.telas_coordenador;

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

public class TelaEditarDoAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEditarDoAdm() {
        super("Editar");
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
        JLabel iconMain = new JLabel(new ImageIcon(IconUtils.getAppIcon()));
        content.add(iconMain, gbc);

        gbc.insets = titleInsets;
        gbc.gridy = 1;
        JLabel line1 = new JLabel("Editar", SwingConstants.CENTER);
        line1.setFont(titleFont);
        content.add(line1, gbc);

        RoundedButton btnAlunos = new RoundedButton("Alunos");
        RoundedButton btnProfessores    = new RoundedButton("Professores");
         RoundedButton btnPerguntas    = new RoundedButton("Perguntas");
        RoundedButton btnVoltar    = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnAlunos, new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnProfessores,    new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnPerguntas,    new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnVoltar,    new Dimension(320, 50));
        btnAlunos.setFont(btnFont);
        btnProfessores.setFont(btnFont);
        btnPerguntas.setFont(btnFont);
        btnVoltar.setFont(btnFont);

        gbc.insets = new Insets(60,0,10,0);
        gbc.gridy = 2; content.add(btnAlunos, gbc);
        gbc.insets = defaultInsets;
        gbc.gridy = 3; content.add(btnProfessores,    gbc);
        gbc.gridy = 4; content.add(btnPerguntas,    gbc);
        gbc.gridy = 5; content.add(btnVoltar,    gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaEditarDoAdm::new);
    }
}