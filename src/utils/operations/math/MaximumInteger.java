package utils.operations.math;

import java.util.List;

/**
 *
 * @author Luis
 */
public class MaximumInteger {

    /**
     * @param <T>
     * @param objectList
     * @return returns largest array length from a list of an object array
     */
    public static <T> int maximumInteger_fromListOfArray(List<T[]> objectList) {
        int largestArraySize = 0;

        for (Object[] list : objectList) {
            if (list.length >= largestArraySize) {
                largestArraySize = list.length;
            }
        }

        return largestArraySize;
    }

}
