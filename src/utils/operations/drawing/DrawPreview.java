package utils.operations.drawing;

import java.util.List;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author Luis
 */
public class DrawPreview {

    //List of ImageViews
    public static Image drawFromImageViewList(List<ImageView> images) {
        if (!images.isEmpty()) {
            Canvas CV = new Canvas(images.get(0).getImage().getWidth(), images.get(0).getImage().getHeight());
            GraphicsContext GC = CV.getGraphicsContext2D();

            for (ImageView selectedImage : images) {
                GC.drawImage(selectedImage.getImage(), 0, 0);
            }

            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);

            return CV.snapshot(sp, null);
        }

        return null;
    }

    //List of Images
    public static Image drawFromImageList(List<Image> images) {
        Canvas CV = new Canvas(images.get(0).getWidth(), images.get(0).getHeight());
        GraphicsContext GC = CV.getGraphicsContext2D();

        for (Image image : images) {
            GC.drawImage(image, 0, 0);
        }

        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);

        return CV.snapshot(sp, null);
    }
}
