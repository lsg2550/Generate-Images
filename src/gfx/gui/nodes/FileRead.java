package gfx.gui.nodes;

import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class FileRead {

    private static Text fileRead = new Text();

    public static void setText(String message) {
        fileRead.setText(message);
    }

    public static Text getFileRead() {
        return fileRead;
    }
}
