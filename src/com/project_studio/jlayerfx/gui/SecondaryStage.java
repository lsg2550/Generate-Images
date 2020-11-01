package com.project_studio.jlayerfx.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SecondaryStage { 

	private final static Stage DISPLAY_STAGE = new Stage();

	protected static void init() {
		DISPLAY_STAGE.getIcons().add(Icon.APP_ICON);
		DISPLAY_STAGE.setResizable(true);

		DISPLAY_STAGE.setOnCloseRequest(e -> {
			DISPLAY_STAGE.setScene(null);
			DISPLAY_STAGE.setMaximized(false);
			DISPLAY_STAGE.setResizable(true);
		});
	}

	// -----------------------------------------------
	// Stage Options
	// -----------------------------------------------
	public static void setResizable(boolean isResizable) { 
		DISPLAY_STAGE.setResizable(isResizable);
	}

	public static void setScene(Scene scene) {
		DISPLAY_STAGE.setScene(scene);
	}
	
	// -----------------------------------------------
	// Stage Actions
	// -----------------------------------------------
	public static void show(Scene scene, boolean isResizable) {
		setScene(scene);
		setResizable(isResizable);
		DISPLAY_STAGE.show();
	}
	
	public static void showAndWait(Scene scene, boolean isResizable) {
		setScene(scene);
		setResizable(isResizable);
		DISPLAY_STAGE.showAndWait();
	}

	public static void close() {
		DISPLAY_STAGE.close();
	}

}