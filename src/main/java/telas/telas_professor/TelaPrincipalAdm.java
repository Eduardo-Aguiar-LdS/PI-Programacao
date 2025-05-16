package telas.telas_professor;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class TelaPrincipalAdm extends JFrame {
    public TelaPrincipalAdm() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // --- Carrega e escala o ícone da aplicação para 64×64px ---
        URL iconURL = getClass().getResource("/telas/img/poliedro_logo.png");
        if (iconURL != null) {
            ImageIcon rawIcon = new ImageIcon(iconURL);
            Image scaledIcon = rawIcon.getImage()
                                      .getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            setIconImage(scaledIcon);
        }

        // --- Carrega a imagem principal ---
        URL imgURL = getClass().getResource("/telas/img/poliedro_upscaled.png");
        ImageIcon ico = (imgURL != null)
            ? new ImageIcon(imgURL)
            : new ImageIcon();

        // --- Painel principal ---
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        Insets titleInsets   = new Insets(0, 0, 0, 0);
        Insets defaultInsets = new Insets(10, 0, 10, 0);

        // --- Título em duas linhas ---
        Font titleFont = new Font("Inter", Font.PLAIN, 32);
        if (!"Inter".equalsIgnoreCase(titleFont.getFamily()))
            titleFont = new Font("SansSerif", Font.PLAIN, 32);
        gbc.insets = titleInsets;

        gbc.gridy = 0;
        JLabel line1 = new JLabel("Show do Milhão");
        line1.setFont(titleFont);
        line1.setForeground(Color.BLACK);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(line1, gbc);

        gbc.gridy = 1;
        JLabel line2 = new JLabel("Acadêmico");
        line2.setFont(titleFont);
        line2.setForeground(Color.BLACK);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(line2, gbc);

        // --- Imagem principal ---
        gbc.insets = defaultInsets;
        gbc.gridy  = 2;
        content.add(new JLabel(ico), gbc);

        // --- Botões ---
        Color btnColor = new Color(0x1FB0C3);
        Font  btnFont  = new Font("Inter", Font.PLAIN, 24);
        if (!"Inter".equalsIgnoreCase(btnFont.getFamily()))
            btnFont = new Font("SansSerif", Font.PLAIN, 24);

        int sairWidth  = 320;
        int otherWidth = sairWidth - 70;

        RoundedButton btnJogar       = new RoundedButton("Jogar");
        RoundedButton btnEstatistica = new RoundedButton("Estatística");
        RoundedButton btnGerenciar   = new RoundedButton("Gerenciar");
        RoundedButton btnSair        = new RoundedButton("Sair da conta");

        for (RoundedButton btn : Arrays.asList(btnJogar, btnEstatistica, btnGerenciar, btnSair)) {
            int w = btn.getText().equals("Sair da conta") ? sairWidth : otherWidth;
            btn.setPreferredSize(new Dimension(w, 50));
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);

            // cursor de mão ao passar sobre o botão
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // hover de cor
            Color hover = adjustColorBrightness(btnColor, 1.2f);
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    btn.setBackground(hover);
                }
                @Override public void mouseExited(MouseEvent e) {
                    btn.setBackground(btnColor);
                }
            });
        }

        gbc.gridy = 3; content.add(btnJogar,       gbc);
        gbc.gridy = 4; content.add(btnEstatistica, gbc);
        gbc.gridy = 5; content.add(btnGerenciar,   gbc);
        gbc.gridy = 6; content.add(btnSair,        gbc);

        // --- Finaliza janela ---
        setContentPane(content);
        pack();

        // força tamanho inicial e mínimo em 1366×768
        setSize(1366, 768);
        setMinimumSize(new Dimension(1366, 768));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static Color adjustColorBrightness(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1], Math.min(hsb[2] * factor, 1f));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipalAdm::new);
    }
}

class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g2);
        g2.dispose();
    }
    @Override protected void paintBorder(Graphics g) { }
    @Override public boolean isContentAreaFilled() { return false; }
}
