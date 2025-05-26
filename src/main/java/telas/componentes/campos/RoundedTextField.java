package telas.componentes.campos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import telas.componentes.util.ColorUtils;

public class RoundedTextField extends JTextField {
    private final Color borderColor = Color.BLACK;
    private final Color original;
    private final Color hover;

    // placeholder e sua cor, configuráveis via setter
    private String placeholder      = "";
    private Color placeholderColor  = new Color(0xBBBBBB);

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        original = getBackground();
        hover    = ColorUtils.adjustColorBrightness(original, 0.9f);

        // muda background ao passar o mouse
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { setBackground(hover); }
            @Override public void mouseExited(MouseEvent e)  { setBackground(original); }
        });

        // *NOVO*: ao ganhar ou perder foco, repinta para atualizar placeholder
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    /**
     * Garante que a largura seja sempre "columns × largura de M" 
     * e que todos os campos com o mesmo columns tenham o mesmo tamanho.
     */
    @Override
    public Dimension getPreferredSize() {
        Insets in = getInsets();
        FontMetrics fm = getFontMetrics(getFont());
        int charWidth = fm.charWidth('M');
        int w = charWidth * getColumns() + in.left + in.right;
        int h = super.getPreferredSize().height;
        return new Dimension(w, h);
    }

    /** Define o texto de dica */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    /** Define o texto de dica e sua cor */
    public void setPlaceholder(String placeholder, Color color) {
        this.placeholder = placeholder;
        this.placeholderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);

        // texto digitado
        super.paintComponent(g2);

        // placeholder só se vazio e sem foco
        if (!placeholder.isEmpty() && getText().isEmpty() && !isFocusOwner()) {
            g2.setColor(placeholderColor);
            Insets ins = getInsets();
            FontMetrics fm = g2.getFontMetrics(getFont());
            int x = ins.left;
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(placeholder, x, y);
        }
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
