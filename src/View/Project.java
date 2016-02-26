package View;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import Model.CommandNode;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Project {
	private List<Character> myCharacters;
	private Canvas myCanvas;
	private List<CommandNode> myCommandHistory;
	
	
	//Visualization Primitives
	//private Group myRoot;
	private Scene myProjectScene;
	private Stage myProjectStage;
	
	public Project() {
		
	}

	public void initialize() throws IOException {

	    Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
		myProjectScene = new Scene(root);
		myProjectStage = new Stage();
		myProjectStage.setScene(myProjectScene);
		myProjectStage.setTitle("SLOGO");
	    
	}
	
	
	public void updateStates() {
		
		
	}

	public void show() throws SLogoException{
		getStage().show();
	}
	
	public void hide() {
		
		
	}
	
	public void setBackgroundColor() {
		
		
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


	public Canvas getMyCanvas() {
		return myCanvas;
	}


	public void setMyCanvas(Canvas myCanvas) {
		this.myCanvas = myCanvas;
	}


	public Collection<CommandNode> getMyCommandHistory() {
		return myCommandHistory;
	}


	public void setMyCommandHistory(List<CommandNode> myCommandHistory) {
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
	
}
