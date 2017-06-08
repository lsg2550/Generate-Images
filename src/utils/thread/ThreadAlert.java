package utils.thread;

import assets.icon.Icon;
import gui.DisplayStage;
import gui.DisplayText;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Luis
 */
public class ThreadAlert {

    //Scene
    private static Scene scene;

    public static void init() {
        //VBox
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        //HBox
        HBox topHB = new HBox(30);
        topHB.setAlignment(Pos.CENTER);

        HBox buttonHB = new HBox(5);
        buttonHB.setAlignment(Pos.CENTER);

        //StackPane
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        //Button
        Button btnInterruptThread = new Button("Yes"),
                btnKeepThread = new Button("No");
        btnKeepThread.setOnAction(e -> {
            DisplayStage.close();
        });
        btnInterruptThread.setOnAction(e -> {
            DisplayText.setUpdateText("Please Wait For The Process To Safely Halt!");
            BuildThread.interruptThread();
            DisplayStage.close();
        });

        //Text
        Text warningText = new Text("You're currently loading images would you like to stop?");
        warningText.setFont(Font.font(null, 12));
        warningText.setTextAlignment(TextAlignment.CENTER);
        warningText.setWrappingWidth(200); //Find a way to avoid using hardcode
        warningText.setFill(Color.BLACK);

        //Label
        Label warningLabel = new Label("ATTENTION!");
        warningLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        warningLabel.setTextAlignment(TextAlignment.CENTER);
        warningLabel.setTextFill(Color.BLACK);

        //ImageView
        ImageView warningImageView = new ImageView(Icon.ICON);
        warningImageView.setFitHeight(24);
        warningImageView.setFitWidth(24);

        //Children
        topHB.getChildren().addAll(warningLabel, warningImageView);
        buttonHB.getChildren().addAll(btnInterruptThread, btnKeepThread);
        stackPane.getChildren().add(warningText);
        root.getChildren().addAll(topHB, new Separator(Orientation.HORIZONTAL), stackPane, buttonHB);
        scene = new Scene(root, 300, 150);
    }

    public static void show() {
        DisplayStage.setResizable(false);
        DisplayStage.setScene(scene);
        DisplayStage.show();
    }

}
