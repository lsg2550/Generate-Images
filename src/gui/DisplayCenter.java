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
class DisplayCenter { //NOT USED BY CLASSES OUTSIDE PACKAGE

    //UI
    private static final VBox GENERATED_VBOX = new VBox(),
            CENTER_ALL_CONTAINER_VBOX = new VBox();

    //Initialize
    protected static void init() {
        //Image Preview VBox
        GENERATED_VBOX.getChildren().addAll(new Text("Image To Be Generated"), DisplayPreviewImageView.getPREVIEW_IMAGEVIEW());
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
                DisplayStage.setScene(scene);
                DisplayStage.show();
            }
        });

        //Container for all nodes
        CENTER_ALL_CONTAINER_VBOX.heightProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getHeight(), 5);
        CENTER_ALL_CONTAINER_VBOX.widthProperty().isEqualTo(CENTER_ALL_CONTAINER_VBOX.getScene().getWidth(), 5);
        CENTER_ALL_CONTAINER_VBOX.getChildren().addAll(DisplayCenterScrollPane.getSCROLLPANE_HOLDING_HBOX(), GENERATED_VBOX);
        CENTER_ALL_CONTAINER_VBOX.setAlignment(Pos.CENTER);
    }

    protected static VBox getGENERATED_VBOX() {
        return GENERATED_VBOX;
    }

    protected static VBox getCENTER_ALL_CONTAINER_VBOX() {
        return CENTER_ALL_CONTAINER_VBOX;
    }
}
