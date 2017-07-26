package batchgi;

import java.util.List;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author Luis
 */
class DrawPreview {

    protected static Image draw(List<Image> images) {
        Canvas CV = new Canvas(images.get(0).getWidth(), images.get(0).getHeight());
        GraphicsContext GC = CV.getGraphicsContext2D();

        //System.out.println("Drawing...");
        for (Image image : images) {
            GC.drawImage(image, 0, 0);
        }

        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);

        return CV.snapshot(sp, null);
    }
}
