package com.project_studio.jlayerfx.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.project_studio.jlayerfx.util.imagegeneration.ImageCache;
import com.project_studio.jlayerfx.util.io.IO;
import com.project_studio.utils.CloneUtils.Clone;
import com.project_studio.utils.ImageUtils;
import com.project_studio.utils.ObjectUtils;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class MenuSave implements Cloneable { // NOT USED BY CLASSES OUTSIDE PACKAGE

	private static Scene saveScene;
	private final static HBox VIEW_HBOX = new HBox(2.5);
	private final static ScrollPane SCROLLPANE = new ScrollPane(VIEW_HBOX);

	private MenuSave() {
	}

	static void init() {
		// Main Container
		VBox root = new VBox(10.0);

		// Button HBox
		HBox buttonHB = new HBox(5.0);
		buttonHB.setAlignment(Pos.CENTER);

		// Scrollpane
		SCROLLPANE.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		SCROLLPANE.setMinHeight(400);
		SCROLLPANE.setMinWidth(640);
		SCROLLPANE.setPannable(true);

		// Ouside ScrollPane
		Button save = new Button("Save");
		Button preview = new Button("Preview");
		Button cancel = new Button("Cancel");

		// Alignments
		VIEW_HBOX.setAlignment(Pos.CENTER);
		SCROLLPANE.setFitToHeight(true);
		SCROLLPANE.setFitToWidth(true);
		save.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.BASELINE_CENTER);

		// Misc
		save.setMaxSize(60, 20);
		GUIPreviewStage dgps = new GUIPreviewStage();
		dgps.init();

		// Children
		SCROLLPANE.setContent(VIEW_HBOX);
		buttonHB.getChildren().addAll(save, preview, cancel);
		root.getChildren().addAll(SCROLLPANE, buttonHB);
		saveScene = new Scene(root, 640, 480);

		// Handlers
		save.setOnAction(e -> {
			List<ImageView> imageViewList = new ArrayList<ImageView>(updateObservableList(images));
			Image drawnImage = ImageUtils.drawImageFromList(imageViewList);
			IO.saveImage(drawnImage);
			SecondaryStage.close();
		});

		preview.setOnAction(e -> {
			try {
				List<ImageView> imageViewList = new ArrayList<ImageView>(updateObservableList(images));
				Image drawnImage = ImageUtils.drawImageFromList(imageViewList);
				dgps.setPreviewImage(drawnImage);
				dgps.show();
			} catch (NullPointerException ex) {
			}
		});

		cancel.setOnAction(e -> {
			SecondaryStage.close();
		});

		// Listeners
		iLayer.addListener((MapChangeListener.Change<? extends Integer, ? extends ImageView> change) -> {
			// System.out.println("Hello from Map!");
		});
	}

	// Create Clones
	private static ObservableList<ImageView> images;

	// Create Layers
	private static ObservableMap<Integer, ImageView> iLayer = FXCollections.observableHashMap();

	// Show Save Menu
	static void show() {
		// Grab Images
		images = null;
		grabImages();

		// Show
		SecondaryStage.show(saveScene, false);
	}

	private static void grabImages() {
		if (!ImageCache.SELECTED_IMAGES.isEmpty()) {
			// Clear Past Images
			VIEW_HBOX.getChildren().clear();

			if (images == null) {
				images = FXCollections.observableArrayList(cloneAndReplace(ImageCache.SELECTED_IMAGES));
				images.forEach((imageView) -> {
					iLayer.put(images.indexOf(imageView), imageView); // Places into Map
				});
			} else {
				images.clear();
				iLayer.forEach((t, u) -> {
					images.add(u);
				});
			}

			// Iterate and place the clones into the GUI
			images.forEach((imageView) -> {
				VIEW_HBOX.getChildren().add(getList(imageView, images.indexOf(imageView)));
			});
		}
	}

	// ImageView MouseEvent Draggable Event
	private static MouseEvent dragDetected;

	// Generates a VBox that contains a checkbox and the imageviews placement in the selected arraylist
	private static VBox getList(ImageView imageView, int layer) {
		// Inside VIEW_HBOX
		HBox innerHB = new HBox(5.0);
		VBox innerVB = new VBox(2.5);

		// Inside InnerVBox
		TextField orderInLayer = new TextField("" + layer);
		CheckBox checkBox = new CheckBox();

		// Alignments
		orderInLayer.setAlignment(Pos.CENTER);
		innerHB.setAlignment(Pos.CENTER);
		innerVB.setAlignment(Pos.CENTER);

		// Misc
		orderInLayer.setMaxSize(32, 32);
		imageView.setFitWidth(250);
		imageView.setFitHeight(250);

		// Children
		innerHB.getChildren().addAll(orderInLayer, checkBox);
		innerVB.getChildren().addAll(imageView, innerHB);

		// Handlers
		orderInLayer.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (!newValue.equals("") && ObjectUtils.parseInteger(newValue) != -1) {
				int temp = ObjectUtils.parseInteger(newValue); // New Value

				if (iLayer.containsKey(temp)) {
					if (!iLayer.get(temp).isDisabled()) {
						swapMapValues(iLayer, temp, layer, imageView);
						grabImages();
					}
				} else {
					orderInLayer.setText(oldValue);
				}
			}
		});

		checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			orderInLayer.setDisable(newValue);
			imageView.setDisable(newValue);
		});

		return innerVB;
	}

	private static List<ImageView> updateObservableList(List<ImageView> oldList) {
		if (oldList != null) {
			List<ImageView> newList = new ArrayList<ImageView>(oldList.size());

			for (ImageView imageView : oldList) {
				if (!imageView.isDisabled()) {
					newList.add(imageView);
				}
			}

			return newList;
		}

		return null;
	}

	/**
	 * Method to clone images - due to Java's use of references, loading images that
	 * are in the main GUI only cause them to disappear when loading them here. So I
	 * am forced to clone them to prevent this.
	 *
	 * @toBeCloned - the images that the user has selected
	 */
	private static List<ImageView> cloneAndReplace(List<ImageView> toBeCloned) {
		List<ImageView> temp = new ArrayList<>(toBeCloned.size());

		toBeCloned.forEach((imageView) -> {
			try {
				temp.add(new ImageView((Image) new Clone(imageView.getImage()).clone()));
			} catch (CloneNotSupportedException ex) {
			}
		});

		return temp;
	}

	private static void swapMapValues(Map<Integer, ImageView> map, Integer newKey, Integer oldKey, ImageView newValue) {
		ImageView temp = map.get(newKey);
		map.replace(newKey, newValue);
		map.replace(oldKey, temp);
	}

	private static class GUIPreviewStage {

		private final StackPane sp = new StackPane();
		private final ImageView iv = new ImageView();
		private final Scene scene = new Scene(sp, 800, 600);
		private final Stage stage = new Stage();

		void init() {
			sp.getChildren().add(iv);
			stage.setScene(scene);
		}

		void setPreviewImage(Image image) {
			iv.setImage(image);
		}

		void show() {
			stage.show();
		}
	}
	
}
