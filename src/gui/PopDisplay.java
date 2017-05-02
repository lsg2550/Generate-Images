package gui;

import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class PopDisplay {

    public final static Stage EXTRA_DISPLAY = new Stage();

    public static void init() {
        EXTRA_DISPLAY.setResizable(true);

        EXTRA_DISPLAY.setOnCloseRequest(e -> {
            EXTRA_DISPLAY.setScene(null);
        });
    }
}
