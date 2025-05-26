package telas.telas_professor;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.componentes.botoes.ButtonUtils;

public class TelaPrincipalAdm extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaPrincipalAdm() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font btnFont   = FontUtils.interOrSans(24);

        URL imgURL = getClass().getResource("/telas/img/poliedro_upscaled.png");
        ImageIcon ico = imgURL != null ? new ImageIcon(imgURL) : new ImageIcon();

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Insets titleInsets   = new Insets(0,0,0,0);
        Insets defaultInsets = new Insets(10,0,10,0);

        gbc.insets = titleInsets;
        gbc.gridy  = 0;
        JLabel line1 = new JLabel("Show do Milhão", SwingConstants.CENTER);
        line1.setFont(titleFont);
        content.add(line1, gbc);

        gbc.gridy = 1;
        JLabel line2 = new JLabel("Acadêmico", SwingConstants.CENTER);
        line2.setFont(titleFont);
        content.add(line2, gbc);

        gbc.insets = defaultInsets;
        gbc.gridy  = 2;
        content.add(new JLabel(ico), gbc);

        RoundedButton btnJogar       = new RoundedButton("Jogar");
        RoundedButton btnEstatistica = new RoundedButton("Estatística");
        RoundedButton btnGerenciar   = new RoundedButton("Gerenciar");
        RoundedButton btnSair        = new RoundedButton("Sair da conta");
        for (RoundedButton btn : Arrays.asList(btnJogar, btnEstatistica, btnGerenciar, btnSair)) {
            int width = btn.getText().equals("Sair da conta") ? 320 : 250;
            ButtonUtils.estilizarPadrao(btn, new Dimension(width, 50));
            btn.setFont(btnFont);
        }

        gbc.insets = defaultInsets;
        gbc.gridy = 3; content.add(btnJogar,       gbc);
        gbc.gridy = 4; content.add(btnEstatistica, gbc);
        gbc.gridy = 5; content.add(btnGerenciar,   gbc);
        gbc.gridy = 6; content.add(btnSair,        gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipalAdm::new);
    }
}