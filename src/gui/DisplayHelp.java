package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
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
class DisplayHelp {

    protected static Menu help = new Menu("Help");

    protected static void init() {
        MenuItem about = new MenuItem("About");
        help.getItems().add(about);

        //Scene
        Scene scene = new Scene(aboutPane(), 150, 100);

        about.setOnAction(e -> {
            DisplayWindow.setResizable(false);
            DisplayWindow.setScene(scene);
            DisplayWindow.show();
        });
    }

    private static VBox aboutPane() {
        VBox gp = new VBox();
        gp.setAlignment(Pos.CENTER);

        //Node - Column - Row
        ImageView imageView = new ImageView(new Image("assets/icon/icon418.png"));
        Text info = new Text("Made by Luis"
                + "\nGitHub: @lsg2550"
                + "\n2016-2017"
                + "\nCurrent OS: " + System.getProperty("os.name"));
        info.setTextAlignment(TextAlignment.CENTER);

        gp.getChildren().addAll(imageView, info);
        return gp;
    }

}
