package utils.operations.enhancednodes;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class EnhancedImageView extends ImageView {
    
    private ColorAdjust grayScale = new ColorAdjust(1.0, -0.75, 0.0, -0.5);
    
    public EnhancedImageView() {
        this.setFitHeight(225);
        this.setFitWidth(225);
    }
    
    public EnhancedImageView(Image image) {
        this.setImage(image);
        this.setFitHeight(225);
        this.setFitWidth(225);
    }
    
    public void applyGrayscale() {
        this.setEffect(grayScale);
    }
    
    public void removeEffect() {
        this.setEffect(null);
    }
    
}
