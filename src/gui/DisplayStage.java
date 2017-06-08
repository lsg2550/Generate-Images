package gui;

import assets.icon.Icon;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class DisplayStage { //USED BY CLASSES OUTSIDE PACKAGE

    private final static Stage OUTER_STAGE = new Stage();

    protected static void init() {
        OUTER_STAGE.getIcons().add(Icon.ICON);
        OUTER_STAGE.setResizable(true);

        OUTER_STAGE.setOnCloseRequest(e -> {
            OUTER_STAGE.setScene(null);
            OUTER_STAGE.setResizable(true);
        });
    }

    public static void setResizable(boolean isResizable) { //Some scenes shouldn't be resized, this enables that.
        OUTER_STAGE.setResizable(isResizable);
    }

    public static void setScene(Scene scene) {
        OUTER_STAGE.setScene(scene);
    }

    public static void close() {
        OUTER_STAGE.close();
    }

    public static void show() {
        OUTER_STAGE.show();
    }
}
