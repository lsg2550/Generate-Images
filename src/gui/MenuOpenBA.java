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
import utils.batch.Batch;
import utils.conversion.ToImage;
import utils.io.Read;

/**
 *
 * @author Luis
 */
public class MenuOpenBA {

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
        Button loadSingle = new Button("Load Base Image + Multiple Directory");
        Button loadMulti = new Button("Load Base Directory + Multiple Directory");

        //Children
        hbox.getChildren().addAll(loadSingle, loadMulti);
        vbox.getChildren().addAll(hbox, new Separator(Orientation.HORIZONTAL), buildProgressBar);

        //Handlers
        loadSingle.setOnAction(e -> {
            //Grab BaseImage + SubDirectories
            Object[] bisd = Read.batchReadBM();

            //Check if bisd is null
            if (bisd == null) {
                return;
            }

            //Create BatchScript
            Batch bs = new Batch((Image) bisd[0], ToImage.convertListOfFileArray((List<File[]>) bisd[1]));
            bs.batchSave();

            //Close DisplayStage
            DisplayStage.close();
        });

        loadMulti.setOnAction(e -> {
            //Base Directory + Sub-Directories
            Object[] bdsd = Read.batchReadSM();

            //Check if bisd is null
            if (bdsd == null) {
                return;
            }

            //Create BatchScript
            Batch bs = new Batch(ToImage.convertFileArray((File[]) bdsd[0]), ToImage.convertListOfFileArray((List<File[]>) bdsd[1]));
            bs.batchSave();

            //Close DisplayStage
            DisplayStage.close();
        });

        //Scene
        scene = new Scene(vbox, 500, 105);
    }

    static void show() {
        DisplayStage.setResizable(false);
        DisplayStage.setScene(scene);
        DisplayStage.show();
    }
}
