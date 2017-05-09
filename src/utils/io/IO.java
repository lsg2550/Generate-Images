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
    private static DirectoryChooser dChooser = new DirectoryChooser();
    private static FileChooser filechooser = new FileChooser();

    public static void init() {
        filechooser.setTitle("Save Image");
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public static File[] readDirectoryListOfFiles() {
        File selectedDirectory = dChooser.showDialog(null);
        File[] selectedDirectoryFiles = null;

        if (selectedDirectory != null && !selectedDirectory.getAbsolutePath().equals(DisplayText.getDirectoryText())) { //Directory was Selected
            dChooser.setInitialDirectory(selectedDirectory.getParentFile());
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
            File saveFile = filechooser.showSaveDialog(null);

            if (saveFile != null) {
                filechooser.setInitialDirectory(saveFile.getParentFile());
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
