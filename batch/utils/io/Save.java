package utils.io;

import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import static utils.io.IO.DIRECTORY_CHOOSER;

/**
 *
 * @author Luis
 */
public class Save {

    public static void saveFile(Image image) {
        File saveDirectory = new File(DIRECTORY_CHOOSER.getInitialDirectory().getAbsolutePath() + "/output/");

        if (!saveDirectory.exists()) {
            System.out.println("Creating Directory...");
            saveDirectory.mkdirs();
        } else {
            System.out.println("Output Directory Found...");
        }

        int counter = 0;
        boolean fileExists = true;

        while (fileExists) {
            String imageName = "/output" + counter + ".png";
            File imageFileName = new File(saveDirectory.getAbsolutePath() + imageName);

            if (imageFileName.exists()) {
                //System.out.println("File Exists... Changing Name...");
                counter++;
            } else {
                System.out.println("Generating Image...");
                System.out.println("Saving New Image In: " + imageFileName.getAbsolutePath());

                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imageFileName);
                } catch (IOException ex) {
                    System.out.println("Issue Saving File!");
                } finally {
                    fileExists = false;
                }
            }
        }
    }
}
