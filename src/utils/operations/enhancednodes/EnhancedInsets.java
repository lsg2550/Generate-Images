package utils.operations.enhancednodes;

import javafx.geometry.Insets;

/**
 *
 * @author Luis
 */
public class EnhancedInsets {
    
    private static Insets i = new Insets(0, 5, 0, 5);
    
    public static Insets getI() {
        return i;
    }
    
    public static void setI(Insets aI) {
        i = aI;
    }
}
