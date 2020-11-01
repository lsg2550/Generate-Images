package com.project_studio.jlayerfx.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class PrimaryStage {

	protected static void init(final Stage primaryStage) {
        final Scene scene = new Scene(GUI.getRoot(), 800, 600);
        primaryStage.setTitle("Generate Images");
        primaryStage.getIcons().add(Icon.APP_ICON);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
    }

}