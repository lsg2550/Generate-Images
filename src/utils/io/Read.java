package utils.io;

import gui.GUIText;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
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

            if (selectedDirectory == null) {
                return null;
            }

            //Reading
            File[] selectedDirectoryFiles = null;
            if (!selectedDirectory.getAbsolutePath().equals(GUIText.getDirectoryText())) {
                selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                        || name.toLowerCase().endsWith(".jpeg")
                );
            }

            //GUI
            DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
            GUIText.setDirectoryText(selectedDirectory.getAbsolutePath());

            //Return
            return selectedDirectoryFiles;
        } else { //Multiple
            File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

            if (selectedDirectory == null) {
                return null;
            }

            //Reading
            List<File> files = Arrays.asList(selectedDirectory.listFiles());
            List<File[]> readImages = new LinkedList<>();
            files.stream().filter((file) -> (file.isDirectory())).forEachOrdered((file) -> {
                File[] temp = file.listFiles((File filesFromDirectory, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                        || name.toLowerCase().endsWith(".jpeg")
                );

                if (!Arrays.asList(temp).isEmpty()) {
                    readImages.add(temp);
                }
            });

            //GUI
            DIRECTORY_CHOOSER.setInitialDirectory(files.get(0).getParentFile().getParentFile());
            GUIText.setDirectoryText(files.get(0).getParentFile().getAbsolutePath());

            //Return
            return readImages;
        }
    }
}
