package building;

import assets.ico.Icon;
import gfx.gui.GUI;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static building.ImageList.toGeneratedIV;
import building.stages.StageBuilds;
import building.stages.StageList;
import javafx.scene.image.Image;
import utils.operations.enhancednodes.EnhancedButton;
import utils.operations.enhancednodes.EnhancedImageView;
import utils.operations.io.IO;

/**
 *
 * @author Luis
 */
public class Handlers {

    private static Stage stage = new Stage();

    public static void init() {
        stage.getIcons().add(Icon.ICON);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(GUI.getScene().getWindow());
        stage.setOnCloseRequest(e -> {
            stage.setScene(null);
        });
    }

    //SetOnAction for Buttons
    public static void extendAndCreateSubMenu(ArrayList<Image> arrayOfImageSet, EnhancedImageView imageView, EnhancedButton extend) {
        new Thread(() -> {

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
            sp.prefHeightProperty().bind(scene.heightProperty());
            sp.prefWidthProperty().bind(scene.widthProperty());

            for (Image image : arrayOfImageSet) {
                StageBuilds sb = new StageBuilds(image);
                StageList.sbAL.add(sb);
                hb.getChildren().add(sb.getRoot());
            }

            extend.setOnAction(e -> {
                stage.setScene(scene);
                stage.show();
            });

        }).start();
    }

    //SetOnAction for the Checkbox
    //When the user uses the Clear All option from the operations menu, this will clear
    //all images and reset the checkboxes
    public static void enableDisable(EnhancedImageView eImageView, CheckBox enableDisable) {

        /*
        ListChangeListener lcl = new WeakListChangeListener(e -> {
            if (ImageList.outputImagesOL.isEmpty()) {
                enableDisable.setSelected(true);
            }
        });
        ImageList.outputImagesOL.addListener(lcl);
         */
        enableDisable.selectedProperty().addListener(e -> {
            if (enableDisable.isSelected()) {
                ImageList.outputImagesOL.remove(eImageView);
                eImageView.applyGrayscale();
            } else {
                ImageList.outputImagesOL.add(eImageView);
                eImageView.removeEffect();
            }
        });

        enableDisable.setSelected(true);
    }

    //When the user enables/disables an imageview this method will be called to 
    //update the "To Be Generated" image
    public static void imageViewListener(EnhancedImageView eImageView) {
        eImageView.effectProperty().addListener(e -> {
            liveImageBuilding();
        });
    }

    //When the user clicks on the ImageToBeGenerated a new window will open
    //to allow the user to clearly see the image
    static void imageViewStageBuilder(EnhancedImageView eImageView) {
        ImageView imageV = new ImageView();

        StackPane sp = new StackPane();
        sp.getChildren().add(imageV);
        sp.setAlignment(Pos.CENTER);

        Scene scene = new Scene(sp, 800, 600);

        eImageView.setOnMouseClicked(e -> {
            imageV.setImage(eImageView.getImage());
            stage.setScene(scene);
            stage.show();
        });
    }

    //This method is called whenever there is a change in the ImageViews in the
    //program. e.g. When they're enabled or disabled
    static void liveImageBuilding() {
        toGeneratedIV.clear();

        ImageList.outputImagesOL.forEach((eImageView) -> {
            Image imageViewImg = eImageView.getImage();

            BufferedImage bufferedImageImg = SwingFXUtils.fromFXImage(imageViewImg, null);
            toGeneratedIV.add(bufferedImageImg);

            //CleanUp
            bufferedImageImg.flush();
            bufferedImageImg = null;
            imageViewImg = null;
        });

        BufferedImage genImg = BuildImage.buildImageLive(toGeneratedIV);
        IO.bI = genImg;
        Image fxImg = SwingFXUtils.toFXImage(genImg, null);
        BuildCache.toBeGeneratedIV.setImage(fxImg);

        //CleanUp
        genImg.flush();
        genImg = null;
        fxImg = null;
    }
}
