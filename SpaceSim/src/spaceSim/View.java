package spaceSim;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.SingleSelectionModel;

public class View
{
	/*
	 * Class Instance Variables
	 * 
	 */
	
	private Controller 			controller;

	private Tab 				rocketTab;
	private Tab 				externalBodyTab;
	private Tab 				internalBodyTab;
	private Tab 				engineTab;
	private Tab 				missionControlTab;
	private HBox				rocketTabPane;
	private HBox				externalBodyTabPane;
	private HBox				internalBodyTabPane;
	private HBox				engineTabPane;
	private HBox				missionControlTabPane;
	private ColorPicker 		tabPaneColorPicker;
	private Font 				bigFont;
	private Font				smallFont;
	private ChoiceBox<String>	noseConeChoiceBox;
	private TextField			noseConeLengthTextField;
	private TextField			noseConeDiameterTextField;
	private TextField			noseConeShoulderLengthTextField;
	private TextField			noseConeShoulderDiameterTextField;
	private TextField			noseConeMassTextField;
	
	/*
	 * Class Constants
	 * 
	 */
	
	private final String 		IMAGE_PATH							= "/Images/";
	private final String		EXTERNAL_ROCKET_PARTS_IMAGE			= "ExternalRocketParts.png";
	private final String		INTERNAL_ROCKET_PARTS_IMAGE			= "InternalRocketParts.png";
	private final String		ENGINE_PARTS_IMAGE					= "EngineParts.png";
	private final String		NOSE_CONE_IMAGE						= "nosecone.png";
	private final String		DEFAULT_TAB_COLOR_NAME				= "#87CEFA";				// Light Sky Blue
	private final Color			DEFAULT_TAB_COLOR					= Color.LIGHTSKYBLUE;
	private final String		FONT_NAME							= "Arial";
	private final double		BIG_FONT_SIZE						= 14;
	private final double		SMALL_FONT_SIZE						= 12;
	private final int			VERTICAL_SPACING					= 10;
	private final int			VERTICAL_PADDING					= 50;
	private final int			REFERENCE_SCREEN_RESOLUTION_WIDTH	= 1366;
	private final int			REFERENCE_SCREEN_RESOLUTION_HEIGHT	= 768;

	
	/*
	 * Constructor Method
	 * 
	 */
	
	public View(Stage primaryStage, Controller rocket)
	{
		this.controller = rocket;
		
		/*
		 *  Get the screen dimensions and scale the UI components
		 */
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// Calculate the UI scaling factor based on the current screen resolution and our reference resolution
		double scalingWidthFactor  = (double) (primaryScreenBounds.getWidth() - REFERENCE_SCREEN_RESOLUTION_WIDTH) / (double) REFERENCE_SCREEN_RESOLUTION_WIDTH + 1;	
		double scalingHeightFactor = (double) (primaryScreenBounds.getHeight() - REFERENCE_SCREEN_RESOLUTION_HEIGHT) / (double) REFERENCE_SCREEN_RESOLUTION_HEIGHT + 1;	
		
		int verticalSpacing = (int)(VERTICAL_SPACING * scalingHeightFactor + 0.5);
		int verticalPadding = (int)(VERTICAL_PADDING * scalingHeightFactor + 0.5);
		
		/*
		 * Create the fonts
		 * 
		 */
		
		// Create the fonts
		bigFont 	= Font.font(FONT_NAME, FontWeight.BOLD, BIG_FONT_SIZE);
		smallFont 	= Font.font(FONT_NAME, FontWeight.NORMAL, SMALL_FONT_SIZE);
				
		/*
		 * Create the Outer Pane
		 * 
		 */
		
		VBox outerPane = new VBox();
		
		// Set spacing between child elements
		outerPane.setSpacing(10);

		/*
		 * Create the Color Picker
		 * 
		 */
		
		tabPaneColorPicker = new ColorPicker(DEFAULT_TAB_COLOR);
		tabPaneColorPicker.setOnAction(new TabColorPickerEventHandler());
		
		/*
		 * Create the Menu ToolBar
		 * 
		 */
		
		ToolBar toolBar = new ToolBar(new Label("Tab Color"), tabPaneColorPicker);

		// Add the Menu ToolBar to the Outer Pane
		outerPane.getChildren().add(toolBar);	
		
		/*
		 *  Create the Tab Pane
		 *  
		 */
		
		TabPane tabPane = new TabPane();
		
		// Add the Tab Pane to the Outer Pane
		outerPane.getChildren().add(tabPane);
		
		/*
		 *  Create the Rocket Tab Components
		 */
		
		// Rocket Tab
		rocketTab = new Tab();
		rocketTab.setText("Rocket");
		rocketTab.setContent(new Rectangle(800,400, Color.WHEAT));
		rocketTab.setClosable(false);
		
		// Add the Rocket Tab to the Tab Pane
		tabPane.getTabs().add(rocketTab);
				
		// Scale the Image Views
		
		int scaledRocketTabImageViews = (int) primaryScreenBounds.getWidth() / 3;
		
		// Create the Rocket Tab Pane
		
		rocketTabPane = new HBox();
		rocketTabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		rocketTabPane.setStyle("-fx-background-color:" + DEFAULT_TAB_COLOR_NAME);

		// Create the External Rocket Parts Image and View
		
		Image externalRocketPartsImage 			= new Image(IMAGE_PATH + EXTERNAL_ROCKET_PARTS_IMAGE);
		ImageView externalRocketPartsImageView 	= new ImageView(externalRocketPartsImage);

		/*
		 *  Resize the image to have a scaled width of 1/3 of the screen width while preserving the ratio and using higher quality filtering method.
		 *  This ImageView is also cached to improve performance.
		 */

		externalRocketPartsImageView.setFitWidth(scaledRocketTabImageViews);
		externalRocketPartsImageView.setPreserveRatio(true);
		externalRocketPartsImageView.setSmooth(true);
		externalRocketPartsImageView.setCache(true);
		
		// Add the External Rocket Parts Image View to the Rocket Tab Pane
		rocketTabPane.getChildren().add(externalRocketPartsImageView);

		// Create the Internal Rocket Parts Image and View
		
		Image internalRocketPartsImage 			= new Image(IMAGE_PATH + INTERNAL_ROCKET_PARTS_IMAGE);
		ImageView internalRocketPartsImageView 	= new ImageView(internalRocketPartsImage);

		/*
		 *  Resize the image to have a scaled width of 1/3 of the screen width while preserving the ratio and using higher quality filtering method.
		 *  This ImageView is also cached to improve performance.
		 */

		internalRocketPartsImageView.setFitWidth(scaledRocketTabImageViews);
		internalRocketPartsImageView.setPreserveRatio(true);
		internalRocketPartsImageView.setSmooth(true);
		internalRocketPartsImageView.setCache(true);
		
		// Add the Internal Rocket Parts Image View to the Rocket Tab Pane		
		rocketTabPane.getChildren().add(internalRocketPartsImageView);
		
		// Create the Engine Parts Image and View
		
		Image enginePartsImage 			= new Image(IMAGE_PATH + ENGINE_PARTS_IMAGE);
		ImageView enginePartsImageView 	= new ImageView(enginePartsImage);

		/*
		 *  Resize the image to have a scaled width of 1/3 of the screen width while preserving the ratio and using higher quality filtering method.
		 *  This ImageView is also cached to improve performance.
		 */

		enginePartsImageView.setFitWidth(scaledRocketTabImageViews);
		enginePartsImageView.setPreserveRatio(true);
		enginePartsImageView.setSmooth(true);
		enginePartsImageView.setCache(true);
		
		// Add the Engine Parts Image View to the Rocket Tab Pane	
		rocketTabPane.getChildren().add(enginePartsImageView);	
		
		// Add the Rocket Tab Pane to the Rocket Tab
		rocketTab.setContent(rocketTabPane);
		
		/*
		 * Create the External Body Tab Components
		 * 
		 */
		
		// External Body Tab
		
		externalBodyTab = new Tab();
		externalBodyTab.setText("External Body");
		externalBodyTab.setClosable(false);
		
		// Add the External Body Tab to the Tab Pane
		tabPane.getTabs().add(externalBodyTab);

		// Create the External Body Tab Pane
		
		externalBodyTabPane = new HBox();
		externalBodyTabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		externalBodyTabPane.setStyle("-fx-background-color:" + DEFAULT_TAB_COLOR_NAME);
		
		// Add the External Rocket Parts Image View to the Rocket Tab Pane
		externalBodyTabPane.getChildren().add(externalRocketPartsImageView);

		// Add the External Body Tab Pane to the External Body Tab
		externalBodyTab.setContent(externalBodyTabPane);

		// Create the External Body Tab Vertical Panes
		
		VBox externalBodyTabVerticalPane1 = new VBox();
		externalBodyTabVerticalPane1.setStyle("-fx-border-color:black; -fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10;");
		externalBodyTabVerticalPane1.setAlignment(Pos.CENTER);

		// Set spacing between child elements
		externalBodyTabVerticalPane1.setSpacing(verticalSpacing);

		// Insets (clock-wise): top-right-buttom-left. USE BETWEEN PANES.		
		externalBodyTabVerticalPane1.setPadding(new Insets(0, 0, verticalPadding, 0));
		
		VBox externalBodyTabVerticalPane2 = new VBox();
		
		/*
		 * Nose Cone
		 * 
		 */
		
		HBox noseConeHorizontalPane = new HBox();
		VBox noseConeVerticalPane1	= new VBox();
		VBox noseConeVerticalPane2	= new VBox();
		
		// Add the Nose Cone Vertical Panes to the Nose Cone Horizontal Pane
		noseConeHorizontalPane.getChildren().addAll(noseConeVerticalPane1, noseConeVerticalPane2);
		
		// Add the Nose Cone Horizontal Pane to the External Body Tab Vertical Pane		
		externalBodyTabVerticalPane1.getChildren().add(noseConeHorizontalPane);
		
		// Add the External Body Tab Vertical Panes to the External Body Tab Pane
		externalBodyTabPane.getChildren().addAll(externalBodyTabVerticalPane1, externalBodyTabVerticalPane2);
				
		// Create the Nose Cone Label
		Label noseConeLabel = new Label("Nose Cones");
		noseConeLabel.setPrefSize(125, 20);
		noseConeLabel.setFont(bigFont);
		noseConeLabel.setAlignment(Pos.CENTER);
				
		// Add Nose Cone Label to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeLabel);

		// Add the Nose Cone ImageView
		Image noseConeImage = new Image(IMAGE_PATH + NOSE_CONE_IMAGE);
		ImageView noseConeImageView = new ImageView(noseConeImage);
		
		noseConeImageView.setFitWidth(150);
		noseConeImageView.setPreserveRatio(true);
		noseConeImageView.setSmooth(true);
		noseConeImageView.setCache(true);
		
		// Add the Nose Cone ImageView to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeImageView);
		
		// Create the Nose Cone Choice Box
		noseConeChoiceBox = new ChoiceBox<String>();
		noseConeChoiceBox.setItems(FXCollections.observableArrayList("Custom", "Apogee 19620", "Apogee 19650", "Apogee 19682"));
		noseConeChoiceBox.setPrefSize(150, 40);
		noseConeChoiceBox.setStyle("-fx-text-fill:black");
		noseConeChoiceBox.getSelectionModel().selectFirst();

		// Add Nose Cone Choice Box to the External Body Tab Vertical Pane		
		externalBodyTabVerticalPane1.getChildren().add(noseConeChoiceBox);

		// Create the Nose Cone Length Label
		Label noseConeLengthLabel = new Label("Length");
		noseConeLengthLabel.setPrefSize(125, 20);
		noseConeLengthLabel.setFont(bigFont);
		noseConeLengthLabel.setAlignment(Pos.CENTER);
		
		// Add Nose Cone Length Label to the External Body Tab Vertical Pane	
		externalBodyTabVerticalPane1.getChildren().add(noseConeLengthLabel);

		// Create the Nose Cone Length TextField
		noseConeLengthTextField = new TextField("0.000");
		noseConeLengthTextField.setPrefSize(125, 20);
		noseConeLengthTextField.setFont(smallFont);
		noseConeLengthTextField.setAlignment(Pos.CENTER);
		noseConeLengthTextField.setStyle("-fx-border-color:blue");
		
		// Add Nose Cone Length TextField to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeLengthTextField);

		// Create the Nose Cone Diameter Label
		Label noseConeDiameterLabel = new Label("Diameter");
		noseConeDiameterLabel.setPrefSize(125, 20);
		noseConeDiameterLabel.setFont(bigFont);
		noseConeDiameterLabel.setAlignment(Pos.CENTER);
		
		// Add Nose Cone Diameter Label to the External Body Tab Vertical Pane		
		externalBodyTabVerticalPane1.getChildren().add(noseConeDiameterLabel);

		// Create the Nose Cone Diameter TextField
		noseConeDiameterTextField = new TextField("0.000");
		noseConeDiameterTextField.setPrefSize(125, 20);
		noseConeDiameterTextField.setFont(smallFont);
		noseConeDiameterTextField.setAlignment(Pos.CENTER);
		noseConeDiameterTextField.setStyle("-fx-border-color:blue");
		
		// Add Nose Cone Diameter TextField to the External Body Tab Vertical Pane		
		externalBodyTabVerticalPane1.getChildren().add(noseConeDiameterTextField);		
		
		// Create the Nose Cone Shoulder Length Label
		Label noseConeShoulderLengthLabel = new Label("Shoulder Length");
		noseConeShoulderLengthLabel.setPrefSize(125, 20);
		noseConeShoulderLengthLabel.setFont(bigFont);
		noseConeShoulderLengthLabel.setAlignment(Pos.CENTER);
		
		// Add Nose Cone Shoulder Length Label to the External Body Tab Vertical Pane		
		externalBodyTabVerticalPane1.getChildren().add(noseConeShoulderLengthLabel);

		// Create the Nose Cone Shoulder Length TextField
		noseConeShoulderLengthTextField = new TextField("0.000");
		noseConeShoulderLengthTextField.setPrefSize(125, 20);
		noseConeShoulderLengthTextField.setFont(smallFont);
		noseConeShoulderLengthTextField.setAlignment(Pos.CENTER);
		noseConeShoulderLengthTextField.setStyle("-fx-border-color:blue");
		
		// Add Nose Cone Shoulder Length TextField to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeShoulderLengthTextField);		
		
		// Create the Nose Cone Shoulder Diameter Label	
		Label noseConeShoulderDiameterLabel = new Label("Shoulder Diameter");
		noseConeShoulderDiameterLabel.setPrefSize(125, 20);
		noseConeShoulderDiameterLabel.setFont(bigFont);
		noseConeShoulderDiameterLabel.setAlignment(Pos.CENTER);
		
		// Add Nose Cone Shoulder Diameter to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeShoulderDiameterLabel);

		// Create the Nose Cone Shoulder Diameter TextField
		noseConeShoulderDiameterTextField = new TextField("0.000");
		noseConeShoulderDiameterTextField.setPrefSize(125, 20);
		noseConeShoulderDiameterTextField.setFont(smallFont);
		noseConeShoulderDiameterTextField.setAlignment(Pos.CENTER);
		noseConeShoulderDiameterTextField.setStyle("-fx-border-color:blue");
		
		// Add Nose Cone Shoulder Length TextField to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeShoulderDiameterTextField);
		
		// Create the Nose Cone Mass Label
		Label noseConeMassLabel = new Label("Mass");
		noseConeMassLabel.setPrefSize(125, 20);
		noseConeMassLabel.setFont(bigFont);
		noseConeMassLabel.setAlignment(Pos.CENTER);
		
		// Add Nose Cone Mass Label to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeMassLabel);

		// Create the Nose Mass TextField
		noseConeMassTextField = new TextField("0.000");
		noseConeMassTextField.setPrefSize(125, 20);
		noseConeMassTextField.setFont(smallFont);
		noseConeMassTextField.setAlignment(Pos.CENTER);
		noseConeMassTextField.setStyle("-fx-border-color:blue");
		
		// Add Nose Cone Mass TextField to the External Body Tab Vertical Pane
		externalBodyTabVerticalPane1.getChildren().add(noseConeMassTextField);
		
		/*
		 * Create the Internal Body Tab Components
		 * 
		 */
		
		// Internal Body Tab		
		internalBodyTab = new Tab();
		internalBodyTab.setText("Internal Body");
		internalBodyTab.setClosable(false);
		
		// Add the Internal Body Tab to the Tab Pane
		tabPane.getTabs().add(internalBodyTab);
		
		// Create the Internal Body Tab Pane
		internalBodyTabPane = new HBox();
		internalBodyTabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		internalBodyTabPane.setStyle("-fx-background-color:" + DEFAULT_TAB_COLOR_NAME);

		// Add the Internal Body Tab Pane to the Internal Body Tab
		internalBodyTab.setContent(internalBodyTabPane);
				
		/*
		 * Create the Engine Tab Components
		 * 
		 */
				
		// Engine Tab
		engineTab = new Tab();
		engineTab.setText("Engine");
		engineTab.setClosable(false);
		
		// Add the Engine Tab to the Tab Pane		
		tabPane.getTabs().add(engineTab);
		
		// Create the Engine Tab Pane
		engineTabPane = new HBox();
		engineTabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		engineTabPane.setStyle("-fx-background-color:" + DEFAULT_TAB_COLOR_NAME);

		// Add the Engine Tab Pane to the Engine Tab
		engineTab.setContent(engineTabPane);
		
		/*
		 * Create the Mission Control Tab Components
		 * 
		 */
				
		// Mission Control Tab
		missionControlTab = new Tab();
		missionControlTab.setText("Mission Control");
		missionControlTab.setClosable(false);
		
		// Add the Mission Control Tab to the Tab Pane
		tabPane.getTabs().add(missionControlTab);

		// Create the Mission Control Tab Pane
		missionControlTabPane = new HBox();
		missionControlTabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		missionControlTabPane.setStyle("-fx-background-color:" + DEFAULT_TAB_COLOR_NAME);

		// Add the Mission Control Tab Pane to the Mission Control Tab
		missionControlTab.setContent(missionControlTabPane);
		
		/*
		 *  Create the Scene and setup the Primary Stage
		 *  
		 */
		
		Scene scene = new Scene(outerPane);
		
		// Setup the primary stage (like the Swing JFrame)
		primaryStage.setScene(scene);
		primaryStage.setTitle("SpaceSim Version 0.1");
		primaryStage.setMaximized(true);
		
		// Display the Stage	
		primaryStage.show();		
	}
	
	/*
	 * Tab Color Picker Event Handler
	 * 
	 */
	
	private class TabColorPickerEventHandler implements EventHandler<ActionEvent>
    {
		public void handle(ActionEvent e)
		{
			// Get the Color chosen by the user
			
			Color color 	 = tabPaneColorPicker.getValue();
			String colorName = color.toString();
			colorName 		 = "#" + colorName.substring(2);
			
			// Color the Tab Panes with the Standard Color selected 
			
			rocketTabPane.setStyle("-fx-background-color:" + colorName);
			externalBodyTabPane.setStyle("-fx-background-color:" + colorName);
			internalBodyTabPane.setStyle("-fx-background-color:" + colorName);
			engineTabPane.setStyle("-fx-background-color:" + colorName);
			missionControlTabPane.setStyle("-fx-background-color:" + colorName);
		}
    }
}
