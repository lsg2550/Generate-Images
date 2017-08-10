package utils.batch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import utils.drawing.DrawPreview;
import utils.io.Save;
import utils.math.MPC;

/**
 *
 * @author Luis
 */
public class Batch {

    //Initial Variables
    private Image[][] subDirectories;
    private Image baseImage;

    //Generated During Batch Process
    private List<Image> imageListToBuild;
    private List<Image> tempImageList = new LinkedList<>();

    public Batch(Image baseImage, Image[][] subDirectories) {
        this.baseImage = baseImage;
        this.subDirectories = subDirectories;
        imageListToBuild = new ArrayList<>(MPC.maximumPossibleCombination_From2DArray(subDirectories, -1));

        batch(null);
    }

    public Batch(Image[] baseDirectory, Image[][] subDirectories) {
        this.subDirectories = subDirectories;
        imageListToBuild = new ArrayList<>(MPC.maximumPossibleCombination_From2DArray(subDirectories, baseDirectory.length));

        for (Image image : baseDirectory) {
            batch(image);
        }
    }

    public void batchSave() {
        //Save Images
        System.out.println("ImageList To Build Size: " + imageListToBuild.size());
        for (Image image : imageListToBuild) {
            Save.batchSave(image);
        }

        //Clean Up
        imageListToBuild = null;
        baseImage = null;
        subDirectories = null;
        System.gc();
    }

    private void batch(Image base) {
        //Set Base Image - We WANT the base image to be the first index
        if (base == null) {
            tempImageList.add(0, baseImage);
        } else {
            tempImageList.add(0, base);
        }

        //Batch
        batchSecondary(0);

        //Reset
        tempImageList.clear();
    }

    private void batchSecondary(int rowIndex) {
        for (Image subDirectory : subDirectories[rowIndex]) {
            if (subDirectory != null) {
                try {
                    tempImageList.set(rowIndex + 1, subDirectory);
                } catch (IndexOutOfBoundsException e) {
                    tempImageList.add(rowIndex + 1, subDirectory);
                }

                imageListToBuild.add(DrawPreview.drawFromImageList(tempImageList));

                //Check if more rows exist, if they do move on to the next row, o/w move on to the next column (if any)
                if (rowIndex + 1 < subDirectories.length) {
                    System.out.println((subDirectories.length - rowIndex + 1) + " Rows Remaining - Continuing to Next Row...");
                    batchSecondary(rowIndex + 1);
                }

                System.out.println("No More Rows Exist..." + System.lineSeparator() + "Continuing to Next Column...");
            }
        }
    }
}
