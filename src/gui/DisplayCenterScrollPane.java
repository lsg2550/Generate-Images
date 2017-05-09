package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Luis
 */
public class DisplayCenterScrollPane {

    private final static HBox HBOX_INSIDE_SCROLLPANE = new HBox(2.5);
    protected final static ScrollPane SCROLLPANE_HOLDING_HBOX = new ScrollPane(HBOX_INSIDE_SCROLLPANE);

    protected static void init() {
        //HBox inside Scrollpane
        HBOX_INSIDE_SCROLLPANE.setAlignment(Pos.CENTER);
        HBOX_INSIDE_SCROLLPANE.setMaxHeight(5);

        //Scrollpane
        SCROLLPANE_HOLDING_HBOX.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        SCROLLPANE_HOLDING_HBOX.setMinHeight(300); //Original 270
        SCROLLPANE_HOLDING_HBOX.setPannable(true);
    }

    public static void addToHBox(Node node) {
        HBOX_INSIDE_SCROLLPANE.getChildren().add(node);
    }

    public static void clearHBox() {
        HBOX_INSIDE_SCROLLPANE.getChildren().clear();
    }
}
