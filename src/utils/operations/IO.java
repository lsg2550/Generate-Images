package utils.operations;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
            return null;
        }
    }

    public static void writeImage(BufferedImage bufferedImage, String fileName) {
        try {
            File file = new File("src/savedImages/" + fileName + ".png");
            file.mkdirs();
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
        }
    }

    public static BufferedImage toGray(BufferedImage original) {
        BufferedImage gray = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        return op.filter(original, gray);
    }
}
