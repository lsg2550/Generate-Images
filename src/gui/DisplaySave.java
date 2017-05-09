package gui;

import cache.DrawPreview;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.cloning.ImageClone;
import utils.io.IO;

/**
 *
 * @author Luis
 */
class DisplaySave implements Cloneable {

    protected static Scene saveScene;
    private final static HBox VIEW_HBOX = new HBox(2.5);
    private final static ScrollPane SCROLLPANE = new ScrollPane(VIEW_HBOX);

    protected static void init() {
        /*Main Container*/
        VBox vbox = new VBox(10.0);

        //Scrollpane
        SCROLLPANE.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        SCROLLPANE.setMinHeight(400);
        SCROLLPANE.setMinWidth(640);
        SCROLLPANE.setPannable(true);

        /*Ouside ScrollPane*/
        Button save = new Button("Save");

        /*Alignments*/
        VIEW_HBOX.setAlignment(Pos.CENTER);
        SCROLLPANE.setFitToHeight(true);
        SCROLLPANE.setFitToWidth(true);
        save.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.BASELINE_CENTER);

        /*Misc*/
        save.setMaxSize(60, 20);

        /*Children*/
        SCROLLPANE.setContent(VIEW_HBOX);
        vbox.getChildren().addAll(SCROLLPANE, save);
        saveScene = new Scene(vbox, 640, 480);

        //Handlers
        save.setOnAction(e -> {
            IO.saveFile();
        });
    }

    private static VBox getList(ImageView imageView, int layer) {
        /*Inside VIEW_HBOX*/
        HBox innerHB = new HBox(5.0);
        VBox innerVB = new VBox(2.5);

        /*Inside InnerVBox*/
        TextField orderInLayer = new TextField("" + layer);
        CheckBox checkBox = new CheckBox();

        /*Alignments*/
        orderInLayer.setAlignment(Pos.CENTER);
        innerHB.setAlignment(Pos.CENTER);
        innerVB.setAlignment(Pos.CENTER);

        /*Misc*/
        orderInLayer.setMaxSize(32, 32);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        /*Children*/
        innerHB.getChildren().addAll(orderInLayer, checkBox);
        innerVB.getChildren().addAll(imageView, innerHB);

        return innerVB;
    }

    protected static void show() {
        if (!DrawPreview.getSELECTED_IMAGES().isEmpty()) {
            VIEW_HBOX.getChildren().clear();

            ArrayList<ImageView> temp = cloneAndReplace(DrawPreview.getSELECTED_IMAGES());

            for (ImageView imageView : temp) {
                VIEW_HBOX.getChildren().add(getList(imageView, temp.indexOf(imageView)));
            }
        }

        DisplayWindow.setResizable(false);
        DisplayWindow.setScene(saveScene);
        DisplayWindow.show();
    }

    private static ArrayList<ImageView> cloneAndReplace(ArrayList<ImageView> toBeCloned) {
        ArrayList<ImageView> temp = new ArrayList<>(toBeCloned.size());

        for (ImageView imageView : toBeCloned) {
            try {
                temp.add(new ImageView((Image) new ImageClone(imageView.getImage()).clone()));
            } catch (CloneNotSupportedException ex) {
            }
        }

        return temp;
    }
}
