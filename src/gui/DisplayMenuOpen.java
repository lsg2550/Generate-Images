package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
class DisplayMenuOpen {

    //Scene
    private static Scene scene;

    //CheckBox
    private final static CheckBox LOAD_TYPE = new CheckBox("Single/Multiple"),
            LOAD_TYPE_WINDOW = new CheckBox("Don't Ask Me Again");

    protected static void init() {
        //Root
        BorderPane root = new BorderPane();

        //VBox
        VBox topVB = new VBox();
        topVB.setAlignment(Pos.CENTER);
        VBox botVB = new VBox();
        botVB.setAlignment(Pos.CENTER);
        botVB.setPadding(new Insets(5));

        //Label
        Label loadTypeLabel = new Label("Load a Single Directory or Multiple Directories");
        Label windowLabel = new Label("You can re-enable the popup or change loadtype in Help -> Settings");
        windowLabel.setFont(Font.font(null, FontWeight.BOLD, 10));
        windowLabel.setTextAlignment(TextAlignment.CENTER);
        windowLabel.setWrapText(true);

        //Button
        Button cont = new Button("Continue");

        //Handlers
        LOAD_TYPE.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.loadType = newValue;
        }));
        LOAD_TYPE_WINDOW.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.loadTypeWindow = !newValue;
        }));
        cont.setOnAction(e -> {
            DisplayStage.close();
        });

        //Children
        topVB.getChildren().addAll(loadTypeLabel, LOAD_TYPE);
        root.setTop(topVB);
        botVB.getChildren().addAll(LOAD_TYPE_WINDOW, windowLabel, cont);
        root.setBottom(botVB);

        //Scene
        scene = new Scene(root, 350, 100);
    }

    protected static void show() {
        //CheckBox Initialize
        LOAD_TYPE.setSelected(Settings.loadType);
        LOAD_TYPE_WINDOW.setSelected(!Settings.loadTypeWindow);

        //Show
        DisplayStage.setResizable(false);
        DisplayStage.setScene(scene);
        DisplayStage.showAndWait();
    }
}
