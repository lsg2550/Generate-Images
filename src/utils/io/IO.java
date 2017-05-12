package utils.io;

import gui.DisplayPreviewImageView;
import gui.DisplayText;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
        FILE_CHOOSER.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public static File[] readDirectoryListOfFiles() {
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);
        File[] selectedDirectoryFiles = null;

        if (selectedDirectory != null && !selectedDirectory.getAbsolutePath().equals(DisplayText.getDirectoryText())) { //Directory was Selected
            DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
            DisplayText.setDirectoryText(selectedDirectory.getAbsolutePath());

            selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                    -> name.toLowerCase().endsWith(".png")
                    || name.toLowerCase().endsWith(".jpg")
            );
        }

        return selectedDirectoryFiles;
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
