package telas.componentes.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IconUtils {
    public static Image getAppIcon() {
        URL iconURL = IconUtils.class.getResource("/telas/img/poliedro_logo.png");
        if (iconURL != null) {
            return new ImageIcon(iconURL).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        }
        return null;
    }

    public static JLabel getAppIconLabel() {
        URL iconURL = IconUtils.class.getResource("/telas/img/poliedro_logo.png");
        return iconURL != null ? new JLabel(new ImageIcon(iconURL)) : new JLabel();
    }
}
