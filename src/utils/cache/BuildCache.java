package utils.cache;

import gfx.GUI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import utils.operations.Handlers;
import static utils.operations.IO.readImage;

/**
 *
 * @author Luis
 */
public class BuildCache {

    //UI Objects, to avoid redundancy these will only be created once and re-used.
    private static ImageView toBeGeneratedIV = new ImageView();
    private static HBox imageViewHBox = new HBox(2.5);
    private static VBox toBeGeneratedVBox = new VBox();

    //ArrayLists of Images, to avoid redundancy these will only be created once and re-used.
    private static ArrayList<ArrayList<BufferedImage>> splitImgs = new ArrayList();
    private static ArrayList<BufferedImage> imgA = new ArrayList(), //A-Z
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
    //Owner
    private static Scene owner;

    /*START OF BULIDING CACHE*/
    public static void chooseFolder(Scene scene) {
        owner = scene;
        DirectoryChooser dChooser = new DirectoryChooser();
        File selectedDirectory = dChooser.showDialog(new Stage());

        if (selectedDirectory == null) {
            new AlertBox("No Directory Was Selected!", owner);
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
                        new AlertBox("No Images Were Found", owner);
                    });
                }
            };
            new Thread(task).start();
        }
    }

    private static ArrayList<ArrayList<BufferedImage>> splitImagesToRespectiveArrayLists(File[] files) {
        Handlers.clearCache(splitImgs, imgA, imgB, imgC, imgD, imgE, imgF, imgG,
                imgH, imgI, imgJ, imgK, imgL, imgN, imgO, imgP, imgQ, imgR, imgS,
                imgT, imgU, imgV, imgW, imgX, imgY, imgZ);

        for (int i = 0; i < files.length; i++) { //Goes through each file, puts them into respective arraylist
            String temp = files[i].getName().toLowerCase().substring(0, 1);
            GUI.getText().setText("Loading Files: " + files[i].getName());
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
                    new AlertBox("Nothing Found", owner);
                    break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }

        Handlers.addCache(splitImgs, imgA, imgB, imgC, imgD, imgE, imgF, imgG,
                imgH, imgI, imgJ, imgK, imgL, imgN, imgO, imgP, imgQ, imgR, imgS,
                imgT, imgU, imgV, imgW, imgX, imgY, imgZ);
        GUI.getText().setText("Done!");
        return splitImgs;
    }

    private static void displayImagesToBeEdited(ArrayList<ArrayList<BufferedImage>> splitImages) {
        try {
            GUI.getText().setText("Updating GUI...");
            Thread.sleep(150);  //THREAD
            Platform.runLater(() -> {
                toBeGeneratedVBox.getChildren().clear();
                imageViewHBox.getChildren().clear();
                toBeGeneratedIV.setImage(null);
            });
            /*START: HBOX INSIDE SCROLLPANE*/
            imageViewHBox.setMaxHeight(5);
            imageViewHBox.setAlignment(Pos.CENTER);

            //Builds ImageViews to be added to this.HBox
            for (ArrayList<BufferedImage> splitImage : splitImages) {
                if (!splitImage.isEmpty()) {
                    GUI.getText().setText("Updating GUI.");
                    VBox tempVB = new VBox();
                    tempVB.setAlignment(Pos.CENTER);
                    Button left = new Button("<<"), right = new Button(">>");
                    CheckBox enableDisable = new CheckBox();

                    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(splitImage.get(0), null));

                    GUI.getText().setText("Updating GUI..");
                    Handlers.directionButton(splitImage, imageView, left, right);
                    Handlers.enableDisable(imageView, enableDisable, left, right);
                    Handlers.imageViewListener(imageView);

                    tempVB.getChildren().addAll(left, imageView, right, enableDisable);
                    imageView.setFitWidth(150);
                    imageView.setFitHeight(225);

                    GUI.getText().setText("Updating GUI...");
                    GUI.getpBar().setProgress((double) splitImages.indexOf(splitImage) / splitImages.size());

                    Thread.sleep(150);  //THREAD
                    Platform.runLater(() -> {
                        imageViewHBox.getChildren().add(tempVB);
                    });
                }
            }

            ScrollPane sp = new ScrollPane(imageViewHBox);
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setPannable(true);
            GUI.getpBar().setProgress(0.60);
            /*END: HBOX INSIDE SCROLLPANE*/

            //
            //

            /*START: LONE IMAGEVIEW BELOW SCROLLPANE*/
            toBeGeneratedVBox.setMaxHeight(225);
            toBeGeneratedVBox.setAlignment(Pos.CENTER);
            liveImageBuilding();
            toBeGeneratedIV.setFitWidth(150);
            toBeGeneratedIV.setFitHeight(225);
            Thread.sleep(150);  //THREAD
            Platform.runLater(() -> {
                toBeGeneratedVBox.getChildren().addAll(new Text("Image To Be Generated"), toBeGeneratedIV);
            });
            GUI.getpBar().setProgress(0.90);
            /*END: LONE IMAGEVIEW BELOW SCROLLPANE*/

            //Center HBox
            VBox centerVBox = new VBox(0);
            VBox.setVgrow(toBeGeneratedVBox, Priority.ALWAYS);
            centerVBox.setAlignment(Pos.CENTER);
            Thread.sleep(150);  //THREAD
            Platform.runLater(() -> {
                centerVBox.getChildren().addAll(sp, toBeGeneratedVBox);
                GUI.getRoot().setCenter(centerVBox);
                GUI.getpBar().setProgress(1.0);
                GUI.getText().setText("Done!");
            });
        } catch (InterruptedException ex) {
        }
    }/*END OF BULIDING CACHE*/

    //
    /*START: LIVE BUILDING*/
    public static void liveImageBuilding() {
        ArrayList<BufferedImage> toGeneratedIV = new ArrayList();
        for (Node node : imageViewHBox.getChildren()) {
            ImageView temp = (ImageView) ((VBox) node).getChildren().get(1);
            if (!temp.isDisabled()) {
                toGeneratedIV.add(SwingFXUtils.fromFXImage(temp.getImage(), null));
            }
        }
        toBeGeneratedIV.setImage(SwingFXUtils.toFXImage(BuildImage.buildImageLive(toGeneratedIV), null));
    }/*END: LIVE BUILDING*/

    public static ImageView getToBeGeneratedIV() {
        return toBeGeneratedIV;
    }
}
