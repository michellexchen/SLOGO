package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Turtle implements Character {

	private TurtleState myCurrentState;
	private TurtleState myNextState;
	private String myName;
	private int TURTLE_SIZE = 50;

	public Turtle(String myName, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden) {
		this.myCurrentState = new TurtleState(xCoor, yCoor, penDown, direction, isHidden);
		this.myNextState = null;
		this.myName = myName;
	}

	private Character step(CharacterState myState) {
		
	}
	
	public CharacterState getState() {
		return myCurrentState;
	}

	public String getName() {
		return myName;
	}

}