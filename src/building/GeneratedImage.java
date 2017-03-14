package building;

import java.awt.image.BufferedImage;

/**
 *
 * @author Luis
 */
public class GeneratedImage {

    private static BufferedImage generatedImage;

    public static BufferedImage getGeneratedImage() {
        return generatedImage;
    }

    static void setGeneratedImage(BufferedImage aGeneratedImage) {
        generatedImage = aGeneratedImage;
    }

}
