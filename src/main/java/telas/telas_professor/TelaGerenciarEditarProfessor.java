package telas.telas_professor;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*; 

import show_milhao.Professor;

public class TelaGerenciarEditarProfessor extends JFrame {
    private Professor professor_tela;
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaGerenciarEditarProfessor(Professor professor) {
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

        // --- Imagem principal (acima do título) ---
        URL imgURL = getClass().getResource("/telas/img/poliedro_logo.png");
        ImageIcon ico = (imgURL != null)
            ? new ImageIcon(imgURL)
            : new ImageIcon();

        // --- Painel central ---
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Insets defaultInsets = new Insets(10, 0, 10, 0);
        Insets titleInsets   = new Insets(0, 0, 0, 0);

        // 1) Ícone acima
        gbc.insets = defaultInsets;
        gbc.gridy  = 0;
        content.add(new JLabel(ico), gbc);

        // 2) Título "Editar"
        Font titleFont = new Font("Inter", Font.PLAIN, 32);
        if (!"Inter".equalsIgnoreCase(titleFont.getFamily())) {
            titleFont = new Font("SansSerif", Font.PLAIN, 32);
        }
        gbc.insets = titleInsets;
        gbc.gridy  = 1;
        JLabel lblEditar = new JLabel("Editar");
        lblEditar.setFont(titleFont);
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(lblEditar, gbc);

        // 3) Botões
        Color btnColor = new Color(0x1FB0C3);
        Font  btnFont  = new Font("Inter", Font.PLAIN, 24);
        if (!"Inter".equalsIgnoreCase(btnFont.getFamily())) {
            btnFont = new Font("SansSerif", Font.PLAIN, 24);
        }

        int sairWidth  = 320;
        int otherWidth = sairWidth - 70; // 250

        RoundedButton btnAlunos    = new RoundedButton("Alunos");
        RoundedButton btnPerguntas = new RoundedButton("Perguntas");
        RoundedButton btnVoltar    = new RoundedButton("Voltar");

        btnAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnAlunos)).dispose();
                try {
                    new TelaEditarAlunoProfessor(professor, null).setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnPerguntas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnAlunos)).dispose();
                try {
                    new TelaEditarPerguntaProfessor(professor, null).setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(btnVoltar)).dispose();
                new TelaGerenciamentoProfessor(professor, null).setVisible(true);
            }
        });

        for (RoundedButton btn : new RoundedButton[]{ btnAlunos, btnPerguntas, btnVoltar }) {
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

        // 4) Espaçamento antes do primeiro botão
        gbc.insets = new Insets(60, 0, 10, 0);
        gbc.gridy  = 2;
        content.add(btnAlunos, gbc);

        // 5) Botão "Perguntas"
        gbc.insets = defaultInsets;
        gbc.gridy  = 3;
        content.add(btnPerguntas, gbc);

        // 6) Botão "Voltar"
        gbc.gridy  = 4;
        content.add(btnVoltar, gbc);

        // --- Finaliza janela ---
        setContentPane(content);
        pack();
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
            TelaGerenciarEditarProfessor frame = new TelaGerenciarEditarProfessor(null);
            frame.setVisible(true); 
        });
    }
}

// Classe auxiliar para botões arredondados
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
    @Override public boolean isContentAreaFilled() { return false; }
}
