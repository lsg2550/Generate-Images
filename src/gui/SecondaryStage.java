package gui;

import assets.icon.Icon;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class SecondaryStage { //USED BY CLASSES OUTSIDE PACKAGE

    private final static Stage DISPLAY_STAGE = new Stage();

    static void init() {
        DISPLAY_STAGE.getIcons().add(Icon.ICON);
        DISPLAY_STAGE.setResizable(true);

        DISPLAY_STAGE.setOnCloseRequest(e -> {
            DISPLAY_STAGE.setScene(null);
            DISPLAY_STAGE.setMaximized(false);
            DISPLAY_STAGE.setResizable(true);
        });
    }

    public static void setResizable(boolean isResizable) { //Some scenes shouldn't be resized, this enables that.
        DISPLAY_STAGE.setResizable(isResizable);
    }

    public static void setScene(Scene scene) {
        DISPLAY_STAGE.setScene(scene);
    }

    public static void close() {
        DISPLAY_STAGE.close();
    }

    public static void showAndWait() {
        DISPLAY_STAGE.showAndWait();
    }

    public static void show() {
        DISPLAY_STAGE.show();
    }
}
