package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class IO {

    public static Object[] ChooseFolder() {
        DirectoryChooser dChooser = new DirectoryChooser();
        File selectedDirectory = dChooser.showDialog(new Stage());
        //ArrayList<BufferedImage> imgArrayList = new ArrayList();
        Object[] obj = new Object[2];

        /*Conditional: Used to Display Information to User*/
        if (selectedDirectory == null) { //No Directory was Selected
            obj[0] = obj[1] = "No Directory Was Selected!";
            return obj;
        } else { //Directory was Selected
            File[] listOfFiles = selectedDirectory.listFiles((File file, String name)
                    -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg")
            );
            obj[0] = splitImages(listOfFiles);
            obj[1] = selectedDirectory.getAbsolutePath();
            return obj;
        }
    }

    public static ArrayList<ArrayList<BufferedImage>> splitImages(File[] files) {
        ArrayList<ArrayList<BufferedImage>> splitImgs = new ArrayList();
        ArrayList<BufferedImage> imgA = new ArrayList(),
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
                imgZ = new ArrayList();
        /*
        ArrayList<String> stringSeparator = new ArrayList();
        for (File file : files) { //Gets Separators
            String temp = ;
            if (!stringSeparator.contains(temp)) {
                stringSeparator.add(temp);
            }
        }
         */
        for (File file : files) {
            String temp = file.getName().toLowerCase().substring(0, 1);
            System.out.println("File Name: " + file.getName());
            switch (temp) {
                case "a":
                    imgA.add(readImage(file));
                    break;
                case "b":
                    imgB.add(readImage(file));
                    break;
                case "c":
                    imgC.add(readImage(file));
                    break;
                case "d":
                    imgD.add(readImage(file));
                    break;
                case "e":
                    imgE.add(readImage(file));
                    break;
                case "f":
                    imgF.add(readImage(file));
                    break;
                case "g":
                    imgG.add(readImage(file));
                    break;
                case "h":
                    imgH.add(readImage(file));
                    break;
                case "i":
                    imgI.add(readImage(file));
                    break;
                case "j":
                    imgJ.add(readImage(file));
                    break;
                case "k":
                    imgK.add(readImage(file));
                    break;
                case "l":
                    imgL.add(readImage(file));
                    break;
                case "m":
                    imgM.add(readImage(file));
                    break;
                case "n":
                    imgN.add(readImage(file));
                    break;
                case "o":
                    imgO.add(readImage(file));
                    break;
                case "p":
                    imgP.add(readImage(file));
                    break;
                case "q":
                    imgQ.add(readImage(file));
                    break;
                case "r":
                    imgR.add(readImage(file));
                    break;
                case "s":
                    imgS.add(readImage(file));
                    break;
                case "t":
                    imgT.add(readImage(file));
                    break;
                case "u":
                    imgU.add(readImage(file));
                    break;
                case "v":
                    imgV.add(readImage(file));
                    break;
                case "w":
                    imgW.add(readImage(file));
                    break;
                case "x":
                    imgX.add(readImage(file));
                    break;
                case "y":
                    imgY.add(readImage(file));
                    break;
                case "z":
                    imgZ.add(readImage(file));
                    break;
                default:
                    System.out.println("Nothing Found."); //Nothing Found
                    break;
            }
        }
        splitImgs.add(imgA);
        splitImgs.add(imgB);
        splitImgs.add(imgC);
        splitImgs.add(imgD);
        splitImgs.add(imgE);
        splitImgs.add(imgF);
        splitImgs.add(imgG);
        splitImgs.add(imgH);
        splitImgs.add(imgI);
        splitImgs.add(imgJ);
        splitImgs.add(imgK);
        splitImgs.add(imgL);
        splitImgs.add(imgM);
        splitImgs.add(imgN);
        splitImgs.add(imgO);
        splitImgs.add(imgP);
        splitImgs.add(imgQ);
        splitImgs.add(imgR);
        splitImgs.add(imgS);
        splitImgs.add(imgT);
        splitImgs.add(imgU);
        splitImgs.add(imgV);
        splitImgs.add(imgW);
        splitImgs.add(imgX);
        splitImgs.add(imgY);
        splitImgs.add(imgZ);
        System.out.println("Done!");
        return splitImgs;
    }

    public static BufferedImage readImage(File file) {
        BufferedImage img = null;
        try {
            //System.out.println("File Name: " + file.getName());
            img = ImageIO.read(file);
        } catch (IOException e) {
        }
        return img;
    }

    public static void writeImage() {

    }
}
