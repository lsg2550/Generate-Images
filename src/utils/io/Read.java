package utils.io;

import gui.DisplayText;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Luis
 */
public class Read {

    //Selecting Directory & Saving File
    private static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();

    public static Object readDirectory(boolean type) {
        if (type) {
            File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);
            File[] selectedDirectoryFiles = null;

            if (selectedDirectory != null /*Directory Selected*/ && !selectedDirectory.getAbsolutePath().equals(DisplayText.getDirectoryText()) /*Directory is not the current one*/) {
                DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
                DisplayText.setDirectoryText(selectedDirectory.getAbsolutePath());

                selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                );
            }

            return selectedDirectoryFiles;
        } else {
            File[] directory = DIRECTORY_CHOOSER.showDialog(null).listFiles();
            List<File> selectedDirectory;
            List<File[]> readImages = new ArrayList<>(10);

            if (directory != null) {
                selectedDirectory = null;
            } else {
                selectedDirectory = Arrays.asList(directory);
            }

            if (selectedDirectory != null) {
                DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.get(0).getParentFile().getParentFile());
                DisplayText.setDirectoryText(selectedDirectory.get(0).getParentFile().getAbsolutePath());

                selectedDirectory.stream().filter((file) -> (file.isDirectory())).forEachOrdered((file) -> {
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
