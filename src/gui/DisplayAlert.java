package gui;

import assets.icon.Icon;
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
public class DisplayAlert {

    //Text
    private static final Text TEXT = new Text();

    //Scene
    private static Scene scene;

    protected static void init() {
        //VBox
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        //HBox
        HBox topHB = new HBox(30);
        topHB.setAlignment(Pos.CENTER);

        //StackPane
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        //Button
        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            DisplayWindow.close();
        });

        //Text
        TEXT.setFont(Font.font(null, 12));
        TEXT.setTextAlignment(TextAlignment.CENTER);
        TEXT.setWrappingWidth(200); //Find a way to avoid using hardcode
        TEXT.setFill(Color.BLACK);

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
        stackPane.getChildren().add(TEXT);
        root.getChildren().addAll(topHB, new Separator(Orientation.HORIZONTAL), stackPane, ok);
        scene = new Scene(root, 300, 150);
    }

    public static void show(String message) {
        TEXT.setText(message); //Editable Message

        //Stage
        DisplayWindow.setResizable(false);
        DisplayWindow.setScene(scene);
        DisplayWindow.show();
    }

}
