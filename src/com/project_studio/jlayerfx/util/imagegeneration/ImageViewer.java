package com.project_studio.jlayerfx.util.imagegeneration;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.project_studio.jlayerfx.gui.SecondaryStage;
import com.project_studio.utils.ImageUtils;
import com.project_studio.utils.ObjectUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class ImageViewer {

	// GUI
	private final VBox root = new VBox(); // VBox Scene from main GUI

	// Images
	private List<Image> listOfImages; // List of Images of CacheBuild
	private char listIdentifier; // ID of CacheBuild

	// -----------------------------------------------
	// Constructors
	// -----------------------------------------------
	protected ImageViewer(Image image) { // For the submenu & savemenu
		listOfImages = new ArrayList<Image>(1);
		listOfImages.add(image);
	}

	protected ImageViewer(char arrayListCharIdentifier, Image image) { // Single Directories
		listIdentifier = arrayListCharIdentifier;
		listOfImages = new LinkedList<Image>();
		listOfImages.add(image); // Initial Image
	}

	protected ImageViewer(File[] arrayOfImages) { // Multiple Directories
		listOfImages = new ArrayList<Image>(arrayOfImages.length);
		for (File imageFile : arrayOfImages) {
			Image image = ImageUtils.fileToImage(imageFile);
			listOfImages.add(image);
		}
	}

	// -----------------------------------------------
	// UI Work
	// -----------------------------------------------
	private void initContainer() {
		root.setAlignment(Pos.CENTER);
	}
	
	private void createUIForSingleImage(Image image) {
		ImageView imageView = new ImageViewerImageView(image);
		CheckBox enableDisable = new ImageCheckBox(imageView);
		root.getChildren().addAll(imageView, enableDisable);
	}

	private void createUIForMultipleImages(List<Image> listOfImages) {
		ImageView imageView = new ImageViewerImageView(listOfImages.get(0));
		Button extendButton = new ExtendedImageViewButton(listOfImages);
		root.getChildren().addAll(imageView, extendButton);
	}
	
	protected void createUI() {
		if(ObjectUtils.isNullOrEmpty(listOfImages)) {
			return;
		}
		
		initContainer();
		if(listOfImages.size() == 1) {
			createUIForSingleImage(listOfImages.get(0));
		} else {
			createUIForMultipleImages(listOfImages);
		}
	}

	// -----------------------------------------------
	// Custom UI Nodes
	// -----------------------------------------------

	// -----------------------------------------------
	// Custom UI Nodes
	// -----------------------------------------------
	private static class ImageCheckBox extends CheckBox {

		private static final ColorAdjust TO_GRAY = new ColorAdjust(1.0, -0.75, 0.0, -0.5);
		
		private ImageCheckBox(ImageView imageView) {
			setListener(imageView);
		}
		
		private void setListener(ImageView imageView) {
			final ChangeListener<Boolean> cbChangeListener = new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (isSelected()) {
						ImageCache.removeFromSelected(imageView);
						imageView.setEffect(TO_GRAY);
					} else {
						ImageCache.addToSelected(imageView);
						imageView.setEffect(null);
					}
	
					ImageCache.redrawPreviewImage();
				}
			};

			super.selectedProperty().addListener(cbChangeListener);
			super.setSelected(true);
		}
		
	}
	
	
	private static class ExtendedImageViewButton extends Button {
		
		private static final String TEXT = "Extend";
		private static final Insets INSETS = new Insets(0, 5, 0, 5);
		
		private ExtendedImageViewButton(List<Image> listOfImages) {
			super.setText(TEXT);
			super.setPadding(INSETS);
			setListener(listOfImages);
		}
		
		private void setListener(List<Image> listOfImages) {
			final EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// SP
					final ScrollPane sp = new ScrollPane();
					sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
					sp.setFitToHeight(true);
					sp.setFitToWidth(true);
					sp.setPannable(true);

					// HBox
					final HBox hb = new HBox();
					hb.setAlignment(Pos.CENTER);

					// Binding
					sp.setContent(hb);

					for (Image image : listOfImages) {
						ImageViewer imageViewer = new ImageViewer(image);
						imageViewer.createUI();
						hb.getChildren().add(imageViewer.getRoot());
					}
					
					final Scene scene = new Scene(sp, 600, 275);
					SecondaryStage.show(scene, true);
				}
			};
			
			super.setOnAction(eh);
		}
		
	}

	private static class ImageViewerImageView extends ImageView {
		
		private static final int WIDTH = 250;
		private static final int HEIGHT = 250;
		
		private ImageViewerImageView(Image image) {
			super(image);
			super.setFitHeight(HEIGHT);
			super.setFitWidth(WIDTH);
		}
		
	}
	
	// -----------------------------------------------
	// Accessors
	// -----------------------------------------------
	// -----------------------------------------------
	// Accessors
	// -----------------------------------------------
	protected VBox getRoot() {
		return root;
	}

	
	protected char getCharIdentifier() {
		return listIdentifier;
	}

	
	protected List<Image> getListOfImages() {
		return listOfImages;
	}
	

}