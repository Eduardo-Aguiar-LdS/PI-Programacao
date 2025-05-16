package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import show_milhao.*;

public class TelaCadastroAluno extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JComboBox<String> turmaComboBox;
    private JButton cadastrarButton;
    private JButton voltarButton;
    private JLabel titleLabel;
    private Color buttonColor = new Color(31, 176, 195);

    public TelaCadastroAluno() {
        // Configurações básicas da janela
        setTitle("Cadastrar - Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(600, 500));
        setLocationRelativeTo(null);
        
        // Painel principal sem ScrollPane
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        
        // Painel de conteúdo centralizado
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Título
        titleLabel = new JLabel("Cadastrar - Aluno");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        centerPanel.add(titleLabel);
        
        // Painel do formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        
        // Criar campos
        nomeField = createRoundedTextField();
        emailField = createRoundedTextField();
        senhaField = createRoundedPasswordField();
        try {
            Turma turma = new Turma();
            String[] turmas = turma.exibirTurma();
            turmaComboBox = createRoundedComboBox(turmas);
        } catch (Exception e){
            e.printStackTrace();
            turmaComboBox = createRoundedComboBox(new String[] {"Erro ao carregar turmas"});
        }
        
        // Configurar placeholders
        nomeField.setText("Insira seu nome");
        emailField.setText("Insira seu e-mail");
        senhaField.setText("Insira sua senha");
        addPlaceholderBehavior(nomeField, "Insira seu nome");
        addPlaceholderBehavior(emailField, "Insira seu e-mail");
        addPlaceholderBehavior(senhaField, "Insira sua senha");
        
        // Adicionar campos com rótulos ao formulário
        formPanel.add(createFieldWithLabel("Nome", nomeField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(createFieldWithLabel("E-mail", emailField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(createFieldWithLabel("Senha", senhaField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(createFieldWithLabel("Turma", turmaComboBox));
        
        // Centralizar o formPanel
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formContainer.setBackground(Color.WHITE);
        formContainer.add(formPanel);
        centerPanel.add(formContainer);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        
        // Criar botões
        cadastrarButton = createRoundedButton("Cadastrar", buttonColor);
        voltarButton = createRoundedButton("Voltar", buttonColor);
        
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(voltarButton);
        
        // Organizar componentes
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setBackground(Color.WHITE);
        buttonContainer.add(buttonPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(buttonContainer);
        centerPanel.add(Box.createVerticalGlue());
        
        // Ações dos botões
        cadastrarButton.addActionListener(e -> cadastrarAluno());
        voltarButton.addActionListener(e -> voltarParaTelaAnterior());
        
        // Listener para redimensionamento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });
    }
    
    // Métodos auxiliares modificados
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
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setForeground(Color.GRAY);
        field.setPreferredSize(new Dimension(500, 40));
        field.setMaximumSize(new Dimension(500, 40));
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
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setForeground(Color.GRAY);
        field.setEchoChar((char)0);
        field.setPreferredSize(new Dimension(500, 40));
        field.setMaximumSize(new Dimension(500, 40));
        return field;
    }
    
    private JComboBox<String> createRoundedComboBox(String[] options) {
        JComboBox<String> combo = new JComboBox<>(options) {
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
        
        combo.setOpaque(false);
        combo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        combo.setBackground(Color.WHITE);
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setPreferredSize(new Dimension(500, 40));
        combo.setMaximumSize(new Dimension(500, 40));
        
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1 && getText().isEmpty()) {
                    setText("Selecione uma turma");
                    setForeground(Color.GRAY);
                }
                return this;
            }
        });
        
        return combo;
    }
    
    private JPanel createFieldWithLabel(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(500, 60));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
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
    
    private void adjustComponents() {
        int titleFontSize = Math.max(24, Math.min(48, getWidth() / 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD, titleFontSize));
        
        int buttonWidth = Math.min(400, (int)(getWidth() * 0.8));
        int buttonHeight = Math.min(50, (int)(getHeight() * 0.08));
        
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
        cadastrarButton.setMaximumSize(buttonSize);
        voltarButton.setMaximumSize(buttonSize);
        
        int buttonFontSize = Math.max(14, Math.min(18, getWidth() / 50));
        Font buttonFont = new Font("Arial", Font.BOLD, buttonFontSize);
        cadastrarButton.setFont(buttonFont);
        voltarButton.setFont(buttonFont);
    }
    
    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        String turma = turmaComboBox.getSelectedItem().toString();
        
        if (nome.isEmpty() || nome.equals("Insira seu nome") || 
            email.isEmpty() || email.equals("Insira seu e-mail") || 
            senha.isEmpty() || senha.equals("Insira sua senha") ||
            turma.equals("Selecione uma turma")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, 
                "Aluno cadastrado com sucesso!\n" +
                "Nome: " + nome + "\n" +
                "E-mail: " + email + "\n" +
                "Turma: " + turma, 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private void voltarParaTelaAnterior() {
        this.dispose();
        // Apagar depois, usando para testes
        new TelaLogin().setVisible(true);
        // new TelaInicial().setVisible(true);
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
                    g2.setFont(getFont().deriveFont(Font.BOLD, getFont().getSize()));
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
        button.setMaximumSize(new Dimension(500, 60));
        
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
            TelaCadastroAluno frame = new TelaCadastroAluno();
            frame.setVisible(true);
        });
    }
}