package com.project_studio.jlayerfx.util.imagegeneration;

import java.io.File;
import java.util.List;

import com.project_studio.jlayerfx.gui.GUI;
import com.project_studio.jlayerfx.gui.Icon;
import com.project_studio.jlayerfx.gui.SecondaryStage;
import com.project_studio.jlayerfx.util.io.IO;
import com.project_studio.jlayerfx.util.settings.Settings;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ImageLoaderThread {

    private static Thread imageLoaderThread;

    public static void init() {
    	ImageLoaderThreadAlert.init();
    }
    
    public static void runBuild() {
    	if(imageLoaderThread != null && imageLoaderThread.isAlive()) {
            ImageLoaderThreadAlert.show();
    	} else {
    		ImageLoader imageLoader = null;
    		
    		if(Settings.LOAD_SINGLE) {
    			File[] singleDirectory = IO.readDirectorySingle();
    			imageLoader = new ImageLoader(singleDirectory);
    		} else if (Settings.LOAD_MULTIPLE) {
    			List<File[]> multipleDirectory = IO.readDirectoryMultiple();
    			imageLoader = new ImageLoader(multipleDirectory);
    		} else {
    			return;
    		}
    		
            imageLoaderThread = new Thread(imageLoader);
            imageLoaderThread.start();
    	}
    }

    protected static void interruptThread() {
        imageLoaderThread.interrupt();
    }
    
    protected static boolean isAlive() {
    	return imageLoaderThread.isAlive();
    }
    
	private static class ImageLoaderThreadAlert {
	
	    //Scene
	    private static Scene scene;
	
	    public static void init() {
	        //VBox
	        VBox root = new VBox(10);
	        root.setAlignment(Pos.CENTER);
	
	        //HBox
	        HBox topHB = new HBox(30);
	        topHB.setAlignment(Pos.CENTER);
	
	        HBox buttonHB = new HBox(5);
	        buttonHB.setAlignment(Pos.CENTER);
	
	        //StackPane
	        StackPane stackPane = new StackPane();
	        stackPane.setAlignment(Pos.CENTER);
	
	        //Button
	        Button btnInterruptThread = new Button("Yes");
	        Button btnKeepThread = new Button("No");
	        btnInterruptThread.setOnAction(e -> {
	            GUI.updateStatusText("Please wait for the process to safely halt.");
	            ImageLoaderThread.interruptThread();
	            SecondaryStage.close();
	            if(ImageLoaderThread.isAlive()) { GUI.updateStatusText("Process stopped."); }
	        });
	        btnKeepThread.setOnAction(e -> {
	            SecondaryStage.close();
	        });
	
	        //Text
	        Text warningText = new Text("You're currently loading images would you like to stop?");
	        warningText.setFont(Font.font(null, 12));
	        warningText.setTextAlignment(TextAlignment.CENTER);
	        warningText.setWrappingWidth(200); // Find a way to avoid using hard-code
	        warningText.setFill(Color.BLACK);
	
	        //Label
	        Label warningLabel = new Label("ATTENTION!");
	        warningLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
	        warningLabel.setTextAlignment(TextAlignment.CENTER);
	        warningLabel.setTextFill(Color.BLACK);
	
	        //ImageView
	        ImageView warningImageView = new ImageView(Icon.APP_ICON);
	        warningImageView.setFitHeight(24);
	        warningImageView.setFitWidth(24);
	
	        //Children
	        topHB.getChildren().addAll(warningLabel, warningImageView);
	        buttonHB.getChildren().addAll(btnInterruptThread, btnKeepThread);
	        stackPane.getChildren().add(warningText);
	        root.getChildren().addAll(topHB, new Separator(Orientation.HORIZONTAL), stackPane, buttonHB);
	        
	        //Scene
	        scene = new Scene(root, 300, 150);
	    }
	
	    public static void show() {
	        SecondaryStage.show(scene, false);
	    }
	
	}
    
}