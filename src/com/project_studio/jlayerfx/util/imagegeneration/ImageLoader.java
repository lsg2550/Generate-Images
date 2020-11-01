package com.project_studio.jlayerfx.util.imagegeneration;

import java.io.File;
import java.util.List;

import com.project_studio.jlayerfx.gui.GUI;
import com.project_studio.utils.FileUtils;
import com.project_studio.utils.ObjectUtils;
import com.project_studio.utils.StringUtils;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class ImageLoader implements Runnable {

	private Object selectedDirectory;

	public ImageLoader(Object selectedDirectory) {
		this.selectedDirectory = selectedDirectory;
	}

	@Override
	public void run() {
		if (ObjectUtils.isNotNullOrEmpty(selectedDirectory)) {
			// Garbage Collection
			ImageCache.cleanup();

			// Update Text
			updateProgress("Loading Images...", 0.25);

			// Processing
			if (ObjectUtils.isInstanceOf(selectedDirectory, File[].class)) { // Single Directory
				handleSingleDirectory();
			} else if (ObjectUtils.isInstanceOf(selectedDirectory, List.class) && ObjectUtils.isInstanceOf(((List<?>) selectedDirectory).get(0), File[].class)) { // Multiple Directories
				handleMultipleDirectories();
			} else {
				updateProgress("Files were not loaded correctly...", 0);
				return;
			}

			// Test for User Cancellation
			try {
				updateProgress("Loading Images...", 0.5);
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				updateProgress("Process Halted.", 0);
				Platform.runLater(() -> { 
					// Because the check if the current directory is already selected will trigger even if load was canceled
					GUI.updateDirectoryText("");
				});
				return;
			}

			// Builds
			for (ImageViewer cacheType : ImageCache.LOADED_IMAGES) {
				cacheType.createUI();
				Platform.runLater(() -> {
					GUI.addChildToCenterHBox(cacheType.getRoot());
				});
			}

			// DONE
			updateProgress("Done!", 1.0);
		} else {
			updateProgress("No directory was selected or current directory is already selected...", 0);
		}
	}

	// -----------------------------------------------
	// Private Functions
	// -----------------------------------------------
	private void handleSingleDirectory() {
		for (File file : (File[]) selectedDirectory) {
			// Single will still read multiple directories so to prevent this check if the file is a directory itself.
			if (file.isDirectory()) { continue; }

			boolean cacheTypeExists = false; // False = cacheType doesn't exist; True = cacheType exists;
			String filePath = FileUtils.getPathFromFile(file);
			if(StringUtils.isBlank(filePath)) {
				updateProgress("File path not valid.", 0);
				return;
			}

			for (ImageViewer cacheType : ImageCache.LOADED_IMAGES) {
				String fileName = file.getName().substring(0, file.getName().length() - 4); // Substring up to extension
				if (cacheType.getCharIdentifier() == StringUtils.findFirstCharacterInString(fileName)) {
					cacheType.getListOfImages().add(new Image(filePath));
					cacheTypeExists = true;
					break;
				}
			}

			if (!cacheTypeExists) {
				String fileName = file.getName().substring(0, file.getName().length() - 4); // Substring up to extension
				char identifier = StringUtils.findFirstCharacterInString(fileName);
				ImageViewer imageViewer = new ImageViewer(identifier, new Image(filePath));
				ImageCache.addToLoaded(imageViewer);
			}
		}
	}
	
	private void handleMultipleDirectories() {
		for (File[] files : (List<File[]>) selectedDirectory) {
			ImageViewer imageViewer = new ImageViewer(files);
			ImageCache.addToLoaded(imageViewer);
		}
	}
	
	// -----------------------------------------------
	// GUI Functions
	// -----------------------------------------------
	private void updateProgress(String message, double progress) {
		Platform.runLater(() -> {
			GUI.updateProgressBar(progress);
			GUI.updateStatusText(message);
		});
	}
	
}