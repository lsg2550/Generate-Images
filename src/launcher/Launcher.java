package launcher;

import gfx.MainDisplay;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainDisplay mD = new MainDisplay();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
