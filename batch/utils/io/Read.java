package utils.io;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import static utils.io.IO.DIRECTORY_CHOOSER;

/**
 *
 * @author Luis
 */
public class Read {

    private final static FileChooser FILE_CHOOSER = new FileChooser();

    public static void init() {
        FILE_CHOOSER.setTitle("Select a Base Image");
        FILE_CHOOSER.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    }

    public static Image readSingleImage() {
        System.out.println("Selecting Base Image...");
        File selectedImage = FILE_CHOOSER.showOpenDialog(null);

        if (selectedImage == null) {
            throw new NullPointerException();
        }

        FILE_CHOOSER.setInitialDirectory(selectedImage.getParentFile().getParentFile());
        DIRECTORY_CHOOSER.setInitialDirectory(selectedImage.getParentFile().getParentFile());

        try {
            return new Image(selectedImage.toURI().toURL().toExternalForm());
        } catch (NullPointerException | MalformedURLException ex) {
            System.out.println("No Image Selected!");
            System.out.println("Returning Null...");
            return null;
        }
    }

    public static File[] readSingleDirectory() {
        System.out.println("Selecting Directory...");
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        File[] selectedDirectoryFiles = null;

        if (selectedDirectory != null) {
            DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());

            selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                    -> name.toLowerCase().endsWith(".png")
                    || name.toLowerCase().endsWith(".jpg")
                    || name.toLowerCase().endsWith(".jpeg")
            );
        }

        return selectedDirectoryFiles;
    }

    public static List<File[]> readMultipleDirectories() {
        System.out.println("Selecting Directory...");
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        if (selectedDirectory == null) {
            System.out.println("No Directory was Selected!");
            System.out.println("Returning Null...");
            return null;
        }

        System.out.println("Grabbing Folders from Directory...");
        List<File> directoryFiles = Arrays.asList(selectedDirectory.listFiles());
        List<File[]> readImages = new ArrayList<>(100);

        System.out.println("Setting Directory Chooser to Previous Location...");
        DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile().getParentFile());

        System.out.println("Grabbing Files from Each Directory...");
        for (File file : directoryFiles) {
            if (file.isDirectory()) {
                //Look for files ending in .png, .jpg, .jpeg
                File[] temp = file.listFiles((File filesFromDirectory, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                        || name.toLowerCase().endsWith(".jpeg")
                );

                //If the folder read is empty, don't add the empty File[] array to the arraylist
                if (!Arrays.asList(temp).isEmpty()) {
                    System.out.println(file.getName() + "'s contents (Size: " + temp.length + ") have been added to the list.");
                    readImages.add(temp);
                }
            }
        }

        return readImages;
    }
}
