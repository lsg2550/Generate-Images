package gui;

import assets.css.CSS;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.io.IO;
import utils.thread.ThreadAlert;

/**
 *
 * @author Luis
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Primary Stage Init
        GUI.gui(primaryStage);
        CSS.init(primaryStage.getScene());

        //GUI Init
        GUICenterScrollPane.init();
        GUIPreviewImageView.init();
        MenuSettings.init();
        GUIMenuBar.init();
        GUIBottom.init();
        GUICenter.init();
        MenuAbout.init();
        MenuOpenGI.init();
        MenuOpenBA.init();
        MenuSave.init();
        DisplayStage.init();

        //Utils Init
        ThreadAlert.init();
        IO.init();

        //Show
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
