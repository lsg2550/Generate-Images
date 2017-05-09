package utils.benchmarking;

/**
 *
 * @author Luis
 */
public class MemoryUsage {

    public static long memoryUsageInBytes() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long memoryUsageInKBytes() {
        return memoryUsageInBytes() / 1024;
    }

    public static long memoryUsageInMBytes() {
        return memoryUsageInKBytes() / 1024;
    }
}
