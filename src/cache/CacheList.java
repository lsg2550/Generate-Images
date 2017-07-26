package cache;

import utils.drawing.DrawPreview;
import gui.DisplayGUICenterScrollPane;
import gui.DisplayGUIPreviewImageView;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class CacheList {

    //List of directories and their menus 
    private static final List<CacheBuild> CACHE_LIST = new LinkedList<CacheBuild>();

    //List of Selected Images
    private static final List<ImageView> SELECTED_IMAGES = new LinkedList<ImageView>();

    protected static void cleanup() {
        Platform.runLater(() -> {
            DisplayGUIPreviewImageView.setImageForImageView(null);
            DisplayGUICenterScrollPane.clearHBox();
        });

        SELECTED_IMAGES.clear();
        CACHE_LIST.clear();
    }

    protected static void remove(ImageView ivToRemove) {
        SELECTED_IMAGES.remove(ivToRemove);
    }

    protected static void add(ImageView ivToAdd) {
        SELECTED_IMAGES.add(ivToAdd);
    }

    protected static void clear() {
        SELECTED_IMAGES.clear();
    }

    public static Image draw() {
        return DrawPreview.draw(SELECTED_IMAGES);
    }

    protected static List<CacheBuild> getCACHE_LIST() {
        return CACHE_LIST;
    }

    public static List<ImageView> getSELECTED_IMAGES() {
        return SELECTED_IMAGES;
    }

}
