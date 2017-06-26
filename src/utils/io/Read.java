package utils.io;

import gui.DisplayGUIText;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.stage.DirectoryChooser;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
public class Read {

    //Selecting Directory & Saving File
    private static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();

    public static Object readDirectory() {
        if (Settings.loadType) { //Single
            File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);
            File[] selectedDirectoryFiles = null;

            if (selectedDirectory != null /*Directory Selected*/ && !selectedDirectory.getAbsolutePath().equals(DisplayGUIText.getDirectoryText()) /*Directory is not the current one*/) {
                DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
                DisplayGUIText.setDirectoryText(selectedDirectory.getAbsolutePath());

                selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                );
            }

            return selectedDirectoryFiles;
        } else { //Multiple
            File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);
            List<File> files;
            List<File[]> readImages = new ArrayList<>(10);

            if (selectedDirectory != null) {
                files = Arrays.asList(selectedDirectory.listFiles());
            } else {
                files = null;
            }

            if (files != null) {
                DIRECTORY_CHOOSER.setInitialDirectory(files.get(0).getParentFile().getParentFile());
                DisplayGUIText.setDirectoryText(files.get(0).getParentFile().getAbsolutePath());

                files.stream().filter((file) -> (file.isDirectory())).forEachOrdered((file) -> {
                    File[] temp = file.listFiles((File filesFromDirectory, String name)
                            -> name.toLowerCase().endsWith(".png")
                            || name.toLowerCase().endsWith(".jpg")
                    );
                    if (!Arrays.asList(temp).isEmpty()) {
                        readImages.add(temp);
                    }
                });
            } else {
                return null;
            }

            return readImages;
        }
    }
}
