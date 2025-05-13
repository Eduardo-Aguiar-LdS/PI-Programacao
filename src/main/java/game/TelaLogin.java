package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton acessarButton, voltarButton;
    private Color buttonColor = new Color(31, 176, 195); // Cor azul para ambos botões

    public TelaLogin() {
        // Configuração da janela
        setTitle("Fazer Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        
        // Painel de conteúdo centralizado
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Título dividido em duas linhas
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        JLabel titlePart1 = new JLabel("Fazer Login");
        titlePart1.setFont(new Font("Arial", Font.BOLD, 36));
        titlePart1.setForeground(Color.BLACK);
        titlePart1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
        titlePanel.add(titlePart1);
        centerPanel.add(titlePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Painel do formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        
        // Campo de e-mail
        emailField = createRoundedTextField();
        emailField.setText("Digite seu e-mail");
        addPlaceholderBehavior(emailField, "Digite seu e-mail");
        
        // Campo de senha
        senhaField = createRoundedPasswordField();
        senhaField.setText("Digite sua senha");
        addPlaceholderBehavior(senhaField, "Digite sua senha");
        
        // Adiciona campos com rótulos
        formPanel.add(createFieldWithLabel("E-mail", emailField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formPanel.add(createFieldWithLabel("Senha", senhaField));
        
        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Painel de botões - agora ambos iguais
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        
        // Botões idênticos
        acessarButton = createRoundedButton("Acessar", buttonColor);
        voltarButton = createRoundedButton("Voltar", buttonColor); // Mesma cor do botão Acessar
        
        buttonPanel.add(acessarButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(voltarButton);
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalGlue());
        
        // Ações dos botões
        acessarButton.addActionListener(e -> realizarLogin());
        voltarButton.addActionListener(e -> voltarTelaAnterior());
        
        // Listener para redimensionamento responsivo
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents(titlePart1);
            }
        });
    }

    private void adjustComponents(JLabel titlePart1) {
        // Ajuste responsivo do título
        int titleFontSize = Math.max(24, Math.min(48, getWidth() / 20));
        
        titlePart1.setFont(new Font("Arial", Font.BOLD, titleFontSize));
    
        
        // Ajuste responsivo dos campos
        int fieldWidth = Math.min(400, (int)(getWidth() * 0.8));
        Dimension fieldSize = new Dimension(fieldWidth, 50);
        emailField.setMaximumSize(fieldSize);
        senhaField.setMaximumSize(fieldSize);
        
        // Ajuste responsivo dos botões
        int buttonWidth = Math.min(300, (int)(getWidth() * 0.7));
        Dimension buttonSize = new Dimension(buttonWidth, 50);
        acessarButton.setMaximumSize(buttonSize);
        voltarButton.setMaximumSize(buttonSize);
        
        // Ajuste de fonte dos botões
        int buttonFontSize = Math.max(16, Math.min(20, getWidth() / 40));
        Font buttonFont = new Font("Arial", Font.BOLD, buttonFontSize);
        acessarButton.setFont(buttonFont);
        voltarButton.setFont(buttonFont);
    }

    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont().deriveFont(Font.BOLD, getFont().getSize()));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
        button.setMaximumSize(new Dimension(300, 60));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return button;
    }

    private JTextField createRoundedTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        field.setForeground(Color.GRAY);
        field.setPreferredSize(new Dimension(400, 50));
        field.setMaximumSize(new Dimension(400, 50));
        return field;
    }

    private JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        field.setForeground(Color.GRAY);
        field.setEchoChar((char)0);
        field.setPreferredSize(new Dimension(400, 50));
        field.setMaximumSize(new Dimension(400, 50));
        return field;
    }

    private JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(400, 80));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 0));
        panel.add(label, BorderLayout.NORTH);
        
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void addPlaceholderBehavior(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField)field).setEchoChar('*');
                    }
                }
            }
            public void focusLost(FocusEvent evt) {
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

    private void realizarLogin() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        
        if (email.isEmpty() || email.equals("Digite seu e-mail") || 
            senha.isEmpty() || senha.equals("Digite sua senha")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Lógica de autenticação aqui
        JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarTelaAnterior() {
        this.dispose();
        new TelaInicial().setVisible(true);
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
        SwingUtilities.invokeLater(() -> {
            TelaLogin frame = new TelaLogin();
            frame.setVisible(true);
        });
    }
}