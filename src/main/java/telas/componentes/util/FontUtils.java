package telas.componentes.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontUtils {
    public static Font interOrSans(float size, int style) {
        String inter = "Inter";
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String name : ge.getAvailableFontFamilyNames()) {
            if (name.equalsIgnoreCase(inter)) {
                return new Font(inter, style, Math.round(size));
            }
        }
        return new Font("SansSerif", style, Math.round(size));
    }

    public static Font interOrSans(float size) {
        return interOrSans(size, Font.PLAIN);
    }
}
