package telas.telas_professor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaEstatisticaAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaEstatisticaAdm(int total, int corretas, int erradas) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font headerFont = FontUtils.interOrSans(32);
        Font labelFont  = FontUtils.interOrSans(24);
        Font valueFont  = FontUtils.interOrSans(22);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Logo
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(IconUtils.getAppIconLabel(), gbc);

        // Título
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel lblTitle = new JLabel("Estatísticas", SwingConstants.CENTER);
        lblTitle.setFont(headerFont);
        content.add(lblTitle, gbc);
        // voltar a alinhar à esquerda para os demais
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Perguntas totais
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        JLabel lblTotal = new JLabel("Perguntas totais:", SwingConstants.LEFT);
        lblTotal.setFont(labelFont);
        content.add(lblTotal, gbc);
        gbc.gridy = 3;
        JLabel valTotal = new JLabel(total + " perguntas", SwingConstants.LEFT);
        valTotal.setFont(valueFont);
        content.add(valTotal, gbc);

        // Perguntas corretas
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 5, 0);
        JLabel lblCorretas = new JLabel("Perguntas corretas:", SwingConstants.LEFT);
        lblCorretas.setFont(labelFont);
        content.add(lblCorretas, gbc);
        gbc.gridy = 5;
        JLabel valCorretas = new JLabel(corretas + " perguntas", SwingConstants.LEFT);
        valCorretas.setFont(valueFont);
        content.add(valCorretas, gbc);

        // Perguntas erradas
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 5, 0);
        JLabel lblErradas = new JLabel("Perguntas erradas:", SwingConstants.LEFT);
        lblErradas.setFont(labelFont);
        content.add(lblErradas, gbc);
        gbc.gridy = 7;
        JLabel valErradas = new JLabel(erradas + " perguntas", SwingConstants.LEFT);
        valErradas.setFont(valueFont);
        content.add(valErradas, gbc);

        // Botão Voltar
        gbc.gridy = 8;
        gbc.insets = new Insets(30, 30, 0, 30);
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnVoltar);
        content.add(btnVoltar, gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEstatisticaAdm(100, 80, 20));
    }
}