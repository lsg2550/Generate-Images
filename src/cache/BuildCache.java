package cache;

import gui.CenterDisplay;
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
public class BuildCache {

    private final char[] ENGLISH_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z'};
    private final ArrayList<CacheType> CACHE_LIST = new ArrayList(26);

    public void selectFolder(File[] selectedDirectory) {
        if (selectedDirectory != null) {
            new Thread(() -> {
                grab(selectedDirectory);
                build();

                logging();
                return;
            }).start();
        }
    }

    private void grab(File[] selectedDirectory) {
        boolean cacheTypeAlreadyExists = false; //False = cacheType doesn't exist; True = cacheType exists
        String filePath;
        cleanup();

        for (File file : selectedDirectory) {
            try {
                filePath = file.toURI().toURL().toExternalForm();
                System.out.println("File Read: " + filePath); //Logging

                for (CacheType cacheType : CACHE_LIST) {
                    if (cacheType.getIdentifier() == getListDir(file.getName())) {
                        cacheType.getArrayListOfImages().add(new Image(filePath));
                        cacheTypeAlreadyExists = true;
                        break;
                    }
                }

                if (!cacheTypeAlreadyExists) {
                    CACHE_LIST.add(new CacheType(getListDir(file.getName()), new Image(filePath)));
                }

                cacheTypeAlreadyExists = false; //Resets
            } catch (MalformedURLException ex) {
            }
        }
    }

    private void build() {
        CACHE_LIST.stream().forEach((cacheType) -> {
            System.out.println("CacheList contents: " + cacheType.getIdentifier()
                    + "\nSize of CacheType: " + cacheType.getArrayListOfImages().size()); //Logging

            //Building
            cacheType.buildCacheType();
            Platform.runLater(() -> {
                CenterDisplay.IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX.getChildren().add(cacheType.getRoot());
            });
        });
    }

    private char getListDir(String fileName) {
        fileName = fileName.toLowerCase().substring(0, fileName.length() - 4);

        for (char letter : ENGLISH_ALPHABET) {
            for (int i = fileName.length() - 1; i >= 0; i--) {
                if (fileName.charAt(i) == letter) {
                    //System.out.println("Character Found: " + letter + ".\nFor filename: " + fileName); //Logging
                    return letter;
                }
            }
        }

        return ' ';
    }

    private void cleanup() {
        Platform.runLater(() -> {
            CenterDisplay.IMAGEVIEWS_INSIDE_SCROLLPANE_HBOX.getChildren().clear();
        });
        SelectedImageList.selectedImages.clear();
        CACHE_LIST.clear();
    }

    private void logging() {
        System.out.println("Size of CacheList: " + CACHE_LIST.size());
        System.out.println("Memory Used in MB: " + MemoryUsage.memoryUsageInMBytes() + "MB");
    }
}
