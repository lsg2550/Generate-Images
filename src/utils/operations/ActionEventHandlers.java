package utils.operations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.notifications.AlertBox;

/**
 *
 * @author Luis
 */
public class ActionEventHandlers {

    public static void directionButton(ArrayList<BufferedImage> splitImage, ImageView imageView, Button... directionButton) {
        List<Image> images = new ArrayList();

        for (BufferedImage image : splitImage) {
            images.add(SwingFXUtils.toFXImage(image, null));
        }

        EnhancedIterator<Image> iteratorIMG = new EnhancedIterator(images, 0);

        /*Buttons*/
        directionButton[0].setOnAction(e -> {   //Left Button
            if (!iteratorIMG.hasPrevious()) {
                new AlertBox("You've reached the end of the list!");
            } else {
                imageView.setImage(iteratorIMG.previous());
            }
        });
        directionButton[1].setOnAction(e -> {   //Right Button
            if (!iteratorIMG.hasNext()) {
                new AlertBox("You've reached the end of the list!");
            } else {
                imageView.setImage(iteratorIMG.next());
            }
        });
    }

    public static void enableDisable(ImageView imageView, CheckBox enableDisable, Button... directionButton) {
        enableDisable.selectedProperty().addListener((o, oV, nV) -> {
            if (enableDisable.isSelected()) {
                Image img = imageView.getImage();
                savedImage.setImage(img);

                for (Button button : directionButton) {
                    button.setDisable(true);
                }
                imageView.setDisable(true);

                BufferedImage temp = SwingFXUtils.fromFXImage(img, null);
                imageView.setImage(SwingFXUtils.toFXImage(IO.toGray(temp), null));
            } else {
                for (Button button : directionButton) {
                    button.setDisable(false);
                }
                imageView.setDisable(false);
                imageView.setImage(savedImage.getImage());
            }
            BuildCache.liveImageBuilding();
        });
    }

    public static void imageViewListener(ImageView imageView) {
        imageView.imageProperty().addListener((o, oV, nV) -> {
            BuildCache.liveImageBuilding();
        });
    }

    //Make a backup of current image in imageview
    private static class savedImage {

        private static Image image;

        public static Image getImage() {
            return image;
        }

        public static void setImage(Image aImage) {
            image = aImage;
        }
    }
}
