package View;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import Controller.SLogoException;
import Model.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Project {
	private List<Character> myCharacters;
	private Canvas myCanvas;
	private List<Node> myCommandHistory;
	
	private FXMLLoader myLoader;
	private GUIController myGUIController;
	
	//Visualization Primitives
	//private Group myRoot;
	private Scene myProjectScene;
	private Stage myProjectStage;
	
	public Project () {
		
	}

	public void initialize () throws SLogoException, IOException {
		myLoader = new FXMLLoader();
	    Parent root = myLoader.load(getClass().getResource("UI.fxml"));
	    myGUIController = (GUIController) myLoader.getController();

		myProjectScene = new Scene(root);
		myProjectStage = new Stage();
		myProjectStage.setScene(myProjectScene);
		myProjectStage.setTitle("SLogo");
	    
	}
	
	
	public void updateStates () {
		
		
	}

	public void show () throws SLogoException {
		getStage().show();
	}
	
	public void hide () {
		
		
	}
	
	public void setBackgroundColor () {
		
		
	}
	
	
	
	
	
	//////////////////////////
	// getters and setters  //
	//////////////////////////
	public Collection<Character> getMyCharacters() {
		return myCharacters;
	}


	public void setMyCharacters(List<Character> myCharacters) {
		this.myCharacters = myCharacters;
	}


	public Canvas getMyCanvas () {
		return myCanvas;
	}


	public void setMyCanvas(Canvas myCanvas) {
		this.myCanvas = myCanvas;
	}


	public Collection<Node> getMyCommandHistory() {
		return myCommandHistory;
	}


	public void setMyCommandHistory(List<Node> myCommandHistory) {
		this.myCommandHistory = myCommandHistory;
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
	 * @param myLoader the myLoader to set
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
	 * @param myGUIController the myGUIController to set
	 */
	public void setGUIController(GUIController myGUIController) {
		this.myGUIController = myGUIController;
	}
	
}
