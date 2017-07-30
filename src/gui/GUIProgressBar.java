package gui;

import javafx.scene.control.ProgressBar;

/**
 *
 * @author Luis
 */
public class GUIProgressBar { //USED BY CLASSES OUTSIDE PACKAGE

    private static final ProgressBar PROGRESS_BAR = new ProgressBar(0);

    public static ProgressBar getpBar() {
        return PROGRESS_BAR;
    }
}
