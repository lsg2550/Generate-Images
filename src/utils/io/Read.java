package utils.io;

import gui.GUIText;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import static utils.io.IO.DIRECTORY_CHOOSER;
import static utils.io.IO.FILE_CHOOSER;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
public class Read {

    /**
     * Will prompt the user to select a directory, depending on the settings set
     * by the user in the application it will read the directories as a single
     * directory with files inside or a directory with more directories inside,
     * in which case will it search for files inside those sub-directories
     *
     * @return a single directory of images, or multiple directories of images
     */
    public static Object read() {
        //Select Directory
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        //Check if SelectedDirectory is Null
        if (selectedDirectory == null) {
            return null;
        }

        //GUI
        DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());
        GUIText.setDirectoryText(selectedDirectory.getAbsolutePath());

        if (Settings.loadType) { //Reading Single
            //Reading
            File[] selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                    -> name.toLowerCase().endsWith(".png")
                    || name.toLowerCase().endsWith(".jpg")
                    || name.toLowerCase().endsWith(".jpeg")
            );

            //Return
            return selectedDirectoryFiles;
        } else { //Reading Multiple
            List<File> files = Arrays.asList(selectedDirectory.listFiles());
            List<File[]> selectedDirectoryFiles = new ArrayList<>(50);

            for (File file : files) {
                File[] temp = file.listFiles((File filesFromDirectory, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                        || name.toLowerCase().endsWith(".jpeg")
                );

                //If the folder read is empty, don't add the empty File[] array to the arraylist
                if (temp != null) {
                    selectedDirectoryFiles.add(temp);
                }
            }

            //Return
            return selectedDirectoryFiles;
        }
    }

    /**
     * Method for selecting a BASE IMAGE + SUB-DIRECTORIES
     *
     * Selecting a Base Image followed by selecting a directory with multiple
     * directories to be used as sub images.
     *
     * @return Base Image (Image) + Sub Directories (File[])
     */
    public static Object[] batchReadBM() {
        //Base Image + Sub-Directories Object Array
        Object[] bisdoa = new Object[2];

        // Selecting Base Image
        FILE_CHOOSER.setTitle("Select a Base Image");
        File selectedImage = FILE_CHOOSER.showOpenDialog(null);

        //Check if SelectedImage is null
        if (selectedImage == null) {
            //System.out.println("No Image Selected!" + System.lineSeparator() + "Returning Null...");
            return null;
        }

        //GUI
        FILE_CHOOSER.setInitialDirectory(selectedImage.getParentFile());

        //Set Base Image
        try {
            bisdoa[0] = new Image(selectedImage.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            //System.out.println("Issue Reading Image..." + System.lineSeparator() + "Returning Null...");
            return null;
        }

        //Get Sub-Directories
        bisdoa[1] = batchReadMD();

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
    public static Object[] batchReadSM() {
        //Base Directory + Sub-Directories Object Array
        Object[] bdsdoa = new Object[2];

        //Select Base-Directory
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        //Check if SelectedDirectory is null
        if (selectedDirectory == null) {
            //System.out.println("No Directory Selected!" + System.lineSeparator() + "Returning Null...");
            return null;
        }

        //GUI
        DIRECTORY_CHOOSER.setInitialDirectory(selectedDirectory.getParentFile());

        //Get Base-Directory
        File[] selectedDirectoryFiles = selectedDirectory.listFiles((File file, String name)
                -> name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".jpg")
                || name.toLowerCase().endsWith(".jpeg")
        );

        //Set Base-Directory
        bdsdoa[0] = selectedDirectoryFiles;

        //Get Sub-Directories
        bdsdoa[1] = batchReadMD();

        //Check if the sub-directories selected is null
        if (bdsdoa[1] == null) {
            return null;
        }

        //Return
        return bdsdoa;
    }

    /**
     * Method for selecting a directory which contains sub-directories of images
     * that will be used in the batch process to build on top of a base image
     *
     * @return List<File[]> which is a list of images from the subdirectories of
     * the selected directory
     */
    private static List<File[]> batchReadMD() {
        //Selecting Sub-Directory
        File selectedDirectory = DIRECTORY_CHOOSER.showDialog(null);

        //Check if SelectedDirectory is null
        if (selectedDirectory == null) {
            //System.out.println("No Directory was Selected!" + System.lineSeparator() + "Returning Null...");
            return null;
        }

        //Grabbing Folders from Directory
        List<File> directoryFiles = Arrays.asList(selectedDirectory.listFiles());
        List<File[]> listOfImages = new ArrayList<>(50);

        //Grabbing Files from Each Directory
        for (File file : directoryFiles) {
            File[] temp = file.listFiles((File filesFromDirectory, String name)
                    -> name.toLowerCase().endsWith(".png")
                    || name.toLowerCase().endsWith(".jpg")
                    || name.toLowerCase().endsWith(".jpeg")
            );

            if (temp == null) {
                continue;
            }

            //If the folder read is empty, don't add the empty File[] array to the arraylist
            if (!Arrays.asList(temp).isEmpty()) {
                //System.out.println(file.getName() + "'s contents (Size: " + temp.length + ") have been added to the list.");
                listOfImages.add(temp);
            }
        }

        //Return
        return listOfImages;
    }
}
