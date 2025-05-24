package telas.componentes.botoes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import telas.componentes.util.ColorUtils;

public class RoundedButton extends JButton {
    private final Color btnColor;
    private final Color hover;
    private int wrapWidth = -1;  // largura máxima em px
    private int columns = -1;    // número de caracteres antes de quebrar

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnColor = new Color(0x1FB0C3);
        hover    = ColorUtils.adjustColorBrightness(btnColor, 1.2f);
        setBackground(btnColor);
        setForeground(Color.WHITE);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { setBackground(hover); }
            @Override public void mouseExited(java.awt.event.MouseEvent e)  { setBackground(btnColor); }
        });
    }

    /** Define a largura máxima (px) do texto antes de quebrar */
    public void setWrapWidth(int wrapWidth) {
        this.wrapWidth = wrapWidth;
    }

    /** Define o número de colunas (caracteres) antes de quebrar */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /** Retorna o número de colunas configurado */
    public int getColumns() {
        return columns;
    }

    @Override
    public Dimension getPreferredSize() {
        Insets in = getInsets();
        FontMetrics fm = getFontMetrics(getFont());
        int charWidth = fm.charWidth('M');
        int w;
        if (columns > 0) {
            w = charWidth * columns + in.left + in.right;
        } else {
            w = super.getPreferredSize().width;
        }
        int h = super.getPreferredSize().height;
        return new Dimension(w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // 1) fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        // 2) prepara texto
        g2.setFont(getFont());
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();

        // determina largura interna para wrap
        int maxW;
        if (wrapWidth > 0) {
            maxW = wrapWidth;
        } else if (columns > 0) {
            maxW = fm.charWidth('M') * columns;
        } else {
            maxW = getWidth() - getInsets().left - getInsets().right;
        }

        // determina linhas de texto sem quebra se texto curto
        String text = getText();
        List<String> lines;
        if (text.length() < 50) {
            lines = Arrays.asList(text);
        } else {
            lines = wrapText(text, fm, maxW);
        }

        // posição X da primeira linha (centralizada)
        int firstW = fm.stringWidth(lines.get(0));
        int startX  = (getWidth() - firstW) / 2;

        // desenha cada linha
        int totalH = lines.size() * fm.getHeight();
        int y      = (getHeight() - totalH) / 2 + fm.getAscent();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int x;
            if (i == 0) {
                x = (getWidth() - fm.stringWidth(line)) / 2;
            } else {
                x = startX;
            }
            g2.drawString(line, x, y);
            y += fm.getHeight();
        }

        g2.dispose();
    }

    private List<String> wrapText(String text, FontMetrics fm, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int len = text.length();
        int pos = 0;

        while (pos < len) {
            int end = len;
            while (end > pos && fm.stringWidth(text.substring(pos, end)) > maxWidth) {
                end--;
            }
            int split = text.lastIndexOf(' ', end);
            if (split > pos) {
                lines.add(text.substring(pos, split));
                pos = split + 1;
            } else {
                lines.add(text.substring(pos, end));
                pos = end;
            }
        }
        return lines;
    }
}