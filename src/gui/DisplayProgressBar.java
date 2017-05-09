package gui;

import javafx.scene.control.ProgressBar;

/**
 *
 * @author Luis
 */
public class DisplayProgressBar {

    protected static ProgressBar pBar = new ProgressBar(0);

    public static void setProgress(double progress) {
        pBar.setProgress(progress);
    }
}
