package com.project_studio.jlayerfx.gui;

import com.sun.javafx.css.StyleManager;
import javafx.scene.Scene;

public final class CSS {

	private static final String CSS_PATH = "css/persian.css";

	public static void init(final Scene rootScene) {
		rootScene.getStylesheets().add(CSS_PATH);
		StyleManager.getInstance().addUserAgentStylesheet(CSS_PATH);
	}
	
}