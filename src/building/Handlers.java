package building;

import assets.ico.Icon;
import gfx.gui.GUI;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static building.ImageList.toGeneratedIV;
import utils.operations.enhancednodes.EnhancedImageView;

/**
 *
 * @author Luis
 */
class Handlers {

    //SetOnAction for Buttons
    static void extendAndCreateSubMenu(ArrayList<BufferedImage> arrayOfImageSet, EnhancedImageView imageView, Button extend) {
        new Thread(() -> {

            //UI
            HBox hb = new HBox();
            hb.setMaxHeight(5);
            hb.setAlignment(Pos.CENTER);

            arrayOfImageSet.forEach((bufferedImage) -> {
                //UI
                VBox tempVB = new VBox();
                CheckBox enableDisable = new CheckBox();

                //Details
                EnhancedImageView innerImageView = new EnhancedImageView(SwingFXUtils.toFXImage(bufferedImage, null));
                innerImageView.setFitWidth(250);
                innerImageView.setFitHeight(250);
                tempVB.getChildren().addAll(innerImageView, enableDisable);
                tempVB.setAlignment(Pos.CENTER);

                //Handlers
                Handlers.enableDisable(innerImageView, enableDisable);
                Handlers.imageViewListener(innerImageView);

                //Adding to HBox
                hb.getChildren().add(tempVB);
            });

            ScrollPane sp = new ScrollPane();
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setPannable(true);
            sp.setContent(hb);

            Scene scene = new Scene(sp, 600, 275);

            Platform.runLater(() -> {

                Stage stage = new Stage();
                stage.getIcons().add(Icon.ICON);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUI.getScene().getWindow());
                stage.setResizable(false);
                stage.setScene(scene);

                extend.setOnMouseClicked(e -> {
                    stage.show();
                });

            });

        }).start();
    }

    //When the user clicks on the ImageToBeGenerated a new window will open
    //to allow the user to clearly see the image
    static void imageViewStageBuilder(EnhancedImageView eImageView) {
        new Thread(() -> {

            ImageView imageV = new ImageView();

            StackPane sp = new StackPane();
            sp.getChildren().add(imageV);
            sp.setAlignment(Pos.CENTER);

            Scene scene = new Scene(sp, 800, 600);

            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.getIcons().add(Icon.ICON);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUI.getScene().getWindow());
                stage.setScene(scene);

                eImageView.setOnMouseClicked(e -> {
                    imageV.setImage(eImageView.getImage());
                    stage.show();
                });
            });

        }).start();

    }

    //SetOnAction for the Checkbox
    static void enableDisable(EnhancedImageView eImageView, CheckBox enableDisable) {
        new Thread(() -> {

            enableDisable.selectedProperty().addListener(e -> {

                if (enableDisable.isSelected()) {

                    ImageList.outputImages.remove(eImageView);
                    ImageList.outputImages.trimToSize();
                    eImageView.applyGrayscale();

                } else {

                    ImageList.outputImages.add(eImageView);
                    eImageView.removeEffect();

                }

            });

            enableDisable.setSelected(true);

        }).start();
    }

    //When the user enables/disables an imageview this method will be called to 
    //update the "To Be Generated" image
    static void imageViewListener(EnhancedImageView eImageView) {
        eImageView.effectProperty().addListener(e -> {
            liveImageBuilding();
        });
    }

    //This method is called whenever there is a change in the ImageViews in the
    //program. e.g. When they're enabled or disabled
    static void liveImageBuilding() {
        new Thread(() -> {

            toGeneratedIV.clear();

            ImageList.outputImages.forEach((eImageView) -> {
                toGeneratedIV.add(SwingFXUtils.fromFXImage(eImageView.getImage(), null));
            });

            BufferedImage genImg = BuildImage.buildImageLive(toGeneratedIV);
            GeneratedImage.setGeneratedImage(genImg);
            BuildCache.toBeGeneratedIV.setImage(SwingFXUtils.toFXImage(genImg, null));

        }).start();
    }
}
