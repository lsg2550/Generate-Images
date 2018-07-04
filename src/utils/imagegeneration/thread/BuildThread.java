package utils.imagegeneration.thread;

import utils.imagegeneration.Cache;
import utils.operations.io.IO;
import utils.settings.Settings;

/**
 *
 * @author Luis
 */
public class BuildThread {

    private static Thread buildThread = new Thread();

    public static void runBuild() {
        if (!buildThread.isAlive()) {
            Cache cache = null;
            if (Settings.INSTANCE.isLoadType()) {
                cache = new Cache(IO.readDirectorySingle());
            } else {
                cache = new Cache(IO.readDirectoryMultiple());
            }

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
