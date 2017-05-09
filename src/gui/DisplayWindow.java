package gui;

import assets.icon.Icon;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class DisplayWindow {

    private final static Stage EXTRA_DISPLAY = new Stage();

    protected static void init() {
        EXTRA_DISPLAY.getIcons().add(Icon.ICON);
        EXTRA_DISPLAY.setResizable(true);

        EXTRA_DISPLAY.setOnCloseRequest(e -> {
            EXTRA_DISPLAY.setScene(null);
            EXTRA_DISPLAY.setResizable(true);
        });
    }

    public static void setResizable(boolean isResizable) { //Some scenes shouldn't be resized, this enables that.
        EXTRA_DISPLAY.setResizable(isResizable);
    }

    public static void setScene(Scene scene) {
        EXTRA_DISPLAY.setScene(scene);
    }

    public static void show() {
        EXTRA_DISPLAY.show();
    }
}
