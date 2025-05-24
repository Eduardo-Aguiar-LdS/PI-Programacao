package telas.componentes.util;

import java.awt.Color;

public class ColorUtils {

    public static Color adjustColorBrightness(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1], Math.min(hsb[2] * factor, 1f));
    }
}
