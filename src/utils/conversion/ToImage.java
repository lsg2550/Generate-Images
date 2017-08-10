package utils.conversion;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import javafx.scene.image.Image;
import utils.math.MI;

/**
 *
 * @author Luis
 */
public class ToImage {

    /**
     *
     * @param listOfFiles
     * @return
     */
    public static Image[][] convertListOfFileArray(List<File[]> listOfFiles) {
        //Create Image 2D Array
        Image[][] batch = new Image[listOfFiles.size()][MI.maximumInteger_fromListOfArray(listOfFiles)];
        System.out.println("Batch Matrix Rows: " + batch.length + System.lineSeparator() + "Batch Matrix Columns: " + batch[0].length);

        //Create Image Arrays and Convert File to Images
        for (int i = 0; i < listOfFiles.size(); i++) {
            //Create Image Array
            Image[] listOfImages = new Image[batch[0].length];

            //Convert File to Image
            for (int j = 0; j < listOfFiles.get(i).length; j++) {
                try {
                    //String url = listOfFiles.get(i)[j].toURI().toURL().toExternalForm();
                    //System.out.println("File URL: " + url);

                    listOfImages[j] = new Image(listOfFiles.get(i)[j].toURI().toURL().toExternalForm());
                } catch (MalformedURLException e) {
                    return null;
                }
            }

            //Add Array to Batch
            batch[i] = listOfImages;

            //Cleanup
            listOfImages = null;
        }

        return batch;
    }

    /**
     * @param listOfFiles
     * @return an array of Images
     */
    public static Image[] convertFileArray(File[] listOfFiles) {
        //Create Image Array
        Image[] listOfImages = new Image[listOfFiles.length];

        //Convert Files to Images
        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                //String url = listOfFiles[i].toURI().toURL().toExternalForm();
                //System.out.println("File URL: " + url);

                listOfImages[i] = new Image(listOfFiles[i].toURI().toURL().toExternalForm());
            } catch (MalformedURLException e) {
                return null;
            }
        }

        //Return
        return listOfImages;
    }

}
