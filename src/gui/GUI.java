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

    private final Stage primaryStage;

    GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gui();
    }

    private void gui() {
        //UI
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(DisplayGUIMenuBar.getMENU_BAR());
        borderPane.setCenter(DisplayGUICenter.getCENTER_ALL_CONTAINER_VBOX());
        borderPane.setBottom(DisplayGUIBottom.getHb());

        //Scene & Stage
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Generate Images");
        primaryStage.getIcons().add(Icon.ICON);
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
    }

}
