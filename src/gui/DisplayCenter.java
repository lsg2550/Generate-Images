package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class DisplayCenter {

    //UI
    protected static VBox GENERATED_VBOX = new VBox(), CENTER_ALL_CONTAINER_VBOX = new VBox();

    //Initialize
    protected static void init() {
        //Image Preview VBox
        GENERATED_VBOX.getChildren().addAll(new Text("Image To Be Generated"), DisplayPreviewImageView.toBeGeneratedIV);
        GENERATED_VBOX.setAlignment(Pos.CENTER);
        GENERATED_VBOX.setMinSize(250, 250);

        //ImagePreview
        ScrollPane sp = new ScrollPane();
        StackPane stp = new StackPane();
        ImageView iv = new ImageView();
        stp.getChildren().add(iv);
        sp.setContent(stp);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        //Scene
        Scene scene = new Scene(sp, 800, 600);

        //ImageView
        GENERATED_VBOX.setOnMouseClicked(e -> {
            if (DisplayPreviewImageView.containsImage()) {
                iv.setImage(DisplayPreviewImageView.getImageFromImageView());
                DisplayWindow.setScene(scene);
                DisplayWindow.show();
            }
        });

        //Container for all nodes
        CENTER_ALL_CONTAINER_VBOX.heightProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getHeight(), 5);
        CENTER_ALL_CONTAINER_VBOX.widthProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getWidth(), 5);
        CENTER_ALL_CONTAINER_VBOX.getChildren().addAll(DisplayCenterScrollPane.SCROLLPANE_HOLDING_HBOX, GENERATED_VBOX);
        CENTER_ALL_CONTAINER_VBOX.setAlignment(Pos.CENTER);
    }
}
