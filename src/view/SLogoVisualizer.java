package view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import exception.SLogoException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Model;
import model.SLogoPosition;
import model.SLogoDisplayData;

/**
 * 
 * Visualizer class that contains methods that renders turtles 
 * on the screen to show the user
 *
 */
public class SLogoVisualizer extends Observable implements Observer {

	private static final String IMAGE_PATH = "file:resources/turtle_images/";
	private static final int PANE_SIZE = 440;

	private int myTurtleSize = 40;

	private ObservableList<SLogoDisplayData> myObservableDataList;

	private int myWidth;
	private int myHeight;

	private FXMLLoader myLoader;
	private SLogoGUIController myGUIController;
	private SLogoPromptBuilder myPromptBuilder;
	private Parent root;

	// Visualization Primitives
	private Scene myScene;
	private Stage myStage;
	private Model myModel;
	private String myCanvasColor;
	private SLogoPropertiesData myProperties = new SLogoPropertiesData();

	private double COORDINATE_SHIFT = PANE_SIZE / 2;
	private double LINE_SHIFT = myTurtleSize / 2;
	private int DIRECTION_FLIP = -1;

	public SLogoVisualizer(Model model, int width, int height) {
		myModel = model;
		myWidth = width;
		myHeight = height;
	}

	public SLogoVisualizer(Model model) {
		myWidth = 331;
		myHeight = 331;
		myModel = model;
	}

	public SLogoVisualizer(){

	}


	public void initialize() throws SLogoException, IOException {

		myPromptBuilder = new SLogoPromptBuilder(myProperties);
		// data = myPromptBuilder.getPropertiesData();
		// observe the data
		// because i am observing data, when data changes and data calls notify observers, i will call my update
		// and in updatel, i will set all properties to what is in the current properties data
		myPromptBuilder.promptScreen();
		// get your properties data 
		// data = myPromptBuilder.getData();
		setCanvasColor(toRGBCode(myPromptBuilder.sendMyColor()));
		getModel().loadLanguage();

		//Get the ObservableDataList
		setObservableDataList(getModel().getObservableDataList());


		// GUI Initialization
		myLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
		root = (Parent) myLoader.load();
		myGUIController = (SLogoGUIController) myLoader.getController();
		myGUIController.setModel(myModel);
		myGUIController.getCustomizer().addObserver(this); //subscribe to changes in customizer
		myGUIController.setPropertiesData(myProperties);
		myScene = new Scene(root);
		myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle("SLogo");
		show();
	}

	public void show() {
		getStage().show();
	}

	public void hide () {
		getStage().hide();

	}

	@Override
	public void update(Observable observable, Object arg1) {
		updateDisplayData();
	}

	public void updateMenuButton (ObservableList<MenuItem> items) {
		getGUIController().updateMenuButton(items);
	}

	/**
	 * Creates a Line object with default color black
	 * 
	 * @param position
	 * @return Line
	 */
	public Line createLine(SLogoPosition position) {
		Line newLine = new Line();
		newLine.setStartX(position.getPrevX() + COORDINATE_SHIFT + LINE_SHIFT);
		newLine.setStartY(DIRECTION_FLIP * position.getPrevY() + COORDINATE_SHIFT + LINE_SHIFT);
		newLine.setEndX(position.getX() + COORDINATE_SHIFT + LINE_SHIFT);
		newLine.setEndY(DIRECTION_FLIP * position.getY() + COORDINATE_SHIFT + LINE_SHIFT);
		newLine.setStrokeWidth(1.0f);
		return newLine;
	}

	/**
	 * Creates a Line object with specified color
	 * 
	 * @param position
	 * @param color
	 * @return Line
	 */
	public Line createLine(SLogoDisplayData turtledata) {
		Line newLine = createLine(turtledata.getPosition());
		Color myColor = turtledata.getPen().getColor();
		newLine.setFill(myColor);
		if(turtledata.getPen().getDown())
			newLine.setStrokeWidth(turtledata.getPen().getSize());
		else
			newLine.setStrokeWidth(0);
		return newLine;
	}

	/**
	 * This method updates turtles' attributes and position
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateDisplayData () {		
		//Clear entire Pane
		getGUIController().getCanvas().getChildren().clear();

		getModel().getObservableDataList();
		for (SLogoDisplayData turtledata : getObservableDataList()) {
			//Place the turtle
			placeTurtle(turtledata);

			//Create a line
			turtledata.addLine(createLine(turtledata));

			//Add lines to Pane
			getGUIController().addToCanvas(turtledata.getLines());

			//Update the properties pane after turtle has moved
			getGUIController().updateProperties(turtledata);
			myProperties.setPaneColor(turtledata.getBGColor());
			myGUIController.setPropertiesData(myProperties);
		}

	}

	public void placeTurtle(SLogoDisplayData displaydata) {
		Image image = new Image(IMAGE_PATH + displaydata.getImage());

		ImageView turtle = new ImageView();

		turtle.setImage(image);
		turtle.setVisible(!displaydata.getTurtleHidden());

		//assign click action - change the action to change attributes
		//temporary method to demonstrate use
		turtle.setOnMouseClicked(e -> {
			turtle.setFitWidth(120);
			turtle.setLayoutX(displaydata.getX() - 120 / 2);
			turtle.setLayoutY(displaydata.getY() - 120 / 2);
			turtle.setRotate(90);
		});

		//turtle resize
		turtle.setFitWidth(myTurtleSize);
		turtle.setPreserveRatio(true);
		turtle.setSmooth(true);
		turtle.setCache(true);

		//place turtle using Position and center at the coordinates (x,y)
		turtle.setLayoutX(displaydata.getX() + COORDINATE_SHIFT);
		turtle.setLayoutY(DIRECTION_FLIP * displaydata.getY() + COORDINATE_SHIFT);

		System.out.println("To rotate: " + displaydata.getDirection());
		//turtle rotate
		turtle.setRotate(DIRECTION_FLIP * displaydata.getPrevDirection());
		turtle.setRotate(displaydata.getDirection());



		//Put it in the Pane
		getGUIController().addToCanvas(turtle);
	}

	/**
	 * This method updates command history display in GUI.
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateCommandHistory () {

	}
	
	/**
	 * Converts Color object into its hex String representation
	 * 
	 * @param color
	 * @return String
	 */
	public String toRGBCode (Color color) {
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue() * 255 ) );
	}

	//////////////////////////
	// getters and setters //
	//////////////////////////

	public String getLanguage() {
		return myPromptBuilder.sendMyLanguage();
	}

	public int getWidth() {
		return myWidth;
	}

	public void setWidth(int width) {
		this.myWidth = width;
	}

	public int getHeight() {
		return myHeight;
	}

	public void setHeight(int height) {
		this.myHeight = height;
	}

	/**
	 * @return the myLoader
	 */
	public FXMLLoader getLoader() {
		return myLoader;
	}

	/**
	 * @param myLoader
	 *            the myLoader to set
	 */
	public void setLoader(FXMLLoader myLoader) {
		this.myLoader = myLoader;
	}

	/**
	 * @return the myGUIController
	 */
	public SLogoGUIController getGUIController() {
		return myGUIController;
	}

	/**
	 * @param myGUIController
	 *            the myGUIController to set
	 */
	public void setGUIController(SLogoGUIController myGUIController) {
		this.myGUIController = myGUIController;
	}

	/**
	 * @return the myModel
	 */
	public Model getModel() {
		return myModel;
	}

	/**
	 * @param myModel
	 *            the myModel to set
	 */
	public void setModel(Model myModel) {
		this.myModel = myModel;
	}

	/**
	 * @return the myScene
	 */
	public Scene getScene() {
		return myScene;
	}

	/**
	 * @param myScene the myScene to set
	 */
	public void setScene(Scene myScene) {
		this.myScene = myScene;
	}

	/**
	 * @return the myStage
	 */
	public Stage getStage() {
		return myStage;
	}

	/**
	 * @param myStage the myStage to set
	 */
	public void setStage(Stage myStage) {
		this.myStage = myStage;
	}

	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<SLogoDisplayData> getObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList the myObservableDataList to set
	 */
	public void setObservableDataList(ObservableList<SLogoDisplayData> 
	myObservableDataList) {
		this.myObservableDataList = myObservableDataList;
	}

	/**
	 * @return the myCanvasColor
	 */
	public String getCanvasColor() {
		return myCanvasColor;
	}

	/**
	 * @param myCanvasColor the myCanvasColor to set
	 */
	public void setCanvasColor(String myCanvasColor) {
		this.myCanvasColor = myCanvasColor;
	}

	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<SLogoDisplayData> getMyObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList the myObservableDataList to set
	 */
	public void setMyObservableDataList(ObservableList<SLogoDisplayData> myObservableDataList) {
		this.myObservableDataList = myObservableDataList;
	}

	/**
	 * @return the myWidth
	 */
	public int getMyWidth() {
		return myWidth;
	}

	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}

	/**
	 * @return the myHeight
	 */
	public int getMyHeight() {
		return myHeight;
	}

	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}

	/**
	 * @return the myLoader
	 */
	public FXMLLoader getMyLoader() {
		return myLoader;
	}

	/**
	 * @param myLoader the myLoader to set
	 */
	public void setMyLoader(FXMLLoader myLoader) {
		this.myLoader = myLoader;
	}

	/**
	 * @return the myGUIController
	 */
	public SLogoGUIController getMyGUIController() {
		return myGUIController;
	}

	/**
	 * @param myGUIController the myGUIController to set
	 */
	public void setMyGUIController(SLogoGUIController myGUIController) {
		this.myGUIController = myGUIController;
	}

	/**
	 * @return the root
	 */
	public Parent getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Parent root) {
		this.root = root;
	}

	/**
	 * @return the myScene
	 */
	public Scene getMyScene() {
		return myScene;
	}

	/**
	 * @param myScene the myScene to set
	 */
	public void setMyScene(Scene myScene) {
		this.myScene = myScene;
	}

	/**
	 * @return the myStage
	 */
	public Stage getMyStage() {
		return myStage;
	}

	/**
	 * @param myStage the myStage to set
	 */
	public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

	/**
	 * @return the myModel
	 */
	public Model getMyModel() {
		return myModel;
	}

	/**
	 * @param myModel the myModel to set
	 */
	public void setMyModel(Model myModel) {
		this.myModel = myModel;
	}

	/**
	 * @return the myCanvasColor
	 */
	public String getMyCanvasColor() {
		return myCanvasColor;
	}

	/**
	 * @param myCanvasColor the myCanvasColor to set
	 */
	public void setMyCanvasColor(String myCanvasColor) {
		this.myCanvasColor = myCanvasColor;
	}

	/**
	 * @return the myTurtleSize
	 */
	public int getmyTurtleSize() {
		return myTurtleSize;
	}

	/**
	 * @return the myPromptBuilder
	 */
	public SLogoPromptBuilder getMyPromptBuilder() {
		return myPromptBuilder;
	}

	/**
	 * @param myPromptBuilder the myPromptBuilder to set
	 */
	public void setMyPromptBuilder(SLogoPromptBuilder myPromptBuilder) {
		this.myPromptBuilder = myPromptBuilder;
	}

	/**
	 * @return the myTurtleSize
	 */
	public int getMyTurtleSize() {
		return myTurtleSize;
	}

	/**
	 * @param myTurtleSize the myTurtleSize to set
	 */
	public void setMyTurtleSize(int myTurtleSize) {
		this.myTurtleSize = myTurtleSize;
	}

	public SLogoPropertiesData getPropertiesData() {
		return myProperties;
	}
}