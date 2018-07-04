package utils.imagegeneration;

import gui.GUICenterScrollPane;
import gui.GUIProgressBar;
import gui.GUIText;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.image.Image;
import utils.operations.parsing.ListDirectoryIdentifier;
import utils.operations.parsing.TryParse;

/**
 *
 * @author Luis
 */
public class Cache implements Runnable {

    private final Object selectedDirectory;

    public Cache(Object selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (selectedDirectory != null) {
                //Garbage Collection
                CacheList.cleanup();

                //Update Text
                Platform.runLater(() -> {
                    GUIProgressBar.getpBar().setProgress(0.25);
                    GUIText.setUpdateText("Loading Images...");
                });

                //Processing
                if (TryParse.TryFileArray(selectedDirectory)) { //Single Directory
                    try {
                        for (File file : (File[]) selectedDirectory) {
                            if (file.isDirectory()) { //Single will still read multiple directories so to prevent this check if the file is a directory itself.
                                continue;
                            }

                            boolean cacheTypeExists = false; //False = cacheType doesn't exist; True = cacheType exists;
                            String filePath = file.toURI().toURL().toExternalForm();

                            for (CacheBuild cacheType : CacheList.getCACHE_LIST()) {
                                if (cacheType.getCharIdentifier() == ListDirectoryIdentifier.getListDir(file.getName())) {
                                    cacheType.getListOfImages().add(new Image(filePath));
                                    cacheTypeExists = true;
                                    break;
                                }
                            }

                            if (!cacheTypeExists) {
                                CacheList.getCACHE_LIST().add(new CacheBuild(ListDirectoryIdentifier.getListDir(file.getName()), new Image(filePath)));
                            }
                        }
                    } catch (MalformedURLException ex) {
                        return;
                    }
                } else if (TryParse.TryListOfFileArray(selectedDirectory)) { //Multiple Directories
                    for (File[] files : (List<File[]>) selectedDirectory) {
                        CacheList.getCACHE_LIST().add(new CacheBuild(files));
                    }
                }

                //Test for User Cancellation
                try {
                    GUIProgressBar.getpBar().setProgress(0.50);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Platform.runLater(() -> {
                        GUIProgressBar.getpBar().setProgress(0);
                        GUIText.setUpdateText("Process Halted");
                        GUIText.setDirectoryText(""); //Because the check if the current directory is already selected will trigger even if load was canceled
                    });
                    return;
                }

                //Builds
                for (CacheBuild cacheType : CacheList.getCACHE_LIST()) {
                    cacheType.buildCacheType();

                    Platform.runLater(() -> {
                        GUICenterScrollPane.addToHBox(cacheType.getRoot());
                    });
                }

                //DONE
                Platform.runLater(() -> {
                    GUIProgressBar.getpBar().setProgress(1.0);
                    GUIText.setUpdateText("Done!");
                });

                return;
            } else {
                Platform.runLater(() -> {
                    GUIText.setUpdateText("No Directory Was Selected or Current Directory is Already Selected");
                });

                return;
            }
        }
    }
}
