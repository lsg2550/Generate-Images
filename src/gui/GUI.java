package gui;

import assets.icon.Icon;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
class GUI { //NOT USED BY CLASSES OUTSIDE PACKAGE

    static void gui(Stage primaryStage) {
        //UI
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(GUIMenuBar.getMENU_BAR());
        borderPane.setCenter(GUICenter.getCENTER_ALL_CONTAINER_VBOX());
        borderPane.setBottom(GUIBottom.getHb());

        //Scene & Stage
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Generate Images");
        primaryStage.getIcons().add(Icon.ICON);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
    }

}
