package cache;

import gui.DisplayGUIPreviewImageView;
import gui.DisplayStage;
import java.io.File;
import java.net.MalformedURLException;
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
class CacheBuild {

    private ArrayList<Image> arrayListOfImages; //ArrayList of Images of CacheBuild
    private char arrayListCharIdentifier; //ID of CacheBuild
    private VBox root = new VBox(); //VBox Scene from main GUI

    protected CacheBuild(File[] listOfImages) {
        arrayListOfImages = new ArrayList<>(listOfImages.length);

        for (File image : listOfImages) {
            try {
                arrayListOfImages.add(new Image(image.toURI().toURL().toExternalForm()));
            } catch (MalformedURLException ex) {
            }
        }
    }

    protected CacheBuild(char arrayListCharIdentifier, Image image) {
        this.arrayListCharIdentifier = arrayListCharIdentifier;
        arrayListOfImages = new ArrayList<>(150);
        arrayListOfImages.add(image);
    }

    protected void buildCacheType() {
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

    protected CacheBuild(Image image) { //For the submenu & savemenu
        buildForSingleImage(image);
    }

    private void buildForSingleImage(Image image) {
        //ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        //CheckBox
        CheckBox enableDisable = new CheckBox();
        enableDisable(imageView, enableDisable);

        root.getChildren().addAll(imageView, enableDisable);
        root.setAlignment(Pos.CENTER);
    }

    //Listeners
    private void enableDisable(ImageView imageView, CheckBox enableDisable) {
        ChangeListener changeListenerForCheckBox = (ChangeListener) (ObservableValue observable, Object oldValue, Object newValue) -> {
            ColorAdjust toGray = new ColorAdjust(1.0, -0.75, 0.0, -0.5);

            if (enableDisable.isSelected()) {
                CacheList.remove(imageView);
                imageView.setEffect(toGray);
            } else {
                CacheList.add(imageView);
                imageView.setEffect(null);
            }

            DisplayGUIPreviewImageView.setImageForImageView();
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

        arrayOfImageSet.forEach((image) -> {
            hb.getChildren().add(new CacheBuild(image).getRoot());
        });

        EventHandler eh = (EventHandler) (Event event) -> {
            DisplayStage.setScene(scene);
            DisplayStage.show();
        };

        extend.setOnAction(eh);
    }

    //Garbage Collection Purposes
    private void nullImages() {
        arrayListOfImages.stream().forEach((image) -> {
            image = null;
        });

        arrayListOfImages.trimToSize();
        arrayListOfImages.clear();
        arrayListOfImages = null;
    }

    //Accessors
    protected VBox getRoot() {
        return root;
    }

    protected char getArrayListCharIdentifier() {
        return arrayListCharIdentifier;
    }

    protected ArrayList<Image> getArrayListOfImages() {
        return arrayListOfImages;
    }

}
