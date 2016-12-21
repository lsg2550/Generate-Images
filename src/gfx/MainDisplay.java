package gfx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.IO;

/**
 *
 * @author Luis
 */
public class MainDisplay {

    public MainDisplay() {
        mainStage();
    }

    private void mainStage() {
        //U(ser)I(nterface)
        BorderPane root = new BorderPane(); //BorderPane (Root)
        HBox topHBox = new HBox(5),
                centerHBox = new HBox(10); //REMINDER: Have this HBox be returned by another class!
        Button folderSelect = new Button("Select Folder"),
                generateImage = new Button("Generate Image");
        ScrollPane centerScrollPane = new ScrollPane();
        Text directoryText = new Text();

        topHBox.getChildren().addAll(folderSelect, generateImage, directoryText);
        centerScrollPane.setContent(centerHBox); //REMINDER: Have this HBox be returned by another class!
        topHBox.setAlignment(Pos.CENTER);
        centerHBox.setAlignment(Pos.CENTER);
        root.setTop(topHBox);
        root.setCenter(centerScrollPane);

        //Button Handlers
        folderSelect.setOnAction(e -> {
            directoryText.setText((String) IO.ChooseFolder()[1]);
        });
        generateImage.setOnAction(e -> {
            
        });

        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Generate Image");
        stage.getIcons().add(new Image("gfx/assets/ico/briefcase.png"));
        stage.setScene(scene);
        stage.show();
    }
}
