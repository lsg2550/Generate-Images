package gui;

import javafx.scene.control.Menu;
import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class DisplayText {

    protected static Text updateText = new Text();
    protected static Menu directoryText = new Menu();

    public static void setUpdateText(String message) {
        updateText.setText(message);
    }

    public static void setDirectoryText(String message) {
        directoryText.setText(message);
    }

    public static String getUpdateText() {
        return updateText.getText();
    }

    public static String getDirectoryText() {
        return directoryText.getText();
    }
}
