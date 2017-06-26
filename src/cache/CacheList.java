package cache;

import gui.DisplayGUICenterScrollPane;
import gui.DisplayGUIPreviewImageView;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class CacheList {

    //ArrayList of directories and their menus - Default 26 as the program is meant for folders a-z
    private static final ArrayList<CacheBuild> CACHE_LIST = new ArrayList<CacheBuild>(26);

    //ArrayList of Selected Images
    private static final ArrayList<ImageView> SELECTED_IMAGES = new ArrayList<ImageView>(30);

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

    protected static ArrayList<CacheBuild> getCACHE_LIST() {
        return CACHE_LIST;
    }

    public static ArrayList<ImageView> getSELECTED_IMAGES() {
        return SELECTED_IMAGES;
    }

}
