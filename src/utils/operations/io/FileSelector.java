package utils.operations.io;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

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
    static File saveFile, selectedDirectory;

    public static void init() {
        filechooser.setTitle("Save Image");
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

    public static File readDirectory() {
        selectedDirectory = dChooser.showDialog(null);

        if (selectedDirectory != null) { //Directory was Selected
            setOpenDirectory();
            return selectedDirectory;
        } else {
            return null;
        }
    }

    public static void setOpenDirectory() {
        dChooser.setInitialDirectory(selectedDirectory.getParentFile());
    }

    public static void setSaveDirectory() {
        filechooser.setInitialDirectory(saveFile.getParentFile());
    }
}
