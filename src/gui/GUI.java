package gui;

import cache.BuildCache;
import com.sun.javafx.css.StyleManager;
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

    private BuildCache buildCache = new BuildCache();
    private Stage primaryStage;

    GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gui();
    }

    private void gui() {
        //Menu
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem open = new MenuItem("Open"),
                save = new MenuItem("Save"),
                exit = new MenuItem("Exit");
        menuBar.getMenus().add(file);
        file.getItems().addAll(open, save, new SeparatorMenuItem(), exit);

        //UI
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(CenterDisplay.CENTER_ALL_CONTAINER_VBOX);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add("assets/persian.css");
        StyleManager.getInstance().addUserAgentStylesheet("assets/persian.css");

        open.setOnAction(e -> {
            buildCache.selectFolder(IO.readDirectoryListOfFiles());
        });
        save.setOnAction(e -> {
            IO.saveFile();
        });
        exit.setOnAction(e -> {
            Thread.currentThread().interrupt();
            primaryStage.close();
        });
    }

}
