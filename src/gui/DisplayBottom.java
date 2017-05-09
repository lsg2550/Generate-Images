package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author Luis
 */
public class DisplayBottom {

    protected static HBox hb = new HBox(5);

    protected static void init() {
        hb.getChildren().addAll(DisplayProgressBar.pBar, DisplayText.updateText);
        hb.setAlignment(Pos.CENTER);
    }
}
