package utils.operations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import utils.notifications.AlertBox;

/**
 *
 * @author Luis
 */
public class Handlers {

    public static Button directionButton(ArrayList<BufferedImage> splitImage,
            ImageView imageView, Button directionButton, boolean directionBoolean) {
        //Left = True; Right = False
        if (directionBoolean) { //If True
            directionButton.setOnAction(e -> {
                if ((splitImage.indexOf(SwingFXUtils.fromFXImage(imageView.getImage(), null)) - 1) < 0) {
                    new AlertBox("You've reached the end of the list!");
                } else {
                    System.out.println("REACHED.");
                    imageView.setImage(SwingFXUtils.toFXImage(splitImage.get(splitImage.indexOf(SwingFXUtils.fromFXImage(imageView.getImage(), null)) - 1), null));
                }
            });
        } else { //If False
            directionButton.setOnAction(e -> {
                if ((splitImage.indexOf(SwingFXUtils.fromFXImage(imageView.getImage(), null)) + 1) > splitImage.size()) {
                    new AlertBox("You've reached the end of the list!");
                } else {
                    System.out.println("REACHED.");
                    imageView.setImage(SwingFXUtils.toFXImage(splitImage.get(splitImage.indexOf(SwingFXUtils.fromFXImage(imageView.getImage(), null)) + 1), null));
                }
            });
        }
        return directionButton;
    }

    public static CheckBox enableDisable(CheckBox enableDisable) {

        return enableDisable;
    }
}
