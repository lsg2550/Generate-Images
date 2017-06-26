package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author Luis
 */
class DisplayGUIBottom { //NOT USED BY CLASSES OUTSIDE PACKAGE

    private static final HBox BOTTOM_HBOX = new HBox(5);

    protected static void init() {
        BOTTOM_HBOX.getChildren().addAll(DisplayGUIProgressBar.getpBar(), DisplayGUIText.getUpdateTextNode());
        BOTTOM_HBOX.setAlignment(Pos.CENTER);
    }

    protected static HBox getHb() {
        return BOTTOM_HBOX;
    }
}
