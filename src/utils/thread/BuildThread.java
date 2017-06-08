package utils.thread;

import cache.CacheMultiple;
import cache.CacheSingle;
import gui.DisplayAlert;
import java.io.File;
import java.util.List;
import utils.io.IO;

/**
 *
 * @author Luis
 */
public class BuildThread {

    private static Thread buildThread = new Thread();

    public static void runSingleBuild() {
        if (!buildThread.isAlive()) {
            File[] temp = IO.readDirectory();

            buildThread = new Thread(() -> {
                new CacheSingle().selectFolder(temp);
            });

            buildThread.start();
        } else {
            DisplayAlert.show("You're currently loading images would you like to stop?");
            //System.out.println("You're currently loading images would you like to stop?");
        }
    }

    public static void runMultiBuild() {
        if (!buildThread.isAlive()) {
            List<File[]> temp = IO.readMultipleDirectories();

            buildThread = new Thread(() -> {
                new CacheMultiple().selectFolder(temp);
            });

            buildThread.start();
        } else {
            DisplayAlert.show("You're currently loading images would you like to stop?");
            //System.out.println("You're currently loading images would you like to stop?");
        }
    }
}
