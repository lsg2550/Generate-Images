package utils.thread;

import cache.Cache;
import utils.io.Read;

/**
 *
 * @author Luis
 */
public class BuildThread {

    private static Thread buildThread = new Thread();

    public static void runBuild() {
        if (!buildThread.isAlive()) {
            Cache cache = new Cache(Read.readDirectory());

            buildThread = new Thread(cache);
            buildThread.start();
        } else {
            ThreadAlert.show();
        }
    }

    protected static void interruptThread() {
        buildThread.interrupt();
    }
}
