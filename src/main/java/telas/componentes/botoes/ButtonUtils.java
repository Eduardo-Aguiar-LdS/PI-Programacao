package telas.componentes.botoes;

import java.awt.*;
import javax.swing.*;
import telas.componentes.util.ColorUtils;

public class ButtonUtils {

    public static void estilizarPadrao(AbstractButton btn) {
        estilizarPadrao(btn, new Dimension(320, 50));
    }

    public static void estilizarPadrao(AbstractButton btn, Dimension tamanho) {
        Color corBase = new Color(0x1FB0C3);
        Font fonte = new Font("Inter", Font.PLAIN, 24);

        btn.setPreferredSize(tamanho);
        btn.setBackground(corBase);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(fonte);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Color hover = ColorUtils.adjustColorBrightness(corBase, 1.2f);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            @Override public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(corBase); }
        });
    }
}
