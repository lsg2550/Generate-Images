package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import utils.settings.Settings;
import utils.thread.BuildThread;

/**
 *
 * @author Luis
 */
class GUIMenuBar { //NOT USED BY CLASSES OUTSIDE PACKAGE

    //Menu
    private static final MenuBar MENU_BAR = new MenuBar();

    static void init() {
        //Menu
        Menu file = new Menu("File"), help = new Menu("Help"); //Main GUI
        Menu open = new Menu("Open"); //SubMenu

        //Menu Children
        MENU_BAR.getMenus().addAll(file, help, GUIText.getDirectoryTextMenu());

        //MenuItem
        MenuItem openGI = new MenuItem("Image-Gen"), //Open Menu
                openBA = new MenuItem("Batch");
        MenuItem save = new MenuItem("Save"), //File Menu
                exit = new MenuItem("Exit");
        MenuItem about = new MenuItem("About"), //Help Menu
                settings = new MenuItem("Settings");

        //Children
        file.getItems().addAll(open, save, new SeparatorMenuItem(), exit);
        help.getItems().addAll(about, settings);
        open.getItems().addAll(openGI, openBA);

        //Handlers
        openGI.setOnAction(e -> {
            if (Settings.loadTypeWindow) {
                MenuOpenGI.show();
            }
            BuildThread.runBuild();
        });
        openBA.setOnAction(e -> {
            MenuOpenBA.show();
        });
        save.setOnAction(e -> {
            MenuSave.show(); //Window to display chosen images where user will be able to re-arrange order etc before writing image
        });
        exit.setOnAction(e -> {
            Thread.currentThread().interrupt();
            System.exit(0);
        });
        about.setOnAction(e -> {
            MenuAbout.show();
        });
        settings.setOnAction(e -> {
            MenuSettings.show();
        });
    }

    static MenuBar getMENU_BAR() {
        return MENU_BAR;
    }
}
