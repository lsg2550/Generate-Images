package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author Luis
 */
class GUIBottom { //NOT USED BY CLASSES OUTSIDE PACKAGE

    private static final HBox BOTTOM_HBOX = new HBox(5);

    static void init() {
        BOTTOM_HBOX.getChildren().addAll(GUIProgressBar.getpBar(), GUIText.getUpdateTextNode());
        BOTTOM_HBOX.setAlignment(Pos.CENTER);
    }

    static HBox getHb() {
        return BOTTOM_HBOX;
    }
}
