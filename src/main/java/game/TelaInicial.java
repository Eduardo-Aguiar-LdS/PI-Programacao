package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaInicial extends JFrame {
    private ImageIcon originalLogoIcon;
    private JLabel logoLabel;
    private JButton registerButton;
    private JButton loginButton;
    private JButton exitButton;
    private Color buttonColor = new Color(31, 176, 195);

    public TelaInicial() {
        // Configurações básicas da janela
        setTitle("Show do Milhão Acadêmico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        
        // Painel principal com fundo branco
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título "Show do Milhão" em preto
        JLabel titleLabel = new JLabel("Show do Milhão");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Subtítulo "Acadêmico" em preto
        JLabel subtitleLabel = new JLabel("Acadêmico");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Painel para a imagem do logo
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setOpaque(false);
        
        try {
            originalLogoIcon = new ImageIcon("src/main/java/img/poliedro.png");
            logoLabel = new JLabel(originalLogoIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Imagem não encontrada");
            errorLabel.setForeground(Color.RED);
            logoPanel.add(errorLabel);
        }
        
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Criação dos botões
        registerButton = createRoundedButton("Fazer cadastro", buttonColor);
        loginButton = createRoundedButton("Fazer login", buttonColor);
        exitButton = createRoundedButton("Sair", buttonColor);
        
        // Configuração dos ActionListeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaInicial.this.dispose();
                TelaCadastroAluno telaCadastro = new TelaCadastroAluno();
                telaCadastro.setVisible(true);
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Funcionalidade de login será implementada aqui!");
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(exitButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Adiciona todos os componentes ao painel principal
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(logoPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        
        // Adiciona o painel principal à janela
        add(mainPanel);
        
        // Listener para redimensionamento responsivo
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });
    }
    
    private void adjustComponents() {
        // Ajusta o tamanho da imagem do logo
        if (logoLabel != null && originalLogoIcon != null) {
            int maxLogoWidth = (int) (getWidth() * 0.6);
            int maxLogoHeight = (int) (getHeight() * 0.3);
            
            double widthRatio = (double) maxLogoWidth / originalLogoIcon.getIconWidth();
            double heightRatio = (double) maxLogoHeight / originalLogoIcon.getIconHeight();
            double ratio = Math.min(widthRatio, heightRatio);
            
            int newWidth = (int) (originalLogoIcon.getIconWidth() * ratio);
            int newHeight = (int) (originalLogoIcon.getIconHeight() * ratio);
            
            Image scaledImage = originalLogoIcon.getImage().getScaledInstance(
                newWidth, newHeight, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        }
        
        // Ajusta o tamanho da fonte dos títulos
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] panelComps = ((JPanel) comp).getComponents();
                for (Component panelComp : panelComps) {
                    if (panelComp instanceof JLabel) {
                        JLabel label = (JLabel) panelComp;
                        if (label.getText().equals("Show do Milhão")) {
                            int fontSize = Math.max(24, Math.min(48, getWidth() / 20));
                            label.setFont(new Font("Arial", Font.BOLD, fontSize));
                        } else if (label.getText().equals("Acadêmico")) {
                            int fontSize = Math.max(12, Math.min(24, getWidth() / 40));
                            label.setFont(new Font("Arial", Font.PLAIN, fontSize));
                        }
                    }
                }
            }
        }
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
                TelaInicial frame = new TelaInicial();
                frame.setVisible(true);
                frame.adjustComponents();
            }
        });
    }
}