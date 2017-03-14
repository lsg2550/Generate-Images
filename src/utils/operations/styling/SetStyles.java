package utils.operations.styling;

import com.sun.javafx.css.StyleManager;
import gfx.gui.GUI;

/**
 *
 * @author Luis
 */
public class SetStyles {

    private static String currentTheme = Styles.persian;

    public static void changeStyle(String themePath) {

        GUI.getScene().getStylesheets().remove(currentTheme);
        StyleManager.getInstance().removeUserAgentStylesheet(currentTheme);

        currentTheme = themePath;

        GUI.getScene().getStylesheets().add(currentTheme);
        StyleManager.getInstance().addUserAgentStylesheet(currentTheme);

    }
}
