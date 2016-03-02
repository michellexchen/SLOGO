package View;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import CommandNode.DisplayData;
import CommandNode.Position;
import Exception.SLogoException;
import Model.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Visualizer implements Observer {
	
	private static final String TURTLE_IMAGE_PATH = "file:resources/turtle_images/";
	private static final String TURTLE_IMAGE = "turtle_1.png";
	private static final int TURTLE_SIZE = 20;
	
	private ObservableList<DisplayData> myObservableDataList;
	
	private int myWidth;
	private int myHeight;
	
	private FXMLLoader myLoader;
	private GUIController myGUIController;
	private Parent root;

	// Visualization Primitives
	private Scene myScene;
	private Stage myStage;
	private Model myModel;
	private String myCanvasColor;
	
	public Visualizer(Model model, int width, int height) {
		myModel = model;
		myWidth = width;
		myHeight = height;
	}
	
	public Visualizer(Model model) {
		myWidth = 331;
		myHeight = 331;
		myModel = model;
	}

	public void initialize() throws SLogoException, IOException {

		SLogoPromptBuilder myPrompt = new SLogoPromptBuilder();
		myPrompt.promptScreen();
		setCanvasColor(toRGBCode(myPrompt.sendMyColor()));

		//Get the ObservableDataList
		setObservableDataList(getModel().getObservableDataList());
		
		// GUI Initialization
		myLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
		root = (Parent) myLoader.load();
		myGUIController = (GUIController) myLoader.getController();
		myGUIController.setModel(myModel);
		
		myScene = new Scene(root);
		myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setTitle("SLogo");
		show();
	}

	public void updateStates() {

	}

	public void show() throws SLogoException {
		getStage().show();
	}

	public void hide() {

	}

	public void setBackgroundColor() {

	}

	@Override
	public void update(Observable observable, Object arg1) {
		updateDisplayData();
	}
	
	public void setImage (ImageView image) {
		
		
	}

	public void setPenDown (boolean penDown) {
		
		
	}
	
	public void setPenColor (Color color) {
		
	}
	
	public void rotate (DisplayData displaydata) {
		
	}
	
	/**
	 * Creates a Line object with default color black
	 * 
	 * @param position
	 * @return Line
	 */
	public Line createLine(Position position) {
		Line newLine = new Line();
		newLine.setStartX(position.xPrevious());
		newLine.setStartY(position.yPrevious());
		newLine.setEndX(position.xCurrent());
		newLine.setEndY(position.yCurrent());
		newLine.setFill(Color.BLACK);
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
	public Line createLine(Position position, Color color) {
		Line newLine = createLine(position);
		newLine.setFill(color);
		return newLine;
	}
	/**
	 * This method updates turtles' attributes and position
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateDisplayData () {
		//Clear entire Pane
		getGUIController().getCanvas().getChildren().clear();
		
		//Set Pane Color
		getGUIController().getCanvas().setStyle("-fx-background-color: "
													+ getCanvasColor());
		
		getModel().getObservableDataList();
		for (DisplayData turtledata : getObservableDataList()) {
			//Place the turtle
			placeTurtle(turtledata);
			
			//Create a line
			turtledata.addLine(createLine(turtledata.getPosition()));
			
			//Add lines to Pane
			getGUIController().addToCanvas(turtledata.getLines());
			
			
		}
	}
	
	public void placeTurtle(DisplayData displaydata) {
        Image image = new Image(TURTLE_IMAGE_PATH + TURTLE_IMAGE);
        
		ImageView turtle = new ImageView();
		turtle.setImage(image);
		
		//turtle resize
		turtle.setFitWidth(TURTLE_SIZE);
		turtle.setPreserveRatio(true);
		turtle.setSmooth(true);
		turtle.setCache(true);
		
		//turtle rotate
		turtle.setRotate(displaydata.getAngle());
		
		//place turtle using Position and center at the coordinates (x,y)
		turtle.setLayoutX(displaydata.getPosition().xCurrent() - TURTLE_SIZE / 2);
		turtle.setLayoutY(displaydata.getPosition().yCurrent() - TURTLE_SIZE / 2);
		
		//Put it in the Pane
		getGUIController().addToCanvas(turtle);
	}
	
	
	/**
	 * This method updates command history display in GUI.
	 * Caller is Workspace (MyCurrentWorkspace in MainModel)
	 */
	public void updateCommandHistory () {
		
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
	public GUIController getGUIController() {
		return myGUIController;
	}

	/**
	 * @param myGUIController
	 *            the myGUIController to set
	 */
	public void setGUIController(GUIController myGUIController) {
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
	public ObservableList<DisplayData> getObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList the myObservableDataList to set
	 */
	public void setObservableDataList(ObservableList<DisplayData> 
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

	/**
	 * @return the myObservableDataList
	 */
	public ObservableList<DisplayData> getMyObservableDataList() {
		return myObservableDataList;
	}

	/**
	 * @param myObservableDataList the myObservableDataList to set
	 */
	public void setMyObservableDataList(ObservableList<DisplayData> myObservableDataList) {
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
	public GUIController getMyGUIController() {
		return myGUIController;
	}

	/**
	 * @param myGUIController the myGUIController to set
	 */
	public void setMyGUIController(GUIController myGUIController) {
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
	 * @return the turtleImagePath
	 */
	public static String getTurtleImagePath() {
		return TURTLE_IMAGE_PATH;
	}

	/**
	 * @return the turtleImage
	 */
	public static String getTurtleImage() {
		return TURTLE_IMAGE;
	}

	/**
	 * @return the turtleSize
	 */
	public static int getTurtleSize() {
		return TURTLE_SIZE;
	}
}