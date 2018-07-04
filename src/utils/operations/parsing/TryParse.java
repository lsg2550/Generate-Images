package utils.operations.parsing;

import java.io.File;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Luis
 */
public class TryParse {

    public static boolean TryListOfFileArray(Object o) {
        if (o instanceof List) {
            if (((List) o).isEmpty()) {
                return false;
            }

            if (TryFileArray(((List) o).get(0))) {
                return true;
            }
        }

        return false;
    }

    public static boolean TryFileArray(Object o) {
        return o instanceof File[];
    }

    public static boolean TryInteger(Object o) {
        return o instanceof Integer;
    }

    public static boolean TryImage(Object o) {
        return o instanceof Image;
    }

    public static int TryInt(String o) {
        try {
            int i = Integer.parseInt(o);
            return i;
        } catch (ClassCastException e) {
            return -1;
        }
    }
}
