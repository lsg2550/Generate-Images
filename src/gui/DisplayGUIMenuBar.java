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
class DisplayGUIMenuBar { //NOT USED BY CLASSES OUTSIDE PACKAGE

    //Menu
    private static final MenuBar MENU_BAR = new MenuBar();

    protected static void init() {
        //Menu
        Menu file = new Menu("File"),
                help = new Menu("Help");

        //Menu Children
        MENU_BAR.getMenus().addAll(file, help, DisplayGUIText.getDirectoryTextMenu());

        //MenuItem
        MenuItem open = new MenuItem("Open"), //File Menu
                save = new MenuItem("Save"),
                exit = new MenuItem("Exit");
        MenuItem about = new MenuItem("About"), //Help Menu
                settings = new MenuItem("Settings");

        //Children
        file.getItems().addAll(open, save, new SeparatorMenuItem(), exit);
        help.getItems().addAll(about, settings);

        //Handlers
        open.setOnAction(e -> {
            if (Settings.loadTypeWindow) {
                DisplayMenuOpen.show();
            }
            BuildThread.runBuild();
        });
        save.setOnAction(e -> {
            DisplayMenuSave.show(); //Window to display chosen images where user will be able to re-arrange order etc before writing image
            //Save.saveFile(DisplayGUIPreviewImageView.getImageFromImageView());
        });
        exit.setOnAction(e -> {
            Thread.currentThread().interrupt();
            System.exit(0);
        });
        about.setOnAction(e -> {
            DisplayMenuAbout.show();
        });
        settings.setOnAction(e -> {
            DisplayMenuSettings.show();
        });
    }

    protected static MenuBar getMENU_BAR() {
        return MENU_BAR;
    }

}
