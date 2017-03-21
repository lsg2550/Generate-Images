package utils.operations.enhancednodes;

import javafx.scene.control.Button;

/**
 *
 * @author Luis
 */
public class EnhancedButton extends Button {

    public EnhancedButton(String label) {
        this.setPadding(EnhancedInsets.getI());
        this.setMinHeight(16);
        this.setText(label);
    }
    
}
