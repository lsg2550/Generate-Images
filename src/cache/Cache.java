package cache;

import gui.GUICenterScrollPane;
import gui.GUIProgressBar;
import gui.GUIText;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.image.Image;
import utils.parsing.TryParse;

/**
 *
 * @author Luis
 */
public class Cache implements Runnable {

    private final char[] ENGLISH_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z'};
    private final Object selectedDirectory;

    public Cache(Object selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    /**
     *
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (selectedDirectory != null) {
                //Garbage Collection
                CacheList.cleanup();

                //Update Text
                Platform.runLater(() -> {
                    GUIProgressBar.getpBar().setProgress(25.0);
                    GUIText.setUpdateText("Loading Images...");
                });

                //Processing
                if (TryParse.TryFileArray(selectedDirectory)) { //Single
                    try {
                        for (File file : (File[]) selectedDirectory) {
                            if (file.isDirectory()) { //Single will still read multiple directories so to prevent this check if the file is a directory itself.
                                continue;
                            }

                            boolean cacheTypeExists = false; //False = cacheType doesn't exist; True = cacheType exists;
                            String filePath = file.toURI().toURL().toExternalForm();

                            for (CacheBuild cacheType : CacheList.getCACHE_LIST()) {
                                if (cacheType.getCharIdentifier() == getListDir(file.getName())) {
                                    cacheType.getListOfImages().add(new Image(filePath));
                                    cacheTypeExists = true;
                                    break;
                                }
                            }

                            if (!cacheTypeExists) {
                                CacheList.getCACHE_LIST().add(new CacheBuild(getListDir(file.getName()), new Image(filePath)));
                            }
                        }
                    } catch (MalformedURLException ex) {
                    }
                } else if (TryParse.TryListOfFileArray(selectedDirectory)) { //Multiple
                    for (File[] files : (List<File[]>) selectedDirectory) {
                        CacheList.getCACHE_LIST().add(new CacheBuild(files));
                    }
                }

                //Test for User Cancellation
                try {
                    GUIProgressBar.getpBar().setProgress(50.0);
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Platform.runLater(() -> {
                        GUIProgressBar.getpBar().setProgress(0.0);
                        GUIText.setUpdateText("Process Halted");
                        GUIText.setDirectoryText(""); //Because the check if the current directory is already selected will trigger even if load was canceled
                    });
                    return;
                }

                //Builds
                CacheList.getCACHE_LIST().stream().forEach((cacheType) -> {
                    cacheType.buildCacheType();
                    Platform.runLater(() -> {
                        GUICenterScrollPane.addToHBox(cacheType.getRoot());
                    });
                });

                //DONE
                Platform.runLater(() -> {
                    GUIProgressBar.getpBar().setProgress(1);
                    GUIText.setUpdateText("Done!");
                });
                return;
            } else {
                Platform.runLater(() -> {
                    GUIText.setUpdateText("No Directory Was Selected - Current Directory is Already Selected");
                });

                return;
            }
        }
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
