package cache;

import gui.CenterDisplay;
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
public class SelectedImageList {
    
    public static ArrayList<ImageView> selectedImages = new ArrayList(30);
    
    public static void draw() {
        if (!selectedImages.isEmpty()) {
            Canvas cv = new Canvas(selectedImages.get(0).getImage().getWidth(), selectedImages.get(0).getImage().getHeight());
            GraphicsContext gc = cv.getGraphicsContext2D();
            
            for (ImageView selectedImage : selectedImages) {
                gc.drawImage(selectedImage.getImage(), 0, 0);
            }
            
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            
            CenterDisplay.toBeGeneratedIV.setImage(cv.snapshot(sp, null));
        } else {
            CenterDisplay.toBeGeneratedIV.setImage(null);
        }
    }
}
