package model;

/**
 * SLogo's Turtle with a TurtleState and name
 *
 */

public class Turtle implements Character {

	TurtleState myState;
	String myName;

	public Turtle(String myName, double xCoor, double yCoor, boolean penDown, double direction, boolean isHidden,
			double angle) {
		myState = new TurtleState(xCoor, yCoor, direction, isHidden, penDown, angle);
		System.out.println("tutle " + myName + " current state is: location xCoor " + xCoor);
		this.myName = myName;
	}

	public void setState(CharacterState myState) {
		this.myState = (TurtleState) myState;
		System.out.println("tutle " + myState.getXCoor() + " current state is: location xCoor " + myState.getXCoor());
	}

	public CharacterState getState() {
		return myState;
	}

	public String getName() {
		return myName;
	}

}