package utils.imagegeneration;

import gui.GUIPreviewImageView;
import gui.SecondaryStage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    private List<Image> listOfImages; //List of Images of CacheBuild
    private char listIdentifier; //ID of CacheBuild
    private VBox root = new VBox(); //VBox Scene from main GUI

    CacheBuild(File[] arrayOfImages) { //Multiple Directories
        listOfImages = new ArrayList<>(arrayOfImages.length);

        try {
            for (File image : arrayOfImages) {
                listOfImages.add(new Image(image.toURI().toURL().toExternalForm()));
            }
        } catch (MalformedURLException ex) {
        }
    }

    CacheBuild(char arrayListCharIdentifier, Image image) { //Single Directories
        this.listIdentifier = arrayListCharIdentifier;
        listOfImages = new LinkedList<>();
        listOfImages.add(image); //Initial Image
    }

    CacheBuild(Image image) { //For the submenu & savemenu
        buildForSingleImage(image);
    }

    void buildCacheType() {
        //ImageView
        ImageView imageView = new ImageView(listOfImages.get(0));
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        if (listOfImages.size() == 1) {
            //CheckBox
            CheckBox enableDisable = new CheckBox();
            enableDisable(imageView, enableDisable);

            root.getChildren().addAll(imageView, enableDisable);
        } else {
            //Button
            Button extend = new Button("Extend");
            extend.setPadding(new Insets(0, 5, 0, 5));
            extendAndCreateSubMenu(listOfImages, extend);

            root.getChildren().addAll(imageView, extend);
        }

        root.setAlignment(Pos.CENTER);
        nullifyImages();
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

            GUIPreviewImageView.setImageForImageView(CacheList.draw());
        };

        enableDisable.selectedProperty().addListener(changeListenerForCheckBox);
        enableDisable.setSelected(true);
    }

    private void extendAndCreateSubMenu(List<Image> arrayOfImageSet, Button extend) {
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
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        };

        extend.setOnAction(eh);
    }

    //Garbage Collection Purposes
    private void nullifyImages() {
        listOfImages.stream().forEach((image) -> {
            image = null;
        });

        listOfImages.clear();
        listOfImages = null;
    }

    //Accessors
    VBox getRoot() {
        return root;
    }

    char getCharIdentifier() {
        return listIdentifier;
    }

    List<Image> getListOfImages() {
        return listOfImages;
    }

}
