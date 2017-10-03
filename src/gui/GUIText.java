package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Luis
 */
public class GUIText { //USED BY CLASSES OUTSIDE PACKAGE

    private static final Menu DIRECTORY_TEXT = new Menu();
    private static final Text UPDATE_TEXT = new Text();

    static void init() {
        Desktop dt = Desktop.getDesktop();

        DIRECTORY_TEXT.setOnAction(e -> {
            try {
                dt.open(new File(DIRECTORY_TEXT.getText()));
            } catch (IOException ex) {
            }
        });
    }

    /*Accessible only by inside package - GUI purposes*/
    static Menu getDirectoryTextMenu() {
        return DIRECTORY_TEXT;
    }

    static Text getUpdateTextNode() {
        return UPDATE_TEXT;
    }

    /*Accessible by other classes - In and outside package*/
    public static void setUpdateText(String message) {
        UPDATE_TEXT.setText(message);
    }

    public static void setDirectoryText(String message) {
        DIRECTORY_TEXT.setText(message);
    }

    public static String getDirectoryText() {
        return DIRECTORY_TEXT.getText();
    }
}
