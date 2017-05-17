package cache;

import gui.DisplayCenterScrollPane;
import gui.DisplayProgressBar;
import gui.DisplayText;
import java.io.File;
import java.util.List;
import javafx.application.Platform;
import utils.benchmarking.MemoryUsage;
import utils.parse.TryParse;

/**
 *
 * @author Luis
 */
public class CacheMultiple {

    public void selectFolder(Object selectedObjects) {
        if (TryParse.TryListOfFileArray(selectedObjects) && (List<File[]>) selectedObjects != null) {
            DisplayText.setUpdateText("Loading Images...");

            new Thread(() -> {
                CacheList.cleanup();
                processAndBuild((List<File[]>) selectedObjects);

                System.out.println("Memory Used: " + MemoryUsage.memoryUsageInMBytes() + "MB");
            }).start();
        } else {
            DisplayText.setUpdateText("No Directory Was Selected/Current Directory is Already Selected/Directory Selected Contains Directories");
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
