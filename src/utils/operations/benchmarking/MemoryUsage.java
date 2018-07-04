package utils.operations.benchmarking;

/**
 *
 * @author Luis
 */
public class MemoryUsage {

    //private final static int MEGABYTES = 1048576;
    //private final static int GIGABYTES = 1073741824;
    public static long currentHeapSize() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long heapSize() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long freeHeapSize() {
        return Runtime.getRuntime().freeMemory();
    }

    public static long maxHeapSize() {
        return Runtime.getRuntime().maxMemory();
    }
}
