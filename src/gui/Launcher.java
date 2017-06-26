package gui;

import assets.css.CSS;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Stage Init
        GUI gui = new GUI(primaryStage);
        CSS.init(primaryStage.getScene());
        Init.init();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
