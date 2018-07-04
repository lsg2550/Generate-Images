package gui;

import java.io.File;
import java.util.List;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.imagegeneration.Batch;
import utils.operations.conversion.ToImage;
import utils.operations.io.IO;

/**
 *
 * @author Luis
 */
public class MenuOpenGenerateBatch {

    //Scene
    private static Scene scene;

    //ProgressBar
    public static ProgressBar buildProgressBar = new ProgressBar(0.0);

    static void init() {
        //Root
        VBox vbox = new VBox(2.5);
        vbox.setAlignment(Pos.CENTER);

        //HB
        HBox hbox = new HBox(5.0);
        hbox.setAlignment(Pos.CENTER);

        //Buttons
        Button buttonBaseImageMultipleDirectory = new Button("Load Base Image + Multiple Directory");
        Button buttonBaseDirectoryMultipleDirectory = new Button("Load Base Directory + Multiple Directory");

        //Children
        hbox.getChildren().addAll(buttonBaseImageMultipleDirectory, buttonBaseDirectoryMultipleDirectory);
        vbox.getChildren().addAll(hbox, new Separator(Orientation.HORIZONTAL), buildProgressBar);

        //Handlers
        buttonBaseImageMultipleDirectory.setOnAction(e -> {
            //Grab BaseImage + SubDirectories
            Object[] baseImageSubDirectories = IO.readBatchBaseImage();

            //Check if bisd is null
            if (baseImageSubDirectories == null) {
                return;
            }

            //Create BatchScript
            Batch bs = new Batch((Image) baseImageSubDirectories[0], ToImage.convertListOfFileArray((List<File[]>) baseImageSubDirectories[1]));
            bs.batchSave();

            //Garbage Collection
            bs = null;

            //Close SecondaryStage
            SecondaryStage.close();
        });

        buttonBaseDirectoryMultipleDirectory.setOnAction(e -> {
            //Base Directory + Sub-Directories
            Object[] baseDirectorySubDirectories = IO.readBatchBaseDirectory();

            //Check if bdsd is null
            if (baseDirectorySubDirectories == null) {
                return;
            }

            //Create BatchScript
            Batch bs = new Batch(ToImage.convertFileArray((File[]) baseDirectorySubDirectories[0]), ToImage.convertListOfFileArray((List<File[]>) baseDirectorySubDirectories[1]));
            bs.batchSave();

            //Garbage Collection
            bs = null;

            //Close SecondaryStage
            SecondaryStage.close();
        });

        //Scene
        scene = new Scene(vbox, 500, 105);
    }

    static void show() {
        SecondaryStage.setResizable(false);
        SecondaryStage.setScene(scene);
        SecondaryStage.show();
    }
}