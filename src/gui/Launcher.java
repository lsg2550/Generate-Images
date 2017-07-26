package gui;

import assets.css.CSS;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.io.Save;
import utils.thread.ThreadAlert;

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
        DisplayGUICenterScrollPane.init();
        DisplayGUIPreviewImageView.init();
        DisplayMenuSettings.init();
        DisplayGUIMenuBar.init();
        DisplayGUIBottom.init();
        DisplayGUICenter.init();
        DisplayMenuAbout.init();
        DisplayMenuOpen.init();
        DisplayMenuSave.init();
        DisplayStage.init();

        //Utils Init
        ThreadAlert.init();
        Save.init();

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
