package utils.parse;

import java.io.File;
import java.util.List;

/**
 *
 * @author Luis
 */
public class TryParse {
    
    public static boolean TryListOfFileArray(Object testObject) {
        if (testObject instanceof List) {
            if (TryFileArray(((List) testObject).get(0))) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean TryFileArray(Object testObject) {
        return testObject instanceof File[];
    }
}
