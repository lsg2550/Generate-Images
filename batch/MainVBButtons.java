package gui;

import batchgi.BatchScript;
import java.net.MalformedURLException;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.io.Read;

/**
 *
 * @author Luis
 */
public class MainVBButtons {

    //Root
    static VBox vbox = new VBox(2.5);

    //ProgressBar
    public static ProgressBar buildProgressBar = new ProgressBar(0.0);

    static void init() {
        //Root
        vbox.setAlignment(Pos.CENTER);

        //HB
        HBox hbox = new HBox(5.0);
        hbox.setAlignment(Pos.CENTER);

        //Buttons
        Button loadSingle = new Button("Load Base/Multiple Directory");
        Button loadMulti = new Button("Load Multi/Multiple Directory");
        Button runBtn = new Button("Run Batch");

        //Children
        hbox.getChildren().addAll(loadSingle, loadMulti, runBtn);
        vbox.getChildren().addAll(new Separator(Orientation.HORIZONTAL), hbox, buildProgressBar);

        //Handlers
        loadSingle.setOnAction(e -> {
            try {
                //Grabs Base Image
                BatchScript.baseImage = Read.readSingleImage();
                //Grabs Other Images
                BatchScript.convertFiletoImage_ListFileArray(Read.readMultipleDirectories());

                System.out.println("Done Loading!");
            } catch (NullPointerException | MalformedURLException ex) {
                System.out.println("No Image Was Selected!");
            }
        });
        
        loadMulti.setOnAction(e -> {
            try {
                //Grabs Base Image
                BatchScript.convertFiletoImage_FileArray(Read.readSingleDirectory());
                //Grabs Other Images
                BatchScript.convertFiletoImage_ListFileArray(Read.readMultipleDirectories());

                System.out.println("Done Loading!");
            } catch (NullPointerException | MalformedURLException ex) {
                ex.printStackTrace();
                System.out.println("No Image Was Selected!");
            }
        });
        
        runBtn.setOnAction(e -> {
            BatchScript.genImage();
        });
    }

}
