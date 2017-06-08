package cache;

import gui.DisplayCenterScrollPane;
import gui.DisplayText;
import gui.DisplayProgressBar;
import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 *
 * @author Luis
 */
public class CacheSingle {

    private static final char[] ENGLISH_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z'};

    public void selectFolder(File[] selectedObjects) {
        if (selectedObjects != null) {
            Platform.runLater(() -> {
                DisplayText.setUpdateText("Loading Images...");
            });

            //new Thread(() -> {
            CacheList.cleanup();
            processAndBuild(selectedObjects);
            //System.out.println("Memory Used: " + MemoryUsage.memoryUsageInMBytes() + "MB"); //Logging
            //}).start();
        } else {
            Platform.runLater(() -> {
                DisplayText.setUpdateText("No Directory Was Selected - Current Directory is Already Selected - Directory Selected Only Contains Directories");
            });
        }
    }

    private void processAndBuild(File[] selectedDirectory) {
        /*Processes*/
        boolean cacheTypeAlreadyExists = false; //False = cacheType doesn't exist; True = cacheType exists
        double counter = 0.0;
        String filePath;

        for (File file : selectedDirectory) {
            DisplayProgressBar.setProgress(counter / (double) selectedDirectory.length);
            counter++;

            try {
                filePath = file.toURI().toURL().toExternalForm();
                //System.out.println("File Read: " + filePath); //Logging

                for (CacheBuild cacheType : CacheList.getCACHE_LIST()) {
                    if (cacheType.getArrayListCharIdentifier() == getListDir(file.getName())) {
                        cacheType.getArrayListOfImages().add(new Image(filePath));
                        cacheTypeAlreadyExists = true;
                        break;
                    }
                }

                if (!cacheTypeAlreadyExists) {
                    CacheList.getCACHE_LIST().add(new CacheBuild(getListDir(file.getName()), new Image(filePath)));
                }

                cacheTypeAlreadyExists = false; //Resets boolean
            } catch (MalformedURLException ex) {
            }
        }

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

    private char getListDir(String fileName) {
        fileName = fileName.toLowerCase().substring(0, fileName.length() - 4);

        for (char letter : ENGLISH_ALPHABET) {
            for (int i = fileName.length() - 1; i >= 0; i--) {
                if (fileName.charAt(i) == letter) {
                    return letter;
                }
            }
        }

        return ' ';
    }
}
