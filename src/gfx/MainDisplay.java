/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class MainDisplay {

    public MainDisplay() {
        mainStage();
    }

    private void mainStage() {
        //Buttons
        Button folderSelect = new Button("Select Folder"),
                generateImage = new Button("Generate Image");
        //Button Handlers
        folderSelect.setOnAction(e -> {
            
        });
        generateImage.setOnAction(e -> {
            
        });
        
        //U(ser)I(nterface)
        BorderPane root = new BorderPane(); //BorderPane (Root)
        HBox upperBar = new HBox(5);
        

        Scene scene = new Scene(root, 300, 250);
        Stage stage = new Stage();
        stage.setTitle("Generate Image");
        stage.getIcons().add(new Image("gfx/assets/ico/briefcase.png"));
        stage.setScene(scene);
        stage.show();
    }
}
