package cache;

import utils.drawing.DrawPreview;
import gui.GUICenterScrollPane;
import gui.GUIPreviewImageView;
import java.util.ArrayList;
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
    private static final List<CacheBuild> CACHE_LIST = new ArrayList<CacheBuild>(26);

    //List of Selected Images
    private static final List<ImageView> SELECTED_IMAGES = new ArrayList<ImageView>(50);

    static void cleanup() {
        Platform.runLater(() -> {
            GUIPreviewImageView.setImageForImageView(null);
            GUICenterScrollPane.clearHBox();
        });

        SELECTED_IMAGES.clear();
        CACHE_LIST.clear();
    }

    static void remove(ImageView ivToRemove) {
        SELECTED_IMAGES.remove(ivToRemove);
    }

    static void add(ImageView ivToAdd) {
        SELECTED_IMAGES.add(ivToAdd);
    }

    static void clear() {
        SELECTED_IMAGES.clear();
    }

    static Image draw() {
        return DrawPreview.drawFromImageViewList(SELECTED_IMAGES);
    }

    static List<CacheBuild> getCACHE_LIST() {
        return CACHE_LIST;
    }

    public static List<ImageView> getSELECTED_IMAGES() {
        return SELECTED_IMAGES;
    }

}
