package telas.telas_professor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.combos.RoundedComboBox;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaEstatisticaAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEstatisticaAdm(int total, int corretas, int erradas) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        // fonts
        Font headerFont = FontUtils.interOrSans(32);
        Font comboFont  = FontUtils.interOrSans(24);
        Font textFont   = FontUtils.interOrSans(26);

        // logo
        JLabel lblLogo = IconUtils.getAppIconLabel();

        // título
        JLabel title = new JLabel("Estatísticas", SwingConstants.CENTER);
        title.setFont(headerFont);

        // combos
        RoundedComboBox<String> cbTurma = new RoundedComboBox<>(new String[]{"Turma A", "Turma B", "Turma C"});
        cbTurma.setFont(comboFont);
        cbTurma.setPlaceholder("Selecione a turma");
        cbTurma.setPreferredSize(new Dimension(270, 50));

        RoundedComboBox<String> cbAluno = new RoundedComboBox<>(new String[]{"Aluno 1", "Aluno 2", "Aluno 3"});
        cbAluno.setFont(comboFont);
        cbAluno.setPlaceholder("Selecione o aluno");
        cbAluno.setPreferredSize(new Dimension(270, 50));

        // estatísticas
        JLabel lblTotal       = new JLabel("Perguntas totais:",    SwingConstants.CENTER);
        JLabel lblTotalVal    = new JLabel(total   + " perguntas", SwingConstants.CENTER);
        JLabel lblCorretas    = new JLabel("Perguntas corretas:", SwingConstants.CENTER);
        JLabel lblCorretasVal = new JLabel(corretas+ " perguntas", SwingConstants.CENTER);
        JLabel lblErradas     = new JLabel("Perguntas erradas:",  SwingConstants.CENTER);
        JLabel lblErradasVal  = new JLabel(erradas + " perguntas", SwingConstants.CENTER);

        for (JLabel lbl : new JLabel[]{
                lblTotal, lblTotalVal,
                lblCorretas, lblCorretasVal,
                lblErradas, lblErradasVal
        }) {
            lbl.setFont(textFont);
        }

        // botão Voltar
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnVoltar);

        // painel principal
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // logo
        gbc.gridy = 0;
        gbc.gridx = 0;
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
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 15, 20, 0);
        content.add(cbAluno, gbc);

        // total
        gbc.gridy = 3;
        gbc.gridx = 0; gbc.gridwidth = 2;
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

        // botão Voltar
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEstatisticaAdm(100, 75, 25));
    }
}