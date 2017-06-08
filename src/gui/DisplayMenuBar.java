package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import utils.io.Save;
import utils.thread.BuildThread;

/**
 *
 * @author Luis
 */
class DisplayMenuBar { //NOT USED BY CLASSES OUTSIDE PACKAGE

    //Menu
    private static final MenuBar MENU_BAR = new MenuBar();

    protected static void init() {
        //Menu
        Menu file = new Menu("File"),
                openSetting = new Menu("Open Setting"),
                help = new Menu("Help");

        //Menu Children
        MENU_BAR.getMenus().addAll(file, help, DisplayText.getDirectoryTextMenu());

        //MenuItem
        MenuItem open = new MenuItem("Open"),
                save = new MenuItem("Save"),
                exit = new MenuItem("Exit");

        //RadioMenuItem
        RadioMenuItem singleDirectory = new RadioMenuItem("Single"),
                multipleDirectory = new RadioMenuItem("Multiple");

        //ToggleGroup
        ToggleGroup tg = new ToggleGroup();

        //Children
        tg.getToggles().addAll(singleDirectory, multipleDirectory);
        openSetting.getItems().addAll(singleDirectory, multipleDirectory);
        singleDirectory.setSelected(true);
        file.getItems().addAll(open, openSetting, save, new SeparatorMenuItem(), exit);
        help.getItems().add(DisplayAbout.getABOUT_MENUITEM());

        //Handlers
        open.setOnAction(e -> {
            if (singleDirectory.isSelected() && !multipleDirectory.isSelected()) {
                BuildThread.runBuild(true);
            } else if (multipleDirectory.isSelected() && !singleDirectory.isSelected()) {
                BuildThread.runBuild(false);
            }
        });
        save.setOnAction(e -> {
            //DisplaySave.show(); //Window to display chosen images where user will be able to re-arrange order etc before writing image
            Save.saveFile();
        });
        exit.setOnAction(e -> {
            Thread.currentThread().interrupt();
            System.exit(0);
        });
    }

    protected static MenuBar getMENU_BAR() {
        return MENU_BAR;
    }

}
