package cache;

import gui.DisplayCenterScrollPane;
import gui.DisplayProgressBar;
import gui.DisplayText;
import java.io.File;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author Luis
 */
public class CacheMultiple {

    public void selectFolder(List<File[]> selectedObjects) {
        if (selectedObjects != null) {
            Platform.runLater(() -> {
                DisplayText.setUpdateText("Loading Images...");
            });

            //new Thread(() -> {
            CacheList.cleanup();
            processAndBuild((List<File[]>) selectedObjects);

            //System.out.println("Memory Used: " + MemoryUsage.memoryUsageInMBytes() + "MB"); //Logging
            //}).start();
        } else {
            Platform.runLater(() -> {
                DisplayText.setUpdateText("No Directory Was Selected - Current Directory is Already Selected");
            });
        }
    }

    private void processAndBuild(List<File[]> selectedDirectories) {
        /*Processes*/
        selectedDirectories.forEach((selectedDirectory) -> {
            DisplayProgressBar.setProgress(selectedDirectories.indexOf(selectedDirectory) / selectedDirectories.size());
            CacheList.getCACHE_LIST().add(new CacheBuild(selectedDirectory));
        });

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
        }

        /*Builds*/
        CacheList.getCACHE_LIST().stream().forEach((cacheType) -> {
            cacheType.buildCacheType();

            Platform.runLater(() -> {
                DisplayCenterScrollPane.addToHBox(cacheType.getRoot());
            });
        });

        Platform.runLater(() -> {
            DisplayProgressBar.setProgress(1);
            DisplayText.setUpdateText("Done!");
        });
    }
}
