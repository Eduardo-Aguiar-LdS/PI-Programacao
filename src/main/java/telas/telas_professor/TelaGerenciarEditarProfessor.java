package telas.telas_professor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaGerenciarEditarProfessor extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaGerenciarEditarProfessor() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font btnFont   = FontUtils.interOrSans(24);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Insets defaultInsets = new Insets(10, 0, 10, 0);
        Insets titleInsets   = new Insets(0, 0, 0, 0);

        // Ícone acima do título
        gbc.insets = defaultInsets;
        gbc.gridy  = 0;
        JLabel iconLabel = new JLabel(new ImageIcon(IconUtils.getAppIcon()));
        content.add(iconLabel, gbc);

        // Título
        gbc.insets = titleInsets;
        gbc.gridy  = 1;
        JLabel lblEditar = new JLabel("Editar", SwingConstants.CENTER);
        lblEditar.setFont(titleFont);
        content.add(lblEditar, gbc);

        // Botões
        RoundedButton btnAlunos    = new RoundedButton("Alunos");
        RoundedButton btnPerguntas = new RoundedButton("Perguntas");
        RoundedButton btnVoltar    = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnAlunos,    new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnPerguntas, new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnVoltar,    new Dimension(320, 50));
        btnAlunos.setFont(btnFont);
        btnPerguntas.setFont(btnFont);
        btnVoltar.setFont(btnFont);

        gbc.insets = new Insets(60, 0, 10, 0);
        gbc.gridy  = 2;
        content.add(btnAlunos, gbc);

        gbc.insets = defaultInsets;
        gbc.gridy  = 3;
        content.add(btnPerguntas, gbc);

        gbc.gridy  = 4;
        content.add(btnVoltar, gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaGerenciarEditarProfessor::new);
    }
}