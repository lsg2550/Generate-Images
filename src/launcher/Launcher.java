package launcher;

import gfx.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        new GUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
