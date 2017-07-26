package gui;

import assets.icon.Icon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Luis
 */
class DisplayMenuAbout { //NOT USED BY CLASSES OUTSIDE PACKAGE

    private static Scene scene;

    protected static void init() {
        //Root
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //Node - Column - Row
        ImageView imageView = new ImageView(Icon.ICON);
        Text info = new Text("Made by Luis"
                + System.lineSeparator()
                + "GitHub: @lsg2550"
                + System.lineSeparator()
                + "Current OS: " + System.getProperty("os.name")
                + System.lineSeparator()
                + "Icons by Enterbrain");
        info.setTextAlignment(TextAlignment.CENTER);

        //Children
        root.getChildren().addAll(imageView, info);

        //Scene
        scene = new Scene(root, 150, 100);
    }

    protected static void show() {
        DisplayStage.setResizable(false);
        DisplayStage.setScene(scene);
        DisplayStage.show();
    }
}
