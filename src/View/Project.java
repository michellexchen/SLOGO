package View;

import java.util.Collection;
import java.util.List;

import Model.CommandNode;
import javafx.geometry.Insets;
import javafx.scene.Group;
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

	public void initialize() {

        BorderPane myBorderPane = new BorderPane();
        myBorderPane.setPadding(new Insets(10, 20, 10, 20));
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        myBorderPane.setCenter(grid);
        myBorderPane.setPrefSize(500,400);
		myProjectScene = new Scene(myBorderPane);
		myProjectStage = new Stage();
		myProjectStage.setScene(myProjectScene);
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
