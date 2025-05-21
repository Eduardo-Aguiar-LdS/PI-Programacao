package telas.telas_professor;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import show_milhao.Professor;

import java.awt.event.*;

public class TelaGerenciamentoProfessor extends JFrame {
    private Professor professor_tela;
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaGerenciamentoProfessor(Professor professor) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // --- Ícone da aplicação (64×64px) ---
        URL iconURL = getClass().getResource("/telas/img/poliedro_logo.png");
        if (iconURL != null) {
            ImageIcon rawIcon = new ImageIcon(iconURL);
            Image scaledIcon = rawIcon.getImage()
                                      .getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            setIconImage(scaledIcon);
        }

        // --- Imagem principal (acima do texto) ---
        URL imgURL = getClass().getResource("/telas/img/poliedro_logo.png");
        ImageIcon ico = (imgURL != null) ? new ImageIcon(imgURL) : new ImageIcon();

        // --- Painel central ---
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Insets titleInsets   = new Insets(0, 0, 0, 0);
        Insets defaultInsets = new Insets(10, 0, 10, 0);

        // 1) Imagem acima
        gbc.insets = defaultInsets;
        gbc.gridy  = 0;
        content.add(new JLabel(ico), gbc);

        // 2) Título em duas linhas
        Font titleFont = new Font("Inter", Font.PLAIN, 32);
        if (!"Inter".equalsIgnoreCase(titleFont.getFamily()))
            titleFont = new Font("SansSerif", Font.PLAIN, 32);

        gbc.insets = titleInsets;
        gbc.gridy = 1;
        JLabel line1 = new JLabel("Show do Milhão");
        line1.setFont(titleFont);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(line1, gbc);

        gbc.gridy = 2;
        JLabel line2 = new JLabel("Acadêmico");
        line2.setFont(titleFont);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(line2, gbc);

        // 3) Botões
        Color btnColor = new Color(0x1FB0C3);
        Font  btnFont  = new Font("Inter", Font.PLAIN, 24);
        if (!"Inter".equalsIgnoreCase(btnFont.getFamily()))
            btnFont = new Font("SansSerif", Font.PLAIN, 24);

        int sairWidth  = 320;
        int otherWidth = sairWidth - 70; // 250

        RoundedButton btnCadastrar = new RoundedButton("Cadastrar");
        RoundedButton btnEditar    = new RoundedButton("Editar");
        RoundedButton btnVoltar    = new RoundedButton("Voltar");

        // Ação dos botões
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnCadastrar)).dispose();
                try {
                    new TelaGerenciarCadastrarProfessor(professor).setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnEditar)).dispose();
                try {
                    new TelaGerenciarEditarProfessor(professor).setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                new TelaPrincipalAdmin(professor, null).setVisible(true);
            }
        });

        for (RoundedButton btn : new RoundedButton[]{ btnCadastrar, btnEditar, btnVoltar }) {
            int w = btn.getText().equals("Voltar") ? sairWidth : otherWidth;
            btn.setPreferredSize(new Dimension(w, 50));
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            Color hover = adjustColorBrightness(btnColor, 1.2f);
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
                @Override public void mouseExited(MouseEvent e)  { btn.setBackground(btnColor); }
            });
        }

        // 3.1) Espaço de 60px (antes era 100px) entre o texto e o primeiro botão
        gbc.insets = new Insets(60, 0, 10, 0);
        gbc.gridy  = 3;
        content.add(btnCadastrar, gbc);

        // 3.2) Botões “Editar” e “Voltar” com espaçamento padrão (10px)
        gbc.insets = defaultInsets;
        gbc.gridy = 4; content.add(btnEditar, gbc);
        gbc.gridy = 5; content.add(btnVoltar, gbc);

        // --- Finaliza janela ---
        setContentPane(content);
        pack();

        // Define tamanho fixo de notebook 1366x768 e impede redimensionamento menor
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static Color adjustColorBrightness(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1], Math.min(hsb[2] * factor, 1f));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaGerenciamentoProfessor frame = new TelaGerenciamentoProfessor(null);
            frame.setVisible(true); 
        });
    }
}

class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override protected void paintBorder(Graphics g) { }

    @Override public boolean isContentAreaFilled() {
        return false;
    }
}
