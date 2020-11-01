package com.project_studio.jlayerfx.util.imagegeneration;

import java.util.ArrayList;
import java.util.List;

import com.project_studio.jlayerfx.gui.GUI;
import com.project_studio.utils.ImageUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class ImageCache {
	
	// List of directories and their menus
	public static final List<ImageViewer> LOADED_IMAGES = new ArrayList<ImageViewer>();

	// List of Selected Images
	public static final List<ImageView> SELECTED_IMAGES = new ArrayList<ImageView>();

	private ImageCache() {}

	// -----------------------------------------------
	// List Functions
	// -----------------------------------------------
	protected static void removeFromLoaded(ImageViewer iv) {
		LOADED_IMAGES.remove(iv);
	}
	
	protected static void addToLoaded(ImageViewer iv) {
		LOADED_IMAGES.add(iv);
	}
	
	protected static void removeFromSelected(ImageView iv) {
		SELECTED_IMAGES.remove(iv);
	}

	protected static void addToSelected(ImageView iv) {
		SELECTED_IMAGES.add(iv);
	}
	
	// -----------------------------------------------
	// Utility Functions
	// -----------------------------------------------
	protected static void cleanup() {
		GUI.clearUI();
		SELECTED_IMAGES.clear();
		LOADED_IMAGES.clear();
	}
	
	public static void redrawPreviewImage() {
		Image drawnImage = ImageUtils.drawImageFromList(SELECTED_IMAGES);
		GUI.setPreviewImage(drawnImage);
	}
	
}