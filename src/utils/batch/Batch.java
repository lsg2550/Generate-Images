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

        batchPrimary(null);
    }

    public Batch(Image[] baseDirectory, Image[][] subDirectories) {
        this.subDirectories = subDirectories;
        imageListToBuild = new ArrayList<>(MPC.maximumPossibleCombination_From2DArray(subDirectories, baseDirectory.length));

        for (Image image : baseDirectory) {
            batchPrimary(image);
        }
    }

    public void batchSave() {
        //Save Images
        for (Image image : imageListToBuild) {
            Save.batchSave(image);
        }

        //Clean Up
        imageListToBuild = null;
        baseImage = null;
        subDirectories = null;
    }

    private void batchPrimary(Image base) {
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
                    batchSecondary(rowIndex + 1);
                }
            }
        }
    }
}
