package utils.operations;

import gfx.GUI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import utils.notifications.AlertBox;
import static utils.operations.IO.readImage;

/**
 *
 * @author Luis
 */
public class BuildCache {

    /*START OF BULIDING CACHE*/
    public static void chooseFolder() {
        DirectoryChooser dChooser = new DirectoryChooser();
        File selectedDirectory = dChooser.showDialog(new Stage());

        if (selectedDirectory == null) {
            new AlertBox("No Directory Was Selected!");
        } else { //Directory was Selected
            Runnable task = () -> {
                GUI.getDirectoryText().setText(selectedDirectory.getAbsolutePath());
                File[] listOfFiles = selectedDirectory.listFiles((File file, String name)
                        -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg")
                );
                if (listOfFiles.length > 0) {
                    displayImagesToBeEdited(splitImagesToRespectiveArrayLists(listOfFiles));
                } else {
                    Platform.runLater(() -> {
                        new AlertBox("No Images Were Found");
                    });
                }
            };
            new Thread(task).start();
        }
    }

    private static ArrayList<ArrayList<BufferedImage>> splitImagesToRespectiveArrayLists(File[] files) {
        ArrayList<ArrayList<BufferedImage>> splitImgs = new ArrayList();
        ArrayList<BufferedImage> imgA = new ArrayList(), //A-Z
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

        for (int i = 0; i < files.length; i++) { //Goes through each file, puts them into respective arraylist
            String temp = files[i].getName().toLowerCase().substring(0, 1);
            GUI.getText().setText("Reading File: " + files[i].getName());
            GUI.getpBar().setProgress((double) i / files.length);
            switch (temp) {
                case "a":
                    imgA.add(readImage(files[i]));
                    break;
                case "b":
                    imgB.add(readImage(files[i]));
                    break;
                case "c":
                    imgC.add(readImage(files[i]));
                    break;
                case "d":
                    imgD.add(readImage(files[i]));
                    break;
                case "e":
                    imgE.add(readImage(files[i]));
                    break;
                case "f":
                    imgF.add(readImage(files[i]));
                    break;
                case "g":
                    imgG.add(readImage(files[i]));
                    break;
                case "h":
                    imgH.add(readImage(files[i]));
                    break;
                case "i":
                    imgI.add(readImage(files[i]));
                    break;
                case "j":
                    imgJ.add(readImage(files[i]));
                    break;
                case "k":
                    imgK.add(readImage(files[i]));
                    break;
                case "l":
                    imgL.add(readImage(files[i]));
                    break;
                case "m":
                    imgM.add(readImage(files[i]));
                    break;
                case "n":
                    imgN.add(readImage(files[i]));
                    break;
                case "o":
                    imgO.add(readImage(files[i]));
                    break;
                case "p":
                    imgP.add(readImage(files[i]));
                    break;
                case "q":
                    imgQ.add(readImage(files[i]));
                    break;
                case "r":
                    imgR.add(readImage(files[i]));
                    break;
                case "s":
                    imgS.add(readImage(files[i]));
                    break;
                case "t":
                    imgT.add(readImage(files[i]));
                    break;
                case "u":
                    imgU.add(readImage(files[i]));
                    break;
                case "v":
                    imgV.add(readImage(files[i]));
                    break;
                case "w":
                    imgW.add(readImage(files[i]));
                    break;
                case "x":
                    imgX.add(readImage(files[i]));
                    break;
                case "y":
                    imgY.add(readImage(files[i]));
                    break;
                case "z":
                    imgZ.add(readImage(files[i]));
                    break;
                default:
                    new AlertBox("Nothing Found");
                    break;
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
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
        GUI.getText().setText("Done!");
        return splitImgs;
    }

    /*
        Plan: Make Center an HBOX with SCROLLPANE on top, and lone VBOX in Bottom
        lone VBOX will show how the image will look like once generated 
        a little complex so will save near end. I want to get img generation done first.
     */
    private static void displayImagesToBeEdited(ArrayList<ArrayList<BufferedImage>> splitImages) {
        /*START: HBOX INSIDE SCROLLPANE*/
        HBox imageViewHBox = new HBox(2.5);
        imageViewHBox.setMaxHeight(5);
        imageViewHBox.setAlignment(Pos.CENTER);

        //Builds ImageViews to be added to this.HBox
        for (ArrayList<BufferedImage> splitImage : splitImages) {
            if (!splitImage.isEmpty()) {
                VBox tempVB = new VBox();
                Button left = new Button("<<"),
                        right = new Button(">>");
                CheckBox enableDisable = new CheckBox();
                tempVB.setAlignment(Pos.CENTER);

                if (splitImage.size() > 1) {
                    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(splitImage.get(0), null));
                    Handlers.directionButton(splitImage, imageView, left, true);
                    Handlers.directionButton(splitImage, imageView, right, false);
                    tempVB.getChildren().addAll(left, imageView, right, enableDisable);
                    imageView.setFitWidth(150);
                    imageView.setFitHeight(225);
                } else {
                    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(splitImage.get(0), null));
                    imageView.setFitWidth(150);
                    imageView.setFitHeight(225);
                    tempVB.getChildren().addAll(imageView, enableDisable);
                }

                imageViewHBox.getChildren().add(tempVB);
            }
        }

        ScrollPane sp = new ScrollPane(imageViewHBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPannable(true);
        /*END: HBOX INSIDE SCROLLPANE*/

        //Code to generate image to place inside imageview
        /*START: LONE IMAGEVIEW BELOW SCROLLPANE*/
        VBox toBeGeneratedVBox = new VBox();
        toBeGeneratedVBox.setMaxHeight(225);
        toBeGeneratedVBox.setAlignment(Pos.CENTER);
        ImageView toBeGeneratedIV = new ImageView(SwingFXUtils.toFXImage(splitImages.get(0).get(0), null));
        toBeGeneratedIV.setFitWidth(150);
        toBeGeneratedIV.setFitHeight(225);
        toBeGeneratedVBox.getChildren().addAll(new Text("Image To Be Generated"), toBeGeneratedIV);
        /*END: LONE IMAGEVIEW BELOW SCROLLPANE*/

        //Center HBox
        VBox centerVBox = new VBox(0);
        VBox.setVgrow(sp, Priority.SOMETIMES);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.getChildren().addAll(sp, toBeGeneratedVBox);
        Platform.runLater(() -> {
            GUI.getRoot().setCenter(centerVBox);
        });
    }/*END OF BULIDING CACHE*/
}
