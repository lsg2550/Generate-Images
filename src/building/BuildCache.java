package building;

import gfx.gui.GUI;
import gfx.gui.nodes.FileRead;
import gfx.gui.nodes.Progress;
import gfx.gui.nodes.SelectedDirectory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.benchmarking.Logging;
import utils.notifications.AlertBox;
import utils.operations.enhancednodes.EnhancedImageView;
import utils.operations.io.FileSelector;
import static utils.operations.io.IO.readImage;

/**
 *
 * @author Luis
 */
public class BuildCache {

    //UI or CACHE Objects
    private static VBox toBeGeneratedVBox = new VBox(), centerVBox = new VBox();
    private static HBox imageViewHBox = new HBox(2.5);
    private static ScrollPane scrollPane = new ScrollPane(imageViewHBox);
    static EnhancedImageView toBeGeneratedIV = new EnhancedImageView();

    //Notifications for any errors that occur during the caching
    private static AlertBox alertBox = new AlertBox();

    public static void chooseFolder() {
        File selectedDirectory = FileSelector.readDirectory();

        if (selectedDirectory != null) {

            Runnable task = () -> {

                SelectedDirectory.setText(selectedDirectory.getAbsolutePath());

                File[] listOfFiles = selectedDirectory.listFiles((File file, String name)
                        -> name.toLowerCase().endsWith(".png")
                        || name.toLowerCase().endsWith(".jpg")
                );

                if (listOfFiles.length > 0) {
                    Logging.setStartTime(System.currentTimeMillis()); //Start Time

                    splitImagesToRespectiveArrayLists(listOfFiles);
                    displayImagesToBeEdited();

                    Logging.setEndTime(System.currentTimeMillis()); //End Time
                } else {
                    FileRead.setText("No Images Found!");
                }

            };

            new Thread(task).start();

        } else {
            alertBox.show("No Directory Was Selected!");
        }
    }

    private static void splitImagesToRespectiveArrayLists(File[] files) {
        ImageList.clearCache();

        String fileName;
        String toArrayList;

        for (int i = 0; i < files.length; i++) { //Goes through each file, puts them into respective arraylist
            fileName = files[i].getName();
            toArrayList = FindList.getListDir(fileName);

            //Update GUI
            FileRead.setText("Loading File: " + fileName);
            Progress.setProgress((double) i / files.length);

            switch (toArrayList) {
                case "a":
                    ImageList.imgA.add(readImage(files[i]));
                    break;
                case "b":
                    ImageList.imgB.add(readImage(files[i]));
                    break;
                case "c":
                    ImageList.imgC.add(readImage(files[i]));
                    break;
                case "d":
                    ImageList.imgD.add(readImage(files[i]));
                    break;
                case "e":
                    ImageList.imgE.add(readImage(files[i]));
                    break;
                case "f":
                    ImageList.imgF.add(readImage(files[i]));
                    break;
                case "g":
                    ImageList.imgG.add(readImage(files[i]));
                    break;
                case "h":
                    ImageList.imgH.add(readImage(files[i]));
                    break;
                case "i":
                    ImageList.imgI.add(readImage(files[i]));
                    break;
                case "j":
                    ImageList.imgJ.add(readImage(files[i]));
                    break;
                case "k":
                    ImageList.imgK.add(readImage(files[i]));
                    break;
                case "l":
                    ImageList.imgL.add(readImage(files[i]));
                    break;
                case "m":
                    ImageList.imgM.add(readImage(files[i]));
                    break;
                case "n":
                    ImageList.imgN.add(readImage(files[i]));
                    break;
                case "o":
                    ImageList.imgO.add(readImage(files[i]));
                    break;
                case "p":
                    ImageList.imgP.add(readImage(files[i]));
                    break;
                case "q":
                    ImageList.imgQ.add(readImage(files[i]));
                    break;
                case "r":
                    ImageList.imgR.add(readImage(files[i]));
                    break;
                case "s":
                    ImageList.imgS.add(readImage(files[i]));
                    break;
                case "t":
                    ImageList.imgT.add(readImage(files[i]));
                    break;
                case "u":
                    ImageList.imgU.add(readImage(files[i]));
                    break;
                case "v":
                    ImageList.imgV.add(readImage(files[i]));
                    break;
                case "w":
                    ImageList.imgW.add(readImage(files[i]));
                    break;
                case "x":
                    ImageList.imgX.add(readImage(files[i]));
                    break;
                case "y":
                    ImageList.imgY.add(readImage(files[i]));
                    break;
                case "z":
                    ImageList.imgZ.add(readImage(files[i]));
                    break;
                default:
                    break;
            }
        }

        ImageList.addToCache();
    }

    private static void displayImagesToBeEdited() {

        Platform.runLater(() -> {
            Progress.setProgress(0);
            GUI.getRoot().setCenter(null);
            imageViewHBox.getChildren().clear();
            toBeGeneratedIV.setImage(null);
        });

        int counter = 0; //Restarting/Starting Counter

        //START: HBOX INSIDE SCROLLPANE
        for (ArrayList<BufferedImage> splitImage : ImageList.splitImgs) {
            Progress.setProgress((double) counter / 26);
            FileRead.setText("Building.");

            if (!splitImage.isEmpty()) { //Builds New VBox per ImageView

                //UI
                VBox tempVB = new VBox();
                tempVB.setAlignment(Pos.CENTER);

                EnhancedImageView eImageView = new EnhancedImageView(SwingFXUtils.toFXImage(splitImage.get(0), null));
                tempVB.getChildren().add(eImageView);

                FileRead.setText("Building..");
                if (splitImage.size() == 1) {
                    CheckBox enableDisable = new CheckBox();
                    tempVB.getChildren().add(enableDisable);

                    //Handlers
                    Handlers.enableDisable(eImageView, enableDisable);
                    Handlers.imageViewListener(eImageView);
                } else {
                    Button extend = new Button("Extend");
                    extend.setMinHeight(16);
                    extend.setPadding(new Insets(0, 5, 0, 5));
                    tempVB.getChildren().add(extend);

                    //Handlers
                    Handlers.extendAndCreateSubMenu(splitImage, eImageView, extend);
                }

                FileRead.setText("Building...");

                //Updating GUI & Adding VBox to Center HBox
                Platform.runLater(() -> {
                    imageViewHBox.getChildren().add(tempVB);
                });
            }
            counter++;
        }//END: HBOX INSIDE SCROLLPANE

        Platform.runLater(() -> { //Updates CENTER VBOX
            GUI.getRoot().setCenter(centerVBox);
            Progress.setProgress(1.0);
            FileRead.setText("Done!");
        });

    }

    //Initialize
    public static void init() {
        //HBox inside Scrollpane
        imageViewHBox.setMaxHeight(5);
        imageViewHBox.setAlignment(Pos.CENTER);

        //Scrollpane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinHeight(270);
        scrollPane.setPannable(true);

        //Image Preview VBox
        toBeGeneratedVBox.getChildren().addAll(new Text("Image To Be Generated"), toBeGeneratedIV);
        toBeGeneratedVBox.setMinSize(250, 250);
        toBeGeneratedVBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(toBeGeneratedVBox, Priority.ALWAYS);

        //Image Preview
        toBeGeneratedIV.setFitHeight(235);
        toBeGeneratedIV.setFitHeight(235);
        Handlers.imageViewStageBuilder(toBeGeneratedIV);

        //Container for all nodes
        centerVBox.getChildren().addAll(scrollPane, toBeGeneratedVBox);
        centerVBox.setAlignment(Pos.CENTER);
    }
}
