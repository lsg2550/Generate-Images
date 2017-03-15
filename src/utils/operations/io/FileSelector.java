package utils.operations.io;

import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import building.BuildCache;

/**
 *
 * @author Luis
 */
public class FileSelector {

    //Selecting Directory
    private static DirectoryChooser dChooser = new DirectoryChooser();

    //Saving File
    static FileChooser filechooser = new FileChooser();

    //File
    static File saveFile;

    public static void init() {
        filechooser.setTitle("Save Image");
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

    public static File readDirectory() {
        File selectedDirectory = dChooser.showDialog(null);

        if (selectedDirectory != null) { //Directory was Selected
            return selectedDirectory;
        } else {
            return null;
        }
    }

    public static void setDirectory(File newPath) {
        dChooser.setInitialDirectory(newPath);
    }
}
