package com.project_studio.jlayerfx.util.imagegeneration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.project_studio.jlayerfx.util.io.IO;
import com.project_studio.utils.ImageUtils;

import javafx.scene.image.Image;

public class GenerateBatch {

	// Initial Variables
	private Image[][] subDirectories;

	// Generated During Batch Process
	private final List<Image> buildImageList = new ArrayList<Image>();
	private final List<Image> tempImageList = new LinkedList<Image>();

	/**
	 * Batch using a Base Image + Sub Directories
	 */
	public GenerateBatch(Image baseImage, Image[][] subDirectories) {
		this.subDirectories = subDirectories;
		batchPrimary(baseImage);
	}

	/**
	 * Batch using a Base Directory + Sub Directories
	 */
	public GenerateBatch(Image[] baseDirectory, Image[][] subDirectories) {
		this.subDirectories = subDirectories;
		for (Image image : baseDirectory) {
			batchPrimary(image);
		}
	}

	public void save() {
		IO.saveBatch(buildImageList);
		cleanup();
	}

	private void batchPrimary(Image base) {
		// Set Base Image - We WANT the base image to be the first index
		tempImageList.add(0, base);

		// Batch
		batchSecondary(0);

		// Reset
		tempImageList.clear();
	}

	private void batchSecondary(int rowIndex) {
		for (Image subDirectory : subDirectories[rowIndex]) {
			if (subDirectory != null) {
				try {
					tempImageList.set(rowIndex + 1, subDirectory);
				} catch (IndexOutOfBoundsException e) {
					tempImageList.add(rowIndex + 1, subDirectory);
				}

				Image drawnImage = ImageUtils.drawImageFromList(tempImageList);
				buildImageList.add(drawnImage);

				// Check if more rows exist, if they do move on to the next row, o/w move on to
				// the next column (if any)
				if (rowIndex + 1 < subDirectories.length) {
					batchSecondary(rowIndex + 1);
				}
			}
		}
	}

	private void cleanup() {
		subDirectories = null;
		tempImageList.clear();
		buildImageList.clear();
	}
}
