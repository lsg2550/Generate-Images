package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class GUIPreviewImageView { //USED BY CLASSES OUTSIDE PACKAGE

    private static final ImageView PREVIEW_IMAGEVIEW = new ImageView();

    static void init() {
        //PREVIEW_IMAGEVIEW.fitHeightProperty().bind(PREVIEW_IMAGEVIEW.getParent().getScene().heightProperty());
        //PREVIEW_IMAGEVIEW.fitWidthProperty().bind(PREVIEW_IMAGEVIEW.getParent().getScene().widthProperty());
        PREVIEW_IMAGEVIEW.setFitHeight(235);
        PREVIEW_IMAGEVIEW.setFitWidth(235);
    }

    /*Accessible only by inside package - GUI purposes*/
    static ImageView getPREVIEW_IMAGEVIEW() {
        return PREVIEW_IMAGEVIEW;
    }

    static boolean containsImage() {
        return PREVIEW_IMAGEVIEW.getImage() != null;
    }

    static Image getImageFromImageView() {
        return PREVIEW_IMAGEVIEW.getImage();
    }

    /*Accessible by other classes - In and outside package*/
    public static void setImageForImageView(Image image) {
        PREVIEW_IMAGEVIEW.setImage(image);
    }

}
