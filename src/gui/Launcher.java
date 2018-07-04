package gui;

import assets.css.CSS;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.operations.io.IO;
import utils.imagegeneration.thread.ThreadAlert;

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
        GUIMenuBar.init();
        GUIBottom.init();
        GUICenter.init();
        GUIText.init();
        MenuSettings.init();
        MenuOpenGenerateImage.init();
        MenuOpenGenerateBatch.init();
        MenuAbout.init();
        MenuHowTo.init();
        MenuSave.init();
        SecondaryStage.init();

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
