package cache;

import gui.DisplayCenterScrollPane;
import gui.DisplayPreviewImageView;
import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author Luis
 */
class CacheList {

    private static final ArrayList<CacheBuild> CACHE_LIST = new ArrayList<CacheBuild>(26);

    protected static ArrayList<CacheBuild> getCACHE_LIST() {
        return CACHE_LIST;
    }

    protected static void cleanup() {
        Platform.runLater(() -> {
            DisplayPreviewImageView.setImageForImageView(null);
            DisplayCenterScrollPane.clearHBox();
        });

        DrawPreview.clear();
        CACHE_LIST.clear();
    }
}
