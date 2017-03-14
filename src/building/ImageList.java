package building;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
class ImageList {

    //ArrayLists of Images, to avoid redundancy these will only be created once and re-used.
    static ArrayList<ArrayList<BufferedImage>> splitImgs = new ArrayList();
    static ArrayList<BufferedImage> imgA = new ArrayList(), //A-Z
            imgB = new ArrayList(),
            imgC = new ArrayList(),
            imgD = new ArrayList(),
            imgE = new ArrayList(),
            imgF = new ArrayList(),
            imgG = new ArrayList(),
            imgH = new ArrayList(),
            imgI = new ArrayList(),
            imgJ = new ArrayList(),
            imgK = new ArrayList(),
            imgL = new ArrayList(),
            imgM = new ArrayList(),
            imgN = new ArrayList(),
            imgO = new ArrayList(),
            imgP = new ArrayList(),
            imgQ = new ArrayList(),
            imgR = new ArrayList(),
            imgS = new ArrayList(),
            imgT = new ArrayList(),
            imgU = new ArrayList(),
            imgV = new ArrayList(),
            imgW = new ArrayList(),
            imgX = new ArrayList(),
            imgY = new ArrayList(),
            imgZ = new ArrayList(),
            toGeneratedIV = new ArrayList();
    static ArrayList<ImageView> outputImages = new ArrayList();

    //Clears the arraylists when loading a new directory
    static void clearCache() {
        splitImgs.forEach((splitImg) -> {
            splitImg.clear();
        });
        splitImgs.clear();
        outputImages.clear();
    }

    //Moves all arraylists into the main splitImgs arraylist
    static void addToCache() {
        splitImgs.addAll(Arrays.asList(imgA, imgB, imgC, imgD, imgE, imgF, imgG,
                imgH, imgI, imgJ, imgK, imgL, imgM, imgN, imgO, imgP, imgQ, imgR,
                imgS, imgT, imgU, imgV, imgW, imgX, imgY, imgZ));
    }
}
