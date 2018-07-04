package gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
class MenuOpenGenerateImage {

    //Scene
    private static Scene scene;

    //CheckBox
    private final static CheckBox LOAD_TYPE = new CheckBox("Single/Multiple");
    private final static CheckBox LOAD_TYPE_WINDOW = new CheckBox("Don't Ask Me Again");

    static void init() {
        //VBox - Root
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //Separator
        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        sep1.setPadding(new Insets(5, 0, 0, 0));

        //Label
        Label loadTypeLabel = new Label("Load a Single Directory or Multiple Directories");
        Label windowLabel = new Label("You can re-enable the popup or change loadtype in Help -> Settings");
        windowLabel.setFont(Font.font(null, FontWeight.BOLD, 10));
        windowLabel.setTextAlignment(TextAlignment.CENTER);
        windowLabel.setWrapText(true);

        //Button
        Button buttonContinue = new Button("Continue");

        //Handlers
        LOAD_TYPE.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.INSTANCE.setLoadType(newValue);
        }));
        LOAD_TYPE_WINDOW.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.INSTANCE.setLoadTypeWindow(!newValue);
        }));
        buttonContinue.setOnAction(e -> {
            SecondaryStage.close();
        });

        //Children
        root.getChildren().addAll(loadTypeLabel, LOAD_TYPE, LOAD_TYPE_WINDOW, sep1, windowLabel, buttonContinue);

        //Scene
        scene = new Scene(root, 350, 100);
    }

    static void show() {
        //CheckBox Initialize
        LOAD_TYPE.setSelected(Settings.INSTANCE.isLoadType());
        LOAD_TYPE_WINDOW.setSelected(!Settings.INSTANCE.isLoadTypeWindow());

        //Show
        SecondaryStage.setResizable(false);
        SecondaryStage.setScene(scene);
        SecondaryStage.showAndWait();
    }
}
