package utils.operations.io;

import building.GeneratedImage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static utils.operations.io.FileSelector.filechooser;
import static utils.operations.io.FileSelector.saveFile;

/**
 *
 * @author Luis
 */
public class IO {

    public static BufferedImage readImage(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeImage(BufferedImage bufferedImage, File saveFile) {
        try {
            String fileType = saveFile.toString();
            saveFile.mkdirs();
            ImageIO.write(bufferedImage, fileType.substring(fileType.length() - 3), saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile() {
        saveFile = filechooser.showSaveDialog(null);
        IO.writeImage(GeneratedImage.getGeneratedImage(), saveFile);
    }
}
