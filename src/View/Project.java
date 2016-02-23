package View;

import java.util.Collection;

import Model.CommandNode;
import javafx.scene.canvas.Canvas;

public class Project {
	private Collection<Character> myCharacters;
	private Canvas myCanvas;
	private Collection<CommandNode> myCommandHistory;
	
	public Project() {
	}

	
	public void updateStates() {
		
		
	}

	public void show() {
		
	}
	
	public void hide() {
		
		
	}
	
	public void setBackgroundColor() {
		
		
	}
	public Collection<Character> getMyCharacters() {
		return myCharacters;
	}


	public void setMyCharacters(Collection<Character> myCharacters) {
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


	public void setMyCommandHistory(Collection<CommandNode> myCommandHistory) {
		this.myCommandHistory = myCommandHistory;
	}
	
}
