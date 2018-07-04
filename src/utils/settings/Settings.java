package utils.settings;

/**
 *
 * @author Luis
 */
public enum Settings {
    INSTANCE();

    //LoadType Settings
    private boolean loadType = true; //True = Single; False = Multiple
    private boolean loadTypeWindow = true; //True = Show Popup; False = Disable Popup

    //Other Settings
    /**
     * @return the loadType
     */
    public boolean isLoadType() {
        return loadType;
    }

    /**
     * @param loadType the loadType to set
     */
    public void setLoadType(boolean loadType) {
        this.loadType = loadType;
    }

    /**
     * @return the loadTypeWindow
     */
    public boolean isLoadTypeWindow() {
        return loadTypeWindow;
    }

    /**
     * @param loadTypeWindow the loadTypeWindow to set
     */
    public void setLoadTypeWindow(boolean loadTypeWindow) {
        this.loadTypeWindow = loadTypeWindow;
    }
}
