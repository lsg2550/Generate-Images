package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class DisplayPreviewImageView {

    protected final static ImageView PREVIEW_IMAGEVIEW = new ImageView();

    protected static void init() {
        PREVIEW_IMAGEVIEW.setFitHeight(235);
        PREVIEW_IMAGEVIEW.setFitWidth(235);
    }

    public static boolean containsImage() {
        return PREVIEW_IMAGEVIEW.getImage() != null; //True, contains image; False, does not contain image
    }

    public static Image getImageFromImageView() {
        return PREVIEW_IMAGEVIEW.getImage();
    }

    public static void setImageForImageView(Image image) {
        PREVIEW_IMAGEVIEW.setImage(image);
    }
}
