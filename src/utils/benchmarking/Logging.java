package utils.benchmarking;

/**
 *
 * @author Luis
 */
public class Logging {

    private static long startTime, endTime;

    public static void setStartTime(long aStartTime) {
        startTime = aStartTime;
    }

    public static void setEndTime(long aEndTime) {
        endTime = aEndTime;
    }

    public static long benchmarkTimeInMilli() {
        return endTime - startTime;
    }

    public static long benchmarkTimeInSeconds() {
        return (endTime - startTime) / 1000;
    }
}
