package gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
class MenuSettings {

    //Scene
    private static Scene scene;

    //CheckBox
    private final static CheckBox LOAD_TYPE = new CheckBox("Single/Multiple");
    private final static CheckBox LOAD_TYPE_WINDOW = new CheckBox("Show Load Type Window");

    static void init() {
        //Root
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //Label
        Label loadTypeSettings = new Label("Load Type Settings");
        loadTypeSettings.setAlignment(Pos.CENTER);
        Label otherSettings = new Label("Other Settings");
        otherSettings.setAlignment(Pos.CENTER);

        //Separator
        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        sep1.setPadding(new Insets(5, 0, 0, 0));

        //CheckBox
        LOAD_TYPE.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.loadType = newValue;
        }));
        LOAD_TYPE_WINDOW.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            Settings.loadTypeWindow = newValue;
        }));

        //Children
        root.getChildren().addAll(loadTypeSettings, LOAD_TYPE, LOAD_TYPE_WINDOW, sep1, otherSettings);

        //Scene
        scene = new Scene(root, 250, 100);
    }

    static void show() {
        //CheckBox Update
        LOAD_TYPE.setSelected(Settings.loadType);
        LOAD_TYPE_WINDOW.setSelected(Settings.loadTypeWindow);

        //Show
        DisplayStage.setResizable(false);
        DisplayStage.setScene(scene);
        DisplayStage.show();
    }
}
