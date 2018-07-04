package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import utils.settings.Settings;
import utils.imagegeneration.thread.BuildThread;

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
        MenuItem openImgGen = new MenuItem("Image-Generation"), openBatGen = new MenuItem("Batch-Generation"); //Open Menu
        MenuItem save = new MenuItem("Save Image"), exit = new MenuItem("Exit"); //File Menu
        MenuItem about = new MenuItem("About"), settings = new MenuItem("Settings"), howto = new MenuItem("How-To"); //Help Menu

        //Children
        file.getItems().addAll(open, save, new SeparatorMenuItem(), exit);
        help.getItems().addAll(about, settings, howto);
        open.getItems().addAll(openImgGen, openBatGen);

        //Handlers
        openImgGen.setOnAction(e -> {
            if (Settings.INSTANCE.isLoadTypeWindow()) {
                MenuOpenGenerateImage.show();
            }

            BuildThread.runBuild();
        });
        openBatGen.setOnAction(e -> {
            MenuOpenGenerateBatch.show();
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
        howto.setOnAction(e -> {
            MenuHowTo.show();
        });
    }

    static MenuBar getMENU_BAR() {
        return MENU_BAR;
    }
}
