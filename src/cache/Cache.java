package cache;

import gui.DisplayCenterScrollPane;
import gui.DisplayPreviewImageView;
import gui.DisplayText;
import gui.DisplayProgressBar;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.image.Image;
import utils.benchmarking.MemoryUsage;

/**
 *
 * @author Luis
 */
public class Cache {

    private static final char[] ENGLISH_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z'};
    private static final ArrayList<CacheBuild> CACHE_LIST = new ArrayList<CacheBuild>(26);

    public void selectFolder(File[] selectedDirectory) {
        if (selectedDirectory != null) {
            new Thread(() -> {
                cleanup();
                processAndBuild(selectedDirectory);

                System.out.println("Memory Used: " + MemoryUsage.memoryUsageInMBytes() + "MB");
            }).start();
        }

        DisplayText.setUpdateText("No Directory Was Selected or the Current Directory is Already Selected!");
    }

    private void processAndBuild(File[] selectedDirectory) {
        /*Processes*/
        boolean cacheTypeAlreadyExists = false; //False = cacheType doesn't exist; True = cacheType exists
        DisplayText.setUpdateText("Loading Images...");
        double counter = 0.0;
        String filePath;

        for (File file : selectedDirectory) {
            DisplayProgressBar.setProgress(counter / (double) selectedDirectory.length);
            counter++;

            try {
                filePath = file.toURI().toURL().toExternalForm();
                //System.out.println("File Read: " + filePath); //Logging

                for (CacheBuild cacheType : CACHE_LIST) {
                    if (cacheType.getIdentifier() == getListDir(file.getName())) {
                        cacheType.getArrayListOfImages().add(new Image(filePath));
                        cacheTypeAlreadyExists = true;
                        break;
                    }
                }

                if (!cacheTypeAlreadyExists) {
                    CACHE_LIST.add(new CacheBuild(getListDir(file.getName()), new Image(filePath)));
                }

                cacheTypeAlreadyExists = false; //Resets boolean
            } catch (MalformedURLException ex) {
            }
        }

        try {
            DisplayText.setUpdateText("Building...");
            Thread.sleep(200);
        } catch (InterruptedException ex) {
        }

        /*Builds*/
        CACHE_LIST.stream().forEach((cacheType) -> {

            cacheType.buildCacheType();

            Platform.runLater(() -> {
                DisplayCenterScrollPane.addToHBox(cacheType.getRoot());
            });

        });

        DisplayProgressBar.setProgress(1);
        DisplayText.setUpdateText("Done!");
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

    private void cleanup() {
        Platform.runLater(() -> {
            DisplayPreviewImageView.setImageForImageView(null);
            DisplayCenterScrollPane.clearHBox();
        });

        DrawPreview.clear();
        CACHE_LIST.clear();
    }
}
