package com.project_studio.jlayerfx.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.project_studio.jlayerfx.util.imagegeneration.GenerateBatch;
import com.project_studio.jlayerfx.util.imagegeneration.ImageLoaderThread;
import com.project_studio.jlayerfx.util.io.IO;
import com.project_studio.jlayerfx.util.settings.Settings;
import com.project_studio.utils.ImageUtils;
import com.project_studio.utils.ObjectUtils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public final class GUI {

    //UI
	private static final BorderPane ROOT = new BorderPane();

	// UI - Center
    private static final VBox CENTER_VBOX = new VBox();
    private static final HBox CENTER_SCROLLPANE_HBOX = new HBox(2.5);
    private static final ScrollPane CENTER_SCROLLPANE = new ScrollPane(CENTER_SCROLLPANE_HBOX);
    
    // UI - Bottom
    private static final HBox BOTTOM_HBOX = new HBox(5);
	private static final ImageView BOTTOM_IMAGEVIEW_PREVIEW = new ImageView();
    private static final VBox BOTTOM_IMAGEVIEW_PREVIEW_VBOX = new VBox();
    private static final ProgressBar PROGRESS_BAR = new ProgressBar(0);
    private static final Text STATUS_TEXT = new Text();

    protected static void init() {
    	ROOT.setTop(GUIMenu.getMenuBar());
        ROOT.setCenter(CENTER_VBOX);
        ROOT.setBottom(BOTTOM_HBOX);
    	initTop();
    	initCenter();
    	initBottom();
    }

    // -----------------------------------------------
	// Init Top Container
	// -----------------------------------------------
    private static void initTop() {
    	GUIMenu.init();
    }
    
    // -----------------------------------------------
	// Init Center Container
	// -----------------------------------------------
    private static void initCenter() {
        //HBox inside Scrollpane
        CENTER_SCROLLPANE_HBOX.setAlignment(Pos.CENTER);
        CENTER_SCROLLPANE_HBOX.setMaxHeight(5);

        //Scrollpane
        CENTER_SCROLLPANE.setVbarPolicy(ScrollBarPolicy.NEVER);
        CENTER_SCROLLPANE.setMinHeight(300); // Original 270
        CENTER_SCROLLPANE.setPannable(true);

        //Container for all nodes
        CENTER_VBOX.heightProperty().isEqualTo(CENTER_VBOX.getScene().getHeight(), 5);
        CENTER_VBOX.widthProperty().isEqualTo(CENTER_VBOX.getScene().getWidth(), 5);
        CENTER_VBOX.getChildren().addAll(CENTER_SCROLLPANE, new Separator(Orientation.HORIZONTAL), BOTTOM_IMAGEVIEW_PREVIEW_VBOX);
        CENTER_VBOX.setAlignment(Pos.CENTER);
    }

	// -----------------------------------------------
	// Init Bottom Container
	// -----------------------------------------------
    private static void initBottom() {
        BOTTOM_HBOX.getChildren().addAll(PROGRESS_BAR, STATUS_TEXT);
        BOTTOM_HBOX.setAlignment(Pos.CENTER);
        
		// PREVIEW_IMAGEVIEW.fitHeightProperty().bind(PREVIEW_IMAGEVIEW.getParent().getScene().heightProperty());
		// PREVIEW_IMAGEVIEW.fitWidthProperty().bind(PREVIEW_IMAGEVIEW.getParent().getScene().widthProperty());
		BOTTOM_IMAGEVIEW_PREVIEW.setFitHeight(235);
		BOTTOM_IMAGEVIEW_PREVIEW.setFitWidth(235);
		
        //Image Preview VBox
        BOTTOM_IMAGEVIEW_PREVIEW_VBOX.getChildren().addAll(new Text("Image To Be Generated"), BOTTOM_IMAGEVIEW_PREVIEW);
        BOTTOM_IMAGEVIEW_PREVIEW_VBOX.setAlignment(Pos.CENTER);
        BOTTOM_IMAGEVIEW_PREVIEW_VBOX.setMinSize(250, 250);

        //ImageView
        BOTTOM_IMAGEVIEW_PREVIEW_VBOX.setOnMouseClicked(e -> {
            if (ObjectUtils.isNotNullOrEmpty(BOTTOM_IMAGEVIEW_PREVIEW.getImage())) {
            	// Create temporary image preview for secondary stage
                final ScrollPane sp = new ScrollPane();
                final StackPane stp = new StackPane();
                final ImageView iv = new ImageView();
                stp.getChildren().add(iv);
                sp.setContent(stp);
                sp.setFitToWidth(true);
                sp.setFitToHeight(true);
                iv.setImage(BOTTOM_IMAGEVIEW_PREVIEW.getImage());
                final Scene scene = new Scene(sp, 800, 600);
                SecondaryStage.show(scene, true);
            }
        });
    }
    
    protected static BorderPane getRoot() {
    	return ROOT;
    }
    
    private static void clearCenterHBox() {
    	CENTER_SCROLLPANE_HBOX.getChildren().clear();
    }

    private static void clearPreviewImage() {
    	setPreviewImage(null);
    }

    private static class GUIMenu {

    	private static final MenuBar MENU_BAR = new MenuBar();    
    	
    	private static final Menu MENU_FILE = new Menu("File"); // Main GUI
    	private static final Menu MENU_HELP = new Menu("Help"); // Main GUI
    	private static final Menu MENU_DIR_TEXT = new Menu(""); // Main GUI
    	private static final Menu MENU_OPEN = new Menu("Open"); // SubMenu

    	private static final MenuItem OPEN_IMAGE_GENERATION = new MenuItem("Image-Generation"); // Open Menu
    	private static final MenuItem OPEN_BATCH_GENERATION = new MenuItem("Batch-Generation"); // Open Menu
    	private static final MenuItem SAVE = new MenuItem("Save Image"); // File Menu
    	private static final MenuItem EXIT = new MenuItem("Exit"); // File Menu
    	private static final MenuItem ABOUT = new MenuItem("About"); // Help Menu
    	private static final MenuItem SETTINGS = new MenuItem("Settings"); // Help Menu
    	private static final MenuItem HOW_TO = new MenuItem("How-To"); // Help Menu
    	
    	public static void init() {
    		// Menu Children
    		MENU_BAR.getMenus().addAll(MENU_FILE, MENU_HELP, MENU_DIR_TEXT);

    		// Children
    		MENU_FILE.getItems().addAll(MENU_OPEN, SAVE, new SeparatorMenuItem(), EXIT);
    		MENU_HELP.getItems().addAll(ABOUT, SETTINGS, HOW_TO);
    		MENU_OPEN.getItems().addAll(OPEN_IMAGE_GENERATION, OPEN_BATCH_GENERATION);

    		// Handlers
    		OPEN_IMAGE_GENERATION.setOnAction(e -> {
    			if (Settings.PROMPT_LOAD_TYPE_SELECT) {
    				// Label
    				Label labelLoadType = new Label("Load a Single Directory or Multiple Directories");
    				Label labelLoadTypePrompt = new Label("You can re-enable the popup or change loadtype in Help -> Settings");
    				labelLoadTypePrompt.setFont(Font.font(null, FontWeight.BOLD, 10));
    				labelLoadTypePrompt.setTextAlignment(TextAlignment.CENTER);
    				labelLoadTypePrompt.setWrapText(true);

    				// Separator
    				Separator separator1 = new Separator(Orientation.HORIZONTAL);
    				separator1.setPadding(new Insets(5, 0, 0, 0));
    				
    				// Button
    				Button buttonContinue = new Button("Continue");
    				buttonContinue.setOnAction(ev -> {
    					SecondaryStage.close();
    				});

    				// RadioButton
    				ToggleGroup loadType = new ToggleGroup();
    				RadioButton loadTypeSingle = new RadioButton("Single");
    				RadioButton loadTypeMultiple = new RadioButton("Multiple");
    				loadTypeSingle.setSelected(Settings.LOAD_SINGLE);
    				loadTypeMultiple.setSelected(Settings.LOAD_MULTIPLE);
    				loadTypeSingle.setToggleGroup(loadType);
    				loadTypeMultiple.setToggleGroup(loadType);
    				loadTypeSingle.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    					Settings.LOAD_SINGLE = newValue;
    				}));
    				loadTypeMultiple.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    					Settings.LOAD_MULTIPLE = newValue;
    				}));

    				// CheckBox
    				CheckBox promptLoadType = new CheckBox("Don't Ask Me Again");
    				promptLoadType.setSelected(!Settings.PROMPT_LOAD_TYPE_SELECT);
    				promptLoadType.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    					Settings.PROMPT_LOAD_TYPE_SELECT = !newValue;
    				}));

    				// VBox - Root
    				VBox root = new VBox(labelLoadType, loadTypeSingle, loadTypeMultiple, promptLoadType, separator1, labelLoadTypePrompt, buttonContinue);
    				root.setAlignment(Pos.CENTER);
    				
    				// Scene
    				Scene scene = new Scene(root, 350, 100);

    				// Show
    				SecondaryStage.showAndWait(scene, false);
    			}

    			ImageLoaderThread.runBuild();
    		});
    		
    		OPEN_BATCH_GENERATION.setOnAction(e -> {
    			// ProgressBar
    			ProgressBar buildProgressBar = new ProgressBar(0.0);

    			// Buttons
    			Button buttonBaseImageMultipleDirectory = new Button("Load Base Image + Multiple Directory");
    			Button buttonBaseDirectoryMultipleDirectory = new Button("Load Base Directory + Multiple Directory");

    			// Handlers
    			buttonBaseImageMultipleDirectory.setOnAction(ev -> {
    				// Grab BaseImage + SubDirectories
    				Object[] baseImageSubDirectories = IO.readBatchBaseImage();

    				// Check if bisd is null
    				if (baseImageSubDirectories == null) {
    					return;
    				}

    				// Create BatchScript
    				GenerateBatch bs = new GenerateBatch((Image) baseImageSubDirectories[0], ImageUtils.fileToImage((List<File[]>) baseImageSubDirectories[1]));
    				bs.save();

    				// Garbage Collection
    				bs = null;

    				// Close SecondaryStage
    				SecondaryStage.close();
    			});

    			buttonBaseDirectoryMultipleDirectory.setOnAction(ev -> {
    				// Base Directory + Sub-Directories
    				Object[] baseDirectorySubDirectories = IO.readBatchBaseDirectory();

    				// Check if bdsd is null
    				if (baseDirectorySubDirectories == null) {
    					return;
    				}

    				// Create BatchScript
    				GenerateBatch bs = new GenerateBatch(ImageUtils.fileToImage((File[]) baseDirectorySubDirectories[0]), ImageUtils.fileToImage((List<File[]>) baseDirectorySubDirectories[1]));
    				bs.save();

    				// Garbage Collection
    				bs = null;

    				// Close SecondaryStage
    				SecondaryStage.close();
    			});

    			// HB
    			HBox hbox = new HBox(5.0, buttonBaseImageMultipleDirectory, buttonBaseDirectoryMultipleDirectory);
    			hbox.setAlignment(Pos.CENTER);
    			
    			// Root
    			VBox vbox = new VBox(2.5, hbox, new Separator(Orientation.HORIZONTAL), buildProgressBar);
    			vbox.setAlignment(Pos.CENTER);

    			// Scene
    			Scene scene = new Scene(vbox, 500, 105);

				// Show
				SecondaryStage.show(scene, false);
    		});
    		
    		SAVE.setOnAction(e -> {
    			MenuSave.show();
    		});
    		
    		EXIT.setOnAction(e -> {
    			Thread.currentThread().interrupt();
    			System.exit(0);
    		});
    		
    		ABOUT.setOnAction(e -> {
    	        // Node - Column - Row
    	        ImageView imageView = new ImageView(Icon.APP_ICON);
    	        Text info = new Text("GitHub: @lsg2550" 
    	        		+ System.lineSeparator()
    	                + "Current OS: " + System.getProperty("os.name") 
    	                + System.lineSeparator()
    	                + "Icons by Enterbrain");
    	        info.setTextAlignment(TextAlignment.CENTER);

    	        // Root
    	        VBox root = new VBox(imageView, info);
    	        root.setAlignment(Pos.CENTER);

    	        // Scene
    	        Scene scene = new Scene(root, 150, 105);
    	        
    	        // Show
    			SecondaryStage.show(scene, false);
    		});
    		
    		SETTINGS.setOnAction(e -> {
    			// Label
    			Label loadTypeSettings = new Label("Load Type Settings");
    			loadTypeSettings.setAlignment(Pos.CENTER);
    			Label otherSettings = new Label("Other Settings");
    			otherSettings.setAlignment(Pos.CENTER);

    			// Separator 1 & 2
    			Separator sep1 = new Separator(Orientation.HORIZONTAL);
    			Separator sep2 = new Separator(Orientation.HORIZONTAL);
    			sep1.setPadding(new Insets(5, 0, 0, 0));
    			sep2.setPadding(new Insets(10, 0, 0, 0));
    			sep2.setVisible(false);

    			// RadioButton
    			ToggleGroup loadType = new ToggleGroup();
    			RadioButton loadTypeSingle = new RadioButton("Single");
    			RadioButton loadTypeMultiple = new RadioButton("Multiple");
    			loadTypeSingle.setSelected(Settings.LOAD_SINGLE);
    			loadTypeMultiple.setSelected(Settings.LOAD_MULTIPLE);
    			loadTypeSingle.setToggleGroup(loadType);
    			loadTypeMultiple.setToggleGroup(loadType);
    			loadTypeSingle.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    				Settings.LOAD_SINGLE = newValue;
    			}));
    			loadTypeMultiple.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    				Settings.LOAD_MULTIPLE = newValue;
    			}));
    			
    			// CheckBox
    			CheckBox loadTypePrompt = new CheckBox("Show Load Type Prompt");
    			loadTypePrompt.setSelected(Settings.PROMPT_LOAD_TYPE_SELECT);
    			loadTypePrompt.selectedProperty().addListener(((observable, oldValue, newValue) -> {
    				Settings.PROMPT_LOAD_TYPE_SELECT = newValue;
    			}));

    			// Button
    			Button buttonClose = new Button("Close");
    			buttonClose.setOnAction(ev -> {
    				SecondaryStage.close();
    			});

    			// Root
    			VBox root = new VBox(loadTypeSettings, loadTypeSingle, loadTypeMultiple, loadTypePrompt, sep1, otherSettings, sep2, buttonClose);
    			root.setAlignment(Pos.CENTER);

    			// Scene
    			Scene scene = new Scene(root, 275, 115);
    			
    	        // Show
    			SecondaryStage.show(scene, false);
    		});
    		
    		HOW_TO.setOnAction(e -> {		
    			// Label
    			Label howto = new Label("How-To");
    			howto.setAlignment(Pos.CENTER);

    			// Separator
    			Separator sep1 = new Separator(Orientation.HORIZONTAL);
    			sep1.setPadding(new Insets(10, 0, 0, 0));
    			sep1.setVisible(false);

    			// Button
    			Button buttonClose = new Button("Close");
    			buttonClose.setOnAction(ev -> {
    				SecondaryStage.close();
    			});
    			
    			// Root
    			VBox root = new VBox(howto, sep1, buttonClose);
    			root.setAlignment(Pos.CENTER);

    			// Scene
    			Scene scene = new Scene(root, 500, 500);
    			
    	        // Show
    			SecondaryStage.show(scene, false);
    		});
    		
            MENU_DIR_TEXT.setOnAction(e -> {
                try {
                	Desktop.getDesktop().open(new File(MENU_DIR_TEXT.getText()));
                } catch (IOException ex) {
                }
            });
    	}
    	
    	static MenuBar getMenuBar() {
    		return MENU_BAR;
    	}
    	
    	static void updateDirectoryText(String string) {
    		MENU_DIR_TEXT.setText(string);
    	}
    	
    }
    
    // -----------------------------------------------
	// Other Functions (Helpers/Utils/Etc)
	// -----------------------------------------------
    public static void clearUI() {
		Platform.runLater(() -> {
			GUI.clearPreviewImage();
			GUI.clearCenterHBox();
		});
    }
    
    public static void updateStatusText(String string) {
    	STATUS_TEXT.setText(string);
    }
    
    public static void updateDirectoryText(String string) {
    	GUIMenu.updateDirectoryText(string);
    }

	public static void updateProgressBar(double progress) {
		PROGRESS_BAR.setProgress(progress);
	}
    
    public static void addChildToCenterHBox(Node node) {
    	CENTER_SCROLLPANE_HBOX.getChildren().add(node);
    }

    public static void setPreviewImage(Image image) {
    	BOTTOM_IMAGEVIEW_PREVIEW.setImage(image);
    }

}