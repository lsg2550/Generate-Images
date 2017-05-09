package cache;

import gui.DisplayPreviewImageView;
import java.util.ArrayList;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author Luis
 */
public class DrawPreview {

    private static final ArrayList<ImageView> SELECTED_IMAGES = new ArrayList<ImageView>(30);

    protected static void draw() {
        if (!SELECTED_IMAGES.isEmpty()) {
            Canvas CV = new Canvas(SELECTED_IMAGES.get(0).getImage().getWidth(), SELECTED_IMAGES.get(0).getImage().getHeight());
            GraphicsContext GC = CV.getGraphicsContext2D();

            for (ImageView selectedImage : SELECTED_IMAGES) {
                GC.drawImage(selectedImage.getImage(), 0, 0);
            }

            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);

            DisplayPreviewImageView.setImageForImageView(CV.snapshot(sp, null));
        } else {
            DisplayPreviewImageView.setImageForImageView(null);
        }
    }

    protected static void remove(ImageView ivToRemove) {
        SELECTED_IMAGES.remove(ivToRemove);
    }

    protected static void add(ImageView ivToAdd) {
        SELECTED_IMAGES.add(ivToAdd);
    }

    protected static void clear() {
        SELECTED_IMAGES.clear();
    }

    public static ArrayList<ImageView> getSELECTED_IMAGES() {
        return SELECTED_IMAGES;
    }
}
