package telas.telas_gerais;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.botoes.ButtonUtils;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;

public class TelaJogar extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    public TelaJogar(String perguntaNum, String textoPergunta, int pontuacao) {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font titleFont = FontUtils.interOrSans(32);
        Font textFont  = FontUtils.interOrSans(24);
        Font btnFont   = FontUtils.interOrSans(20);
        Font scoreFont = FontUtils.interOrSans(18);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // 1) Pontuação
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        gbc.insets=new Insets(0,0,10,0);
        gbc.anchor=GridBagConstraints.EAST;
        JLabel lblScore = new JLabel("Pontuação: " + pontuacao, SwingConstants.RIGHT);
        lblScore.setFont(scoreFont);
        content.add(lblScore, gbc);

        // 2) Logo
        gbc.gridy=0; gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,20,0);
        content.add(IconUtils.getAppIconLabel(), gbc);

        // 3) Número da pergunta
        gbc.gridy=2; gbc.insets=new Insets(0,0,10,0);
        JLabel lblPerg = new JLabel(perguntaNum, SwingConstants.CENTER);
        lblPerg.setFont(titleFont);
        content.add(lblPerg, gbc);

        // 4) Texto da pergunta (centrado)
        gbc.gridy=3; gbc.insets=new Insets(0,0,20,0);
        JTextPane txtPerg = new JTextPane();
        txtPerg.setText(textoPergunta);
        txtPerg.setFont(textFont);
        txtPerg.setEditable(false);
        txtPerg.setOpaque(false);
        StyledDocument doc = txtPerg.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        content.add(txtPerg, gbc);

        // 5) Botões de opção (linhas 4–5)
        String[] opcoes = {
            "Exemplo de pergunta longa que agora começa sempre centralizada e contém quebras corretas.",
            "Opção 2",
            "Opção 3",
            "Opção 4"
        };
        Dimension sizeOpt = new Dimension(300,100);
        gbc.gridwidth=1; gbc.insets=new Insets(10,20,10,20);
        for (int i = 0; i < opcoes.length; i++) {
            RoundedButton btn = new RoundedButton(opcoes[i]);
            btn.setColumns(35);               
            ButtonUtils.estilizarPadrao(btn, sizeOpt);
            Font fonte = new Font("Inter", Font.PLAIN, 18);
            btn.setFont(fonte);

            gbc.gridx = i % 2;
            gbc.gridy = 4 + (i / 2);
            content.add(btn, gbc);
        }

        // 6) “Dica” e “Pular”
        Dimension sizeAct = new Dimension(200,60);
        gbc.gridy=6; gbc.insets=new Insets(20,20,0,20);

        RoundedButton dica = new RoundedButton("Dica");
        dica.setFont(btnFont);
        dica.setColumns(15);
        ButtonUtils.estilizarPadrao(dica, sizeAct);
        gbc.gridx=0; content.add(dica, gbc);

        RoundedButton pular = new RoundedButton("Pular");
        pular.setFont(btnFont);
        pular.setColumns(15);
        ButtonUtils.estilizarPadrao(pular, sizeAct);
        gbc.gridx=1; content.add(pular, gbc);

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaJogar(
            "Pergunta 1",
            "Exemplo de pergunta longa que agora começa sempre centralizada e contém quebras corretas.",
            0
        ));
    }
}
