package cache;

import gui.CenterDisplay;
import gui.PopDisplay;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Luis
 */
/**
 * ACTS AS ONE BOX
 */
public class CacheType {

    private ArrayList<Image> arrayListOfImages = new ArrayList(150);
    private char arrayListIdentifier;
    private VBox root = new VBox();

    private CacheType(Image image) { //For the submenu
        arrayListOfImages.add(image);
    }

    public CacheType(char arrayListIdentifier, Image image) { //Start CacheType with its identifier and initial image
        this.arrayListIdentifier = arrayListIdentifier;
        arrayListOfImages.add(image);
    }

    public void buildCacheType() {
        //ImageView
        ImageView imageView = new ImageView(arrayListOfImages.get(0));
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        if (arrayListOfImages.size() == 1) {
            //CheckBox
            CheckBox enableDisable = new CheckBox();
            enableDisable(imageView, enableDisable);

            root.getChildren().addAll(imageView, enableDisable);
        } else {
            //Button
            Button extend = new Button("Extend");
            extend.setPadding(new Insets(0, 5, 0, 5));
            extendAndCreateSubMenu(arrayListOfImages, extend);

            root.getChildren().addAll(imageView, extend);
        }

        root.setAlignment(Pos.CENTER);
        nullImages();
    }

    //Listeners
    private void enableDisable(ImageView imageView, CheckBox enableDisable) {
        ChangeListener changeListenerForCheckBox = (ChangeListener) (ObservableValue observable, Object oldValue, Object newValue) -> {
            ColorAdjust toGray = new ColorAdjust(1.0, -0.75, 0.0, -0.5);

            if (enableDisable.isSelected()) {
                SelectedImageList.selectedImages.remove(imageView);
                imageView.setEffect(toGray);
            } else {
                SelectedImageList.selectedImages.add(imageView);
                imageView.setEffect(null);
            }

            SelectedImageList.draw();
        };

        enableDisable.selectedProperty().addListener(changeListenerForCheckBox);
        enableDisable.setSelected(true);
    }

    private void extendAndCreateSubMenu(ArrayList<Image> arrayOfImageSet, Button extend) {
        //SP
        ScrollPane sp = new ScrollPane();
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setPannable(true);

        //HBox
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        //Scene
        Scene scene = new Scene(sp, 600, 275);

        //Binding
        sp.setContent(hb);

        for (Image image : arrayOfImageSet) {
            CacheType ct = new CacheType(image);
            ct.buildCacheType();
            hb.getChildren().add(ct.getRoot());
        }

        EventHandler eh = (EventHandler) (Event event) -> {
            PopDisplay.EXTRA_DISPLAY.setScene(scene);
            PopDisplay.EXTRA_DISPLAY.show();
        };

        extend.setOnAction(eh);
    }

    //Garbage Collection Purposes
    private void nullImages() { //Due to JavaFX's poor garbage collection of image objects, I am forced to null the image objects manually
        arrayListOfImages.stream().forEach((image) -> {
            image = null;
        });
        arrayListOfImages.clear();
        arrayListOfImages.trimToSize();
        arrayListOfImages = null;
    }

    //Accessors
    public VBox getRoot() {
        return root;
    }

    public char getIdentifier() {
        return arrayListIdentifier;
    }

    public ArrayList<Image> getArrayListOfImages() {
        return arrayListOfImages;
    }
}
