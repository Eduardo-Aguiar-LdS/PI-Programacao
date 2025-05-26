package telas.componentes.combos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import telas.componentes.util.ColorUtils;

public class RoundedComboBox<E> extends JComboBox<E> {
    private final Color borderColor = Color.BLACK;
    private final Color original;
    private final Color hover;

    // placeholder e sua cor
    private String placeholder = "";
    private Color placeholderColor = new Color(0xBBBBBB);

    public RoundedComboBox(E[] items) {
        super(items);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        original = getBackground();
        hover    = ColorUtils.adjustColorBrightness(original, 0.9f);

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { setBackground(hover); }
            @Override public void mouseExited(MouseEvent e)  { setBackground(original); }
        });

        // sem seleção inicial
        setSelectedIndex(-1);

        // seta a seta customizada
        setUI(new BasicComboBoxUI() {
            @Override protected JButton createArrowButton() {
                JButton btn = new JButton("\u25BE");
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setContentAreaFilled(false);
                return btn;
            }
        });
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

        // componente padrão (texto + seta)
        super.paintComponent(g2);

        // placeholder quando não há item selecionado
        if (!placeholder.isEmpty() && getSelectedIndex() < 0) {
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
