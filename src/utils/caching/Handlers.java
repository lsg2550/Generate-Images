package utils.caching;

import gfx.gui.GUI;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.notifications.AlertBox;
import utils.operations.EnhancedIterator;
import utils.operations.IO;

/**
 *
 * @author Luis
 */
public class Handlers {

    private static AlertBox alertBox = new AlertBox();

    //Clears the arraylists when loading a new directory
    protected static void clearCache(ArrayList<ArrayList<BufferedImage>> arrayList, ArrayList<BufferedImage>... arrayLists) {
        try {
            arrayList.clear();

            for (ArrayList<BufferedImage> arrayListBI : arrayLists) {
                arrayListBI.clear();
            }

            System.gc();
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
    }

    //Moves all arraylists into the main splitImgs arraylist
    protected static void addCache(ArrayList<ArrayList<BufferedImage>> arrayList, ArrayList<BufferedImage>... arrayLists) {
        arrayList.addAll(Arrays.asList(arrayLists));
    }

    //SetOnAction for the direction buttons
    protected static void directionButton(ArrayList<BufferedImage> splitImage, ImageView imageView, Button... directionButton) {
        List<Image> images = new ArrayList();

        splitImage.forEach((image) -> {
            images.add(SwingFXUtils.toFXImage(image, null));
        });

        EnhancedIterator<Image> iteratorIMG = new EnhancedIterator(images, 0);

        /*Buttons*/
        directionButton[0].setOnAction(e -> {   //Left Button
            if (!iteratorIMG.hasPrevious()) {
                alertBox.show("You've reached the end of the list!");
            } else {
                imageView.setImage(iteratorIMG.previous());
            }
        });
        directionButton[1].setOnAction(e -> {   //Right Button
            if (!iteratorIMG.hasNext()) {
                alertBox.show("You've reached the end of the list!");
            } else {
                imageView.setImage(iteratorIMG.next());
            }
        });
    }

    //SetOnAction for the checkbox
    protected static void enableDisable(ImageView imageView, CheckBox enableDisable, Button... directionButton) {
        BackupImage bui = new BackupImage();

        enableDisable.selectedProperty().addListener((o, oV, nV) -> {
            if (enableDisable.isSelected()) {
                Image img = imageView.getImage();
                BufferedImage temp = SwingFXUtils.fromFXImage(img, null);
                bui.setImage(img);

                //Disables Buttons and ImageView
                for (Button button : directionButton) {
                    button.setDisable(true);
                }
                BuildMenu.test.remove(imageView);
                imageView.setDisable(true);
                imageView.setImage(SwingFXUtils.toFXImage(IO.toGray(temp), null));
            } else {
                //Enable Buttons and ImageView
                for (Button button : directionButton) {
                    button.setDisable(false);
                }
                BuildMenu.test.add(imageView);
                imageView.setDisable(false);
                imageView.setImage(bui.getImage());
            }
            BuildCache.liveImageBuilding();
        });
        enableDisable.setSelected(true);
    }

    //When the user uses any of the direction buttons or enables/disables and imageview
    //in the application this method will be called to update the image
    protected static void imageViewListener(ImageView imageView) {
        imageView.imageProperty().addListener((o, oV, nV) -> {
            BuildCache.liveImageBuilding();
        });
    }

    //When the user clicks on an imageview, this opens a new stage so that the user may get a
    //better look at the image they're editing.
    protected static void imageViewStageBuilder(ImageView imageView) {
        imageView.setOnMouseClicked(e -> {
            Image image = imageView.getImage();
            ImageView imageV = new ImageView(image);
            imageV.setPreserveRatio(true);

            Scene scene = new Scene(new StackPane(imageV), 800, 600);

            Stage stage = new Stage();
            stage.getIcons().add(GUI.getICON());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(GUI.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        });
    }

    protected static void buildSubMenu(ArrayList<BufferedImage> arrayOfImageSet, ImageView imageView) {
        imageView.setOnMouseClicked(e -> {
            HBox hb = new HBox();
            hb.setMaxHeight(5);
            hb.setAlignment(Pos.CENTER);

            for (BufferedImage bufferedImage : arrayOfImageSet) {
                //UI
                VBox tempVB = new VBox();
                CheckBox enableDisable = new CheckBox();

                //Details
                ImageView innerImageView = new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
                tempVB.getChildren().addAll(innerImageView, enableDisable);
                tempVB.setAlignment(Pos.CENTER);
                innerImageView.setFitWidth(225);
                innerImageView.setFitHeight(225);

                //Handlers
                Handlers.enableDisable(innerImageView, enableDisable);
                Handlers.imageViewListener(innerImageView);

                //Adding to HBox
                hb.getChildren().add(tempVB);
            }

            ScrollPane sp = new ScrollPane();
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setPannable(true);
            sp.setContent(hb);

            Scene scene = new Scene(sp, 800, 600);
            Stage stage = new Stage();
            stage.getIcons().add(GUI.getICON());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(GUI.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        });
    }
}
