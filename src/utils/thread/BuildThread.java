package utils.thread;

import utils.cache.Cache;
import utils.io.Read;

/**
 *
 * @author Luis
 */
public class BuildThread {

    private static Thread buildThread = new Thread();

    public static void runBuild() {
        if (!buildThread.isAlive()) {
            Cache cache = new Cache(Read.read());

            buildThread = new Thread(cache);
            buildThread.start();
        } else {
            ThreadAlert.show();
        }
    }

    static void interruptThread() {
        buildThread.interrupt();
    }
}
