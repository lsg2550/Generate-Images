package gfx.gui.nodes;

import javafx.scene.control.ProgressBar;

/**
 *
 * @author Luis
 */
public class Progress { //Diplays How Much Loading Of Files Is Done

    private static ProgressBar pBar = new ProgressBar(0);

    public static void resetBar() {
        pBar.setProgress(0);
    }

    public static void setProgress(double currentProgress) {
        pBar.setProgress(currentProgress);
    }

    public static ProgressBar getpBar() {
        return pBar;
    }
}
