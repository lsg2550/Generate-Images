package utils.io;

import gui.DisplayPreviewImageView;
import gui.DisplayText;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class IO {

    //Selecting Directory & Saving File
    private static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();
    private static final FileChooser FILE_CHOOSER = new FileChooser();

    public static void init() {
        FILE_CHOOSER.setTitle("Save Image");
        FILE_CHOOSER.setInitialDirectory(new File(System.getProperty("user.home")));
        FILE_CHOOSER.getExtensionFilters().add(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
    }

    public static File[] readDirectory() {
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
    }

    public static List<File[]> readMultipleDirectories() {
        try {
            List<File> selectedDirectory = Arrays.asList(DIRECTORY_CHOOSER.showDialog(null).listFiles());
            List<File[]> readImages = new ArrayList<>(10);

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
            }
            return readImages;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static void saveFile() {
        try {
            File saveFile = FILE_CHOOSER.showSaveDialog(null);

            if (saveFile != null) {
                FILE_CHOOSER.setInitialDirectory(saveFile.getParentFile());
                ImageIO.write(SwingFXUtils.fromFXImage(DisplayPreviewImageView.getImageFromImageView(), null),
                        saveFile.toString().substring(saveFile.toString().length() - 3),
                        saveFile
                );
            }

        } catch (NullPointerException | IOException | IllegalArgumentException ex) {
            DisplayText.setUpdateText("Nothing to Save!");
        }
    }

}
