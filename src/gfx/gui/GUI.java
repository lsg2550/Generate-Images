package gfx.gui;

import assets.ico.Icon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import building.BuildCache;
import com.sun.javafx.css.StyleManager;
import gfx.gui.nodes.FileRead;
import gfx.gui.nodes.Progress;
import gfx.gui.nodes.SelectedDirectory;
import javafx.scene.control.SeparatorMenuItem;
import utils.benchmarking.Logging;
import utils.benchmarking.MemoryUsage;
import utils.notifications.AlertBox;
import utils.operations.io.FileSelector;
import utils.operations.io.IO;
import utils.operations.styling.SetStyles;
import utils.operations.styling.Styles;

/**
 *
 * @author Luis
 */
public class GUI {

    //Main Stage GUI Nodes
    private static BorderPane root = new BorderPane();
    private static Scene scene = new Scene(root, 800, 600);
    private static Stage stage = new Stage();

    //Notifications
    private static AlertBox aBox = new AlertBox();

    public GUI() {
        mainStage();
        BuildCache.init();
        FileSelector.init();
    }

    private void mainStage() {
        //UI
        HBox topHBox = new HBox(5),
                bottomHBox = new HBox(5);

        //Menu
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File"),
                help = new Menu("Help"),
                benchmarking = new Menu("Benchmarking"),
                themeSelect = new Menu("Select Theme");
        MenuItem folderSelect = new MenuItem("Open"),
                generateImage = new MenuItem("Save"),
                exit = new MenuItem("Exit"),//end File Menu
                about = new MenuItem("About"),
                themeOne = new MenuItem("Persian"),
                themeTwo = new MenuItem("Rainy Day"),
                memoryUsage = new MenuItem("Memory Usage"),
                timeBenchmark = new MenuItem("Run Time"); //end Help Menu
        menuBar.getMenus().addAll(file, help);
        file.getItems().addAll(folderSelect, generateImage, new SeparatorMenuItem(), exit);
        help.getItems().addAll(about, themeSelect, benchmarking);
        benchmarking.getItems().addAll(memoryUsage, timeBenchmark);
        themeSelect.getItems().addAll(themeOne, themeTwo);
        generateImage.setDisable(true);

        //HBoxes
        topHBox.getChildren().addAll(menuBar, SelectedDirectory.getDirectoryRead());
        bottomHBox.getChildren().addAll(Progress.getpBar(), FileRead.getFileRead());
        topHBox.setAlignment(Pos.CENTER);
        bottomHBox.setAlignment(Pos.CENTER);
        topHBox.setMaxHeight(16);
        bottomHBox.setMaxHeight(16);

        //Button Handlers
        folderSelect.setOnAction(e -> {
            BuildCache.chooseFolder();
        });
        generateImage.setOnAction(e -> {
            try {
                IO.writeFile();
            } catch (NullPointerException ex) {
                aBox.show("Image Was Not Saved!");
            }
        });
        exit.setOnAction(e -> {
            stage.close();
        });
        about.setOnAction(e -> {

        });
        memoryUsage.setOnAction(e -> {
            System.out.println("Current Memory Used: " + MemoryUsage.memoryUsageInMBytes() + " MB");
        });
        timeBenchmark.setOnAction(e -> {
            System.out.println("Time to Load Directory: " + Logging.benchmarkTimeInSeconds() + " seconds");
        });
        themeOne.setOnAction(e -> {
            SetStyles.changeStyle(Styles.persian);
        });
        themeTwo.setOnAction(e -> {
            SetStyles.changeStyle(Styles.rainyDay);
        });

        //BorderPane
        root.setTop(topHBox);
        root.setBottom(bottomHBox);
        root.centerProperty().addListener((o, oV, nV) -> {
            if (root.getCenter() != null) {
                generateImage.setDisable(false);
            }
        });

        stage.setTitle("Generate Image");
        stage.getIcons().add(Icon.ICON);
        stage.setScene(scene);
        stage.show();

        scene.getStylesheets().add(Styles.persian);
        StyleManager.getInstance().addUserAgentStylesheet(Styles.persian);

        stage.setOnCloseRequest(e -> {
            Thread.currentThread().interrupt();
            stage.close();
        });
    }

    public static BorderPane getRoot() {
        return root;
    }

    public static Scene getScene() {
        return scene;
    }
}
