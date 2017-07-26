package utils.io;

import gui.DisplayGUIText;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class Save {

    //Saving File
    private static final FileChooser FILE_CHOOSER = new FileChooser();

    public static void init() {
        FILE_CHOOSER.setTitle("Save Image");
        FILE_CHOOSER.setInitialDirectory(new File(System.getProperty("user.home")));
        FILE_CHOOSER.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
    }

    public static void saveFile(Image image) {
        try {
            File saveFile = FILE_CHOOSER.showSaveDialog(null);

            if (saveFile != null) {
                FILE_CHOOSER.setInitialDirectory(saveFile.getParentFile());

                ImageIO.write(SwingFXUtils.fromFXImage(image, null),
                        saveFile.toString().substring(saveFile.toString().length() - 3),
                        saveFile
                );
            }

        } catch (NullPointerException | IOException | IllegalArgumentException ex) {
            DisplayGUIText.setUpdateText("Nothing to Save!");
        }
    }
}
