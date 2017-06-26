package gui;

import utils.io.Save;
import utils.thread.ThreadAlert;

/**
 *
 * @author Luis
 */
class Init {

    protected static void init() {
        //GUI Init
        DisplayGUICenterScrollPane.init();
        DisplayGUIPreviewImageView.init();
        DisplayMenuSettings.init();
        DisplayGUIMenuBar.init();
        DisplayGUIBottom.init();
        DisplayGUICenter.init();
        DisplayMenuAbout.init();
        DisplayMenuOpen.init();
        DisplayMenuSave.init();
        DisplayStage.init();

        //Utils Init
        ThreadAlert.init();
        Save.init();
    }

}
