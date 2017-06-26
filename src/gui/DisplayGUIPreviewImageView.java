package gui;

import cache.CacheList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class DisplayGUIPreviewImageView { //USED BY CLASSES OUTSIDE PACKAGE

    private static final ImageView PREVIEW_IMAGEVIEW = new ImageView();

    protected static void init() {
        PREVIEW_IMAGEVIEW.setFitHeight(235);
        PREVIEW_IMAGEVIEW.setFitWidth(235);
    }

    /*Accessible only by inside package - GUI purposes*/
    protected static ImageView getPREVIEW_IMAGEVIEW() {
        return PREVIEW_IMAGEVIEW;
    }

    /*Accessible by other classes - In and outside package*/
    public static boolean containsImage() {
        return PREVIEW_IMAGEVIEW.getImage() != null; //True, contains image; False, does not contain image
    }

    public static Image getImageFromImageView() {
        return PREVIEW_IMAGEVIEW.getImage();
    }

    //Sets Image from current selected images
    public static void setImageForImageView() {
        PREVIEW_IMAGEVIEW.setImage(CacheList.draw());
    }

    //Sets Image directly
    public static void setImageForImageView(Image image) {
        PREVIEW_IMAGEVIEW.setImage(image);
    }

}
