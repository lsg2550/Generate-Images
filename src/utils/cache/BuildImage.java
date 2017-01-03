package utils.cache;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class BuildImage {

    public static BufferedImage buildImageLive(ArrayList<BufferedImage> img) {
        try {
            Graphics2D g = img.get(0).createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (BufferedImage bufferedImage : img) {
                g.drawImage(bufferedImage, 0, 0, null);
            }

            g.dispose();
            return img.get(0);
        } catch (IndexOutOfBoundsException e) {
            return new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        }
    }
}
