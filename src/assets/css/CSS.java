package assets.css;

import com.sun.javafx.css.StyleManager;
import javafx.scene.Scene;

/**
 *
 * @author Luis
 */
public class CSS {

    private static final String CSS_PATH = "assets/css/persian.css";

    public static void init(Scene rootScene) {
        rootScene.getStylesheets().add(CSS_PATH);
        StyleManager.getInstance().addUserAgentStylesheet(CSS_PATH);
    }
}
