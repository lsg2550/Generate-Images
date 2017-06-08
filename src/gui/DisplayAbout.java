package gui;

import assets.icon.Icon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Luis
 */
class DisplayAbout { //NOT USED BY CLASSES OUTSIDE PACKAGE

    private static final MenuItem ABOUT_MENUITEM = new MenuItem("About");

    protected static void init() {
        //Root
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //Node - Column - Row
        ImageView imageView = new ImageView(Icon.ICON);
        Text info = new Text("Made by Luis"
                + "\nGitHub: @lsg2550"
                + "\n2016-2017"
                + "\nCurrent OS: " + System.getProperty("os.name"));
        info.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().addAll(imageView, info);

        //Scene
        Scene scene = new Scene(root, 150, 100);

        ABOUT_MENUITEM.setOnAction(e -> {
            DisplayStage.setResizable(false);
            DisplayStage.setScene(scene);
            DisplayStage.show();
        });
    }

    protected static MenuItem getABOUT_MENUITEM() {
        return ABOUT_MENUITEM;
    }
}
