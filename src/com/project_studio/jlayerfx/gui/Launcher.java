package com.project_studio.jlayerfx.gui;

import com.project_studio.jlayerfx.util.imagegeneration.ImageLoaderThread;
import com.project_studio.jlayerfx.util.io.IO;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Stage Init
		PrimaryStage.init(primaryStage);
		SecondaryStage.init();
		CSS.init(primaryStage.getScene());
		
		// GUI Init
    	GUI.init();

		// Utils Init
		ImageLoaderThread.init();
		IO.init();
		
		// Show
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}