package utils.drawing;

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

    public static Image draw(List<ImageView> SELECTED_IMAGES) {
        if (!SELECTED_IMAGES.isEmpty()) {
            Canvas CV = new Canvas(SELECTED_IMAGES.get(0).getImage().getWidth(),
                    SELECTED_IMAGES.get(0).getImage().getHeight());
            GraphicsContext GC = CV.getGraphicsContext2D();

            SELECTED_IMAGES.forEach((selectedImage) -> {
                GC.drawImage(selectedImage.getImage(), 0, 0);
            });

            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);

            return CV.snapshot(sp, null);
        } else {
            return null;
        }
    }

}
