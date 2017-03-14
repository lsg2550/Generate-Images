package gfx.gui.nodes;

import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class SelectedDirectory {

    private static Text directoryRead = new Text();

    public static void setText(String message) {
        directoryRead.setText(message);
    }

    public static Text getDirectoryRead() {
        return directoryRead;
    }
}
