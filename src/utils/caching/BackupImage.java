package utils.caching;

import javafx.scene.image.Image;

/**
 *
 * @author Luis
 */
class BackupImage {

    //Make a backup of current image in imageview
    private Image image;

    protected BackupImage() {
    }

    protected Image getImage() {
        return image;
    }

    protected void setImage(Image aImage) {
        image = aImage;
    }
}
