package gui;

import utils.thread.ThreadAlert;
import assets.css.CSS;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.io.Read;
import utils.io.Save;

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

        //GUI Init
        DisplayCenterScrollPane.init();
        DisplayPreviewImageView.init();
        DisplayBottom.init();
        DisplayCenter.init();
        DisplayMenuBar.init();
        DisplayAbout.init();
        DisplayStage.init();
        DisplaySave.init();

        //Utils Init
        ThreadAlert.init();
        Save.init();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
