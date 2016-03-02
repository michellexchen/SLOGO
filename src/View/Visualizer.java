package View;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Controller.SLogoException;
import Model.DisplayData;
import Model.Model;
import Model.Position;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Visualizer implements Observer {
	private ObservableList<DisplayData> myObservableDataList;
	
	private int myWidth;
	private int myHeight;
	
	private FXMLLoader myLoader;
	private GUIController myGUIController;
	private Parent root;

	// Visualization Primitives
	// private Group myRoot;
	private Scene myProjectScene;
	private Stage myProjectStage;

	private Model myModel;
	
	
	
	//private DisplayData myDisplayData;
	public Visualizer(int width, int height) {
		//myDisplayData = data;
		myWidth = width;
		myHeight = height;
	}
	
	public Visualizer() {
		//myDisplayData = data;
		myWidth = 331;
		myHeight = 331;
	}

	
	public void initialize() throws SLogoException, IOException {

		SLogoPromptBuilder myPrompt = new SLogoPromptBuilder();
		myPrompt.promptScreen();

		// GUI Initialization
		myLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
		root = (Parent) myLoader.load();
		myGUIController = (GUIController) myLoader.getController();
		myGUIController.setMyModel(myModel);

		myProjectScene = new Scene(root);
		myProjectStage = new Stage();
		myProjectStage.setScene(myProjectScene);
		myProjectStage.setTitle("SLogo");

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
		// TODO 
//		myDisplayData = (DisplayData) observable;
//		myDisplayData.addLine(createLine(myDisplayData.getPosition()));
		//update rotation (angle)
		//update penDown
		//update penColor
		//update Image
	}
	
	public void setImage (ImageView image) {
		
		
	}

	public void setPenDown (boolean penDown) {
		
		
	}
	
	public void setPenColor (Color color) {
		
	}
	
	public void rotate (DisplayData displaydata) {
		
	}
	
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

	public Scene getScene() {
		return myProjectScene;
	}

	public void setMyProjectScene(Scene myProjectScene) {
		this.myProjectScene = myProjectScene;
	}

	public Stage getStage() {
		return myProjectStage;
	}

	public void setMyProjectStage(Stage myProjectStage) {
		this.myProjectStage = myProjectStage;
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

//	/**
//	 * @return the myDisplayData
//	 */
////	public List<DisplayData> getMyDisplayData() {
////		return myDisplayData;
////	}
//
//	/**
//	 * @param myDisplayData
//	 *            the myDisplayData to set
//	 */
//	public void setMyDisplayData(List<DisplayData> myDisplayData) {
//		this.myDisplayData = myDisplayData;
//	}

	/**
	 * @return the myModel
	 */
	public Model getMyModel() {
		return myModel;
	}

	/**
	 * @param myModel
	 *            the myModel to set
	 */
	public void setMyModel(Model myModel) {
		this.myModel = myModel;
	}


}
