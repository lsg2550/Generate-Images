package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class IO {

    public static Object[] ChooseFolder() {
        DirectoryChooser dChooser = new DirectoryChooser();
        File selectedDirectory = dChooser.showDialog(new Stage());
        //ArrayList<BufferedImage> imgArrayList = new ArrayList();
        Object[] obj = new Object[2];

        /*Conditional: Used to Display Information to User*/
        if (selectedDirectory == null) {
            //No Directory was Selected
            obj[0] = obj[1] = "No Directory Was Selected!";
            return obj;
        } else {
            //Directory was Selected
            File[] listOfFiles = selectedDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    if (name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg")) {
                        return true;
                    }
                    return false;
                }
            });

            obj[0] = splitImages(listOfFiles);
            obj[1] = selectedDirectory.getAbsolutePath();
            return obj;
        }
    }

    public static ArrayList<ArrayList<BufferedImage>> splitImages(File[] files) {
        ArrayList<ArrayList<BufferedImage>> splitImgs = new ArrayList();
        ArrayList<String> stringSeparator = new ArrayList();

        for (File file : files) {
            String temp = file.getName().substring(0, 1);
            if (!stringSeparator.contains(temp)) {
                stringSeparator.add(temp);
            }
        }
        /*
        for (String string : stringSeparator) {
            System.out.println(string);
        }
         */
        for (String string : stringSeparator) {
            for (String string1 : stringSeparator) {
                
            }
        }

        return splitImgs;
    }

    public static BufferedImage readImage(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void writeImage() {

    }
}
