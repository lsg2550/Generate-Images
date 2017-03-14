package utils.operations.parsing;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class TryImageView {

    //Purposefully attempts to catch an error to determine whether the node is an imageview or not
    public static boolean TryImageView(Node node) {
        try {
            ImageView imgV = (ImageView) node;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
