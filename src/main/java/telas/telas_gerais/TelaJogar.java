package telas.telas_gerais;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import show_milhao.Aluno;
import show_milhao.Coordenador;
import show_milhao.Pergunta;
import show_milhao.Professor;
import show_milhao.Resposta;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.botoes.ButtonUtils;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.telas_aluno.TelaPrincipalAluno;

public class TelaJogar extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    private Aluno aluno_tela;
    private Professor professor_tela;
    private Coordenador coordenador_tela;
    private int contador;

    public TelaJogar(Aluno aluno, Professor professor, Coordenador coordenador, int cont, boolean dicaUsada) {
        Pergunta pergunta = new Pergunta();
        Resposta resposta = new Resposta();
        this.contador = cont;
        String[] opcoes = new String[4];

        if (aluno != null) {
            this.aluno_tela = aluno;
            try {
                pergunta.exibir(pergunta, resposta, cont);
                opcoes = new String[] {
                        resposta.getResposta_correta(),
                        resposta.getResposta_um(),
                        resposta.getResposta_dois(),
                        resposta.getResposta_tres()
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (professor != null) {
            this.professor_tela = professor;
            try {
                pergunta.exibir(pergunta, resposta, cont);
                opcoes = new String[] {
                        resposta.getResposta_correta(),
                        resposta.getResposta_um(),
                        resposta.getResposta_dois(),
                        resposta.getResposta_tres()
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (coordenador != null) {
            this.coordenador_tela = coordenador;
            try {
                pergunta.exibir(pergunta, resposta, cont);
                opcoes = new String[] {
                        resposta.getResposta_correta(),
                        resposta.getResposta_um(),
                        resposta.getResposta_dois(),
                        resposta.getResposta_tres()
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            opcoes = new String[] {
                    "Erro em trazer resposta 1",
                    "Erro em trazer resposta 2",
                    "Erro em trazer resposta 3",
                    "Erro em trazer resposta 4"
            };
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font textFont = FontUtils.interOrSans(24);
        Font btnFont = FontUtils.interOrSans(20);
        Font scoreFont = FontUtils.interOrSans(18);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // 1) Pontuação
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblScore = new JLabel("Pontuação: " + aluno.getPontuacao(), SwingConstants.RIGHT);
        lblScore.setFont(scoreFont);
        content.add(lblScore, gbc);

        // 2) Logo
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(IconUtils.getAppIconLabel(), gbc);

        // 3) Número da pergunta
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel lblPerg = new JLabel("" + cont, SwingConstants.CENTER);
        lblPerg.setFont(titleFont);
        content.add(lblPerg, gbc);

        // 4) Texto da pergunta (centrado)
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        JTextPane txtPerg = new JTextPane();
        txtPerg.setText(pergunta.getQuestao());
        txtPerg.setFont(textFont);
        txtPerg.setEditable(false);
        txtPerg.setOpaque(false);
        StyledDocument doc = txtPerg.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        content.add(txtPerg, gbc);

        // 5) Botões de opção (linhas 4–5)
        Dimension sizeOpt = new Dimension(300, 100);
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        ArrayList<RoundedButton> botoes = new ArrayList<>();

        RoundedButton btnUm = new RoundedButton(opcoes[0]);
        btnUm.setColumns(35);
        ButtonUtils.estilizarPadrao(btnUm, sizeOpt);
        Font fonteUm = new Font("Inter", Font.PLAIN, 18);
        btnUm.setFont(fonteUm);
        RoundedButton btnDois = new RoundedButton(opcoes[1]);
        btnDois.setColumns(35);
        ButtonUtils.estilizarPadrao(btnDois, sizeOpt);
        Font fonteDois = new Font("Inter", Font.PLAIN, 18);
        btnDois.setFont(fonteDois);

        botoes.add(btnUm);
        botoes.add(btnDois);
        
        btnUm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "E a alternativa esta...");
                try {
                    aluno.setPontuacao(aluno.getPontuacao() + 1);
                    JOptionPane.showMessageDialog(null, "Correta");
                    aluno.jogar(pergunta, resposta, cont, btnUm);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        btnDois.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "E a alternativa esta...");
                JOptionPane.showMessageDialog(null, "Errada");
                JOptionPane.showMessageDialog(null,
                        "Pontuacao: " + aluno.getPontuacao(), "Perdeu",
                        JOptionPane.INFORMATION_MESSAGE);
                ((JFrame) SwingUtilities.getWindowAncestor(btnDois)).dispose();
                new TelaPrincipalAluno(aluno).setVisible(true);
            }
        });

        if (dicaUsada != true) {
            RoundedButton btnTres = new RoundedButton(opcoes[2]);
            btnTres.setColumns(35);
            ButtonUtils.estilizarPadrao(btnTres, sizeOpt);
            Font fonteTres = new Font("Inter", Font.PLAIN, 18);
            btnTres.setFont(fonteTres);
            RoundedButton btnQuatro = new RoundedButton(opcoes[3]);
            btnQuatro.setColumns(35);
            ButtonUtils.estilizarPadrao(btnQuatro, sizeOpt);
            Font fonteQuatro = new Font("Inter", Font.PLAIN, 18);
            btnQuatro.setFont(fonteQuatro);

            botoes.add(btnTres);
            botoes.add(btnQuatro);
            
            btnTres.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "E a alternativa esta...");
                    JOptionPane.showMessageDialog(null, "Errada");
                    JOptionPane.showMessageDialog(null,
                            "Pontuacao: " + aluno.getPontuacao(), "Perdeu",
                            JOptionPane.INFORMATION_MESSAGE);
                    ((JFrame) SwingUtilities.getWindowAncestor(btnTres)).dispose();
                    new TelaPrincipalAluno(aluno).setVisible(true);
                }
            });

            btnQuatro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "E a alternativa esta...");
                    JOptionPane.showMessageDialog(null, "Errada");
                    JOptionPane.showMessageDialog(null,
                            "Pontuacao: " + aluno.getPontuacao(), "Perdeu",
                            JOptionPane.INFORMATION_MESSAGE);
                    ((JFrame) SwingUtilities.getWindowAncestor(btnQuatro)).dispose();
                    new TelaPrincipalAluno(aluno).setVisible(true);
                }
            });
        }

        Collections.shuffle(botoes);

        for (int i = 0; i < botoes.size(); i++) {
            JButton botao = botoes.get(i);
            gbc.gridx = i % 2;
            gbc.gridy = 4 + (i / 2);
            content.add(botao, gbc);
        }

        // 6) “Dica” e “Pular”
        Dimension sizeAct = new Dimension(200, 60);
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 20, 0, 20);

        if (aluno.isUsouDica() != true) {
            RoundedButton dica = new RoundedButton("Dica");
            dica.setFont(btnFont);
            dica.setColumns(15);
            ButtonUtils.estilizarPadrao(dica, sizeAct);
            gbc.gridx = 0;
            content.add(dica, gbc);
            dica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,
                            "Usou dica ", "Dica",
                            JOptionPane.INFORMATION_MESSAGE);
                    aluno.setUsouDica(true);
                    try {
                        ((JFrame) SwingUtilities.getWindowAncestor(dica)).dispose();
                        new TelaJogar(aluno, null, null, cont, true).setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        if (aluno.isUsouPular() != true) {
            RoundedButton pular = new RoundedButton("Pular");
            pular.setFont(btnFont);
            pular.setColumns(15);
            ButtonUtils.estilizarPadrao(pular, sizeAct);
            gbc.gridx = 1;
            content.add(pular, gbc);
            pular.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,
                            "Usou pular qestão ", "Pulou",
                            JOptionPane.INFORMATION_MESSAGE);
                    aluno.setUsouPular(true);
                    try {
                        aluno.jogar(pergunta, resposta, cont, btnUm);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaJogar frame = new TelaJogar(null, null, null, 1, false);
            frame.setVisible(true);
        });
    }
}
