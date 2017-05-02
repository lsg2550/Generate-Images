package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class CenterDisplay {

    //UI
    public static VBox GENERATED_VBOX = new VBox(), CENTER_ALL_CONTAINER_VBOX = new VBox();
    public final static HBox IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX = new HBox(2.5);
    public final static ScrollPane SCROLLPANE_HOLDING_IMAGEVIEW_HBOX = new ScrollPane(IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX);
    public final static ImageView toBeGeneratedIV = new ImageView();

    //Initialize
    public static void init() {
        //HBox inside Scrollpane
        IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX.setAlignment(Pos.CENTER);
        IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX.setMaxHeight(5);

        //Scrollpane
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setMinHeight(300); //Original 270
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setPannable(true);

        //Image Preview VBox
        GENERATED_VBOX.getChildren().addAll(new Text("Image To Be Generated"), toBeGeneratedIV);
        GENERATED_VBOX.setAlignment(Pos.CENTER);
        GENERATED_VBOX.setMinSize(275, 275);

        //ImagePreview
        ScrollPane sp = new ScrollPane();
        StackPane stp = new StackPane();
        ImageView iv = new ImageView();
        stp.getChildren().add(iv);
        stp.setAlignment(Pos.CENTER);
        sp.setContent(stp);

        //Scene
        Scene scene = new Scene(sp, 800, 600);

        //ImageView
        toBeGeneratedIV.setOnMouseClicked(e -> {
            iv.setImage(toBeGeneratedIV.getImage());
            PopDisplay.EXTRA_DISPLAY.setScene(scene);
            PopDisplay.EXTRA_DISPLAY.show();
        });
        toBeGeneratedIV.setFitHeight(235);
        toBeGeneratedIV.setFitWidth(235);

        //Container for all nodes
        CENTER_ALL_CONTAINER_VBOX.heightProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getHeight(), 5);
        CENTER_ALL_CONTAINER_VBOX.widthProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getWidth(), 5);
        CENTER_ALL_CONTAINER_VBOX.getChildren().addAll(SCROLLPANE_HOLDING_IMAGEVIEW_HBOX, GENERATED_VBOX);
        CENTER_ALL_CONTAINER_VBOX.setAlignment(Pos.CENTER);
    }
}
