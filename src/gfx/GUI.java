package gfx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.operations.BuildCache;

/**
 *
 * @author Luis
 */
public class GUI extends BorderPane {

    private static final Image ICON = new Image("assets/ico/briefcase.png");
    private static ScrollPane centerScrollPane = new ScrollPane();
    private static ProgressBar pBar = new ProgressBar(0); //Displays How Much Loading Of Files Is Done
    private static Text text = new Text(), //Display What Files The App Is Reading
            directoryText = new Text(); //Display Directory Being Read

    public GUI() {
        mainStage();
    }

    private void mainStage() {
        //U(ser)I(nterface)
        HBox topHBox = new HBox(5),
                //centerHBox = new HBox(10), //REMINDER: Have this HBox be returned by another class!
                bottomHBox = new HBox(5);
        Button folderSelect = new Button("Select Folder"),
                generateImage = new Button("Generate Image");

        //HBoxes
        topHBox.getChildren().addAll(folderSelect, generateImage, directoryText);
        //centerScrollPane.setContent(centerHBox); //REMINDER: Have this HBox be returned by another class!
        bottomHBox.getChildren().addAll(pBar, text);
        topHBox.setAlignment(Pos.CENTER);
        //centerHBox.setAlignment(Pos.CENTER);
        bottomHBox.setAlignment(Pos.CENTER);
        topHBox.setMaxHeight(64);
        bottomHBox.setMaxHeight(64);

        //Button Handlers
        folderSelect.setOnAction(e -> {
            BuildCache.chooseFolder();
        });
        generateImage.setOnAction(e -> {
            //IO.writeImage(bufferedImage, fileName);
        });

        //BorderPane
        this.setTop(topHBox);
        this.setCenter(centerScrollPane);
        this.setBottom(bottomHBox);

        Scene scene = new Scene(this, 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Generate Image");
        stage.getIcons().add(ICON);
        stage.setScene(scene);
        stage.show();
    }

    public static Image getICON() {
        return ICON;
    }

    public static ProgressBar getpBar() {
        return pBar;
    }

    public static Text getText() {
        return text;
    }

    public static Text getDirectoryText() {
        return directoryText;
    }

    public static void setCenterScrollPane(ScrollPane aCenterScrollPane) {
        centerScrollPane = aCenterScrollPane;
    }
}
