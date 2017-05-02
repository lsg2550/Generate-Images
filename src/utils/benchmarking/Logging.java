package utils.benchmarking;

/**
 *
 * @author Luis
 */
public class Logging {

    private static long startTime, endTime;

    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    public static void setEndTime() {
        endTime = System.currentTimeMillis();
    }

    public static long benchmarkTime() {
        return endTime - startTime;
    }
}
