package utils.operations;

import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import utils.cache.BuildCache;
import utils.notifications.AlertBox;

/**
 *
 * @author Luis
 */
public class Chooser {

    //Selecting Directory
    private static DirectoryChooser dChooser = new DirectoryChooser();

    //Saving File
    private static FileChooser filechooser = new FileChooser();

    //File
    private static File saveFile;

    public static void init() {
        filechooser.setTitle("Save Image");
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

    public static File readDirectory() {
        File selectedDirectory = dChooser.showDialog(null);

        if (selectedDirectory != null) {
            return selectedDirectory;
        } else { //Directory was Selected
            return null;
        }
    }

    public static void writeFile() {
        saveFile = filechooser.showSaveDialog(null);
        IO.writeImage(SwingFXUtils.fromFXImage(BuildCache.getToBeGeneratedIV().getImage(), null), saveFile);
    }

    public static File getSaveFile() {
        return saveFile;
    }
}
