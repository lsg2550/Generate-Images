package building;

import building.stages.StageList;
import building.stages.StageBuilds;
import gfx.gui.GUI;
import gfx.gui.nodes.FileRead;
import gfx.gui.nodes.Progress;
import gfx.gui.nodes.SelectedDirectory;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
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
    private final static VBox GENERATED_VBOX = new VBox(),
            CENTER_ALL_CONTAINER_VBOX = new VBox();
    private final static HBox IMAGEVIEW_INSIDE_SCROLLPANE_HBOX = new HBox(2.5);
    private final static ScrollPane SCROLLPANE_HOLDING_IMAGEVIEW_HBOX = new ScrollPane(IMAGEVIEW_INSIDE_SCROLLPANE_HBOX);
    private final static Text TO_BE_GENERATED_TEXT = new Text("Image To Be Generated");
    static EnhancedImageView toBeGeneratedIV = new EnhancedImageView();

    public static void chooseFolder() {
        File selectedDirectory = FileSelector.readDirectory();

        if (selectedDirectory != null) {

            new Thread(() -> {
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

            }).start();

        } else {
            AlertBox.show("No Directory Was Selected!");
        }
    }

    private static void splitImagesToRespectiveArrayLists(File[] files) {
        ImageList.clearCache();
        StageList.clearBoxes();
        String filePath = "",
                toArrayList = "",
                fileName = "";

        FileRead.setText("Loading File(s)");
        for (int i = 0; i < files.length; i++) {
            try {
                filePath = files[i].toURI().toURL().toExternalForm();
                fileName = files[i].getName();
                toArrayList = FindList.getListDir(fileName);
            } catch (MalformedURLException ex) {
            }

            //Update GUI
            Progress.setProgress((double) i / files.length);
            switch (toArrayList) {
                case "a":
                    readImage(filePath, ImageList.imgA);
                    break;
                case "b":
                    readImage(filePath, ImageList.imgB);
                    break;
                case "c":
                    readImage(filePath, ImageList.imgC);
                    break;
                case "d":
                    readImage(filePath, ImageList.imgD);
                    break;
                case "e":
                    readImage(filePath, ImageList.imgE);
                    break;
                case "f":
                    readImage(filePath, ImageList.imgF);
                    break;
                case "g":
                    readImage(filePath, ImageList.imgG);
                    break;
                case "h":
                    readImage(filePath, ImageList.imgH);
                    break;
                case "i":
                    readImage(filePath, ImageList.imgI);
                    break;
                case "j":
                    readImage(filePath, ImageList.imgJ);
                    break;
                case "k":
                    readImage(filePath, ImageList.imgK);
                    break;
                case "l":
                    readImage(filePath, ImageList.imgL);
                    break;
                case "m":
                    readImage(filePath, ImageList.imgM);
                    break;
                case "n":
                    readImage(filePath, ImageList.imgN);
                    break;
                case "o":
                    readImage(filePath, ImageList.imgO);
                    break;
                case "p":
                    readImage(filePath, ImageList.imgP);
                    break;
                case "q":
                    readImage(filePath, ImageList.imgQ);
                    break;
                case "r":
                    readImage(filePath, ImageList.imgR);
                    break;
                case "s":
                    readImage(filePath, ImageList.imgS);
                    break;
                case "t":
                    readImage(filePath, ImageList.imgT);
                    break;
                case "u":
                    readImage(filePath, ImageList.imgU);
                    break;
                case "v":
                    readImage(filePath, ImageList.imgV);
                    break;
                case "w":
                    readImage(filePath, ImageList.imgW);
                    break;
                case "x":
                    readImage(filePath, ImageList.imgX);
                    break;
                case "y":
                    readImage(filePath, ImageList.imgY);
                    break;
                case "z":
                    readImage(filePath, ImageList.imgZ);
                    break;
                default:
                    break;
            }
        }

        filePath = null;
        toArrayList = null;
        fileName = null;
    }

    private static void displayImagesToBeEdited() {

        Platform.runLater(() -> {
            IMAGEVIEW_INSIDE_SCROLLPANE_HBOX.getChildren().clear();
        });

        //START: HBOX INSIDE SCROLLPANE
        FileRead.setText("Building UI");
        for (ArrayList<Image> splitImage : ImageList.splitImgs) {
            Progress.setProgress(ImageList.splitImgs.indexOf(splitImage) / 26);

            if (!splitImage.isEmpty()) { //Builds New VBox per ImageView

                StageBuilds sb = new StageBuilds(splitImage);
                StageList.sbAL.add(sb);

                //Updating GUI & Adding VBox to Center HBox
                Platform.runLater(() -> {
                    IMAGEVIEW_INSIDE_SCROLLPANE_HBOX.getChildren().add(sb.getRoot());
                });

            }
        }//END: HBOX INSIDE SCROLLPANE

        Progress.setProgress(1.0);
        FileRead.setText("Done!");
    }

    //Initialize
    public static void init() {
        //HBox inside Scrollpane
        IMAGEVIEW_INSIDE_SCROLLPANE_HBOX.setAlignment(Pos.CENTER);
        IMAGEVIEW_INSIDE_SCROLLPANE_HBOX.setMaxHeight(5);

        //Scrollpane
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setMinHeight(270);
        SCROLLPANE_HOLDING_IMAGEVIEW_HBOX.setPannable(true);

        //Image Preview VBox
        GENERATED_VBOX.getChildren().addAll(TO_BE_GENERATED_TEXT, toBeGeneratedIV);
        GENERATED_VBOX.setAlignment(Pos.CENTER);
        GENERATED_VBOX.setMinSize(275, 275);

        //Image Preview
        Handlers.imageViewStageBuilder(toBeGeneratedIV);
        toBeGeneratedIV.setFitHeight(235);
        toBeGeneratedIV.setFitWidth(235);

        //Container for all nodes
        CENTER_ALL_CONTAINER_VBOX.getChildren().addAll(SCROLLPANE_HOLDING_IMAGEVIEW_HBOX, GENERATED_VBOX);
        CENTER_ALL_CONTAINER_VBOX.heightProperty().isEqualTo(GUI.getScene().getHeight(), 5);
        CENTER_ALL_CONTAINER_VBOX.widthProperty().isEqualTo(GUI.getScene().getWidth(), 5);
        CENTER_ALL_CONTAINER_VBOX.setAlignment(Pos.CENTER);

        //Sets Center
        GUI.getRoot().setCenter(CENTER_ALL_CONTAINER_VBOX);
    }
}


/*
                //UI
                VBox tempVB = new VBox();
                tempVB.setAlignment(Pos.CENTER);

                EnhancedImageView eImageView = new EnhancedImageView(splitImage.get(0));
                tempVB.getChildren().add(eImageView);

                if (splitImage.size() == 1) {
                    CheckBox enableDisable = new CheckBox();
                    tempVB.getChildren().add(enableDisable);

                    //Handlers
                    Handlers.enableDisable(eImageView, enableDisable);
                    Handlers.imageViewListener(eImageView);
                } else {
                    Button extend = new Button("Extend");
                    Insets insets = new Insets(0, 5, 0, 5);
                    extend.setPadding(insets);
                    extend.setMinHeight(16);
                    tempVB.getChildren().add(extend);

                    //Handlers
                    Handlers.extendAndCreateSubMenu(splitImage, eImageView, extend);
                }*/
