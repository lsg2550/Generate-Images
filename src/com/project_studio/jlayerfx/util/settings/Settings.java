package com.project_studio.jlayerfx.util.settings;

public final class Settings {
	
	// LoadType Settings
	public static boolean LOAD_SINGLE = true;
	public static boolean LOAD_MULTIPLE = false;
	public static boolean PROMPT_LOAD_TYPE_SELECT = true;
	
	public static void init() {
		loadSettingsFromFile();
	}
	
	private static void loadSettingsFromFile() {
		// Read from file, if any
	}
	
	public static void saveSettingsToFile() {
		// Save to file
	}
	
}
