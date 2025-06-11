package telas.telas_professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import show_milhao.*;
import telas.telas_gerais.TelaInicial;
import telas.telas_gerais.TelaJogar;

public class TelaPrincipalAdmin extends JFrame {
    private ImageIcon originalLogoIcon;
    private JLabel logoLabel, titleLabel, subtitleLabel;
    private JButton playButton, statsButton, manageButton, exitButton;
    private Color buttonColor = new Color(31, 176, 195);

    public TelaPrincipalAdmin(Professor professor, Coordenador coordenador) {
        // Configuração da janela
        setTitle("Show do Milhão Acadêmico - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 500));
        setLocationRelativeTo(null);

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Painel de conteúdo centralizado
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        // Painel central para conteúdo (agora com BoxLayout Y_AXIS)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Adiciona painel central ao wrapper (centralizado)
        centerWrapper.add(centerPanel, BorderLayout.CENTER);

        // Título
        titleLabel = new JLabel("Show do Milhão");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Subtítulo
        subtitleLabel = new JLabel("Acadêmico");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitleLabel.setForeground(Color.BLACK);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subtitleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Logo e texto "Poliedro Sistema de Ensino"
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setOpaque(false);
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            originalLogoIcon = new ImageIcon("src/main/java/telas/img/poliedro.png");
            logoLabel = new JLabel(originalLogoIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            logoLabel = new JLabel("Poliedro");
            logoLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            logoLabel.setForeground(Color.BLACK);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(logoLabel);
        }

        centerPanel.add(logoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões com tamanho fixo
        playButton = createRoundedButton("Jogar", buttonColor);
        statsButton = createRoundedButton("Estatística", buttonColor);
        manageButton = createRoundedButton("Gerenciar", buttonColor);
        exitButton = createRoundedButton("Sair da conta", buttonColor);

        Dimension buttonSize = new Dimension(300, 50);
        playButton.setPreferredSize(buttonSize);
        playButton.setMinimumSize(buttonSize);
        playButton.setMaximumSize(buttonSize);

        statsButton.setPreferredSize(buttonSize);
        statsButton.setMinimumSize(buttonSize);
        statsButton.setMaximumSize(buttonSize);

        manageButton.setPreferredSize(buttonSize);
        manageButton.setMinimumSize(buttonSize);
        manageButton.setMaximumSize(buttonSize);

        exitButton.setPreferredSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(statsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(manageButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(exitButton);

        centerPanel.add(buttonPanel);

        // Adiciona espaço flexível antes e depois do conteúdo para centralização
        // vertical
        centerPanel.add(Box.createVerticalGlue(), 0); // No início
        centerPanel.add(Box.createVerticalGlue()); // No final

        // Ações dos botões
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(playButton)).dispose();
                if (professor != null) {
                    new TelaJogar(null, professor, null, 1, false).setVisible(true);
                } else if (coordenador != null) {
                    new TelaJogar(null, null, coordenador, 1, false).setVisible(true);
                }
            }
        });
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(exitButton)).dispose();
                try {
                    if (professor != null) {
                        new TelaEstatisticaAdm(professor, null, null, false, null).setVisible(true);
                    } else if (coordenador != null) {
                        new TelaEstatisticaAdm(null, coordenador, null, false, null).setVisible(true);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(exitButton)).dispose();
                if (professor != null) {
                    new TelaGerenciamentoProfessor(professor, null).setVisible(true);
                } else if (coordenador != null) {
                    new TelaGerenciamentoProfessor(null, coordenador).setVisible(true);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(professor);
                ((JFrame) SwingUtilities.getWindowAncestor(exitButton)).dispose();
                new TelaInicial().setVisible(true);
            }
        });

        // Listener para redimensionamento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });
    }

    private void adjustComponents() {
        // Ajuste responsivo do título
        int titleFontSize = Math.max(24, Math.min(48, getWidth() / 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD, titleFontSize));

        // Ajuste responsivo do subtítulo
        int subtitleFontSize = Math.max(14, Math.min(24, getWidth() / 40));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, subtitleFontSize));

        // Ajuste responsivo do logo
        if (originalLogoIcon != null) {
            int maxLogoWidth = (int) (getWidth() * 0.6);
            int maxLogoHeight = (int) (getHeight() * 0.3);

            double ratio = Math.min(
                    (double) maxLogoWidth / originalLogoIcon.getIconWidth(),
                    (double) maxLogoHeight / originalLogoIcon.getIconHeight());

            int newWidth = (int) (originalLogoIcon.getIconWidth() * ratio);
            int newHeight = (int) (originalLogoIcon.getIconHeight() * ratio);

            Image scaledImage = originalLogoIcon.getImage().getScaledInstance(
                    newWidth, newHeight, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            int logoFontSize = Math.max(14, Math.min(24, getWidth() / 50));
            logoLabel.setFont(new Font("Arial", Font.PLAIN, logoFontSize));
        }

        revalidate();
        repaint();
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
                g2.setFont(getFont());
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
        button.setFont(new Font("Arial", Font.BOLD, 18));

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
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipalAdmin frame = new TelaPrincipalAdmin(null, null);
            frame.setVisible(true);
        });
    }
}