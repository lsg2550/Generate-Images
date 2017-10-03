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
        DisplayStage.init();
        MenuSettings.init();
        GUIMenuBar.init();
        MenuOpenGI.init();
        MenuOpenBA.init();
        GUIBottom.init();
        GUICenter.init();
        MenuAbout.init();
        MenuSave.init();
        GUIText.init();

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
