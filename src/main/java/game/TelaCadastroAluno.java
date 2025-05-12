package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        setTitle("Cadastrar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 500)); // Aumentado para evitar cortes
        setLocationRelativeTo(null);
        
        // Painel principal com ScrollPane para garantir visibilidade
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
        
        // Painel de conteúdo com padding
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Título "Cadastrar Aluno"
        titleLabel = new JLabel("Cadastrar Aluno");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        
        // Painel para os campos de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        
        // Método auxiliar para criar campos com bordas arredondadas
        nomeField = createRoundedTextField("Nome", "Insira seu nome");
        emailField = createRoundedTextField("E-mail", "Insira sua e-mail");
        senhaField = createRoundedPasswordField("Senha", "Insira sua senha");
        turmaComboBox = createRoundedComboBox("Turma", new String[]{"A", "B", "C"});
        
        // Adiciona componentes ao painel de formulário
        formPanel.add(nomeField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(senhaField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(turmaComboBox);
        
        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        
        // Botões com estilo arredondado
        cadastrarButton = createRoundedButton("Cadastrar", buttonColor);
        voltarButton = createRoundedButton("Voltar", buttonColor);
        
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(voltarButton);
        
        // Organização dos painéis
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(buttonPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Ações dos botões
        cadastrarButton.addActionListener(e -> cadastrarAluno());
        voltarButton.addActionListener(e -> voltarParaTelaAnterior());
        
        // Listener para redimensionamento responsivo
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });
    }
    
    private JTextField createRoundedTextField(String labelText, String placeholder) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(500, 60));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        fieldPanel.add(label, BorderLayout.NORTH);
        
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo arredondado
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Borda
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setPreferredSize(new Dimension(500, 40));
        field.setMaximumSize(new Dimension(500, 40));
        
        fieldPanel.add(field, BorderLayout.CENTER);
        
        // Comportamento do placeholder
        addPlaceholderBehavior(field, placeholder);
        
        return field;
    }
    
    private JPasswordField createRoundedPasswordField(String labelText, String placeholder) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(500, 60));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        fieldPanel.add(label, BorderLayout.NORTH);
        
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo arredondado
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Borda
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setEchoChar((char)0);
        field.setPreferredSize(new Dimension(500, 40));
        field.setMaximumSize(new Dimension(500, 40));
        
        fieldPanel.add(field, BorderLayout.CENTER);
        
        // Comportamento do placeholder
        addPlaceholderBehavior(field, placeholder);
        
        return field;
    }
    
    private JComboBox<String> createRoundedComboBox(String labelText, String[] options) {
        JPanel comboPanel = new JPanel(new BorderLayout());
        comboPanel.setBackground(Color.WHITE);
        comboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.setMaximumSize(new Dimension(500, 60));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        comboPanel.add(label, BorderLayout.NORTH);
        
        JComboBox<String> combo = new JComboBox<>(options) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fundo arredondado
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Borda
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
        
        comboPanel.add(combo, BorderLayout.CENTER);
        
        return combo;
    }
    
    private void adjustComponents() {
        // Ajusta o tamanho da fonte do título baseado na largura da janela
        int titleFontSize = Math.max(24, Math.min(48, getWidth() / 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD, titleFontSize));
        
        // Ajusta o tamanho dos botões
        int buttonWidth = Math.min(400, (int)(getWidth() * 0.8));
        int buttonHeight = Math.min(50, (int)(getHeight() * 0.08));
        
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
    
    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        String turma = turmaComboBox.getSelectedItem().toString();
        
        // Validação simples
        if (nome.isEmpty() || nome.equals("Insira seu nome") || 
            email.isEmpty() || email.equals("Insira sua e-mail") || 
            senha.isEmpty() || senha.equals("Insira sua senha") ||
            turma.equals("Selecione uma turma")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, 
            "Aluno cadastrado com sucesso!\n" +
            "Nome: " + nome + "\n" +
            "E-mail: " + email + "\n" +
            "Turma: " + turma, 
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltarParaTelaAnterior() {
        this.dispose();
        // Substitua por sua tela anterior
        // TelaAnterior telaAnterior = new TelaAnterior();
        // telaAnterior.setVisible(true);
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