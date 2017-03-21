package utils.operations.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import static utils.operations.io.FileSelector.filechooser;
import static utils.operations.io.FileSelector.saveFile;

/**
 *
 * @author Luis
 */
public class IO {

    private static Image image;
    public static BufferedImage bI;

    public static void readImage(String fileURL, ArrayList<Image> imageAL) {
        image = new Image(fileURL);
        imageAL.add(image);
        image = null;
    }

    public static void writeImage(BufferedImage bufferedImage, File saveFile) {
        try {
            String fileType = saveFile.toString();
            saveFile.mkdirs();
            ImageIO.write(bufferedImage, fileType.substring(fileType.length() - 3), saveFile);
        } catch (IOException e) {
        }
    }

    public static void writeFile() {
        saveFile = filechooser.showSaveDialog(null);
        FileSelector.setSaveDirectory();
        IO.writeImage(bI, saveFile);
    }
}
