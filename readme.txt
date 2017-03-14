1. Launch application

2. On the menubar, select "File" -> "Open". Open a file directory with your images in it.

	- Currently the only supported reading is: "a000","b000","c000","c001"
	- Currently the only supported image type is: ".png", ".jpg"
	- The last letter (english alphabet) read in their file name will be the list they are sent to.

3. After the loading of the directory, the files and their images will be displayed in a scrollpane.

	- In cases where there is only one image that contains a certain letter. That image will appear with a checkbox under it to enable/disable the image.

	- In cases where there are two images that contain the same letter. The first read image will appear with a button labeled 'Extend'
	under it. By pressing extend a new window will open and you can view/enable/disable other images using that same letter.

	- By default all images are disabled. Meaning, all checkboxes will be selected and no image will be displayed in the preview.

	- When checking/unchecking, a preview of the (to be) generated image will appear in the bottom half of the program.

	- You may click on the preview to show a new stage showing the full image. By default the window will open with a 800x600 resolution, but the 
	window is resizable. Currently zooming or scrolling is not supported and the images in the scrollpane are not clickable.

4. Once you are done creating your image, go back to the menubar and select "File" -> "Save" and save your image as a .png or a .jpg.

Other options:
Help -> About = Readme and Information about the program
Help -> Theme -> * = Changes CSS
Help -> Benchmark -> * = Provides information about build-time or memory usage
File -> Exit = Closes the application

//Version 1.05