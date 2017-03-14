package main;

import gfx.gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GUI gui = new GUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
