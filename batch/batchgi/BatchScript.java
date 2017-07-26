package batchgi;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import utils.io.Save;

/**
 *
 * @author Luis
 */
public class BatchScript {

    //Initial Variables
    public static Image[][] batch;
    public static Image[] baseList;
    public static Image baseImage;

    //Generated During Batch Process
    private static ArrayList<Image> imageListToBuild;
    private static ArrayList<Image> tempImageList = new ArrayList<>(20);

    public static void genImage() {
        System.out.println("Generating Images...");

        if (baseImage == null) {
            for (int i = 0; i < baseList.length; i++) {
                //Set Base Layer
                tempImageList.add(0, baseList[i]);

                genImg2(0); //1 is Default

                //Reset
                tempImageList.clear();
            }
        }

        if (baseImage != null) {
            //Generate
            for (int i = 0; i < batch[0].length; i++) {
                if (batch[0][i] != null) {
                    //Set Base Image
                    tempImageList.add(0, baseImage);

                    //Set Second Layer
                    try {
                        tempImageList.set(1, batch[0][i]);
                    } catch (IndexOutOfBoundsException e) {
                        tempImageList.add(1, batch[0][i]);
                    }

                    //Run
                    if (batch.length != 1) {
                        genImg2(1);
                    } else {
                        imageListToBuild.add(DrawPreview.draw(tempImageList));
                    }

                    //Reset
                    tempImageList.clear();
                } else {
                    break;
                }
            }
        }

        //Save Images
        System.out.println("ImageList To Build Size: " + imageListToBuild.size());
        for (Image image : imageListToBuild) {
            Save.saveFile(image);
        }

        //Clean Up
        System.out.println("Cleaning Up...");
        for (Image image : imageListToBuild) {
            image = null;
        }

        for (Image image : baseList) {
            image = null;
        }

        imageListToBuild = null;
        baseImage = null;
        baseList = null;
        batch = null;
        System.gc();

        //Done
        System.out.println("Done!");
    }

    private static void genImg2(int rowIndex) {
        for (int columnIndex = 0; columnIndex < batch[rowIndex].length; columnIndex++) {
            if (batch[rowIndex][columnIndex] != null) {
                try {
                    tempImageList.set(rowIndex + 1, batch[rowIndex][columnIndex]);
                } catch (IndexOutOfBoundsException e) {
                    tempImageList.add(rowIndex + 1, batch[rowIndex][columnIndex]);
                }

                //System.out.println("Position: (" + (rowIndex - 1) + ", " + columnIndex + ")");
                //Are there are more rows in batch?
                if (rowIndex + 1 < batch.length) {
                    System.out.println("More Rows Exist...");
                    System.out.println((batch.length - rowIndex) + " Rows Remaining.");
                    System.out.println("Continuing...");

                    //RECURSE THROUGH genImg2()
                    genImg2(rowIndex + 1);
                } else {
                    System.out.println("No More Rows Exist...");
                    System.out.println("Continuing to Next Column...");
                    
                    imageListToBuild.add(DrawPreview.draw(tempImageList));
                }
            }
        }
    }

    public static void convertFiletoImage_ListFileArray(List<File[]> listOfFiles) throws MalformedURLException {
        //Calc Max Array Length
        System.out.println("Calculating Max Array Size...");
        int largestArraySize = 0;

        for (File[] listOfFile : listOfFiles) {
            //MainVBButtons.buildProgressBar.setProgress((double) (listOfFiles.indexOf(listOfFile) / listOfFiles.size()));
            if (listOfFile.length >= largestArraySize) {
                largestArraySize = listOfFile.length;
            }
        }

        //Create Batch 2D Array
        System.out.println("Creating Batch Matrix...");
        batch = new Image[listOfFiles.size()][largestArraySize];
        System.out.println("Batch Matrix Rows: " + listOfFiles.size());
        System.out.println("Batch Matrix Columns: " + largestArraySize);

        //Clear Old Data
        System.out.println("Clearing Old Data...");
        if (imageListToBuild != null) {
            imageListToBuild.clear();
        }

        //Maximum Possible Combinations
        int mpc = 1;

        //Create Image Arrays and Convert File to Images
        for (int i = 0; i < listOfFiles.size(); i++) {
            //MainVBButtons.buildProgressBar.setProgress((double) (i / listOfFiles.size()));

            //Create Image Array
            System.out.println("Creating Image Array...");
            Image[] listOfImages = new Image[largestArraySize];

            //Convert File to Image
            System.out.println("Converting File to Image...");
            for (int j = 0; j < listOfFiles.get(i).length; j++) {
                String url = listOfFiles.get(i)[j].toURI().toURL().toExternalForm();
                System.out.println("File URL: " + url);

                listOfImages[j] = new Image(url);
            }

            //Display Size of Array vs Content in Array
            System.out.println("Size of Current Array: " + listOfImages.length);
            for (int j = 0; j < listOfImages.length; j++) {
                if (listOfImages[j] == null) {
                    System.out.println("Amount of Content of Current Array: " + j);
                    mpc *= j;
                    break;
                }
                if (j == listOfImages.length - 1) {
                    System.out.println("Amount of Content of Current Array: " + listOfImages.length);
                    mpc *= listOfImages.length;
                    break;
                }
            }

            //Max Possible Combination
            if (baseList != null) {
                mpc *= baseList.length;
            }
            System.out.println("Maximum Possible Combinations: " + mpc);

            //Add Array to Batch
            System.out.println("Adding Array to Batch...");
            batch[i] = listOfImages;

            //Create List
            imageListToBuild = new ArrayList<>(mpc);

            //Cleanup
            listOfImages = null;
        }
    }

    public static void convertFiletoImage_FileArray(File[] listOfFiles) throws MalformedURLException {
        //Convert File to Image
        Image[] listOfImages = new Image[listOfFiles.length];

        System.out.println("Converting File to Image...");
        for (int i = 0; i < listOfFiles.length; i++) {
            String url = listOfFiles[i].toURI().toURL().toExternalForm();
            System.out.println("File URL: " + url);

            listOfImages[i] = new Image(url);
        }

        //Add Array to BaseList
        baseList = listOfImages;

        //Cleanup
        listOfImages = null;
    }
}
