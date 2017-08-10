package utils.io;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Luis
 */
public class IO {

    //Selecting Directory & Saving File
    static final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();

    //Saving File
    static final FileChooser FILE_CHOOSER = new FileChooser();

    public static void init() {
        FILE_CHOOSER.setInitialDirectory(new File(System.getProperty("user.home")));
        FILE_CHOOSER.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
    }

}
