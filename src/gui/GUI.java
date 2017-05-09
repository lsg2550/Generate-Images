package gui;

import assets.icon.Icon;
import cache.Cache;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.io.IO;

/**
 *
 * @author Luis
 */
public class GUI {

    private Cache buildCache = new Cache();
    private Stage primaryStage;

    GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gui();
    }

    private void gui() {
        //Menu
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem open = new MenuItem("Open"), save = new MenuItem("Save"), exit = new MenuItem("Exit");

        //Menu Children
        menuBar.getMenus().addAll(file, DisplayHelp.help, DisplayText.directoryText);
        file.getItems().addAll(open, save, new SeparatorMenuItem(), exit);

        //UI
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(DisplayCenter.CENTER_ALL_CONTAINER_VBOX);
        borderPane.setBottom(DisplayBottom.hb);

        //Scene & Stage
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.getIcons().add(Icon.ICON);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Handlers
        open.setOnAction(e -> {
            buildCache.selectFolder(IO.readDirectoryListOfFiles());
        });
        save.setOnAction(e -> {
            //DisplaySave.show(); //Window to display chosen images where user will be able to re-arrange order etc before writing image
            IO.saveFile();
        });
        exit.setOnAction(e -> {
            Thread.currentThread().interrupt();
            primaryStage.close();
        });
    }

}
