package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaCadastro extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton cadastrarButton;
    private JButton voltarButton;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private Color buttonColor = new Color(31, 176, 195);

    public TelaCadastro() {
        // Configurações básicas da janela
        setTitle("Show do Milhão Acadêmico - Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        
        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título "Show do Milhão" em preto
        titleLabel = new JLabel("Show do Milhão");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Subtítulo "Acadêmico" em preto
        subtitleLabel = new JLabel("Acadêmico");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Painel para os campos de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        
        // Campo Nome
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        nomeField = new JTextField();
        nomeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nomeField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        nomeField.setText("Insira seu nome");
        nomeField.setForeground(Color.GRAY);
        
        // Campo E-mail
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        emailField = new JTextField();
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        emailField.setText("Insira sua e-mail");
        emailField.setForeground(Color.GRAY);
        
        // Campo Senha
        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        senhaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        senhaLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        senhaField = new JPasswordField();
        senhaField.setAlignmentX(Component.LEFT_ALIGNMENT);
        senhaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        senhaField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        senhaField.setText("Insira sua senha");
        senhaField.setForeground(Color.GRAY);
        senhaField.setEchoChar((char)0);
        
        // Adiciona listeners para os placeholders
        addPlaceholderBehavior(nomeField, "Insira seu nome");
        addPlaceholderBehavior(emailField, "Insira sua e-mail");
        addPlaceholderBehavior(senhaField, "Insira sua senha");
        
        // Adiciona componentes ao painel de formulário
        formPanel.add(nomeLabel);
        formPanel.add(nomeField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(senhaLabel);
        formPanel.add(senhaField);
        
        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botões com estilo arredondado
        cadastrarButton = createRoundedButton("Cadastrar", buttonColor);
        voltarButton = createRoundedButton("Voltar", buttonColor);
        
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(voltarButton);
        
        // Painel de conteúdo centralizado verticalmente
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel);
        contentPanel.add(subtitleLabel);
        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(buttonPanel);
        
        // Centraliza verticalmente usando GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(contentPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Adiciona o painel principal à janela
        add(mainPanel);
        
        // Ações dos botões
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });
        
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaInicial();
            }
        });
        
        // Listener para redimensionamento responsivo
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });
    }
    
    private void adjustComponents() {
        // Ajusta o tamanho da fonte do título baseado na largura da janela
        int titleFontSize = Math.max(24, Math.min(48, getWidth() / 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD, titleFontSize));
        
        int subtitleFontSize = Math.max(12, Math.min(24, getWidth() / 40));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, subtitleFontSize));
        
        // Ajusta o tamanho dos botões
        int buttonWidth = Math.min(400, (int)(getWidth() * 0.6));
        int buttonHeight = Math.min(60, (int)(getHeight() * 0.1));
        
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
        cadastrarButton.setMaximumSize(buttonSize);
        voltarButton.setMaximumSize(buttonSize);
        
        // Ajusta o tamanho da fonte dos botões
        int buttonFontSize = Math.max(14, Math.min(18, getWidth() / 50));
        Font buttonFont = new Font("Arial", Font.BOLD, buttonFontSize);
        cadastrarButton.setFont(buttonFont);
        voltarButton.setFont(buttonFont);
    }
    
    private void addPlaceholderBehavior(JTextField field, String placeholder) {
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField)field).setEchoChar('*');
                    }
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField)field).setEchoChar((char)0);
                    }
                }
            }
        });
    }
    
    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        
        // Validação simples
        if (nome.isEmpty() || nome.equals("Insira seu nome") || 
            email.isEmpty() || email.equals("Insira sua e-mail") || 
            senha.isEmpty() || senha.equals("Insira sua senha")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!\nNome: " + nome + "\nE-mail: " + email, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltarParaTelaInicial() {
        this.dispose();
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
    }
    
    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(color);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.setColor(Color.WHITE);
                    g2.setFont(getFont().deriveFont(Font.BOLD, 18f));
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(getText())) / 2;
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(getText(), x, y);
                    g2.dispose();
                } else {
                    super.paintComponent(g);
                }
            }
        };
        
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
        button.setMaximumSize(new Dimension(400, 60));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return button;
    }
    
    private static class RoundedBorder implements javax.swing.border.Border {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        
        public boolean isBorderOpaque() {
            return true;
        }
        
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaCadastro frame = new TelaCadastro();
                frame.setVisible(true);
            }
        });
    }
}