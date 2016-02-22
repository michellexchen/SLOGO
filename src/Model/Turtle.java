package Model;

public class Turtle implements Character{
	
	private TurtleState myState;
	private String myName;
	
	public Turtle(String myName, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden){
		this.myState = new TurtleState(xCoor, yCoor, penDown, direction, isHidden);
		this.myName = myName;
	}

	public Character step(CharacterState myState) {
		return this;
	}

	public CharacterState getState() {
		return myState;
	}

}