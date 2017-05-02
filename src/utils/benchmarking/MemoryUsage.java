package utils.benchmarking;

/**
 *
 * @author Luis
 */
public class MemoryUsage {

    public static long memoryUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long memoryUsageInKBytes() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
    }

    public static long memoryUsageInMBytes() {
        return memoryUsageInKBytes() / 1024;
    }
}
