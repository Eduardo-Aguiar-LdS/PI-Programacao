package telas.telas_professor;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TelaCadastrarAlunoProfessor extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaCadastrarAlunoProfessor() {
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

        // --- Fontes ---
        Font headerFont = new Font("Inter", Font.PLAIN, 32);
        if (!"Inter".equalsIgnoreCase(headerFont.getFamily())) {
            headerFont = new Font("SansSerif", Font.PLAIN, 32);
        }
        Font textFont  = headerFont.deriveFont(headerFont.getSize() - 6f);
        Font fieldFont = textFont.deriveFont(textFont.getSize() - 4f);

        // --- Tamanhos base ---
        // Control size usado para Email; Ajuste conforme necessidade
        Dimension controlSize = new Dimension(300, 50);

        // --- Layout principal ---
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // --- Logo acima do título ---
        URL logoDisplayURL = getClass().getResource("/telas/img/poliedro_logo.png");
        if (logoDisplayURL != null) {
            ImageIcon logoIcon = new ImageIcon(logoDisplayURL);
            JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
            gbc.gridwidth = 2;
            gbc.gridy     = 0;
            gbc.insets    = new Insets(0, 0, 20, 0);
            content.add(logoLabel, gbc);
        }

        // --- Título ---
        JLabel title = new JLabel("Cadastrar - Aluno", SwingConstants.CENTER);
        title.setFont(headerFont);
        gbc.gridwidth = 2;
        gbc.gridy     = 1;
        gbc.insets    = new Insets(0, 0, 20, 0);
        content.add(title, gbc);

        // Reset colunas para 2 e margens
        gbc.gridwidth = 1;
        gbc.insets    = new Insets(0, 0, 5, 0);

        // --- Turma e Aluno Labels (row 2) ---
        gbc.gridy = 2; gbc.gridx = 0;
        JLabel lblTurma = new JLabel("Turma"); lblTurma.setFont(textFont);
        gbc.gridx = 1;
        JLabel lblAluno = new JLabel("Aluno"); lblAluno.setFont(textFont);

        // Mesma largura para labels
        int maxLabelWidth = Math.max(lblTurma.getPreferredSize().width,
                                     lblAluno.getPreferredSize().width);
        Dimension labelSize = new Dimension(maxLabelWidth,
                                          lblTurma.getPreferredSize().height);
        lblTurma.setPreferredSize(labelSize);
        lblAluno.setPreferredSize(labelSize);

        // Adiciona labels
        gbc.gridx = 0; content.add(lblTurma, gbc);
        gbc.gridx = 1; content.add(lblAluno, gbc);

        // --- Combo Turma e TextField Aluno (row 3) ---
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 15, 40);
        gbc.gridx = 0;
        RoundedComboBox<String> cbTurma = new RoundedComboBox<>(new String[]{"Selecione a turma"});
        cbTurma.setFont(fieldFont);
        cbTurma.setToolTipText("Selecionar turma");
        content.add(cbTurma, gbc);

        gbc.gridx = 1; gbc.insets = new Insets(0, 0, 15, 0);
        RoundedTextField tfAluno = new RoundedTextField(20);
        tfAluno.setFont(fieldFont);
        tfAluno.setToolTipText("Inserir nome");
        content.add(tfAluno, gbc);

        // --- Email (row 4 e 5) ---
        gbc.gridy = 4; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 5, 0);
        JLabel lblEmail = new JLabel("Email"); lblEmail.setFont(textFont);
        content.add(lblEmail, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0; gbc.gridwidth = 2; gbc.insets = new Insets(0, 0, 15, 0);
        RoundedTextField tfEmail = new RoundedTextField(20);
        tfEmail.setFont(fieldFont);
        tfEmail.setToolTipText("Inserir e-mail");
        // aplica tamanho base
        tfEmail.setPreferredSize(controlSize);
        content.add(tfEmail, gbc);

        // --- Ajustes de tamanho relativos ao Email ---
        // Email serve de referência
        Dimension emailSize = controlSize;
        // Aluno e Turma = EmailWidth - 20px
        Dimension targetSize = new Dimension(emailSize.width - 20, emailSize.height);
        cbTurma.setPreferredSize(targetSize);
        tfAluno.setPreferredSize(targetSize);

        // --- Senha (row 6 e 7) ---
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 1; gbc.insets = new Insets(0, 0, 5, 0);
        JLabel lblSenha = new JLabel("Senha"); lblSenha.setFont(textFont);
        content.add(lblSenha, gbc);

        gbc.gridy = 7; gbc.gridwidth = 2; gbc.insets = new Insets(0, 0, 15, 0);
        RoundedTextField tfSenha = new RoundedTextField(20);
        tfSenha.setFont(fieldFont);
        tfSenha.setToolTipText("Inserir senha");
        tfSenha.setPreferredSize(controlSize);
        content.add(tfSenha, gbc);

        // --- Botões (row 8 e 9) ---
        gbc.gridy = 8; gbc.gridx = 0; gbc.gridwidth = 2; gbc.insets = new Insets(20, 0, 10, 0);
        // Botões = EmailWidth - 200px
        Dimension buttonSize = new Dimension(emailSize.width - 200, emailSize.height);
        Color btnColor = new Color(0x1FB0C3);
        Font btnFont  = new Font("Inter", Font.PLAIN, 24);
        if (!"Inter".equalsIgnoreCase(btnFont.getFamily())) {
            btnFont = new Font("SansSerif", Font.PLAIN, 24);
        }
        RoundedButton btnCadastrar = new RoundedButton("Cadastrar");
        RoundedButton btnVoltar    = new RoundedButton("Voltar");
        for (RoundedButton btn : new RoundedButton[]{btnCadastrar, btnVoltar}) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setPreferredSize(buttonSize);
            Color hover = adjustColorBrightness(btnColor, 1.2f);
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
                @Override public void mouseExited(MouseEvent e)  { btn.setBackground(btnColor); }
            });
        }
        content.add(btnCadastrar, gbc);
        gbc.gridy = 9; gbc.insets = new Insets(0, 0, 0, 0);
        content.add(btnVoltar, gbc);

        // Finaliza janela
        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static Color adjustColorBrightness(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(
            color.getRed(), color.getGreen(), color.getBlue(), null
        );
        return Color.getHSBColor(hsb[0], hsb[1], Math.min(hsb[2] * factor, 1f));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastrarAlunoProfessor::new);
    }
}

// --- Componentes customizados ---

class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override protected void paintBorder(Graphics g) { }
    @Override public boolean isContentAreaFilled() { return false; }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g2);
        g2.dispose();
    }
}

class RoundedTextField extends JTextField {
    private final Color borderColor = Color.BLACK;
    private final Color original;
    private final Color hover;

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        original = getBackground();
        hover    = TelaCadastrarAlunoProfessor.adjustColorBrightness(original, 0.9f);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                setBackground(hover);
                setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
            @Override public void mouseExited(MouseEvent e) {
                setBackground(original);
            }
        });
    }
    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g2);
        g2.dispose();
    }
    @Override protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
        g2.dispose();
    }
    @Override public boolean isOpaque() { return false; }
}

class RoundedComboBox<E> extends JComboBox<E> {
    private final Color borderColor = Color.BLACK;
    private final Color original;
    private final Color hover;
    public RoundedComboBox(E[] items) {
        super(items);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        original = getBackground();
        hover    = TelaCadastrarAlunoProfessor.adjustColorBrightness(original, 0.9f);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { setBackground(hover); }
            @Override public void mouseExited(MouseEvent e)  { setBackground(original); }
        });
        setUI(new BasicComboBoxUI() {
            @Override protected JButton createArrowButton() {
                JButton btn = new JButton("\u25BE");
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setContentAreaFilled(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                return btn;
            }
            @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                // sem retângulo interno padrão
            }
        });
    }
    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g2);
        g2.dispose();
    }
    @Override protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
        g2.dispose();
    }
    @Override public boolean isOpaque() { return false; }
}
