package utils.cloning;

import javafx.scene.image.Image;

/**
 *
 * @author Luis
 */
public class ImageClone implements Cloneable {

    private Image image;

    public ImageClone(Image image) {
        this.image = image;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ImageClone ivc = new ImageClone(image);
        return ivc.image;
    }
}
