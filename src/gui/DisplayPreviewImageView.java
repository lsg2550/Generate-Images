package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class DisplayPreviewImageView {

    protected final static ImageView toBeGeneratedIV = new ImageView();

    protected static void init() {
        toBeGeneratedIV.setFitHeight(235);
        toBeGeneratedIV.setFitWidth(235);
    }

    public static boolean containsImage() {
        return toBeGeneratedIV.getImage() != null; //True, contains image; False, does not contain image
    }

    public static Image getImageFromImageView() {
        return toBeGeneratedIV.getImage();
    }

    public static void setImageForImageView(Image image) {
        toBeGeneratedIV.setImage(image);
    }
}
