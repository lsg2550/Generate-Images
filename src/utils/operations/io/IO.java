package utils.operations.io;

import gui.GUIText;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
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
        FILE_CHOOSER.setInitialDirectory(new File(System.getProperty("user.home")));
        FILE_CHOOSER.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );

        DIRECTORY_CHOOSER.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    /**
     * For the given directory, there is an array of images
     *
     * @return returns an array of images
     */
    public static File[] readDirectorySingle() {
        //Select Directory
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        //Check if SelectedDirectory is Null
        if (selectedDirectory == null) {
            return null;
        }

        //GUI
        DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
        GUIText.setDirectoryText(selectedDirectory.getAbsolutePath());

        //Get Single/Base Directory
        File[] selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                -> name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".jpg")
                || name.toLowerCase().endsWith(".jpeg")
        );

        //Return
        return selectedDirectoryFiles;
    }

    /**
     * For each directory, there are an array of images.
     *
     * Also, it is a method for selecting a directory which contains
     * sub-directories of images that will be used in the batch process to build
     * on top of a base image
     *
     * @return returns a list of arrays of images
     */
    public static List<File[]> readDirectoryMultiple() {
        //Select Directory
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        //Check if SelectedDirectory is Null
        if (selectedDirectory == null) {
            return null;
        }

        //GUI
        DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
        GUIText.setDirectoryText(selectedDirectory.getAbsolutePath());

        //Get Multiple Directories
        List<File> files = Arrays.asList(selectedDirectory.listFiles());
        List<File[]> selectedDirectoryFiles = new ArrayList<>(50);
        for (File file : files) {
            File[] temp = file.listFiles((File filesFromDirectory, String name)
                    -> name.toLowerCase().endsWith(".png")
                    || name.toLowerCase().endsWith(".jpg")
                    || name.toLowerCase().endsWith(".jpeg")
            );

            //If the folder temp is empty, don't add the empty File[] array to the arraylist
            if (temp != null) {
                selectedDirectoryFiles.add(temp);
            }
        }

        //Return
        return selectedDirectoryFiles;
    }

    /**
     * Method for selecting a BASE IMAGE + SUB-DIRECTORIES
     *
     * Selecting a Base Image followed by selecting a directory with multiple
     * directories to be used as sub images.
     *
     * @return Base Image (Image) + Sub Directories (File[])
     */
    public static Object[] readBatchBaseImage() {
        // Selecting Base Image
        FILE_CHOOSER.setTitle("Select a Base Image");
        File selectedImage = FILE_CHOOSER.showOpenDialog(null);

        //Check if SelectedImage is null
        if (selectedImage == null) {
            return null;
        }

        //GUI
        FILE_CHOOSER.setInitialDirectory(selectedImage.getParentFile());

        //Base Image + Sub-Directories Object Array
        Object[] bisdoa = new Object[2];

        //Set Base Image
        try {
            bisdoa[0] = new Image(selectedImage.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            return null;
        }

        //Get Sub-Directories
        bisdoa[1] = readDirectoryMultiple();

        //Check if the sub-directories selected is null
        if (bisdoa[1] == null) {
            return null;
        }

        //Return
        return bisdoa;
    }

    /**
     * Method for selecting a BASE DIRECTORY + SUB-DIRECTORIES
     *
     * Selecting a Base Directory with images that will be used as base images,
     * followed by selecting a directory with multiple directories to be used as
     * sub images.
     *
     * @return BASE DIRECTORY (File[] of Images) + Sub Directories (File[] of
     * Directories containing Images)
     */
    public static Object[] readBatchBaseDirectory() {
        //Select Base-Directory
        File[] selectedDirectoryFiles = readDirectorySingle();

        //Base Directory + Sub-Directories Object Array
        Object[] bdsdoa = new Object[2];

        //Set Base-Directory
        bdsdoa[0] = selectedDirectoryFiles;

        //Get Sub-Directories
        bdsdoa[1] = readDirectoryMultiple();

        //Check if the sub-directories selected is null
        if (bdsdoa[0] == null || bdsdoa[1] == null) {
            return null;
        }

        //Return
        return bdsdoa;
    }

    /**
     * Saves a single generated image
     *
     * @param image
     */
    public static void saveImage(Image image) {
        try {
            FILE_CHOOSER.setTitle("Save Image");
            File saveFile = FILE_CHOOSER.showSaveDialog(null);

            if (saveFile != null) {
                FILE_CHOOSER.setInitialDirectory(saveFile.getParentFile());
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), saveFile.toString().substring(saveFile.toString().length() - 3), saveFile);
            }
        } catch (NullPointerException | IOException | IllegalArgumentException ex) {
            GUIText.setUpdateText("Nothing to Save!");
        }
    }

    /**
     * Saves a list of images generated in a batch
     *
     * @param listOfImages
     */
    public static void saveBatch(List<Image> listOfImages) {
        File saveDirectory = new File("output/");
        saveDirectory.mkdirs();

        for (Image listOfImage : listOfImages) {
            int counter = 0;
            boolean fileExists = true;

            while (fileExists) {
                String imageName = "/output" + counter + ".png";
                File imageFileName = new File(saveDirectory.getAbsolutePath() + imageName);

                if (!imageFileName.exists()) {
                    System.out.println("Generating New Image In: " + imageFileName.getAbsolutePath());

                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(listOfImage, null), "png", imageFileName);
                    } catch (IOException ex) {
                        System.out.println("Issue Saving File!");
                    } finally {
                        fileExists = false;
                    }
                } else {
                    counter++;
                }
            }
        }
    }
}