package gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 *
 * @author Luis
 */
public class MenuHowTo {

    //Scene
    private static Scene scene;

    static void init() {
        //Root
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //Label
        Label howto = new Label("How-To");
        howto.setAlignment(Pos.CENTER);

        //Separator
        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        sep1.setPadding(new Insets(10, 0, 0, 0));
        sep1.setVisible(false);

        //Button
        Button buttonClose = new Button("Close");
        buttonClose.setOnAction(e -> {
            SecondaryStage.close();
        });

        //Children
        root.getChildren().addAll(howto, sep1, buttonClose);

        //Scene
        scene = new Scene(root, 500, 500);
    }

    static void show() {
        SecondaryStage.setResizable(false);
        SecondaryStage.setScene(scene);
        SecondaryStage.show();
    }
}
