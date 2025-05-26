package telas.telas_professor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.tabelas.StatTablePanel;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaEstatisticaAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEstatisticaAdm() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setIconImage(IconUtils.getAppIcon());

        Font headerFont = FontUtils.interOrSans(32);

        String[] columns = {"Turma", "Aluno", "Perguntas totais", "Perguntas certas", "Perguntas erradas"};
        Object[][] data = {
            {"1A", "AAA", 30, 18, "AAA"},
            {"1A", "BBB", 40, 2, 20},
            {"1A", "CCC", 20, 18, 2},
            {"1A", "DDD", 10, 7, 3}
        };

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);

        gbc.gridy = 0;
        centerPanel.add(IconUtils.getAppIconLabel(), gbc);

        gbc.gridy = 1;
        JLabel lblTitle = new JLabel("Estatísticas", SwingConstants.CENTER);
        lblTitle.setFont(headerFont);
        centerPanel.add(lblTitle, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 75, 20, 75);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        StatTablePanel statsPanel = new StatTablePanel(data, columns);
        centerPanel.add(statsPanel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnVoltar);
        centerPanel.add(btnVoltar, gbc);

        setContentPane(centerPanel);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaEstatisticaAdm::new);
    }
}